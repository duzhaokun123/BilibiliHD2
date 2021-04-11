package com.hiczp.bilibili.api.main.model


import com.google.gson.annotations.SerializedName

data class ResourceInfos(
    @SerializedName("code")
    var code: Int, // 0
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("message")
    var message: String // success
) {
    data class Data(
        @SerializedName("attr")
        var attr: Int, // 0
        @SerializedName("bv_id")
        var bvId: String, // BV1P54y1Q7fW
        @SerializedName("bvid")
        var bvid: String, // BV1P54y1Q7fW
        @SerializedName("cnt_info")
        var cntInfo: CntInfo,
        @SerializedName("coin")
        var coin: Coin,
        @SerializedName("copyright")
        var copyright: Int, // 1
        @SerializedName("cover")
        var cover: String, // http://i1.hdslb.com/bfs/archive/3826df68adeb2e155518431b5ece3fd25f8b8b1e.jpg
        @SerializedName("ctime")
        var ctime: Int, // 1590315141
        @SerializedName("dimension")
        var dimension: Dimension,
        @SerializedName("duration")
        var duration: Int, // 130
        @SerializedName("elec_open")
        var elecOpen: Int, // 0
        @SerializedName("fav_state")
        var favState: Int, // 0
        @SerializedName("id")
        var id: Int, // 840842106
        @SerializedName("intro")
        var intro: String, // 默墨汉化是一个自动汉化漫画的工具，具体使用步骤和效果看视频。如果本视频的收藏点赞投币三连数到达3000，这个软件将会公开放出。到时软件将会在内测群971854612的群文件提供内测，然后在此视频置顶评论中提供下载地址。
        @SerializedName("like_state")
        var likeState: Int, // 0
        @SerializedName("link")
        var link: String, // bilibili://video/840842106
        @SerializedName("page")
        var page: Int, // 1
        @SerializedName("pages")
        var pages: List<Page>,
        @SerializedName("pubtime")
        var pubtime: Int, // 1590315141
        @SerializedName("rights")
        var rights: Rights,
        @SerializedName("short_link")
        var shortLink: String, // https://b23.tv/BV1P54y1Q7fW
        @SerializedName("tid")
        var tid: Int, // 122
        @SerializedName("title")
        var title: String, // 【自动汉化漫画的工具-默墨汉化】
        @SerializedName("type")
        var type: Int, // 2
        @SerializedName("upper")
        var upper: Upper
    ) {
        data class CntInfo(
            @SerializedName("coin")
            var coin: Int, // 6250
            @SerializedName("collect")
            var collect: Int, // 3948
            @SerializedName("danmaku")
            var danmaku: Int, // 41
            @SerializedName("play")
            var play: Int, // 29399
            @SerializedName("reply")
            var reply: Int, // 363
            @SerializedName("share")
            var share: Int, // 320
            @SerializedName("thumb_down")
            var thumbDown: Int, // 0
            @SerializedName("thumb_up")
            var thumbUp: Int // 6004
        )

        data class Coin(
            @SerializedName("coin_number")
            var coinNumber: Int, // 0
            @SerializedName("max_num")
            var maxNum: Int // 2
        )

        data class Dimension(
            @SerializedName("height")
            var height: Int, // 1056
            @SerializedName("rotate")
            var rotate: Int, // 0
            @SerializedName("width")
            var width: Int // 1920
        )

        data class Page(
            @SerializedName("dimension")
            var dimension: Dimension,
            @SerializedName("duration")
            var duration: Int, // 130
            @SerializedName("from")
            var from: String, // vupload
            @SerializedName("id")
            var id: Int, // 194704297
            @SerializedName("metas")
            var metas: List<Meta>,
            @SerializedName("page")
            var page: Int, // 1
            @SerializedName("title")
            var title: String // 自动汉化漫画的工具-默墨汉化
        ) {
            data class Dimension(
                @SerializedName("height")
                var height: Int, // 1056
                @SerializedName("rotate")
                var rotate: Int, // 0
                @SerializedName("width")
                var width: Int // 1920
            )

            data class Meta(
                @SerializedName("quality")
                var quality: Int, // 15
                @SerializedName("size")
                var size: Int // 8294
            )
        }

        data class Rights(
            @SerializedName("autoplay")
            var autoplay: Int, // 1
            @SerializedName("bp")
            var bp: Int, // 0
            @SerializedName("download")
            var download: Int, // 0
            @SerializedName("elec")
            var elec: Int, // 0
            @SerializedName("hd5")
            var hd5: Int, // 0
            @SerializedName("movie")
            var movie: Int, // 0
            @SerializedName("no_background")
            var noBackground: Int, // 0
            @SerializedName("no_reprint")
            var noReprint: Int, // 1
            @SerializedName("pay")
            var pay: Int, // 0
            @SerializedName("ugc_pay")
            var ugcPay: Int // 0
        )

        data class Upper(
            @SerializedName("face")
            var face: String, // http://i2.hdslb.com/bfs/face/f91d6af83fe95577cdfc963e614a2f24ebd634d8.jpg
            @SerializedName("followed")
            var followed: Int, // 0
            @SerializedName("mid")
            var mid: Long, // 2329324
            @SerializedName("name")
            var name: String, // 墨问非名
            @SerializedName("vip_due_date")
            var vipDueDate: Long, // 1620921600000
            @SerializedName("vip_pay_type")
            var vipPayType: Int, // 0
            @SerializedName("vip_statue")
            var vipStatue: Int, // 1
            @SerializedName("vip_type")
            var vipType: Int // 2
        )
    }
}