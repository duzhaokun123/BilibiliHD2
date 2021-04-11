package com.hiczp.bilibili.api.vc.model


import com.google.gson.annotations.SerializedName

data class DynamicHistory(
    @SerializedName("code")
    val code: Int, // 0
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("msg")
    val msg: String
) {
    data class Data(
        @SerializedName("cards")
        val cards: List<Card>,
        @SerializedName("_gt_")
        val gt: Int, // 0
        @SerializedName("has_more")
        val hasMore: Int, // 1
        @SerializedName("mix_light_types")
        val mixLightTypes: String, // 2,8_1
        @SerializedName("next_offset")
        val nextOffset: Long, // 509820022563732837
        @SerializedName("open_rcmd")
        val openRcmd: Int // 1
    ) {
        data class Card(
            @SerializedName("card")
            val card: String, // {"item":{"id":122282852,"title":"","description":"在YouTube的评论里\n\n＿人人人人人人人人＿\n＞ 　　　　　　　  ＜\n￣Y^Y^Y^Y^Y^Y^Y￣\n\n有很多像这样的东西所以试着模仿了。\n用得对吗？","category":"daily","role":[],"source":[],"pictures":[{"img_src":"https://i0.hdslb.com/bfs/album/a86a15d2bbc212468410a3de952ea629a6e77f28.jpg","img_width":946,"img_height":2048,"img_size":359.666015625,"img_tags":null}],"pictures_count":1,"upload_time":1617710425,"at_control":"","reply":180,"settings":{"copy_forbidden":"0"},"is_fav":0},"user":{"uid":387994725,"head_url":"https://i0.hdslb.com/bfs/face/9767b6348454459c17819201ee10a789fc175058.jpg","name":"Mafumafu_Channel","vip":{"type":2,"status":1,"due_date":1622563200000,"vip_pay_type":0,"theme_type":0,"label":{"path":"","text":"年度大会员","label_theme":"annual_vip"},"avatar_subscript":1,"nickname_color":"#FB7299"}}}
            @SerializedName("desc")
            val desc: Desc,
            @SerializedName("display")
            val display: Display,
            @SerializedName("extend_json")
            val extendJson: String // {"from":{"emoji_type":1,"from":"","up_close_comment":0,"verify":{"asw":{"fl":15,"nv":1},"cc":{"nv":1},"sw":{"fl":15,"nv":1}}},"like_icon":{"action":"","action_url":"","end":"","end_url":"","start":"","start_url":""}}
        ) {
            data class Desc(
                @SerializedName("acl")
                val acl: Int, // 0
                @SerializedName("bvid")
                val bvid: String, // BV1Bh411D7cB
                @SerializedName("comment")
                val comment: Int, // 180
                @SerializedName("dynamic_id")
                val dynamicId: Long, // 510551459790097073
                @SerializedName("dynamic_id_str")
                val dynamicIdStr: String, // 510551459790097073
                @SerializedName("inner_id")
                val innerId: Int, // 0
                @SerializedName("is_liked")
                val isLiked: Int, // 0
                @SerializedName("like")
                val like: Int, // 5862
                @SerializedName("orig_dy_id")
                val origDyId: Long, // 0
                @SerializedName("orig_dy_id_str")
                val origDyIdStr: String, // 0
                @SerializedName("orig_type")
                val origType: Int, // 0
                @SerializedName("origin")
                val origin: Origin,
                @SerializedName("pre_dy_id")
                val preDyId: Long, // 0
                @SerializedName("pre_dy_id_str")
                val preDyIdStr: String, // 0
                @SerializedName("r_type")
                val rType: Int, // 1
                @SerializedName("repost")
                val repost: Int, // 29
                @SerializedName("rid")
                val rid: Long, // 122282852
                @SerializedName("rid_str")
                val ridStr: String, // 122282852
                @SerializedName("status")
                val status: Int, // 1
                @SerializedName("stype")
                val stype: Int, // 0
                @SerializedName("timestamp")
                val timestamp: Long, // 1617710425
                @SerializedName("type")
                val type: Int, // 2
                @SerializedName("uid")
                val uid: Int, // 387994725
                @SerializedName("uid_type")
                val uidType: Int, // 1
                @SerializedName("user_profile")
                val userProfile: UserProfile?,
                @SerializedName("view")
                val view: Int // 208218
            ) {
                data class Origin(
                    @SerializedName("acl")
                    val acl: Int, // 0
                    @SerializedName("bvid")
                    val bvid: String, // BV1Hi4y1N7RA
                    @SerializedName("dynamic_id")
                    val dynamicId: Long, // 509458321889091230
                    @SerializedName("dynamic_id_str")
                    val dynamicIdStr: String, // 509458321889091230
                    @SerializedName("inner_id")
                    val innerId: Int, // 0
                    @SerializedName("like")
                    val like: Int, // 0
                    @SerializedName("orig_dy_id")
                    val origDyId: Int, // 0
                    @SerializedName("orig_dy_id_str")
                    val origDyIdStr: String, // 0
                    @SerializedName("pre_dy_id")
                    val preDyId: Int, // 0
                    @SerializedName("pre_dy_id_str")
                    val preDyIdStr: String, // 0
                    @SerializedName("r_type")
                    val rType: Int, // 1
                    @SerializedName("repost")
                    val repost: Int, // 6
                    @SerializedName("rid")
                    val rid: Int, // 544981898
                    @SerializedName("rid_str")
                    val ridStr: String, // 544981898
                    @SerializedName("status")
                    val status: Int, // 1
                    @SerializedName("stype")
                    val stype: Int, // 0
                    @SerializedName("timestamp")
                    val timestamp: Int, // 1617455909
                    @SerializedName("type")
                    val type: Int, // 8
                    @SerializedName("uid")
                    val uid: Int, // 131559248
                    @SerializedName("uid_type")
                    val uidType: Int, // 1
                    @SerializedName("view")
                    val view: Int // 70458
                )

                data class UserProfile(
                    @SerializedName("card")
                    val card: Card,
                    @SerializedName("decorate_card")
                    val decorateCard: DecorateCard,
                    @SerializedName("info")
                    val info: Info,
                    @SerializedName("level_info")
                    val levelInfo: LevelInfo,
                    @SerializedName("pendant")
                    val pendant: Pendant,
                    @SerializedName("rank")
                    val rank: String, // 10000
                    @SerializedName("sign")
                    val sign: String, // 我是个多面手。睡觉 / 歌曲 / 作词作曲 / 编曲 我在网上做音乐。 也就是“歌手(UTAITE)”。
                    @SerializedName("vip")
                    val vip: Vip
                ) {
                    data class Card(
                        @SerializedName("official_verify")
                        val officialVerify: OfficialVerify
                    ) {
                        data class OfficialVerify(
                            @SerializedName("desc")
                            val desc: String, // Mafumafu官方账号
                            @SerializedName("type")
                            val type: Int // 0
                        )
                    }

                    data class DecorateCard(
                        @SerializedName("big_card_url")
                        val bigCardUrl: String, // https://i0.hdslb.com/bfs/garb/item/5298a58563b5f8b469aeda359a7bfdefbaf1c286.png
                        @SerializedName("card_type")
                        val cardType: Int, // 2
                        @SerializedName("card_type_name")
                        val cardTypeName: String, // 免费
                        @SerializedName("card_url")
                        val cardUrl: String, // https://i0.hdslb.com/bfs/garb/item/5298a58563b5f8b469aeda359a7bfdefbaf1c286.png
                        @SerializedName("expire_time")
                        val expireTime: Int, // 0
                        @SerializedName("fan")
                        val fan: Fan,
                        @SerializedName("id")
                        val id: Int, // 1992
                        @SerializedName("image_enhance")
                        val imageEnhance: String, // https://i0.hdslb.com/bfs/garb/item/5298a58563b5f8b469aeda359a7bfdefbaf1c286.png
                        @SerializedName("item_id")
                        val itemId: Int, // 1992
                        @SerializedName("item_type")
                        val itemType: Int, // 3
                        @SerializedName("jump_url")
                        val jumpUrl: String, // https://www.bilibili.com/h5/mall/fans/recommend/2010?navhide=1&mid=161775300&from=dynamic
                        @SerializedName("mid")
                        val mid: Int, // 161775300
                        @SerializedName("name")
                        val name: String, // 明日方舟粉丝专属
                        @SerializedName("uid")
                        val uid: Int // 161775300
                    ) {
                        data class Fan(
                            @SerializedName("color")
                            val color: String, // #006cff
                            @SerializedName("is_fan")
                            val isFan: Int, // 1
                            @SerializedName("num_desc")
                            val numDesc: String, // 000001
                            @SerializedName("number")
                            val number: Int // 1
                        )
                    }

                    data class Info(
                        @SerializedName("face")
                        val face: String, // https://i0.hdslb.com/bfs/face/9767b6348454459c17819201ee10a789fc175058.jpg
                        @SerializedName("uid")
                        val uid: Long, // 387994725
                        @SerializedName("uname")
                        val uname: String // Mafumafu_Channel
                    )

                    data class LevelInfo(
                        @SerializedName("current_level")
                        val currentLevel: Int // 6
                    )

                    data class Pendant(
                        @SerializedName("expire")
                        val expire: Int, // 0
                        @SerializedName("image")
                        val image: String,
                        @SerializedName("image_enhance")
                        val imageEnhance: String,
                        @SerializedName("image_enhance_frame")
                        val imageEnhanceFrame: String,
                        @SerializedName("name")
                        val name: String,
                        @SerializedName("pid")
                        val pid: Int // 0
                    )

                    data class Vip(
                        @SerializedName("avatar_subscript")
                        val avatarSubscript: Int, // 1
                        @SerializedName("avatar_subscript_url")
                        val avatarSubscriptUrl: String, // https://i0.hdslb.com/bfs/vip/icon_Certification_big_member_22_3x.png
                        @SerializedName("label")
                        val label: Label,
                        @SerializedName("nickname_color")
                        val nicknameColor: String, // #FB7299
                        @SerializedName("role")
                        val role: Int, // 3
                        @SerializedName("themeType")
                        val themeType: Int, // 0
                        @SerializedName("vipDueDate")
                        val vipDueDate: Long, // 1622563200000
                        @SerializedName("vipStatus")
                        val vipStatus: Int, // 1
                        @SerializedName("vipType")
                        val vipType: Int // 2
                    ) {
                        data class Label(
                            @SerializedName("bg_color")
                            val bgColor: String, // #FB7299
                            @SerializedName("bg_style")
                            val bgStyle: Int, // 1
                            @SerializedName("border_color")
                            val borderColor: String,
                            @SerializedName("label_theme")
                            val labelTheme: String, // annual_vip
                            @SerializedName("path")
                            val path: String,
                            @SerializedName("text")
                            val text: String, // 年度大会员
                            @SerializedName("text_color")
                            val textColor: String // #FFFFFF
                        )
                    }
                }
            }

            data class Display(
                @SerializedName("add_on_card_info")
                val addOnCardInfo: List<AddOnCardInfo>,
                @SerializedName("attach_card")
                val attachCard: AttachCard,
                @SerializedName("comment_info")
                val commentInfo: CommentInfo,
                @SerializedName("cover_play_icon_url")
                val coverPlayIconUrl: String, // https://i0.hdslb.com/bfs/album/2269afa7897830b397797ebe5f032b899b405c67.png
                @SerializedName("emoji_info")
                val emojiInfo: EmojiInfo,
                @SerializedName("like_info")
                val likeInfo: LikeInfo,
                @SerializedName("origin")
                val origin: Origin,
                @SerializedName("relation")
                val relation: Relation,
                @SerializedName("show_tip")
                val showTip: ShowTip,
                @SerializedName("topic_info")
                val topicInfo: TopicInfo,
                @SerializedName("usr_action_txt")
                val usrActionTxt: String // 投稿了视频
            ) {
                data class AddOnCardInfo(
                    @SerializedName("add_on_card_show_type")
                    val addOnCardShowType: Int, // 2
                    @SerializedName("attach_card")
                    val attachCard: AttachCard
                ) {
                    data class AttachCard(
                        @SerializedName("button")
                        val button: Button,
                        @SerializedName("cover_type")
                        val coverType: Int, // 1
                        @SerializedName("cover_url")
                        val coverUrl: String, // https://i0.hdslb.com/bfs/game/debad5150aa1aeb27dd3bcbac4588c54dc7b5d10.png
                        @SerializedName("desc_first")
                        val descFirst: String, // 策略/养成/剧情
                        @SerializedName("desc_second")
                        val descSecond: String, // 1572.6万+热度
                        @SerializedName("head_text")
                        val headText: String, // 相关游戏
                        @SerializedName("jump_url")
                        val jumpUrl: String, // https://www.biligame.com/detail?id=101772&sourceFrom=1005
                        @SerializedName("oid_str")
                        val oidStr: String, // 101772
                        @SerializedName("title")
                        val title: String, // 明日方舟
                        @SerializedName("type")
                        val type: String // game
                    ) {
                        data class Button(
                            @SerializedName("jump_style")
                            val jumpStyle: JumpStyle,
                            @SerializedName("jump_url")
                            val jumpUrl: String, // https://www.biligame.com/detail?id=101772&sourceFrom=1005
                            @SerializedName("type")
                            val type: Int // 1
                        ) {
                            data class JumpStyle(
                                @SerializedName("text")
                                val text: String // 进入
                            )
                        }
                    }
                }

                data class AttachCard(
                    @SerializedName("button")
                    val button: Button,
                    @SerializedName("cover_type")
                    val coverType: Int, // 1
                    @SerializedName("cover_url")
                    val coverUrl: String, // https://i0.hdslb.com/bfs/game/debad5150aa1aeb27dd3bcbac4588c54dc7b5d10.png
                    @SerializedName("desc_first")
                    val descFirst: String, // 策略/养成/剧情
                    @SerializedName("desc_second")
                    val descSecond: String, // 1572.6万+热度
                    @SerializedName("head_text")
                    val headText: String, // 相关游戏
                    @SerializedName("jump_url")
                    val jumpUrl: String, // https://www.biligame.com/detail?id=101772&sourceFrom=1005
                    @SerializedName("oid_str")
                    val oidStr: String, // 101772
                    @SerializedName("title")
                    val title: String, // 明日方舟
                    @SerializedName("type")
                    val type: String // game
                ) {
                    data class Button(
                        @SerializedName("jump_style")
                        val jumpStyle: JumpStyle,
                        @SerializedName("jump_url")
                        val jumpUrl: String, // https://www.biligame.com/detail?id=101772&sourceFrom=1005
                        @SerializedName("type")
                        val type: Int // 1
                    ) {
                        data class JumpStyle(
                            @SerializedName("text")
                            val text: String // 进入
                        )
                    }
                }

                data class CommentInfo(
                    @SerializedName("comments")
                    val comments: List<Comment>,
                    @SerializedName("emojis")
                    val emojis: List<Emoji>
                ) {
                    data class Comment(
                        @SerializedName("content")
                        val content: String, // ↓同意压轴节目是海猫怒号光明的人
                        @SerializedName("name")
                        val name: String, // 七铭的鲁迪乌斯
                        @SerializedName("uid")
                        val uid: Int // 186732414
                    )

                    data class Emoji(
                        @SerializedName("emoji_name")
                        val emojiName: String, // [doge]
                        @SerializedName("meta")
                        val meta: Meta,
                        @SerializedName("url")
                        val url: String // https://i0.hdslb.com/bfs/emote/3087d273a78ccaff4bb1e9972e2ba2a7583c9f11.png
                    ) {
                        data class Meta(
                            @SerializedName("size")
                            val size: Int // 1
                        )
                    }
                }

                data class EmojiInfo(
                    @SerializedName("emoji_details")
                    val emojiDetails: List<EmojiDetail>
                ) {
                    data class EmojiDetail(
                        @SerializedName("attr")
                        val attr: Int, // 0
                        @SerializedName("emoji_name")
                        val emojiName: String, // [哦呼]
                        @SerializedName("id")
                        val id: Int, // 1940
                        @SerializedName("meta")
                        val meta: Meta,
                        @SerializedName("mtime")
                        val mtime: Int, // 1597738918
                        @SerializedName("package_id")
                        val packageId: Int, // 1
                        @SerializedName("state")
                        val state: Int, // 0
                        @SerializedName("text")
                        val text: String, // [哦呼]
                        @SerializedName("type")
                        val type: Int, // 1
                        @SerializedName("url")
                        val url: String // https://i0.hdslb.com/bfs/emote/362bded07ea5434886271d23fa25f5d85d8af06c.png
                    ) {
                        data class Meta(
                            @SerializedName("size")
                            val size: Int // 1
                        )
                    }
                }

                data class LikeInfo(
                    @SerializedName("display_text")
                    val displayText: String, // 赞了
                    @SerializedName("like_users")
                    val likeUsers: List<LikeUser>
                ) {
                    data class LikeUser(
                        @SerializedName("uid")
                        val uid: Int, // 13839671
                        @SerializedName("uname")
                        val uname: String // uni_JZ-LSX
                    )
                }

                data class Origin(
                    @SerializedName("cover_play_icon_url")
                    val coverPlayIconUrl: String, // https://i0.hdslb.com/bfs/album/2269afa7897830b397797ebe5f032b899b405c67.png
                    @SerializedName("relation")
                    val relation: Relation,
                    @SerializedName("show_tip")
                    val showTip: ShowTip,
                    @SerializedName("topic_info")
                    val topicInfo: TopicInfo,
                    @SerializedName("usr_action_txt")
                    val usrActionTxt: String // 投稿了视频
                ) {
                    data class Relation(
                        @SerializedName("is_follow")
                        val isFollow: Int, // 0
                        @SerializedName("is_followed")
                        val isFollowed: Int, // 0
                        @SerializedName("status")
                        val status: Int // 1
                    )

                    data class ShowTip(
                        @SerializedName("del_tip")
                        val delTip: String // 要删除动态吗？
                    )

                    data class TopicInfo(
                        @SerializedName("topic_details")
                        val topicDetails: List<TopicDetail>
                    ) {
                        data class TopicDetail(
                            @SerializedName("is_activity")
                            val isActivity: Int, // 0
                            @SerializedName("topic_id")
                            val topicId: Int, // 9167687
                            @SerializedName("topic_link")
                            val topicLink: String, // https://www.bilibili.com/tag/9167687/feed
                            @SerializedName("topic_name")
                            val topicName: String // 犬山玉姬
                        )
                    }
                }

                data class Relation(
                    @SerializedName("is_follow")
                    val isFollow: Int, // 1
                    @SerializedName("is_followed")
                    val isFollowed: Int, // 0
                    @SerializedName("status")
                    val status: Int // 2
                )

                data class ShowTip(
                    @SerializedName("del_tip")
                    val delTip: String // 要删除动态吗？
                )

                data class TopicInfo(
                    @SerializedName("topic_details")
                    val topicDetails: List<TopicDetail>
                ) {
                    data class TopicDetail(
                        @SerializedName("is_activity")
                        val isActivity: Int, // 0
                        @SerializedName("topic_id")
                        val topicId: Int, // 4610466
                        @SerializedName("topic_link")
                        val topicLink: String, // https://www.bilibili.com/tag/4610466/feed
                        @SerializedName("topic_name")
                        val topicName: String // 明日方舟
                    )
                }
            }
        }
    }
}