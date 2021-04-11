package com.hiczp.bilibili.api.weblive.model


import com.google.gson.annotations.SerializedName

data class PlayUrl(
    @SerializedName("code")
    val code: Int, // 0
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // 0
    @SerializedName("ttl")
    val ttl: Int // 1
) {
    data class Data(
        @SerializedName("accept_quality")
        val acceptQuality: List<String>,
        @SerializedName("current_qn")
        val currentQn: Int, // 3
        @SerializedName("current_quality")
        val currentQuality: Int, // 3
        @SerializedName("durl")
        val durl: List<Durl>,
        @SerializedName("quality_description")
        val qualityDescription: List<QualityDescription>
    ) {
        data class Durl(
            @SerializedName("length")
            val length: Int, // 0
            @SerializedName("order")
            val order: Int, // 1
            @SerializedName("p2p_type")
            val p2pType: Int, // 0
            @SerializedName("stream_type")
            val streamType: Int, // 0
            @SerializedName("url")
            val url: String // https://d1--cn-gotcha04.bilivideo.com/live-bvc/601131/live_14073662_bs_3699814_1500.flv?cdn=cn-gotcha04&expires=1602496530&len=0&oi=1939228219&pt=&qn=150&trid=e6540d81a5d04c2ea459c46ebe77472a&sigparams=cdn,expires,len,oi,pt,qn,trid&sign=20e4ac695fbdd1d11d5dac4f93caa783&ptype=0&src=9&sl=1&order=1
        )

        data class QualityDescription(
            @SerializedName("desc")
            val desc: String, // 原画
            @SerializedName("qn")
            val qn: Int // 4
        )
    }
}