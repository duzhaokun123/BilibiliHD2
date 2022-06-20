package com.duzhaokun123.bilibilihd2.ui.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.RadioButton
import androidx.core.view.get
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.LayoutTrackSelectorBinding
import com.duzhaokun123.bilibilihd2.utils.forEach
import com.duzhaokun123.bilibilihd2.utils.forEachIndexed
import com.duzhaokun123.bilibilihd2.utils.getColorCompat
import com.duzhaokun123.bilibilihd2.utils.removeFromParent
import com.duzhaokun123.danmakuview.ui.DanmakuView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionOverride
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.material.button.MaterialButton
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.ViewGroup.LayoutParams.MATCH_PARENT

class PlayerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : StyledPlayerView(context, attrs) {
    val overlay = overlayFrameLayout!!
    var showTrackSelector = false
    var onFullScreenModeChangedListener: ((Boolean) -> Unit)? = null
    var isFullScreen = false
        private set
    lateinit var exoFullscreen: View
    lateinit var coverView: ImageView
    lateinit var danmakuView: DanmakuView

    init {
        setBackgroundColor(Color.BLACK)
        if (isInEditMode.not()) {
            exoFullscreen = findViewById(R.id.exo_fullscreen)
            setFullscreenButtonClickListener {
                isFullScreen = it
                onFullScreenModeChangedListener?.invoke(it)
            }
            findViewById<MaterialButton>(R.id.btn_track_selector).setOnClickListener {
                if (showTrackSelector) return@setOnClickListener
                showTrackSelector = true
                val trackSelectorBinding = LayoutTrackSelectorBinding.inflate(LayoutInflater.from(context))
                this.addView(trackSelectorBinding.root, LayoutParams(MATCH_PARENT, MATCH_PARENT))
                trackSelectorBinding.btnClose.setOnClickListener {
                    showTrackSelector = false
                    trackSelectorBinding.root.removeFromParent()
                }
                val trackSelector = (player as ExoPlayer).trackSelector as DefaultTrackSelector
                val renderers = (0 until  (trackSelector.currentMappedTrackInfo?.rendererCount ?: 0)).map { i ->
                    trackSelector.currentMappedTrackInfo?.getRendererName(i)
                }.toTypedArray()
                val adapter = ArrayAdapter(context, R.layout.spinner_item, renderers).also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                }
                trackSelectorBinding.spRender.adapter = adapter
                trackSelectorBinding.spRender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val trackGroups = trackSelector.currentMappedTrackInfo?.getTrackGroups(position) ?: return
                        trackSelectorBinding.rg.removeAllViews()
                        val selectedTrackGroups = trackSelector.parameters.overrides.keys
                        var si = -1
                        trackGroups.forEachIndexed { i, trackGroup ->
                            trackSelectorBinding.rg.addView(RadioButton(context).apply {
                                val sb = StringBuilder()
                                trackGroup.forEach {
                                    sb.append(it.toString()).append("\n")
                                }
                                text = sb.toString()
                                setTextColor(context.getColorCompat(R.color.white))
                                setOnClickListener {
                                    si = i
                                    trackSelector.apply {
                                        parameters = parameters.buildUpon().setOverrideForType(
                                            //这里永远只会有一个 track
                                        TrackSelectionOverride(trackGroup, 0)
                                        ).build()
                                    }
                                }
                                if (trackGroup in selectedTrackGroups) si = i
                                this.id = generateViewId()
                            }, MATCH_PARENT, WRAP_CONTENT)
                        }
                        val idAuto = generateViewId()
                        trackSelectorBinding.rg.addView(RadioButton(context).apply {
                            text = "auto"
                            setTextColor(context.getColorCompat(R.color.white))
                            setOnClickListener {
                                trackSelector.apply {
                                    parameters = parameters.buildUpon()
                                        .clearOverride(trackGroups[si])
                                        .build()
                                }
                            }
                            this.id = idAuto
                        }, MATCH_PARENT, WRAP_CONTENT)
                        trackSelectorBinding.rg.apply {
                            if (si != -1)
                                check(get(si).id)
                            else
                                check(idAuto)
                        }
                        trackSelectorBinding.swEnable.setOnCheckedChangeListener(null)
                        trackSelectorBinding.swEnable.isChecked = trackSelector.parameters.getRendererDisabled(position).not()
                        trackSelectorBinding.swEnable.setOnCheckedChangeListener { _, isChecked ->
                            trackSelector.apply {
                                parameters = parameters.buildUpon().setRendererDisabled(position, isChecked.not()).build()
                            }
                        }
                    }
                }
                trackSelectorBinding.spRender.setSelection(0)
            }
            coverView = ImageView(context).apply {
                setOnClickListener {
                    player?.play()
                    it.removeFromParent()
                }
                scaleType = ImageView.ScaleType.FIT_CENTER
                setBackgroundColor(Color.BLACK)
            }
//            danmakuView = DanmakuView(context)
        }
    }

    fun changeFullScreenMode(isFullScreen: Boolean) {
        if (this.isFullScreen == isFullScreen) return
        exoFullscreen.callOnClick()
    }

    fun setCover(cover: Drawable) {
        this.coverView.setImageDrawable(cover)
        addView(this.coverView, LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        ))
    }
}