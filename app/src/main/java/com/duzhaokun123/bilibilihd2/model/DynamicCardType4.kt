package com.duzhaokun123.bilibilihd2.model


import com.google.gson.annotations.SerializedName

data class DynamicCardType4(
    @SerializedName("item")
    val item: Item,
    @SerializedName("user")
    val user: User
) {
    data class Item(
        @SerializedName("content")
        val content: String, // 从早上开始就一直在进行东京巨蛋的练习唱着自己的歌 到目前为止写了好多的歌啊趁还能哭的时候就哭
        @SerializedName("ctrl")
        val ctrl: String,
        @SerializedName("orig_dy_id")
        val origDyId: Int, // 0
        @SerializedName("pre_dy_id")
        val preDyId: Int, // 0
        @SerializedName("reply")
        val reply: Int, // 261
        @SerializedName("rp_id")
        val rpId: Long, // 512286854207687715
        @SerializedName("timestamp")
        val timestamp: Int, // 1618114478
        @SerializedName("uid")
        val uid: Int // 387994725
    )

    data class User(
        @SerializedName("face")
        val face: String, // https://i0.hdslb.com/bfs/face/9767b6348454459c17819201ee10a789fc175058.jpg
        @SerializedName("uid")
        val uid: Int, // 387994725
        @SerializedName("uname")
        val uname: String // Mafumafu_Channel
    )
}