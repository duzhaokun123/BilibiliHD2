package com.duzhaokun123.bilibilihd2.ui.play.online

import android.content.Context
import android.content.Intent
import com.duzhaokun123.bilibilihd2.bases.BasePlayActivity
import com.duzhaokun123.bilibilihd2.utils.bilibiliClient
import com.duzhaokun123.bilibilihd2.utils.runIOCatchingResultRunMain
import com.duzhaokun123.bilibilihd2.utils.toAid
import com.duzhaokun123.biliplayer.model.PlayInfo
import com.duzhaokun123.danmakuview.interfaces.DanmakuParser
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.hiczp.bilibili.api.player.model.VideoPlayUrl
import com.hiczp.bilibili.api.app.model.View as BiliView

class OnlinePlayActivity : BasePlayActivity() {
    companion object {
        private const val EXTRA_AID = "aid"

        fun enter(context: Context, aid: Long) {
            context.startActivity(Intent(context, OnlinePlayActivity::class.java).apply {
                putExtra(EXTRA_AID, aid)
            })
        }

        fun enter(context: Context, bvid: String) =
            enter(context, bvid.toAid())
    }

    val aid by lazy { startIntent.getLongExtra(EXTRA_AID, 0) }
    lateinit var biliView: BiliView
    var cid = 0L
    var page = 1
    val pageParserMap = mutableMapOf<Int, DanmakuParser>()

    override fun initView() {
        super.initView()
    }

    override fun initData() {
        super.initData()
        if (::biliView.isInitialized.not()) {
            runIOCatchingResultRunMain(this, {
                bilibiliClient.appAPI.view(aid = aid).await()
            }) {
                biliView = it
                updateVideoPlayUrl()
            }
        }
    }

    override fun onGetShare() = biliView.data.title to "https://b23.tv/$aid"

    private fun updateVideoPlayUrl() {
        cid = biliView.data.pages[page - 1].cid
        runIOCatchingResultRunMain(this,
            { bilibiliClient.playerAPI.videoPlayUrl(cid = cid, aid = aid).await() })
        {
            setVideoPlayUrl(it)
        }
    }

    private fun setVideoPlayUrl(videoPlayUrl: VideoPlayUrl) {
        val title = biliView.data.title
        val pageTitle = biliView.data.pages[page - 1].part
        val hasAudio = videoPlayUrl.data.dash.audio != null
        val sources = mutableListOf<PlayInfo.Source>()
        videoPlayUrl.data.dash.video.forEach { video ->
            val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(video.baseUrl))
            val audioSource =
                if (hasAudio) ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(videoPlayUrl.data.dash.audio!![0].baseUrl))
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
            val name = videoPlayUrl.data.acceptDescription.getOrNull(
                videoPlayUrl.data.acceptQuality.indexOf(video.id)
            ) ?: video.id.toString()
            sources.add(PlayInfo.Source(name, mergedSource, backups))
        }
        val danmakuParser = pageParserMap[page]
            ?: LazyCidDanmakuParser(aid, cid, biliView.data.pages[page - 1].duration).also {
                pageParserMap[page] = it
            }
        biliPlayerView.playInfo = PlayInfo(
            title,
            pageTitle,
            sources,
            danmakuParser,
            biliView.data.pages.size > page
        )
    }
}