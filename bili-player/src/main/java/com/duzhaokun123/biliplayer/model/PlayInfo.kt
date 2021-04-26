package com.duzhaokun123.biliplayer.model

import com.duzhaokun123.danmakuview.interfaces.DanmakuParser
import com.google.android.exoplayer2.source.MediaSource

data class PlayInfo(
    val title: String,
    val pageTitle: String,
    val sources: List<Source>,
    val danmakuParser: DanmakuParser,
    val hasNext: Boolean
) {
    data class Source(
        val name: String,
        val mediaSource: MediaSource,
        val backupSources: List<MediaSource>
    )
}
