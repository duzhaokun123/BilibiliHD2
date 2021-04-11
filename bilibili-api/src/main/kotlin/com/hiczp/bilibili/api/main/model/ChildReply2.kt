package com.hiczp.bilibili.api.main.model


import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class ChildReply2(
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
        @SerializedName("assist")
        var assist: Int, // 0
        @SerializedName("blacklist")
        var blacklist: Int, // 0
        @SerializedName("config")
        var config: Config,
        @SerializedName("control")
        var control: Control,
        @SerializedName("cursor")
        var cursor: Cursor,
        @SerializedName("lottery")
        var lottery: Int, // 0
        @SerializedName("Mid")
        var mid: Int, // 0
        @SerializedName("root")
        var root: Root,
        @SerializedName("show_bvid")
        var showBvid: Boolean, // true
        @SerializedName("upper")
        var upper: Upper,
        @SerializedName("vote")
        var vote: Int // 0
    ) {
        data class Config(
            @SerializedName("read_only")
            var readOnly: Boolean, // false
            @SerializedName("show_del_log")
            var showDelLog: Boolean, // true
            @SerializedName("show_up_flag")
            var showUpFlag: Boolean, // true
            @SerializedName("showadmin")
            var showadmin: Int, // 1
            @SerializedName("showentry")
            var showentry: Int, // 1
            @SerializedName("showfloor")
            var showfloor: Int, // 0
            @SerializedName("showtopic")
            var showtopic: Int // 1
        )

        data class Control(
            @SerializedName("answer_guide_android_url")
            var answerGuideAndroidUrl: String, // https://www.bilibili.com/h5/newbie/entry?navhide=1&re_src=6
            @SerializedName("answer_guide_icon_url")
            var answerGuideIconUrl: String, // http://i0.hdslb.com/bfs/emote/96940d16602cacbbac796245b7bb99fa9b5c970c.png
            @SerializedName("answer_guide_ios_url")
            var answerGuideIosUrl: String, // https://www.bilibili.com/h5/newbie/entry?navhide=1&re_src=12
            @SerializedName("answer_guide_text")
            var answerGuideText: String, // 需要升级成为lv2会员后才可以评论，先去答题转正吧！
            @SerializedName("bg_text")
            var bgText: String, // 看看下面~来发评论吧
            @SerializedName("child_input_text")
            var childInputText: String,
            @SerializedName("input_disable")
            var inputDisable: Boolean, // false
            @SerializedName("root_input_text")
            var rootInputText: String, // 发条友善的评论
            @SerializedName("web_selection")
            var webSelection: Boolean // false
        )

        data class Cursor(
            @SerializedName("is_begin")
            var isBegin: Boolean, // true
            @SerializedName("is_end")
            var isEnd: Boolean, // false
            @SerializedName("next")
            var next: Long, // 6
            @SerializedName("prev")
            var prev: Int // 0
        )

        data class Root(
            @SerializedName("action")
            var action: Int, // 0
            @SerializedName("assist")
            var assist: Int, // 0
            @SerializedName("attr")
            var attr: Int, // 2
            @SerializedName("content")
            var content: Content,
            @SerializedName("count")
            var count: Int, // 5
            @SerializedName("ctime")
            var ctime: Int, // 1602253901
            @SerializedName("dialog")
            var dialog: Int, // 0
            @SerializedName("fansgrade")
            var fansgrade: Int, // 0
            @SerializedName("floor")
            var floor: Int, // 237
            @SerializedName("folder")
            var folder: Folder,
            @SerializedName("invisible")
            var invisible: Boolean, // false
            @SerializedName("like")
            var like: Int, // 225
            @SerializedName("member")
            var member: Member,
            @SerializedName("mid")
            var mid: Long, // 2177677
            @SerializedName("oid")
            var oid: Long, // 797476381
            @SerializedName("parent")
            var parent: Long, // 0
            @SerializedName("parent_str")
            var parentStr: String, // 0
            @SerializedName("rcount")
            var rcount: Int, // 5
            @SerializedName("replies")
            var replies: List<Reply>?,
            @SerializedName("root")
            var root: Int, // 0
            @SerializedName("root_str")
            var rootStr: String, // 0
            @SerializedName("rpid")
            var rpid: Long, // 3583209692
            @SerializedName("rpid_str")
            var rpidStr: String, // 3583209692
            @SerializedName("show_follow")
            var showFollow: Boolean, // true
            @SerializedName("state")
            var state: Int, // 0
            @SerializedName("type")
            var type: Int, // 1
            @SerializedName("up_action")
            var upAction: UpAction
        ) {
            data class Content(
                @SerializedName("device")
                var device: String, // phone
                @SerializedName("emote")
                var emote: JsonObject?,
                @SerializedName("jump_url")
                var jumpUrl: JsonObject,
                @SerializedName("max_line")
                var maxLine: Int, // 999
                @SerializedName("members")
                var members: List<JsonElement>,
                @SerializedName("message")
                var message: String, // 直播翻译全程点这里 BV1qa4y1L7GU 还是有一些英语知识点的[Cat_hightouch]热评第一已经总结了
                @SerializedName("plat")
                var plat: Int // 3
            )

            data class Folder(
                @SerializedName("has_folded")
                var hasFolded: Boolean, // false
                @SerializedName("is_folded")
                var isFolded: Boolean, // false
                @SerializedName("rule")
                var rule: String // https://www.bilibili.com/blackboard/foldingreply.html
            )

            data class Member(
                @SerializedName("avatar")
                var avatar: String, // http://i1.hdslb.com/bfs/face/73f6f268277b865c6d0069d2da7edea0249f8310.jpg
                @SerializedName("DisplayRank")
                var displayRank: String, // 0
                @SerializedName("fans_detail")
                var fansDetail: Any?, // null
                @SerializedName("following")
                var following: Int, // 1
                @SerializedName("is_followed")
                var isFollowed: Int, // 0
                @SerializedName("level_info")
                var levelInfo: LevelInfo,
                @SerializedName("mid")
                var mid: String, // 2177677
                @SerializedName("nameplate")
                var nameplate: Nameplate,
                @SerializedName("official_verify")
                var officialVerify: OfficialVerify,
                @SerializedName("pendant")
                var pendant: Pendant,
                @SerializedName("rank")
                var rank: String, // 10000
                @SerializedName("sex")
                var sex: String, // 男
                @SerializedName("sign")
                var sign: String, // 一个人的字幕组，微博@一只出格，哔哩哔哩私人号也是@一只出格君
                @SerializedName("uname")
                var uname: String, // 出格字幕组
                @SerializedName("user_sailing")
                var userSailing: UserSailing,
                @SerializedName("vip")
                var vip: Vip
            ) {
                data class LevelInfo(
                    @SerializedName("current_exp")
                    var currentExp: Int, // 0
                    @SerializedName("current_level")
                    var currentLevel: Int, // 6
                    @SerializedName("current_min")
                    var currentMin: Int, // 0
                    @SerializedName("next_exp")
                    var nextExp: Int // 0
                )

                data class Nameplate(
                    @SerializedName("condition")
                    var condition: String, // 所有自制视频总播放数>=100万
                    @SerializedName("image")
                    var image: String, // http://i0.hdslb.com/bfs/face/27a952195555e64508310e366b3e38bd4cd143fc.png
                    @SerializedName("image_small")
                    var imageSmall: String, // http://i0.hdslb.com/bfs/face/0497be49e08357bf05bca56e33a0637a273a7610.png
                    @SerializedName("level")
                    var level: String, // 稀有勋章
                    @SerializedName("name")
                    var name: String, // 知名偶像
                    @SerializedName("nid")
                    var nid: Int // 8
                )

                data class OfficialVerify(
                    @SerializedName("desc")
                    var desc: String, // bilibili 知名动漫UP主,专栏优质UP主
                    @SerializedName("type")
                    var type: Int // 0
                )

                data class Pendant(
                    @SerializedName("expire")
                    var expire: Int, // 0
                    @SerializedName("image")
                    var image: String, // http://i1.hdslb.com/bfs/garb/item/3a6053f073f979a776e02e088dd7dd7694c5b1f3.png
                    @SerializedName("image_enhance")
                    var imageEnhance: String, // http://i1.hdslb.com/bfs/garb/item/6c7f2ccb92627b11101dfbb616524845cac8f216.webp
                    @SerializedName("name")
                    var name: String, // #EveOneCat
                    @SerializedName("pid")
                    var pid: Int // 2360
                )

                data class UserSailing(
                    @SerializedName("cardbg")
                    var cardbg: Any?, // null
                    @SerializedName("cardbg_with_focus")
                    var cardbgWithFocus: Any?, // null
                    @SerializedName("pendant")
                    var pendant: Pendant
                ) {
                    data class Pendant(
                        @SerializedName("id")
                        var id: Int, // 2360
                        @SerializedName("image")
                        var image: String, // http://i0.hdslb.com/bfs/garb/item/3a6053f073f979a776e02e088dd7dd7694c5b1f3.png
                        @SerializedName("jump_url")
                        var jumpUrl: String,
                        @SerializedName("name")
                        var name: String, // #EveOneCat
                        @SerializedName("type")
                        var type: String // suit
                    )
                }

                data class Vip(
                    @SerializedName("accessStatus")
                    var accessStatus: Int, // 0
                    @SerializedName("dueRemark")
                    var dueRemark: String,
                    @SerializedName("label")
                    var label: Label,
                    @SerializedName("themeType")
                    var themeType: Int, // 0
                    @SerializedName("vipDueDate")
                    var vipDueDate: Long, // 1604678400000
                    @SerializedName("vipStatus")
                    var vipStatus: Int, // 1
                    @SerializedName("vipStatusWarn")
                    var vipStatusWarn: String,
                    @SerializedName("vipType")
                    var vipType: Int // 1
                ) {
                    data class Label(
                        @SerializedName("label_theme")
                        var labelTheme: String, // vip
                        @SerializedName("path")
                        var path: String,
                        @SerializedName("text")
                        var text: String // 大会员
                    )
                }
            }

            data class Reply(
                @SerializedName("action")
                var action: Int, // 0
                @SerializedName("assist")
                var assist: Int, // 0
                @SerializedName("attr")
                var attr: Int, // 0
                @SerializedName("content")
                var content: Content,
                @SerializedName("count")
                var count: Int, // 0
                @SerializedName("ctime")
                var ctime: Int, // 1602254563
                @SerializedName("dialog")
                var dialog: Long, // 3583267475
                @SerializedName("fansgrade")
                var fansgrade: Int, // 0
                @SerializedName("floor")
                var floor: Int, // 1
                @SerializedName("folder")
                var folder: Folder,
                @SerializedName("invisible")
                var invisible: Boolean, // false
                @SerializedName("like")
                var like: Int, // 0
                @SerializedName("member")
                var member: Member,
                @SerializedName("mid")
                var mid: Long, // 313037714
                @SerializedName("oid")
                var oid: Long, // 797476381
                @SerializedName("parent")
                var parent: Long, // 3583209692
                @SerializedName("parent_str")
                var parentStr: String, // 3583209692
                @SerializedName("rcount")
                var rcount: Int, // 0
                @SerializedName("replies")
                var replies: Any?, // null
                @SerializedName("root")
                var root: Long, // 3583209692
                @SerializedName("root_str")
                var rootStr: String, // 3583209692
                @SerializedName("rpid")
                var rpid: Long, // 3583267475
                @SerializedName("rpid_str")
                var rpidStr: String, // 3583267475
                @SerializedName("show_follow")
                var showFollow: Boolean, // false
                @SerializedName("state")
                var state: Int, // 0
                @SerializedName("type")
                var type: Int, // 1
                @SerializedName("up_action")
                var upAction: UpAction
            ) {
                data class Content(
                    @SerializedName("device")
                    var device: String,
                    @SerializedName("emote")
                    var emote: JsonObject?,
                    @SerializedName("jump_url")
                    var jumpUrl: JsonObject,
                    @SerializedName("max_line")
                    var maxLine: Int, // 999
                    @SerializedName("members")
                    var members: List<Member>,
                    @SerializedName("message")
                    var message: String, // F **K着实笑到我了
                    @SerializedName("plat")
                    var plat: Int // 2
                ) {
                    data class Member(
                        @SerializedName("avatar")
                        var avatar: String, // http://i2.hdslb.com/bfs/face/64134e5ece85e7b828b34dcb922d10891215b6ba.jpg
                        @SerializedName("DisplayRank")
                        var displayRank: String, // 0
                        @SerializedName("level_info")
                        var levelInfo: LevelInfo,
                        @SerializedName("mid")
                        var mid: String, // 486682136
                        @SerializedName("nameplate")
                        var nameplate: Nameplate,
                        @SerializedName("official_verify")
                        var officialVerify: OfficialVerify,
                        @SerializedName("pendant")
                        var pendant: Pendant,
                        @SerializedName("rank")
                        var rank: String, // 10000
                        @SerializedName("sex")
                        var sex: String, // 男
                        @SerializedName("sign")
                        var sign: String, // 拖更区up主。有意合作的朋友可以私信给我商用QQ：3136692004
                        @SerializedName("uname")
                        var uname: String, // 隔壁废柴王纸导
                        @SerializedName("vip")
                        var vip: Vip
                    ) {
                        data class LevelInfo(
                            @SerializedName("current_exp")
                            var currentExp: Int, // 0
                            @SerializedName("current_level")
                            var currentLevel: Int, // 3
                            @SerializedName("current_min")
                            var currentMin: Int, // 0
                            @SerializedName("next_exp")
                            var nextExp: Int // 0
                        )

                        data class Nameplate(
                            @SerializedName("condition")
                            var condition: String,
                            @SerializedName("image")
                            var image: String,
                            @SerializedName("image_small")
                            var imageSmall: String,
                            @SerializedName("level")
                            var level: String,
                            @SerializedName("name")
                            var name: String,
                            @SerializedName("nid")
                            var nid: Int // 0
                        )

                        data class OfficialVerify(
                            @SerializedName("desc")
                            var desc: String,
                            @SerializedName("type")
                            var type: Int // -1
                        )

                        data class Pendant(
                            @SerializedName("expire")
                            var expire: Int, // 0
                            @SerializedName("image")
                            var image: String,
                            @SerializedName("image_enhance")
                            var imageEnhance: String,
                            @SerializedName("name")
                            var name: String,
                            @SerializedName("pid")
                            var pid: Int // 0
                        )

                        data class Vip(
                            @SerializedName("accessStatus")
                            var accessStatus: Int, // 0
                            @SerializedName("dueRemark")
                            var dueRemark: String,
                            @SerializedName("label")
                            var label: Label,
                            @SerializedName("themeType")
                            var themeType: Int, // 0
                            @SerializedName("vipDueDate")
                            var vipDueDate: Long, // 1591891200000
                            @SerializedName("vipStatus")
                            var vipStatus: Int, // 0
                            @SerializedName("vipStatusWarn")
                            var vipStatusWarn: String,
                            @SerializedName("vipType")
                            var vipType: Int // 1
                        ) {
                            data class Label(
                                @SerializedName("label_theme")
                                var labelTheme: String,
                                @SerializedName("path")
                                var path: String,
                                @SerializedName("text")
                                var text: String
                            )
                        }
                    }
                }

                data class Folder(
                    @SerializedName("has_folded")
                    var hasFolded: Boolean, // false
                    @SerializedName("is_folded")
                    var isFolded: Boolean, // false
                    @SerializedName("rule")
                    var rule: String
                )

                data class Member(
                    @SerializedName("avatar")
                    var avatar: String, // http://i0.hdslb.com/bfs/face/489fb7eb641f13cdbafef719f6569a933d99b74f.jpg
                    @SerializedName("DisplayRank")
                    var displayRank: String, // 0
                    @SerializedName("fans_detail")
                    var fansDetail: Any?, // null
                    @SerializedName("following")
                    var following: Int, // 0
                    @SerializedName("is_followed")
                    var isFollowed: Int, // 0
                    @SerializedName("level_info")
                    var levelInfo: LevelInfo,
                    @SerializedName("mid")
                    var mid: String, // 313037714
                    @SerializedName("nameplate")
                    var nameplate: Nameplate,
                    @SerializedName("official_verify")
                    var officialVerify: OfficialVerify,
                    @SerializedName("pendant")
                    var pendant: Pendant,
                    @SerializedName("rank")
                    var rank: String, // 10000
                    @SerializedName("sex")
                    var sex: String, // 男
                    @SerializedName("sign")
                    var sign: String,
                    @SerializedName("uname")
                    var uname: String, // 退休老干部刘某人
                    @SerializedName("user_sailing")
                    var userSailing: UserSailing,
                    @SerializedName("vip")
                    var vip: Vip
                ) {
                    data class LevelInfo(
                        @SerializedName("current_exp")
                        var currentExp: Int, // 0
                        @SerializedName("current_level")
                        var currentLevel: Int, // 4
                        @SerializedName("current_min")
                        var currentMin: Int, // 0
                        @SerializedName("next_exp")
                        var nextExp: Int // 0
                    )

                    data class Nameplate(
                        @SerializedName("condition")
                        var condition: String,
                        @SerializedName("image")
                        var image: String,
                        @SerializedName("image_small")
                        var imageSmall: String,
                        @SerializedName("level")
                        var level: String,
                        @SerializedName("name")
                        var name: String,
                        @SerializedName("nid")
                        var nid: Int // 0
                    )

                    data class OfficialVerify(
                        @SerializedName("desc")
                        var desc: String,
                        @SerializedName("type")
                        var type: Int // -1
                    )

                    data class Pendant(
                        @SerializedName("expire")
                        var expire: Int, // 0
                        @SerializedName("image")
                        var image: String, // http://i2.hdslb.com/bfs/garb/item/0312f94397fa9de427b1dc0ecda22b0086fc7197.png
                        @SerializedName("image_enhance")
                        var imageEnhance: String, // http://i2.hdslb.com/bfs/garb/item/0312f94397fa9de427b1dc0ecda22b0086fc7197.png
                        @SerializedName("name")
                        var name: String, // 大理寺日志
                        @SerializedName("pid")
                        var pid: Int // 1638
                    )

                    data class UserSailing(
                        @SerializedName("cardbg")
                        var cardbg: Cardbg?,
                        @SerializedName("cardbg_with_focus")
                        var cardbgWithFocus: Any?, // null
                        @SerializedName("pendant")
                        var pendant: Pendant?
                    ) {
                        data class Cardbg(
                            @SerializedName("fan")
                            var fan: Fan,
                            @SerializedName("id")
                            var id: Int, // 1815
                            @SerializedName("image")
                            var image: String, // http://i0.hdslb.com/bfs/garb/item/8d0b08def82e44d4cdbd38cf83df327bbf23c1ff.png
                            @SerializedName("jump_url")
                            var jumpUrl: String, // https://www.bilibili.com/h5/mall/fans/recommend/1648?navhide=1&mid=313037714&from=reply
                            @SerializedName("name")
                            var name: String, // 大理寺日志
                            @SerializedName("type")
                            var type: String // suit
                        ) {
                            data class Fan(
                                @SerializedName("color")
                                var color: String, // #FF6633
                                @SerializedName("is_fan")
                                var isFan: Int, // 1
                                @SerializedName("name")
                                var name: String, // 大理寺日志
                                @SerializedName("num_desc")
                                var numDesc: String, // 002442
                                @SerializedName("number")
                                var number: Int // 2442
                            )
                        }

                        data class Pendant(
                            @SerializedName("id")
                            var id: Int, // 1638
                            @SerializedName("image")
                            var image: String, // http://i0.hdslb.com/bfs/garb/item/0312f94397fa9de427b1dc0ecda22b0086fc7197.png
                            @SerializedName("jump_url")
                            var jumpUrl: String,
                            @SerializedName("name")
                            var name: String, // 大理寺日志
                            @SerializedName("type")
                            var type: String // suit
                        )
                    }

                    data class Vip(
                        @SerializedName("accessStatus")
                        var accessStatus: Int, // 0
                        @SerializedName("dueRemark")
                        var dueRemark: String,
                        @SerializedName("label")
                        var label: Label,
                        @SerializedName("themeType")
                        var themeType: Int, // 0
                        @SerializedName("vipDueDate")
                        var vipDueDate: Long, // 1604678400000
                        @SerializedName("vipStatus")
                        var vipStatus: Int, // 1
                        @SerializedName("vipStatusWarn")
                        var vipStatusWarn: String,
                        @SerializedName("vipType")
                        var vipType: Int // 1
                    ) {
                        data class Label(
                            @SerializedName("label_theme")
                            var labelTheme: String, // vip
                            @SerializedName("path")
                            var path: String,
                            @SerializedName("text")
                            var text: String // 大会员
                        )
                    }
                }

                data class UpAction(
                    @SerializedName("like")
                    var like: Boolean, // false
                    @SerializedName("reply")
                    var reply: Boolean // false
                )
            }

            data class UpAction(
                @SerializedName("like")
                var like: Boolean, // false
                @SerializedName("reply")
                var reply: Boolean // false
            )
        }

        data class Upper(
            @SerializedName("mid")
            var mid: Long // 2177677
        )
    }
}