package com.duzhaokun123.bilibilihd2.bilisubtitle

import com.google.android.exoplayer2.Format
import com.google.android.exoplayer2.text.SubtitleDecoder
import com.google.android.exoplayer2.text.SubtitleDecoderFactory

object BiliSubtitleDecoderFactory: SubtitleDecoderFactory {
    override fun supportsFormat(format: Format): Boolean {
        return format.sampleMimeType == BiliSubtitle.MIME_TYPE
    }

    override fun createDecoder(format: Format): SubtitleDecoder {
        assert(format.sampleMimeType == BiliSubtitle.MIME_TYPE)
        return BiliSubtitleDecoder()
    }
}