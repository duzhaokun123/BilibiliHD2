package com.duzhaokun123.bilibilihd2.ui.play.online

import android.content.Context
import android.content.Intent
import com.duzhaokun123.bilibilihd2.bases.BasePlayActivity
import com.duzhaokun123.bilibilihd2.utils.bilibiliClient
import com.duzhaokun123.bilibilihd2.utils.runIOCatchingResultRunMain
import com.duzhaokun123.bilibilihd2.utils.toAid
import com.duzhaokun123.danmakuview.interfaces.DanmakuParser
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
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

    lateinit var biliView: BiliView
    var cid = 0L

    override fun initView() {
        super.initView()
    }

    override fun initData() {
        super.initData()
        if (::biliView.isInitialized.not()) {


        runIOCatchingResultRunMain(this, { bilibiliClient.appAPI.view(aid = startIntent.getLongExtra(
            EXTRA_AID, 0)).await()}
        ) {
            biliView = it
            cid = it.data.cid
            runIOCatchingResultRunMain(this, { bilibiliClient.playerAPI.videoPlayUrl(aid = startIntent.getLongExtra(
                EXTRA_AID, 0), cid = cid).await()}
            ) {

                val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(it.data.dash.video[0].baseUrl))
                val audioSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(it.data.dash.audio!![0].baseUrl))
                val mergedSource = MergingMediaSource(videoSource, audioSource)
                biliPlayerView.mediaSources = listOf(mergedSource to DanmakuParser.EMPTY)
//                player.destroy()
            }}
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}