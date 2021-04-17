package com.duzhaokun123.bilibilihd2.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseActivity
import com.duzhaokun123.bilibilihd2.databinding.ActivityMainBinding
import com.duzhaokun123.bilibilihd2.databinding.PopupMyinfoBinding
import com.duzhaokun123.bilibilihd2.ui.scape.UserScapeActivity
import com.duzhaokun123.bilibilihd2.ui.settings.SettingsActivity
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.bilibilihd2.utils.ImageViewUtil.setBiliLevel
import com.hiczp.bilibili.api.app.model.MyInfo

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main,
    Config.NO_TOOL_BAR,
    Config.LAYOUT_MATCH_HORI
) {
    private lateinit var navController: NavController
    private var headerView: View? = null
    private lateinit var myInfo: MyInfo

    override fun findViews() {
        headerView = baseBinding.nv.getHeaderView(0)
    }

    override fun initView() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv) as NavHostFragment
        navController = navHostFragment.navController
        baseBinding.nv.setupWithNavController(navController)
        if (headerView != null) {
            val appBarConfiguration =
                AppBarConfiguration(navController.graph, baseBinding.dl)
            baseBinding.tb.setupWithNavController(navController, appBarConfiguration)
        } else {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                title = destination.label
            }
        }
        baseBinding.nv.menu.findItem(R.id.item_settings).setOnMenuItemClickListener {
            startActivity<SettingsActivity>()
            baseBinding.dl?.close()
            true
        }
        baseBinding.dl?.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerOpened(drawerView: View) {
                reloadMyInfo()
            }
        })
        if (headerView == null) {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(false)
                setDisplayShowHomeEnabled(false)
            }
        }
        headerView?.setOnClickListener {
            if (bilibiliClient.isLogin)
                UserScapeActivity.enter(this, bilibiliClient.loginResponse!!.userId)
        }
    }

    override fun initData() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = navController.currentDestination?.label
    }

    override fun onResume() {
        super.onResume()
        reloadMyInfo()
    }

    override fun onBackPressed() {
        if (baseBinding.dl != null && baseBinding.dl!!.isOpen)
            baseBinding.dl!!.close()
        else
            super.onBackPressed()
    }

    override fun initActionBar() = baseBinding.tb

    override fun onApplyWindowInsetsCompat(insets: WindowInsetsCompat) {
        super.onApplyWindowInsetsCompat(insets)
        insets.systemBars.let {
            baseBinding.abl.updatePadding(left = it.left, right = it.right, top = it.top)
            if (headerView != null) baseBinding.fcv.updatePadding(left = it.left, right = it.right)
            else baseBinding.fcv.updatePadding(right = it.right)
            baseBinding.nv.updatePadding(left = it.left, top = it.top)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (headerView == null && item.itemId == android.R.id.home) {
            val binding = DataBindingUtil.inflate<PopupMyinfoBinding>(
                layoutInflater, R.layout.popup_myinfo, null, false
            )
            PopupWindow(binding.root, WRAP_CONTENT, WRAP_CONTENT, true).apply {
                isOutsideTouchable = true
                binding.myInfo = myInfo
                binding.ivLevel.setBiliLevel(myInfo.data.level)
                binding.cv.setOnClickListener {
                    UserScapeActivity.enter(this@MainActivity, myInfo.data.mid)
                }
            }.showAsDropDown(baseBinding.tb.homeImageView)
            true
        } else
            super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetTextI18n")
    fun reloadMyInfo() {
        if (bilibiliClient.isLogin)
            runIOCatchingResultRunMain(this, { bilibiliClient.appAPI.myInfo().await() }) { myInfo ->
                if (headerView != null) {
                    headerView!!.findViewById<TextView>(R.id.tv_name).apply {
                        text = myInfo.data.name
                        setTextColor(getColor(if (myInfo.data.vip.status == 0) R.color.textColor else R.color.biliPink))
                    }
                    headerView!!.findViewById<TextView>(R.id.tv_coins).text =
                        "硬币: ${myInfo.data.coins}"
                    headerView!!.findViewById<ImageView>(R.id.iv_level)
                        .setBiliLevel(myInfo.data.level)
                    glideSafeLoadInto(myInfo.data.face, headerView!!.findViewById(R.id.civ_face))
                } else {
                    this.myInfo = myInfo
                    supportActionBar?.let { actionBar ->
                        actionBar.setDisplayUseLogoEnabled(true)
                        actionBar.setDisplayShowHomeEnabled(true)
                        runNewThread {
                            val size =
                                (baseBinding.abl.let { it.height - it.paddingTop } * 0.7).toInt()
                            val face = try {
                                Glide.with(this).asBitmap().load(myInfo.data.face).submit().get()
                                    .resize(size, size)
                            } catch (e: Exception) {
                                null
                            }
                            val roundFace =
                                RoundedBitmapDrawableFactory.create(resources, face).apply {
                                    setAntiAlias(true)
                                    isCircular = true
                                }
                            runMain {
                                actionBar.setDisplayHomeAsUpEnabled(true)
                                actionBar.setDisplayShowHomeEnabled(true)
                                actionBar.setHomeAsUpIndicator(roundFace)
                            }
                        }
                    }
                }
            }
        else {
            if (headerView != null) {
                headerView!!.findViewById<TextView>(R.id.tv_name).text = "未登录"
                headerView!!.findViewById<TextView>(R.id.tv_coins).text = "硬币: --"
                headerView!!.findViewById<ImageView>(R.id.iv_level)?.setBiliLevel(0)
                headerView!!.findViewById<ImageView>(R.id.civ_face).setImageDrawable(null)
            } else {
                supportActionBar?.apply {
                    setDisplayHomeAsUpEnabled(false)
                    setDisplayShowHomeEnabled(false)
                }
            }
        }
    }
}