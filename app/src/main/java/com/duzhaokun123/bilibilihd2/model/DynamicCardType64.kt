package com.duzhaokun123.bilibilihd2.model


import com.google.gson.annotations.SerializedName

data class DynamicCardType64(
    @SerializedName("act_id")
    val actId: Int, // 0
    @SerializedName("apply_time")
    val applyTime: String,
    @SerializedName("attributes")
    val attributes: Int, // 24
    @SerializedName("authenMark")
    val authenMark: Any?, // null
    @SerializedName("author")
    val author: Author,
    @SerializedName("banner_url")
    val bannerUrl: String,
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("category")
    val category: Category,
    @SerializedName("check_time")
    val checkTime: String,
    @SerializedName("cover_avid")
    val coverAvid: Int, // 0
    @SerializedName("ctime")
    val ctime: Int, // 1618308147
    @SerializedName("dispute")
    val dispute: Dispute,
    @SerializedName("id")
    val id: Long, // 10840464
    @SerializedName("image_urls")
    val imageUrls: List<String>,
    @SerializedName("is_like")
    val isLike: Boolean, // false
    @SerializedName("item")
    val item: Item,
    @SerializedName("list")
    val list: Any?, // null
    @SerializedName("media")
    val media: Media,
    @SerializedName("origin_image_urls")
    val originImageUrls: List<String>,
    @SerializedName("original")
    val original: Int, // 0
    @SerializedName("publish_time")
    val publishTime: Int, // 1618308232
    @SerializedName("reprint")
    val reprint: Int, // 0
    @SerializedName("state")
    val state: Int, // 0
    @SerializedName("stats")
    val stats: Stats,
    @SerializedName("summary")
    val summary: String, // 大家好这里是明日方舟项目组。在即将到来的版本更新中，【作战终端升级】也将随之上线，新版作战终端界面将对现有视觉效果进行调整，对不同的关卡、活动类别进行梳理与优化。您可以在更新之后通过主界面入口、或上方引导菜单终端按钮前往新的作战终端界面。新的作战终端界面对主线关卡、活动关卡及素材关卡等进行了分类优化，可通过入口下方的分类按钮进入不同的关卡类别。终端主界面将显示目前正开启的活动、您当前的主线进度、本周剿灭报酬合成玉获取进度等信息，您可通过不同的信息入口前往对应的作战关卡。同时，根据当前正在开展的活
    @SerializedName("template_id")
    val templateId: Int, // 4
    @SerializedName("title")
    val title: String, // 作战终端升级内容前瞻
    @SerializedName("top_video_info")
    val topVideoInfo: Any?, // null
    @SerializedName("words")
    val words: Int // 1145
) {
    data class Author(
        @SerializedName("face")
        val face: String, // https://i0.hdslb.com/bfs/face/89154378c06a5ed332c40c2ca56f50cd641c0c90.jpg
        @SerializedName("mid")
        val mid: Int, // 161775300
        @SerializedName("name")
        val name: String, // 明日方舟
        @SerializedName("nameplate")
        val nameplate: Nameplate,
        @SerializedName("official_verify")
        val officialVerify: OfficialVerify,
        @SerializedName("pendant")
        val pendant: Pendant,
        @SerializedName("vip")
        val vip: Vip
    ) {
        data class Nameplate(
            @SerializedName("condition")
            val condition: String,
            @SerializedName("image")
            val image: String,
            @SerializedName("image_small")
            val imageSmall: String,
            @SerializedName("level")
            val level: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("nid")
            val nid: Int // 0
        )

        data class OfficialVerify(
            @SerializedName("desc")
            val desc: String, // 明日方舟官方账号
            @SerializedName("type")
            val type: Int // 1
        )

        data class Pendant(
            @SerializedName("expire")
            val expire: Int, // 0
            @SerializedName("image")
            val image: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("pid")
            val pid: Int // 0
        )

        data class Vip(
            @SerializedName("avatar_subscript")
            val avatarSubscript: Int, // 1
            @SerializedName("due_date")
            val dueDate: Int, // 0
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

    data class Category(
        @SerializedName("id")
        val id: Int, // 1
        @SerializedName("name")
        val name: String, // 游戏
        @SerializedName("parent_id")
        val parentId: Int // 0
    )

    data class Dispute(
        @SerializedName("dispute")
        val dispute: String,
        @SerializedName("dispute_url")
        val disputeUrl: String
    )

    data class Item(
        @SerializedName("at_control")
        val atControl: String
    )

    data class Media(
        @SerializedName("area")
        val area: String,
        @SerializedName("cover")
        val cover: String,
        @SerializedName("media_id")
        val mediaId: Int, // 0
        @SerializedName("score")
        val score: Int, // 0
        @SerializedName("season_id")
        val seasonId: Int, // 0
        @SerializedName("spoiler")
        val spoiler: Int, // 0
        @SerializedName("title")
        val title: String,
        @SerializedName("type_id")
        val typeId: Int, // 0
        @SerializedName("type_name")
        val typeName: String
    )

    data class Stats(
        @SerializedName("coin")
        val coin: Int, // 391
        @SerializedName("dislike")
        val dislike: Int, // 0
        @SerializedName("dynamic")
        val `dynamic`: Int, // 0
        @SerializedName("favorite")
        val favorite: Int, // 781
        @SerializedName("like")
        val like: Int, // 22785
        @SerializedName("reply")
        val reply: Int, // 2513
        @SerializedName("share")
        val share: Int, // 2023
        @SerializedName("view")
        val view: Int // 273448
    )
}