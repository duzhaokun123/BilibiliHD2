package com.duzhaokun123.bilibilihd2.bases

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.ActivityBaseRootBinding
import com.duzhaokun123.bilibilihd2.utils.TipUtil
import com.duzhaokun123.bilibilihd2.utils.systemBars

abstract class BaseActivity<BaseBinding : ViewDataBinding>(
    @LayoutRes private val layoutId: Int, private vararg val configs: Config
) : AppCompatActivity() {
    enum class Config {
        NO_TOOL_BAR,
        LAYOUT_NO_TOOL_BAR,
        TRANSPARENT_TOOL_BAR,
        NO_BACK,
        LAYOUT_MATCH_HORI
    }

    val className by lazy { this::class.simpleName }
    val startIntent: Intent by lazy { intent }

    lateinit var rootBinding: ActivityBaseRootBinding
    lateinit var baseBinding: BaseBinding
        private set
    var isFirstCreate = true

    private val windowInsetsCompatModel by viewModels<WindowInsetsCompatModel>()

    class WindowInsetsCompatModel : ViewModel() {
        val windowInsetsCompat = MutableLiveData<WindowInsetsCompat>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        isFirstCreate = savedInstanceState == null
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
            navigationBarColor = Color.TRANSPARENT
        }

        rootBinding = DataBindingUtil.setContentView(this, R.layout.activity_base_root)
        if (Config.NO_TOOL_BAR in configs) rootBinding.tb.visibility = View.GONE
        if (Config.LAYOUT_NO_TOOL_BAR in configs) rootBinding.flRoot.updateLayoutParams<RelativeLayout.LayoutParams> {
            removeRule(RelativeLayout.BELOW)
        }
        if (Config.TRANSPARENT_TOOL_BAR in configs) {
            rootBinding.abl.outlineProvider = null
            rootBinding.abl.background = null
        }
        ViewCompat.setOnApplyWindowInsetsListener(rootBinding.root) { _, insets ->
            windowInsetsCompatModel.windowInsetsCompat.value = insets
            insets
        }

        baseBinding = DataBindingUtil.inflate(layoutInflater, layoutId, rootBinding.flRoot, true)

        findViews()
        setSupportActionBar(initActionBar())
        if (Config.NO_BACK !in configs) supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
        }
        initView()
        initData()

        TipUtil.registerCoordinatorLayout(this, registerCoordinatorLayout())
        windowInsetsCompatModel.windowInsetsCompat.observe(this, ::onApplyWindowInsetsCompat)
    }

    override fun onDestroy() {
        super.onDestroy()
        TipUtil.unregisterCoordinatorLayout(this)
    }

    override fun setTitle(title: CharSequence?) {
        supportActionBar?.title = title
    }

    fun setSubtitle(subtitle: CharSequence?) {
        supportActionBar?.subtitle = subtitle
    }

    fun setSubtitle(@StringRes subtitleId: Int) = setSubtitle(getText(subtitleId))

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else
            super.onOptionsItemSelected(item)
    }

    fun reinitLayout() {
        rootBinding.flRoot.removeAllViews()
        baseBinding = DataBindingUtil.inflate(layoutInflater, layoutId, rootBinding.flRoot, true)
        findViews()
        setSupportActionBar(initActionBar())
        if (Config.NO_BACK !in configs) supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
        }
        initView()
        initData()

        TipUtil.registerCoordinatorLayout(this, registerCoordinatorLayout())
        onApplyWindowInsetsCompat(windowInsetsCompatModel.windowInsetsCompat.value!!)
    }

    open fun findViews() {}
    open fun initActionBar() =
        if (Config.NO_TOOL_BAR in configs) null else rootBinding.tb

    abstract fun initView()
    abstract fun initData()

    @CallSuper
    open fun onApplyWindowInsetsCompat(insets: WindowInsetsCompat) {
        insets.systemBars.let {
            if (Config.LAYOUT_MATCH_HORI !in configs) {
                rootBinding.abl.updatePadding(left = it.left, right = it.right)
                rootBinding.flRoot.updatePadding(left = it.left, right = it.right)
            }
            if (Config.NO_TOOL_BAR !in configs)
                rootBinding.abl.updatePadding(top = it.top)
        }
    }

    open fun registerCoordinatorLayout(): CoordinatorLayout? = rootBinding.cl
}