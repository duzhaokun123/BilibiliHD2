package com.duzhaokun123.bilibilihd2.bilisubtitle

import com.duzhaokun123.bilibilihd2.utils.gson
import com.duzhaokun123.bilibilihd2.utils.readText
import com.github.salomonbrys.kotson.fromJson
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder
import com.google.android.exoplayer2.text.Subtitle
import com.google.android.exoplayer2.util.ParsableByteArray

class BiliSubtitleDecoder : SimpleSubtitleDecoder("BiliSubtitleDecoder") {
    override fun decode(data: ByteArray, size: Int, reset: Boolean): Subtitle {
        val parsableByteArray = ParsableByteArray()
        parsableByteArray.reset(data, size)
        val j = parsableByteArray.readText()
        return BiliSubtitle(com.duzhaokun123.bilibilihd2.model.Subtitle.from(gson.fromJson(j)))
    }
}