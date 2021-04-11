package com.hiczp.bilibili.api.web.model

import com.google.gson.annotations.SerializedName

data class VideoShot(
        @SerializedName("code")
        var code: Int,
        @SerializedName("message")
        var message: String,
        @SerializedName("ttl")
        var ttl: Int,
        @SerializedName("data")
        var data: Data
) {
    data class Data(
            @SerializedName("pvdata")
            var pvdata: String,
            @SerializedName("img_x_len")
            var imgXLen: Int,
            @SerializedName("img_y_len")
            var imgYLen: Int,
            @SerializedName("img_x_size")
            var imgXSize: Int,
            @SerializedName("img_y_size")
            var imgYSize: Int,
            @SerializedName("image")
            var image: List<String>,
            @SerializedName("index")
            var index: List<Int>
    )
}