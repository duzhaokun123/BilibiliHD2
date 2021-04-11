package com.hiczp.bilibili.api.app.model


import com.google.gson.annotations.SerializedName

data class History(
        @SerializedName("code")
        var code: Int, // 0
        @SerializedName("data")
        var `data`: Data,
        @SerializedName("message")
        var message: String, // 0
        @SerializedName("ttl")
        var ttl: Int // 1
) {
    data class Data(
            @SerializedName("cursor")
            var cursor: Cursor?,
            @SerializedName("list")
            var list: List<List_>,
            @SerializedName("tab")
            var tab: List<Tab>
    ) {
        data class Cursor(
                @SerializedName("max")
                var max: Long, // 1602136752
                @SerializedName("max_tp")
                var maxTp: Int, // 3
                @SerializedName("ps")
                var ps: Int // 20
        )

        data class List_(
                @SerializedName("badge")
                var badge: String, // 番剧
                @SerializedName("cover")
                var cover: String?, // http://i2.hdslb.com/bfs/archive/b250b86b4c89cb8641803a3bf571492c4262b486.jpg
                @SerializedName("covers")
                var covers: List<String>?,
                @SerializedName("display_attention")
                var displayAttention: Int, // 1
                @SerializedName("duration")
                var duration: Int?, // 66
                @SerializedName("goto")
                var goto: String, // av
                @SerializedName("history")
                var history: History,
                @SerializedName("mid")
                var mid: Int, // 2177677
                @SerializedName("name")
                var name: String, // 出格字幕组
                @SerializedName("progress")
                var progress: Int?, // 456
                @SerializedName("show_title")
                var showTitle: String, // 第1话 结婚
                @SerializedName("title")
                var title: String, // 【氰化欢乐秀】人生：OP君的独白 @一只出格君
                @SerializedName("uri")
                var uri: String, // bilibili://video/797476381?player_width=1920&player_height=1080&player_rotate=0
                @SerializedName("videos")
                var videos: Int, // 1
                @SerializedName("view_at")
                var viewAt: Int // 1602353269
        ) {
            data class History(
                    @SerializedName("business")
                    var business: String, // archive
                    @SerializedName("cid")
                    var cid: Int, // 243776175
                    @SerializedName("oid")
                    var oid: Int, // 797476381
                    @SerializedName("page")
                    var page: Int, // 1
                    @SerializedName("part")
                    var part: String, // 人参的杯具
                    @SerializedName("tp")
                    var tp: Int // 3
            )
        }

        data class Tab(
                @SerializedName("business")
                var business: String, // all
                @SerializedName("name")
                var name: String, // 全部
                @SerializedName("router")
                var router: String
        )
    }
}