package com.hiczp.bilibili.api.web.model


import com.google.gson.annotations.SerializedName

data class EmoteList(
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
        @SerializedName("all_packages")
        val allPackages: List<AllPackage>,
        @SerializedName("mall")
        val mall: Mall,
        @SerializedName("user_panel_packages")
        val userPanelPackages: List<UserPanelPackage>
    ) {
        data class AllPackage(
            @SerializedName("attr")
            val attr: Int, // 284
            @SerializedName("emote")
            val emote: List<Emote>,
            @SerializedName("flags")
            val flags: Flags,
            @SerializedName("id")
            val id: Int, // 344
            @SerializedName("meta")
            val meta: Meta,
            @SerializedName("mtime")
            val mtime: Int, // 1644043660
            @SerializedName("text")
            val text: String, // 拜年纪2022
            @SerializedName("type")
            val type: Int, // 1
            @SerializedName("url")
            val url: String // http://i0.hdslb.com/bfs/emote/0c9f398d98136f77e2a1cbe6adeb2209a0c65b83.png
        ) {
            data class Emote(
                @SerializedName("activity")
                val activity: Any?, // null
                @SerializedName("attr")
                val attr: Int, // 0
                @SerializedName("flags")
                val flags: Flags,
                @SerializedName("gif_url")
                val gifUrl: String, // http://i0.hdslb.com/bfs/emote/48f75163437445665a9be80bb316e4cb252c5415.gif
                @SerializedName("id")
                val id: Int, // 5659
                @SerializedName("meta")
                val meta: Meta,
                @SerializedName("mtime")
                val mtime: Int, // 1642749381
                @SerializedName("package_id")
                val packageId: Int, // 344
                @SerializedName("text")
                val text: String, // [拜年纪2022_比心]
                @SerializedName("type")
                val type: Int, // 8
                @SerializedName("url")
                val url: String // http://i0.hdslb.com/bfs/emote/310ea639aee98b68178f2fc50aa54d7a97bf7d2f.png
            ) {
                data class Flags(
                    @SerializedName("unlocked")
                    val unlocked: Boolean // false
                )

                data class Meta(
                    @SerializedName("alias")
                    val alias: String, // 比心
                    @SerializedName("gif_url")
                    val gifUrl: String, // http://i0.hdslb.com/bfs/emote/48f75163437445665a9be80bb316e4cb252c5415.gif
                    @SerializedName("size")
                    val size: Int, // 2
                    @SerializedName("suggest")
                    val suggest: List<String>
                )
            }

            data class Flags(
                @SerializedName("added")
                val added: Boolean, // true
                @SerializedName("no_access")
                val noAccess: Boolean, // true
                @SerializedName("preview")
                val preview: Boolean // true
            )

            data class Meta(
                @SerializedName("item_id")
                val itemId: Int, // 33808
                @SerializedName("item_url")
                val itemUrl: String, // https://www.bilibili.com/h5/mall/suit/detail?navhide=1&stahide=0&from=reply&type=emoji&id=1062
                @SerializedName("size")
                val size: Int, // 2
                @SerializedName("vip_no_access_text")
                val vipNoAccessText: String // 开通大会员即可使用本套表情
            )
        }

        data class Mall(
            @SerializedName("title")
            val title: String, // 个性装扮中心
            @SerializedName("url")
            val url: String // https://www.bilibili.com/h5/mall/home?navhide=1&stahide=0
        )

        data class UserPanelPackage(
            @SerializedName("attr")
            val attr: Int, // 322
            @SerializedName("emote")
            val emote: List<Emote>,
            @SerializedName("flags")
            val flags: Flags,
            @SerializedName("id")
            val id: Int, // 1
            @SerializedName("meta")
            val meta: Meta,
            @SerializedName("mtime")
            val mtime: Int, // 1645292914
            @SerializedName("text")
            val text: String, // 小黄脸
            @SerializedName("type")
            val type: Int, // 1
            @SerializedName("url")
            val url: String // http://i0.hdslb.com/bfs/emote/940c4cf16e9d9dbc1c753b015013bfb772b25ec5.png
        ) {
            data class Emote(
                @SerializedName("activity")
                val activity: Any?, // null
                @SerializedName("attr")
                val attr: Int, // 0
                @SerializedName("flags")
                val flags: Flags,
                @SerializedName("gif_url")
                val gifUrl: String, // http://i0.hdslb.com/bfs/emote/48f75163437445665a9be80bb316e4cb252c5415.gif
                @SerializedName("id")
                val id: Int, // 5936
                @SerializedName("meta")
                val meta: Meta,
                @SerializedName("mtime")
                val mtime: Int, // 1644900361
                @SerializedName("package_id")
                val packageId: Int, // 1
                @SerializedName("text")
                val text: String, // [汤圆]
                @SerializedName("type")
                val type: Int, // 1
                @SerializedName("url")
                val url: String // http://i0.hdslb.com/bfs/emote/93609633a9d194cf336687eb19c01dca95bde719.png
            ) {
                data class Flags(
                    @SerializedName("unlocked")
                    val unlocked: Boolean // false
                )

                data class Meta(
                    @SerializedName("alias")
                    val alias: String, // 知识增加
                    @SerializedName("gif_url")
                    val gifUrl: String, // http://i0.hdslb.com/bfs/emote/48f75163437445665a9be80bb316e4cb252c5415.gif
                    @SerializedName("size")
                    val size: Int, // 1
                    @SerializedName("suggest")
                    val suggest: List<String>
                )
            }

            data class Flags(
                @SerializedName("added")
                val added: Boolean, // true
                @SerializedName("preview")
                val preview: Boolean // true
            )

            data class Meta(
                @SerializedName("item_id")
                val itemId: Int, // 958
                @SerializedName("size")
                val size: Int // 1
            )
        }
    }
}