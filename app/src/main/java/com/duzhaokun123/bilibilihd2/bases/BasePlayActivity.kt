package com.duzhaokun123.bilibilihd2.bases

import android.content.res.Configuration
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
import androidx.core.graphics.Insets
import androidx.core.view.*
import com.bumptech.glide.Glide
import com.duzhaokun123.bilibilihd2.Application
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.ActivityPlayBaseBinding
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.biliplayer.BiliPlayerView
import com.google.android.exoplayer2.ui.StyledPlayerControlView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.CacheDataSource

/**
 * TODO:
 * 这玩意太乱了
 * 该类及其子类都要重构
 */
abstract class BasePlayActivity : BaseActivity<ActivityPlayBaseBinding>(
    R.layout.activity_play_base,
    Config.NO_TOOL_BAR, Config.LAYOUT_MATCH_HORI
), StyledPlayerControlView.OnFullScreenModeChangedListener,
    StyledPlayerControlView.VisibilityListener {
    val dataSourceFactory by lazy {
        CacheDataSource.Factory().setCache(Application.simpleCache)
            .setUpstreamDataSourceFactory(DefaultDataSourceFactory(this))
    }

    var isFullScreen = false
        private set
    lateinit var biliPlayerView: BiliPlayerView
        private set
    val windowInsetsControllerCompat by lazy {
        WindowInsetsControllerCompat(window, rootBinding.root)
    }
    private var coverUrl: String? = null

    @CallSuper
    override fun findViews() {
        if (::biliPlayerView.isInitialized.not()) {
            biliPlayerView = BiliPlayerView(this).apply {
                id = R.id.bpv
                setPlayedColor(getColorCompat(R.color.biliPink))
                playerView.setControllerOnFullScreenModeChangedListener(this@BasePlayActivity)
                setBackgroundColor(getColorCompat(R.color.black))
                playerView.setControllerVisibilityListener(this@BasePlayActivity)
                onNextClickListener = this@BasePlayActivity::onNextClick
                ViewCompat.setElevation(this, 1.dpToPx().toFloat())
            }
        }
        baseBinding.rl.addView(biliPlayerView,1, ViewGroup.LayoutParams(MATCH_CONSTRAINT, MATCH_CONSTRAINT))
        onFullScreenModeChanged(isFullScreen)
    }

    @CallSuper
    override fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            baseBinding.abl.outlineProvider = null
        }
    }

    @CallSuper
    override fun initData() {

    }

    override fun initActionBar() = baseBinding.tb

    @CallSuper
    override fun onApplyWindowInsetsCompat(insets: WindowInsetsCompat) {
        super.onApplyWindowInsetsCompat(insets)
        insets.maxSystemBarsDisplayCutout.let {
            when (baseBinding.rhv.tag) {
                "1" -> {
                    baseBinding.abl.updatePadding(top = it.top)
                    baseBinding.rhv.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        leftMargin = it.left
                        rightMargin = it.right
                    }
                }
                "2" -> {
                    baseBinding.abl.updatePadding(top = it.top)
                    baseBinding.rhv.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        leftMargin = it.left
                    }
                }
            }
        }
        (if (isFullScreen) insets.displayCutoutInsets else Insets.NONE).let {
            biliPlayerView.updatePadding(left = it.left, top = it.top, right = it.right, bottom = it.bottom)
            baseBinding.abl.updatePadding(left = it.left, right = it.right)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        biliPlayerView.destroy()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        beforeReinitLayout()
        reinitLayout()
    }

    override fun onBackPressed() {
        if (isFullScreen)
            biliPlayerView.changeFullscreen()
        else
            super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.base_play, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_share -> {
                onGetShare().run {
                    if (first != null && second != null)
                        ShareUtil.shareUrl(this@BasePlayActivity, second!!, first)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onStart() {
        super.onStart()
        biliPlayerView.playerView.showController()
    }

    fun start() {
        biliPlayerView.start()
    }

    fun stop() {
        biliPlayerView.stop()
    }

    fun pause() {
        biliPlayerView.pause()
    }

    fun resume() {
        biliPlayerView.resume()
    }

    fun setCoverUrl(url: String?) {
        coverUrl = url
        runNewThread {
            try {
                val cover = Glide.with(this).load(url).submit().get()
                runMain {
                    biliPlayerView.setCover(cover)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                TipUtil.showTip(this, e.message)
            }
        }
    }

    @CallSuper
    override fun onFullScreenModeChanged(isFullScreen: Boolean) {
        this.isFullScreen = isFullScreen
        if (isFullScreen) {
            biliPlayerView.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topToTop = PARENT_ID
                startToStart = PARENT_ID
                endToEnd = PARENT_ID
                bottomToBottom = PARENT_ID
            }
            windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.navigationBars())
            windowInsetsControllerCompat.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        } else {
            biliPlayerView.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topToTop = R.id.rhv
                startToStart = R.id.rhv
                endToEnd = R.id.rhv
                bottomToBottom = R.id.rhv
            }
            windowInsetsControllerCompat.show(WindowInsetsCompat.Type.navigationBars())
        }
    }

    override fun onVisibilityChange(visibility: Boolean) {
        if (visibility) {
            supportActionBar?.show()
            windowInsetsControllerCompat.show(WindowInsetsCompat.Type.statusBars())
        } else {
            supportActionBar?.hide()
            windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.statusBars())
            if (isFullScreen)
                windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.navigationBars())
        }
    }

    open fun onNextClick() {}

    /**
     * @return first: title second: url
     */
    open fun onGetShare(): Pair<String?, String?> = null to null

    @CallSuper
    open fun beforeReinitLayout() {
        baseBinding.rl.removeView(biliPlayerView)
    }
}