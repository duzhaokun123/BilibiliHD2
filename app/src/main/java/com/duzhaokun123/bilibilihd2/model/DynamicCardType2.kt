package com.duzhaokun123.bilibilihd2.model


import com.google.gson.annotations.SerializedName

data class DynamicCardType2(
    @SerializedName("item")
    val item: Item,
    @SerializedName("user")
    val user: User
) {
    data class Item(
        @SerializedName("at_control")
        val atControl: String,
        @SerializedName("category")
        val category: String, // daily
        @SerializedName("description")
        val description: String, // GBF,碰都莫碰，要累死了[笑哭]
        @SerializedName("id")
        val id: Int, // 122841619
        @SerializedName("is_fav")
        val isFav: Int, // 0
        @SerializedName("pictures")
        val pictures: List<Picture>,
        @SerializedName("pictures_count")
        val picturesCount: Int, // 1
        @SerializedName("reply")
        val reply: Int, // 3
        @SerializedName("role")
        val role: List<Any>,
        @SerializedName("settings")
        val settings: Settings,
        @SerializedName("source")
        val source: List<Any>,
        @SerializedName("title")
        val title: String,
        @SerializedName("upload_time")
        val uploadTime: Int // 1618075820
    ) {
        data class Picture(
            @SerializedName("img_height")
            val imgHeight: Int, // 791
            @SerializedName("img_size")
            val imgSize: Double, // 157.89
            @SerializedName("img_src")
            val imgSrc: String, // https://i0.hdslb.com/bfs/album/a6b3784f241fb5218eeb2158b9ad93c4bbeb842f.jpg
            @SerializedName("img_tags")
            val imgTags: Any?, // null
            @SerializedName("img_width")
            val imgWidth: Int // 1097
        )

        data class Settings(
            @SerializedName("copy_forbidden")
            val copyForbidden: String // 0
        )
    }

    data class User(
        @SerializedName("head_url")
        val headUrl: String, // https://i0.hdslb.com/bfs/face/84dd3b2ed16d0ae9b6f7074e89c40c27b2562979.jpg
        @SerializedName("name")
        val name: String, // 勇者湾湾
        @SerializedName("uid")
        val uid: Int, // 13308108
        @SerializedName("vip")
        val vip: Vip
    ) {
        data class Vip(
            @SerializedName("avatar_subscript")
            val avatarSubscript: Int, // 1
            @SerializedName("due_date")
            val dueDate: Long, // 1707408000000
            @SerializedName("label")
            val label: Label,
            @SerializedName("nickname_color")
            val nicknameColor: String, // #FB7299
            @SerializedName("status")
            val status: Int, // 1
            @SerializedName("theme_type")
            val themeType: Int, // 0
            @SerializedName("type")
            val type: Int, // 2
            @SerializedName("vip_pay_type")
            val vipPayType: Int // 0
        ) {
            data class Label(
                @SerializedName("label_theme")
                val labelTheme: String, // annual_vip
                @SerializedName("path")
                val path: String,
                @SerializedName("text")
                val text: String // 年度大会员
            )
        }
    }
}