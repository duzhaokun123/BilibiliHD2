package com.duzhaokun123.bilibilihd2.bases

import android.app.PictureInPictureParams
import android.app.assist.AssistContent
import android.content.res.Configuration
import android.os.Build
import android.util.Rational
import android.view.*
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.core.graphics.Insets
import androidx.core.net.toUri
import androidx.core.view.*
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.duzhaokun123.bilibilihd2.Application
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.ActivityPlayBaseBinding
import com.duzhaokun123.bilibilihd2.ui.settings.PlayFragment
import com.duzhaokun123.bilibilihd2.ui.settings.SettingsActivity
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.biliplayer.BiliPlayerView
import com.duzhaokun123.generated.Settings
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerControlView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import io.github.duzhaokun123.androidapptemplate.utils.TipUtil

/**
 * 乱就乱 能用就行
 */
abstract class BasePlayActivity : io.github.duzhaokun123.androidapptemplate.bases.BaseActivity<ActivityPlayBaseBinding>(
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
    var played = false
    private var isPlayBeforeStop = false

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
                player.addListener(object : Player.EventListener {
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        if (isPlaying) {
                            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                            if (played.not()) {
                                played = true
                                onFirstPlay()
                            }
                        }else
                            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                    }

                    override fun onPlayerError(error: ExoPlaybackException) {
                        Snackbar.make(rootBinding.rootCl, error.cause?.localizedMessage ?: "null", Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.retry) {
                                player.prepare()
                            }
                            .show()
                    }
                })
                danmakuView.zOnTop = Settings.danmakuOnTop
            }
        }
        baseBinding.rl.addView(biliPlayerView,1, ViewGroup.LayoutParams(MATCH_CONSTRAINT, MATCH_CONSTRAINT))
        onFullScreenModeChanged(isFullScreen)
    }

    @CallSuper
    override fun initViews() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            baseBinding.abl.outlineProvider = null
        }
    }

    @CallSuper
    override fun initData() {

    }

    override fun initActionBar() = baseBinding.tb as Toolbar

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
            R.id.item_retry -> {
                biliPlayerView.player.prepare()
                true
            }
            R.id.item_settings -> {
                startActivity<SettingsActivity>()
                true
            }
            R.id.item_pip -> {
                if (isFullScreen.not())
                    biliPlayerView.changeFullscreen()
                enterPictureInPictureMode(PictureInPictureParams.Builder().setAspectRatio(getVideoRatioin()).build())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onProvideAssistContent(outContent: AssistContent) {
        super.onProvideAssistContent(outContent)
        outContent.webUri = onGetShare().second?.toUri()
    }

    override fun onStart() {
        super.onStart()
        biliPlayerView.playerView.showController()
        if (Settings.playPauseTime == 1) {
            if (isPlayBeforeStop)
                resume()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Settings.playPauseTime == 1) {
            isPlayBeforeStop = biliPlayerView.player.playWhenReady
            pause()
        }
    }

    override fun onResume() {
        super.onResume()
        biliPlayerView.playerView.onResume()
        if (Settings.playPauseTime == 0) {
            if (isPlayBeforeStop)
                resume()
        }
    }

    override fun onPause() {
        super.onPause()
        biliPlayerView.playerView.onPause()
        if (Settings.playPauseTime == 0) {
            isPlayBeforeStop = biliPlayerView.player.playWhenReady
            pause()
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

    fun prepare() {
        biliPlayerView.prepare()
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
        baseBinding.abl.elevation = 2.dpToPx().toFloat()
        if (visibility) {
            supportActionBar?.show()
            windowInsetsControllerCompat.show(WindowInsetsCompat.Type.statusBars())
        } else {
            supportActionBar?.hide()
            windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.statusBars())
        }
        if (isFullScreen) {
            windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.navigationBars())
            windowInsetsControllerCompat.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        if ((window.decorView.isVisible || biliPlayerView.isPlaying).not())
            finish()
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

    open fun onFirstPlay() {

    }

    open fun getVideoRatioin() = Rational(16, 9)

    fun reinitLayout() {
        rootBinding.rootFl.removeAllViews()
        setAnyField("baseBinding", DataBindingUtil.inflate<ActivityPlayBaseBinding>(layoutInflater, layoutId, rootBinding.rootFl, true), io.github.duzhaokun123.androidapptemplate.bases.BaseActivity::class.java)
        findViews()
        setSupportActionBar(initActionBar())
        if (Config.NO_BACK !in configs) supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
        }
        initViews()
        initData()

        TipUtil.registerCoordinatorLayout(this, registerCoordinatorLayout())
//        onApplyWindowInsetsCompat(getAnyFieldAs<WindowInsetsCompatModel>("windowInsetsCompatModel").windowInsetsCompat.value!!)
    }
}