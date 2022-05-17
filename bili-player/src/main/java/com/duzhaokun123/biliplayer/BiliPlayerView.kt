package com.duzhaokun123.biliplayer

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.PopupMenu
import com.duzhaokun123.biliplayer.model.PlayInfo
import com.duzhaokun123.danmakuview.ui.DanmakuView
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.DefaultTimeBar
import com.google.android.exoplayer2.ui.StyledPlayerControlView
import com.google.android.exoplayer2.ui.StyledPlayerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BiliPlayerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), Player.EventListener {
    enum class State {
        PLAYING, PAUSED, STOPPED, DESTROYED
    }

    private var selectedSource: PlayInfo.Source? = null

    val buttonAlphaEnabled =
        resources.getInteger(R.integer.exo_media_button_opacity_percentage_enabled)
            .toFloat() / 100
    val buttonAlphaDisabled =
        resources.getInteger(R.integer.exo_media_button_opacity_percentage_disabled)
            .toFloat() / 100
    lateinit var playerView: StyledPlayerView
        private set
    lateinit var playerControlView: StyledPlayerControlView
        private set
    lateinit var danmakuView: DanmakuView
        private set
    lateinit var ibNext: ImageButton
        private set
    lateinit var btnQuality: Button
        private set

    val player: SimpleExoPlayer
        get() = playerView.player as SimpleExoPlayer

    var state = State.STOPPED
        private set

    val isDestroyed get() = state == State.DESTROYED
    val isPlaying get() = state == State.PLAYING
    val isPaused get() = state == State.PAUSED

    var onNextClickListener: (() -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_biliplayer, this)
        if (isInEditMode.not()) {
            playerView = findViewById(R.id.pv)
            playerView.player = SimpleExoPlayer.Builder(context).build()
            playerControlView = playerView.findViewById(R.id.exo_controller)
            danmakuView = DanmakuView(context)
            playerView.overlayFrameLayout!!.addView(danmakuView, MATCH_PARENT, MATCH_PARENT)
            ibNext = playerControlView.findViewById(R.id.ib_next)
            ibNext.setOnClickListener {
                if (ibNext.isEnabled) onNextClickListener?.invoke()
            }
            btnQuality = playerControlView.findViewById(R.id.btn_quality)

            player.addListener(this)

            findViewById<View>(R.id.iv_cover).setOnClickListener {
                it.visibility = GONE
                findViewById<View>(R.id.exo_play_pause).callOnClick()
                state = State.PLAYING
            }

            findViewById<Button>(R.id.btn_danmakuSwitch).setOnClickListener {
                danmakuView.visibility = if (danmakuView.isShowing) INVISIBLE else VISIBLE
            }
        }
    }

    fun destroy() {
        player.release()
        danmakuView.destroy()
        state = State.DESTROYED
    }

    var playInfo: PlayInfo? = null

    fun start() {
        player.seekTo(0)
        player.play()
        state = State.PLAYING
    }

    fun prepare() {
        if (isDestroyed) return
        if (playInfo == null) {
            player.setMediaSources(emptyList())
            ibNext.isEnabled = false
            ibNext.alpha = buttonAlphaDisabled
            state = State.STOPPED
            btnQuality.setOnClickListener(null)
        } else {
            val info = playInfo!!
            player.setMediaSource(info.defaultSource.mediaSource)
            selectedSource = info.defaultSource
            ibNext.isEnabled = info.hasNext
            ibNext.alpha = if (info.hasNext) buttonAlphaEnabled else buttonAlphaDisabled
            if (player.playWhenReady)
                findViewById<View>(R.id.exo_play_pause).callOnClick()
            btnQuality.setOnClickListener {
                PopupMenu(context, btnQuality).apply {
                    info.sources.forEachIndexed { i, s ->
                        menu.add(0, i, i, s.name).setOnMenuItemClickListener {
                            if (s == selectedSource) return@setOnMenuItemClickListener true
                            selectedSource = s
                            player.setMediaSource(s.mediaSource, false)
                            btnQuality.text = s.name
                            true
                        }
                    }
                }.show()
            }
            btnQuality.text = info.defaultSource.name
            danmakuView.parse(info.danmakuParser) {
                GlobalScope.launch(Dispatchers.Main) {
                    delay(500)
                    danmakuView.seekTo(player.contentPosition)
                    if (player.isPlaying)
                        danmakuView.resume()
                }
            }
            state = State.PAUSED
        }
    }

    fun pause() {
        if (isDestroyed) return
        if (isPlaying) {
            player.pause()
            state = State.PAUSED
        }
    }

    fun resume() {
        if (isDestroyed) return
        if (isPaused) {
            player.play()
            state = State.PLAYING
        }
    }

    fun stop() {
        if (isDestroyed) return
        player.setMediaSources(emptyList())
        player.stop()
        state = State.STOPPED
    }

    fun changeFullscreen() {
        findViewById<View>(R.id.exo_fullscreen).callOnClick()
    }

    fun setPlayedColor(color: Int) {
        playerControlView.findViewById<DefaultTimeBar>(R.id.exo_progress).setPlayedColor(color)
    }

    override fun onPositionDiscontinuity(reason: Int) {
        danmakuView.seekTo(player.contentPosition)
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        if (isPlaying)
            danmakuView.resume()
        else
            danmakuView.pause()
    }

    override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {
        danmakuView.speed = playbackParameters.speed
    }

    fun setCover(cover: Drawable?) {
        findViewById<AppCompatImageView>(R.id.iv_cover).setImageDrawable(cover)
    }
}