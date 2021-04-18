package com.duzhaokun123.biliplayer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.res.use
import com.duzhaokun123.danmakuview.interfaces.DanmakuParser
import com.duzhaokun123.danmakuview.ui.DanmakuView
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ui.DefaultTimeBar
import com.google.android.exoplayer2.ui.StyledPlayerControlView
import com.google.android.exoplayer2.ui.StyledPlayerView

class BiliPlayerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    enum class State {
        PLAYING, PAUSED, STOPPED, DESTROYED
    }

    lateinit var playerView: StyledPlayerView
        private set
    lateinit var playerControlView: StyledPlayerControlView
        private set
    lateinit var danmakuView: DanmakuView
        private set

    val player: SimpleExoPlayer
        get() = playerView.player as SimpleExoPlayer

    var state = State.STOPPED
        private set

    val isDestroyed get() = state == State.DESTROYED
    val isPLaying get() = state == State.PLAYING
    val isPaused get() = state == State.PAUSED

    init {
        LayoutInflater.from(context).inflate(R.layout.view_biliplayer, this)
        if (isInEditMode.not()) {
            playerView = findViewById(R.id.pv)
            playerView.player = SimpleExoPlayer.Builder(context).build()
            playerControlView = playerView.findViewById(R.id.exo_controller)
            danmakuView = findViewById(R.id.dv)
            danmakuView.zOnTop = true
        }
    }

    fun destroy() {
        player.release()
        danmakuView.destroy()
        state = State.DESTROYED
    }

    var mediaSources = listOf<Pair<MediaSource, DanmakuParser>>()

    fun start() {
        if (isDestroyed) return
        player.setMediaSources(mediaSources.firstList())
        findViewById<View>(R.id.exo_play_pause).callOnClick()
        state = State.PLAYING
    }

    fun pause() {
        if (isDestroyed) return
        if (isPLaying) {
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
        player.stop()
        state = State.STOPPED
    }

    fun changeFullscreen() {
        findViewById<View>(R.id.exo_fullscreen).callOnClick()
    }

    fun setPlayedColor(color: Int) {
        playerControlView.findViewById<DefaultTimeBar>(R.id.exo_progress).setPlayedColor(color)
    }
}