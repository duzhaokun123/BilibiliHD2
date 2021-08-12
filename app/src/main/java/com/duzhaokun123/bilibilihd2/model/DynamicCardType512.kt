package com.duzhaokun123.bilibilihd2.model


import com.google.gson.annotations.SerializedName

data class DynamicCardType512(
    @SerializedName("aid")
    val aid: Int, // 804781038
    @SerializedName("cover")
    val cover: String, // https://i0.hdslb.com/bfs/archive/37b44e6bd189a404273402336a8d8fc092971fdb.png
    @SerializedName("dimension")
    val dimension: Dimension,
    @SerializedName("duration")
    val duration: Int, // 1421
    @SerializedName("episode_id")
    val episodeId: Int, // 411756
    @SerializedName("index_title")
    val indexTitle: String, // 理科生坠入情网故试发表研究
    @SerializedName("is_finish")
    val isFinish: Int, // 0
    @SerializedName("new_desc")
    val newDesc: String, // 第10话 理科生坠入情网故试发表研究
    @SerializedName("season")
    val season: Season,
    @SerializedName("short_title")
    val shortTitle: String, // 10
    @SerializedName("stat")
    val stat: Stat,
    @SerializedName("url")
    val url: String // https://www.bilibili.com/bangumi/play/ep411756
) {
    data class Dimension(
        @SerializedName("height")
        val height: Int, // 1080
        @SerializedName("rotate")
        val rotate: Int, // 0
        @SerializedName("width")
        val width: Int // 1920
    )

    data class Season(
        @SerializedName("cover")
        val cover: String, // https://i0.hdslb.com/bfs/bangumi/image/3b32a67475f21a5f068d10a041edbef6a649d801.png
        @SerializedName("is_finish")
        val isFinish: Int, // 0
        @SerializedName("season_id")
        val seasonId: Int, // 38878
        @SerializedName("square_cover")
        val squareCover: String, // https://i0.hdslb.com/bfs/bangumi/image/3b32a67475f21a5f068d10a041edbef6a649d801.png
        @SerializedName("title")
        val title: String, // 理科生坠入情网，故尝试证明。 中配版
        @SerializedName("total_count")
        val totalCount: Int, // 12
        @SerializedName("ts")
        val ts: Int, // 1628744126
        @SerializedName("type")
        val type: Int, // 1
        @SerializedName("type_name")
        val typeName: String // 番剧
    )

    data class Stat(
        @SerializedName("danmaku")
        val danmaku: Int, // 25
        @SerializedName("play")
        val play: Int, // 4004
        @SerializedName("reply")
        val reply: Int // 29
    )
}