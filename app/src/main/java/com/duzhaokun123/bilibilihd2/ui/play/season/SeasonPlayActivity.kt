package com.duzhaokun123.bilibilihd2.ui.play.season

import android.content.Context
import android.content.Intent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.duzhaokun123.annotationProcessor.IntentFilter
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BasePlayActivity
import com.duzhaokun123.bilibilihd2.databinding.LayoutSeasonBinding
import com.duzhaokun123.bilibilihd2.ui.UrlOpenActivity
import com.duzhaokun123.bilibilihd2.ui.play.online.LazyCidDanmakuParser
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.biliplayer.model.PlayInfo
import com.duzhaokun123.generated.Settings
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import io.github.duzhaokun123.androidapptemplate.utils.launch
import io.github.duzhaokun123.androidapptemplate.utils.maxSystemBarsDisplayCutout
import io.github.duzhaokun123.androidapptemplate.utils.onSuccess
import io.github.duzhaokun123.androidapptemplate.utils.runIOCatching

class SeasonPlayActivity : BasePlayActivity() {
    companion object {
        const val EXTRA_EPID = "epid"
        const val EXTRA_SSID = "ssid"
    }

    @IntentFilter
    class EpidIntentHandler: UrlOpenActivity.IIntentFilter {
        override fun handle(
            parsedIntent: UrlOpenActivity.ParsedIntent,
            context: Context
        ): Pair<Intent?, String?> {
            if (parsedIntent.scheme != "bilibili" || parsedIntent.host != "bangumi") return null to null
            val ssid = parsedIntent.paths[1].toLong()
            val intent = Intent(context, SeasonPlayActivity::class.java).apply {
                putExtra(EXTRA_SSID, ssid)
            }
            return intent to "番剧 ssid$ssid"
        }
    }

    @IntentFilter
    class SsidIntentHandler2: UrlOpenActivity.IIntentFilter {
        override fun handle(
            parsedIntent: UrlOpenActivity.ParsedIntent,
            context: Context
        ): Pair<Intent?, String?> {
            if (parsedIntent.host != "www.bilibili.com") return null to null
            val epid = parsedIntent.paths[2].substringAfter("ep").toLong()
            val intent = Intent(context, SeasonPlayActivity::class.java).apply {
                putExtra(EXTRA_EPID, epid)
            }
            return intent to "番剧 epid$epid"
        }
    }

    val layoutSeasonBinding by lazy { LayoutSeasonBinding.inflate(layoutInflater) }
    val ssid by lazy { startIntent.getLongExtra(EXTRA_SSID, 0).takeIf { it != 0L } }
    val epid by lazy { startIntent.getLongExtra(EXTRA_EPID, 0).takeIf { it != 0L } }

    override fun initViews() {
        super.initViews()
        baseBinding.rl.addView(layoutSeasonBinding.root, ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
            ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
        ).apply {
            topToBottom = R.id.rhv
            startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        })
    }

    override fun initData() {
        super.initData()
        layoutSeasonBinding.tvInfo.text = "ssid: $ssid, epid: $epid"
        runIOCatching { SeasonAgent.seasonApi.season(ssid, epid).await() }
            .setCommonOnFailureHandler(this@SeasonPlayActivity)
            .onSuccessMain {
                layoutSeasonBinding.tvResult.text = it.toString()
            }
            .launch()
    }

    override fun initEvents() {
        super.initEvents()
        layoutSeasonBinding.btnLoad.setOnClickListener {
            runCatching {
                load(layoutSeasonBinding.etAid.text.toString().toLong(), layoutSeasonBinding.etCid.text.toString().toLong())
            }.commonOnFailureHandler(this)
        }
    }

    override fun beforeReinitLayout() {
        super.beforeReinitLayout()
        layoutSeasonBinding.root.removeFromParent()
    }

    override fun onApplyWindowInsetsCompat(insets: WindowInsetsCompat) {
        super.onApplyWindowInsetsCompat(insets)
        with(insets.maxSystemBarsDisplayCutout) {
            layoutSeasonBinding.root.updatePadding(left = left, right = right, bottom = bottom)
        }
    }

    fun load(aid: Long, cid: Long) {
        runIOCatching { SeasonAgent.seasonApi.bangumiPlayUrl(aid, cid).await() }
            .setCommonOnFailureHandler(this@SeasonPlayActivity)
            .onSuccess { playUrl ->
                val hasAudio = playUrl.dash.audio != null
                val sources = mutableListOf<PlayInfo.Source>()
                playUrl.dash.video.forEach { video ->
                    val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(MediaItem.fromUri(video.baseUrl))
                    val audioSource =
                        if (hasAudio) ProgressiveMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(MediaItem.fromUri(playUrl.dash.audio!![0].baseUrl))
                        else null
                    val mergedSource =
                        if (hasAudio) MergingMediaSource(videoSource, audioSource!!) else videoSource
                    val backups = mutableListOf<MediaSource>()
                    video.backupUrl.forEach { backup ->
                        val bv = ProgressiveMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(MediaItem.fromUri(backup))
                        val mb = if (hasAudio) MergingMediaSource(bv, audioSource!!) else bv
                        backups.add(mb)
                    }
                    val name = playUrl.acceptDescription.getOrNull(
                        playUrl.acceptQuality.indexOf(video.id)
                    ) ?: video.id.toString()
                    sources.add(PlayInfo.Source(name, video.id, mergedSource, backups))
                }
                if (hasAudio)
                    sources.add(
                        PlayInfo.Source("audio only", 0,
                            ProgressiveMediaSource.Factory(dataSourceFactory)
                                .createMediaSource(MediaItem.fromUri(playUrl.dash.audio[0].baseUrl)),
                            emptyList()
                        )
                    )

                val danmakuParser =  LazyCidDanmakuParser(aid, cid, 1)
                val onlinePlayQuality = Settings.onlinePlayQuality
                biliPlayerView.playInfo = PlayInfo(
                    "$aid", "$aid", sources, danmakuParser, false, sources.find { it.id == onlinePlayQuality } ?: sources.first()
                )
                runMain {
                    biliPlayerView.prepare()
                }
            }
            .launch()
    }
}