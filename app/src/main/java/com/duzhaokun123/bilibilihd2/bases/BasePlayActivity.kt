package com.duzhaokun123.bilibilihd2.bases

import android.app.PictureInPictureParams
import android.app.assist.AssistContent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.util.Rational
import android.view.*
import androidx.activity.viewModels
import androidx.annotation.CallSuper
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.core.graphics.Insets
import androidx.core.graphics.drawable.toDrawable
import androidx.core.net.toUri
import androidx.core.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duzhaokun123.bilibilihd2.Application
import com.duzhaokun123.bilibilihd2.DESKTOP_USER_AGENT
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bilisubtitle.BiliSubtitleRendererFactory
import com.duzhaokun123.bilibilihd2.databinding.ActivityPlayBaseBinding
import com.duzhaokun123.bilibilihd2.ui.settings.SettingsActivity
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.generated.Settings
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerControlView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.google.android.exoplayer2.video.VideoDecoderOutputBufferRenderer
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
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
            .setUpstreamDataSourceFactory(DefaultHttpDataSource.Factory()
                .setDefaultRequestProperties(mapOf("Referer" to "https://www.bilibili.com"))
                .setUserAgent(DESKTOP_USER_AGENT))
    }

    class PlayerModel: ViewModel() {
        val player = MutableLiveData(ExoPlayer.Builder(application)
            .setRenderersFactory(MergingRenderersFactory(BiliSubtitleRendererFactory, DefaultRenderersFactory(application)))
            .build()) as LiveData<ExoPlayer>

        override fun onCleared() {
            super.onCleared()
            player.value?.release()
        }
    }
    var isFullScreen = false
        private set
//    lateinit var biliPlayerView: BiliPlayerView
//        private set
    val windowInsetsControllerCompat by lazy {
        WindowInsetsControllerCompat(window, rootBinding.root)
    }
    private var coverUrl: String? = null
    var played = false
    private var isPlayBeforeStop = false
    private val audioBecomingNoisyBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == AudioManager.ACTION_AUDIO_BECOMING_NOISY) {
                pause()
            }
        }
    }
    val playerModel by viewModels<PlayerModel>()

    val player
        get() = playerModel.player.value!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(audioBecomingNoisyBroadcastReceiver, IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY))
    }

    @CallSuper
    override fun findViews() {
//        if (::biliPlayerView.isInitialized.not()) {
//            biliPlayerView = BiliPlayerView(this).apply {
//                id = R.id.bpv
//                setPlayedColor(getColorCompat(R.color.biliPink))
//                playerView.setControllerOnFullScreenModeChangedListener(this@BasePlayActivity)
//                setBackgroundColor(getColorCompat(R.color.black))
//                playerView.setControllerVisibilityListener(this@BasePlayActivity)
//                onNextClickListener = this@BasePlayActivity::onNextClick
//                ViewCompat.setElevation(this, 1.dpToPx().toFloat())
//                player.addListener(object : Player.EventListener {
//                    override fun onIsPlayingChanged(isPlaying: Boolean) {
//                        if (isPlaying) {
//                            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
//                            if (played.not()) {
//                                played = true
//                                onFirstPlay()
//                            }
//                        }else
//                            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
//                    }
//
//                    override fun onPlayerError(error: ExoPlaybackException) {
//                        Snackbar.make(rootBinding.rootCl, error.cause?.localizedMessage ?: "null", Snackbar.LENGTH_INDEFINITE)
//                            .setAction(R.string.retry) {
//                                player.prepare()
//                            }
//                            .show()
//                    }
//                })
//                danmakuView.zOnTop = Settings.danmakuOnTop
//            }
//        }
//        baseBinding.rl.addView(biliPlayerView,1, ViewGroup.LayoutParams(MATCH_CONSTRAINT, MATCH_CONSTRAINT))
        baseBinding.pv.changeFullScreenMode(isFullScreen)
        onFullScreenModeChanged(isFullScreen)
    }

    @CallSuper
    override fun initViews() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            baseBinding.abl.outlineProvider = null
        }
        baseBinding.pv.player = player
        baseBinding.pv.onFullScreenModeChangedListener = ::onFullScreenModeChanged
        baseBinding.pv.setControllerVisibilityListener(this)
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
            baseBinding.pv.updatePadding(left = it.left, top = it.top, right = it.right, bottom = it.bottom)
            baseBinding.abl.updatePadding(left = it.left, right = it.right)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(audioBecomingNoisyBroadcastReceiver)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        beforeReinitLayout()
        reinitLayout()
    }

    override fun onBackPressed() {
        if (isFullScreen)
            baseBinding.pv.changeFullScreenMode(false)
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
                player.prepare()
                true
            }
            R.id.item_settings -> {
                startActivity<SettingsActivity>()
                true
            }
            R.id.item_pip -> {
                if (isFullScreen.not())
                    baseBinding.pv.changeFullScreenMode(true)
                runCatching {
                    enterPictureInPictureMode(PictureInPictureParams.Builder().setAspectRatio(getVideoRatioin()).build())
                }.onFailure {
                    runCatching { enterPictureInPictureMode() }
                }.onFailure {
                    TipUtil.showToast("系统不支持 PIP")
                }
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
        baseBinding.pv.showController()
        if (Settings.playPauseTime == 1) {
            if (isPlayBeforeStop)
                resume()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Settings.playPauseTime == 1) {
            isPlayBeforeStop = player.playWhenReady
            pause()
        }
    }

    override fun onResume() {
        super.onResume()
        baseBinding.pv.onResume()
        if (Settings.playPauseTime == 0) {
            if (isPlayBeforeStop)
                resume()
        }
    }

    override fun onPause() {
        super.onPause()
        baseBinding.pv.onPause()
        if (Settings.playPauseTime == 0) {
            isPlayBeforeStop = player.playWhenReady
            pause()
        }
    }

    fun start() {
        player.seekTo(0)
        player.play()
    }

    fun stop() {
        player.stop()
    }

    fun pause() {
        player.playWhenReady = false
    }

    fun resume() {
        player.playWhenReady = true
    }

    fun prepare() {
        player.prepare()
    }

    fun setCoverUrl(url: String?) {
        coverUrl = url
        runNewThread {
            try {
                val cover = Picasso.get().load(url).get().toDrawable(resources)
                runMain {
                    baseBinding.pv.setCover(cover)
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
            baseBinding.pv.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topToTop = PARENT_ID
                startToStart = PARENT_ID
                endToEnd = PARENT_ID
                bottomToBottom = PARENT_ID
            }
            windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.navigationBars())
            windowInsetsControllerCompat.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        } else {
            baseBinding.pv.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topToTop = R.id.rhv
                startToStart = R.id.rhv
                endToEnd = R.id.rhv
                bottomToBottom = R.id.rhv
            }
            windowInsetsControllerCompat.show(WindowInsetsCompat.Type.navigationBars())
        }
    }

    override fun onVisibilityChange(visibility: Int) {
        baseBinding.abl.elevation = 2.dpToPx().toFloat()
        if (visibility == View.VISIBLE) {
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
        if ((window.decorView.isVisible || player.isPlaying).not())
            finish()
    }

    open fun onNextClick() {}

    /**
     * @return first: title second: url
     */
    open fun onGetShare(): Pair<String?, String?> = null to null

    @CallSuper
    open fun beforeReinitLayout() {
        baseBinding.pv.player = null
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