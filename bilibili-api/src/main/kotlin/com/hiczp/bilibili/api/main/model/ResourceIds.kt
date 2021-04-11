package com.hiczp.bilibili.api.main.model


import com.google.gson.annotations.SerializedName

data class ResourceIds(
    @SerializedName("code")
    var code: Int, // 0
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("message")
    var message: String // success
) {
    data class Data(
        @SerializedName("bv_id")
        var bvId: String, // BV1P54y1Q7fW
        @SerializedName("bvid")
        var bvid: String, // BV1P54y1Q7fW
        @SerializedName("fav_state")
        var favState: Int, // 0
        @SerializedName("fav_time")
        var favTime: Int, // 1591804928
        @SerializedName("id")
        var id: Int, // 840842106
        @SerializedName("like_state")
        var likeState: Int, // 0
        @SerializedName("page")
        var page: Int, // 0
        @SerializedName("short_link")
        var shortLink: String, // https://b23.tv/BV1P54y1Q7fW
        @SerializedName("type")
        var type: Int // 2
    )
}