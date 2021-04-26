package com.duzhaokun123.bilibilihd2.bases

import android.content.res.Configuration
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.annotation.CallSuper
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import com.duzhaokun123.bilibilihd2.Application
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.ActivityPlayBaseBinding
import com.duzhaokun123.bilibilihd2.utils.ShareUtil
import com.duzhaokun123.bilibilihd2.utils.WindowInsetsControllerCompatFix
import com.duzhaokun123.bilibilihd2.utils.systemBars
import com.duzhaokun123.biliplayer.BiliPlayerView
import com.google.android.exoplayer2.ui.StyledPlayerControlView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.CacheDataSource

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

    @CallSuper
    override fun findViews() {
        if (::biliPlayerView.isInitialized.not()) {
            biliPlayerView = BiliPlayerView(this).apply {
                id = R.id.bpv
                setPlayedColor(getColor(R.color.biliPink))
                playerView.setControllerOnFullScreenModeChangedListener(this@BasePlayActivity)
                setBackgroundColor(getColor(R.color.black))
                playerView.setControllerVisibilityListener(this@BasePlayActivity)
                onNextClickListener = this@BasePlayActivity::onNextClick
            }
        }
        baseBinding.rl.addView(biliPlayerView)
        onFullScreenModeChanged(isFullScreen)
    }

    @CallSuper
    override fun initView() {
        baseBinding.abl.outlineProvider = null
        baseBinding.start.setOnClickListener { start() }
        baseBinding.stop.setOnClickListener { stop() }
        baseBinding.pause.setOnClickListener { pause() }
        baseBinding.resume.setOnClickListener { resume() }
        baseBinding.fullscreen.setOnClickListener { biliPlayerView.changeFullscreen() }
    }

    @CallSuper
    override fun initData() {

    }

    override fun initActionBar() = baseBinding.tb

    @CallSuper
    override fun onApplyWindowInsetsCompat(insets: WindowInsetsCompat) {
        super.onApplyWindowInsetsCompat(insets)
        insets.systemBars.let {
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
    }

    override fun onDestroy() {
        super.onDestroy()
        biliPlayerView.destroy()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        baseBinding.rl.removeView(biliPlayerView)
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
        return  when (item.itemId) {
            R.id.item_share -> {
                onGetShare().run {
                    if (first != null && second != null)
                        ShareUtil.shareUrl(this@BasePlayActivity, second!!, first)
                }
                true
            }
            else ->  super.onOptionsItemSelected(item)
        }

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

    @CallSuper
    override fun onFullScreenModeChanged(isFullScreen: Boolean) {
        this.isFullScreen = isFullScreen
        if (isFullScreen) {
            biliPlayerView.updateLayoutParams<RelativeLayout.LayoutParams> {
                removeRule(RelativeLayout.ALIGN_TOP)
                removeRule(RelativeLayout.ALIGN_START)
                removeRule(RelativeLayout.ALIGN_END)
                removeRule(RelativeLayout.ALIGN_BOTTOM)
            }
            windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.navigationBars())
            WindowInsetsControllerCompatFix.setSystemBarsBehavior(
                window, WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            )
        } else {
            biliPlayerView.updateLayoutParams<RelativeLayout.LayoutParams> {
                addRule(RelativeLayout.ALIGN_TOP, R.id.rhv)
                addRule(RelativeLayout.ALIGN_START, R.id.rhv)
                addRule(RelativeLayout.ALIGN_END, R.id.rhv)
                addRule(RelativeLayout.ALIGN_BOTTOM, R.id.rhv)
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
        }
    }

    open fun onNextClick() {}

    /**
     * @return first: title second: url
     */
    open fun onGetShare(): Pair<String?, String?> = null to null
}