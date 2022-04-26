package  com.hiczp.bilibili.api.vc.model
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom
@LazyjsonClass class DynamicHistory (private val jsonObject: JsonObject) {
    companion object   {
        @LazyjsonFrom @JvmStatic fun from(jsonObject: JsonObject)  = DynamicHistory(jsonObject)
    }
    fun getJsonObject()  = jsonObject
    override fun toString()  = jsonObject.toString()
     val code: Number
        get() = jsonObject.get("code").asNumber
     val msg: String
        get() = jsonObject.get("msg").asString
     val message: String
        get() = jsonObject.get("message").asString
     val data: Data
        get() = jsonObject.get("data").asData
    private val JsonElement.asData
        get() = Data(this.asJsonObject)
    class Data (private val jsonObject: JsonObject) {
        fun getJsonObject()  = jsonObject
        override fun toString()  = jsonObject.toString()
         val cards: List<Cards>
            get() = jsonObject.get("cards").asJsonArray.map { it.asCards }
         val hasMore: Number
            get() = jsonObject.get("has_more").asNumber
         val openRcmd: Number
            get() = jsonObject.get("open_rcmd").asNumber
         val nextOffset: Long
            get() = jsonObject.get("next_offset").asLong
         val Gt: Number
            get() = jsonObject.get("_gt_").asNumber
        private val JsonElement.asCards
            get() = Cards(this.asJsonObject)
        class Cards (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val desc: Desc
                get() = jsonObject.get("desc").asDesc
             val card: String
                get() = jsonObject.get("card").asString
             val extendJson: String
                get() = jsonObject.get("extend_json").asString
             val display: Display
                get() = jsonObject.get("display").asDisplay
             val extra: Extra?
                get() = jsonObject.get("extra")?.asExtra
            private val JsonElement.asDesc
                get() = Desc(this.asJsonObject)
            class Desc (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val uid: Number
                    get() = jsonObject.get("uid").asNumber
                 val type: Int
                    get() = jsonObject.get("type").asInt
                 val rid: Number
                    get() = jsonObject.get("rid").asNumber
                 val view: Int
                    get() = jsonObject.get("view").asInt
                 val repost: Int
                    get() = jsonObject.get("repost").asInt
                 val like: Int
                    get() = jsonObject.get("like").asInt
                 val isLiked: Number
                    get() = jsonObject.get("is_liked").asNumber
                 val dynamicId: Number
                    get() = jsonObject.get("dynamic_id").asNumber
                 val timestamp: Long
                    get() = jsonObject.get("timestamp").asLong
                 val origType: Number
                    get() = jsonObject.get("orig_type").asNumber
                 val userProfile: UserProfile?
                    get() = jsonObject.get("user_profile")?.asUserProfile
                 val uidType: Number
                    get() = jsonObject.get("uid_type").asNumber
                 val status: Number
                    get() = jsonObject.get("status").asNumber
                 val dynamicIdStr: String
                    get() = jsonObject.get("dynamic_id_str").asString
                 val preDyIdStr: String
                    get() = jsonObject.get("pre_dy_id_str").asString
                 val origDyIdStr: String
                    get() = jsonObject.get("orig_dy_id_str").asString
                 val ridStr: String
                    get() = jsonObject.get("rid_str").asString
                 val bvid: String?
                    get() = jsonObject.get("bvid")?.asString
                 val comment: Int?
                    get() = jsonObject.get("comment")?.asInt
                 val preDyId: Number?
                    get() = jsonObject.get("pre_dy_id")?.asNumber
                 val origDyId: Number?
                    get() = jsonObject.get("orig_dy_id")?.asNumber
                 val origin: Origin?
                    get() = jsonObject.get("origin")?.asOrigin
                 val acl: Number?
                    get() = jsonObject.get("acl")?.asNumber
                 val stype: Number?
                    get() = jsonObject.get("stype")?.asNumber
                 val rType: Number?
                    get() = jsonObject.get("r_type")?.asNumber
                 val innerId: Number?
                    get() = jsonObject.get("inner_id")?.asNumber
                private val JsonElement.asUserProfile
                    get() = UserProfile(this.asJsonObject)
                class UserProfile (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val info: Info
                        get() = jsonObject.get("info").asInfo
                     val card: Card
                        get() = jsonObject.get("card").asCard
                     val vip: Vip
                        get() = jsonObject.get("vip").asVip
                     val pendant: Pendant
                        get() = jsonObject.get("pendant").asPendant
                     val rank: String
                        get() = jsonObject.get("rank").asString
                     val sign: String
                        get() = jsonObject.get("sign").asString
                     val levelInfo: LevelInfo
                        get() = jsonObject.get("level_info").asLevelInfo
                     val decorateCard: DecorateCard?
                        get() = jsonObject.get("decorate_card")?.asDecorateCard
                    private val JsonElement.asInfo
                        get() = Info(this.asJsonObject)
                    class Info (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val uid: Long
                            get() = jsonObject.get("uid").asLong
                         val uname: String
                            get() = jsonObject.get("uname").asString
                         val face: String
                            get() = jsonObject.get("face").asString
                         val faceNft: Number
                            get() = jsonObject.get("face_nft").asNumber
                    }
                    private val JsonElement.asCard
                        get() = Card(this.asJsonObject)
                    class Card (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val officialVerify: OfficialVerify
                            get() = jsonObject.get("official_verify").asOfficialVerify
                        private val JsonElement.asOfficialVerify
                            get() = OfficialVerify(this.asJsonObject)
                        class OfficialVerify (private val jsonObject: JsonObject) {
                            fun getJsonObject()  = jsonObject
                            override fun toString()  = jsonObject.toString()
                             val type: Number
                                get() = jsonObject.get("type").asNumber
                             val desc: String
                                get() = jsonObject.get("desc").asString
                        }
                    }
                    private val JsonElement.asVip
                        get() = Vip(this.asJsonObject)
                    class Vip (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val vipType: Number
                            get() = jsonObject.get("vipType").asNumber
                         val vipDueDate: Number
                            get() = jsonObject.get("vipDueDate").asNumber
                         val vipStatus: Number
                            get() = jsonObject.get("vipStatus").asNumber
                         val themeType: Number
                            get() = jsonObject.get("themeType").asNumber
                         val label: Label
                            get() = jsonObject.get("label").asLabel
                         val avatarSubscript: Number
                            get() = jsonObject.get("avatar_subscript").asNumber
                         val nicknameColor: String
                            get() = jsonObject.get("nickname_color").asString
                         val role: Number
                            get() = jsonObject.get("role").asNumber
                         val avatarSubscriptUrl: String
                            get() = jsonObject.get("avatar_subscript_url").asString
                        private val JsonElement.asLabel
                            get() = Label(this.asJsonObject)
                        class Label (private val jsonObject: JsonObject) {
                            fun getJsonObject()  = jsonObject
                            override fun toString()  = jsonObject.toString()
                             val path: String
                                get() = jsonObject.get("path").asString
                             val text: String
                                get() = jsonObject.get("text").asString
                             val labelTheme: String
                                get() = jsonObject.get("label_theme").asString
                             val textColor: String
                                get() = jsonObject.get("text_color").asString
                             val bgStyle: Number
                                get() = jsonObject.get("bg_style").asNumber
                             val bgColor: String
                                get() = jsonObject.get("bg_color").asString
                             val borderColor: String
                                get() = jsonObject.get("border_color").asString
                        }
                    }
                    private val JsonElement.asPendant
                        get() = Pendant(this.asJsonObject)
                    class Pendant (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val pid: Number
                            get() = jsonObject.get("pid").asNumber
                         val name: String
                            get() = jsonObject.get("name").asString
                         val image: String
                            get() = jsonObject.get("image").asString
                         val expire: Number
                            get() = jsonObject.get("expire").asNumber
                         val imageEnhance: String
                            get() = jsonObject.get("image_enhance").asString
                         val imageEnhanceFrame: String
                            get() = jsonObject.get("image_enhance_frame").asString
                    }
                    private val JsonElement.asLevelInfo
                        get() = LevelInfo(this.asJsonObject)
                    class LevelInfo (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val currentLevel: Number
                            get() = jsonObject.get("current_level").asNumber
                    }
                    private val JsonElement.asDecorateCard
                        get() = DecorateCard(this.asJsonObject)
                    class DecorateCard (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val mid: Number
                            get() = jsonObject.get("mid").asNumber
                         val id: Number
                            get() = jsonObject.get("id").asNumber
                         val cardType: Number
                            get() = jsonObject.get("card_type").asNumber
                         val name: String
                            get() = jsonObject.get("name").asString
                         val expireTime: Number
                            get() = jsonObject.get("expire_time").asNumber
                         val cardTypeName: String
                            get() = jsonObject.get("card_type_name").asString
                         val uid: Number
                            get() = jsonObject.get("uid").asNumber
                         val itemId: Number
                            get() = jsonObject.get("item_id").asNumber
                         val itemType: Number
                            get() = jsonObject.get("item_type").asNumber
                         val bigCardUrl: String
                            get() = jsonObject.get("big_card_url").asString
                         val jumpUrl: String
                            get() = jsonObject.get("jump_url").asString
                         val fan: Fan
                            get() = jsonObject.get("fan").asFan
                         val imageEnhance: String
                            get() = jsonObject.get("image_enhance").asString
                         val cardUrl: String?
                            get() = jsonObject.get("card_url")?.asString
                        private val JsonElement.asFan
                            get() = Fan(this.asJsonObject)
                        class Fan (private val jsonObject: JsonObject) {
                            fun getJsonObject()  = jsonObject
                            override fun toString()  = jsonObject.toString()
                             val isFan: Number
                                get() = jsonObject.get("is_fan").asNumber
                             val number: Number
                                get() = jsonObject.get("number").asNumber
                             val color: String
                                get() = jsonObject.get("color").asString
                             val numDesc: String
                                get() = jsonObject.get("num_desc").asString
                        }
                    }
                }
                private val JsonElement.asOrigin
                    get() = Origin(this.asJsonObject)
                class Origin (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val uid: Number
                        get() = jsonObject.get("uid").asNumber
                     val type: Number
                        get() = jsonObject.get("type").asNumber
                     val rid: Number
                        get() = jsonObject.get("rid").asNumber
                     val view: Number
                        get() = jsonObject.get("view").asNumber
                     val repost: Number
                        get() = jsonObject.get("repost").asNumber
                     val dynamicId: Number
                        get() = jsonObject.get("dynamic_id").asNumber
                     val timestamp: Number
                        get() = jsonObject.get("timestamp").asNumber
                     val uidType: Number
                        get() = jsonObject.get("uid_type").asNumber
                     val status: Number
                        get() = jsonObject.get("status").asNumber
                     val dynamicIdStr: String
                        get() = jsonObject.get("dynamic_id_str").asString
                     val preDyIdStr: String
                        get() = jsonObject.get("pre_dy_id_str").asString
                     val origDyIdStr: String
                        get() = jsonObject.get("orig_dy_id_str").asString
                     val ridStr: String
                        get() = jsonObject.get("rid_str").asString
                     val bvid: String?
                        get() = jsonObject.get("bvid")?.asString
                     val acl: Number?
                        get() = jsonObject.get("acl")?.asNumber
                     val like: Number?
                        get() = jsonObject.get("like")?.asNumber
                     val preDyId: Number?
                        get() = jsonObject.get("pre_dy_id")?.asNumber
                     val origDyId: Number?
                        get() = jsonObject.get("orig_dy_id")?.asNumber
                     val stype: Number?
                        get() = jsonObject.get("stype")?.asNumber
                     val rType: Number?
                        get() = jsonObject.get("r_type")?.asNumber
                     val innerId: Number?
                        get() = jsonObject.get("inner_id")?.asNumber
                }
            }
            private val JsonElement.asDisplay
                get() = Display(this.asJsonObject)
            class Display (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val topicInfo: TopicInfo?
                    get() = jsonObject.get("topic_info")?.asTopicInfo
                 val usrActionTxt: String?
                    get() = jsonObject.get("usr_action_txt")?.asString
                 val relation: Relation
                    get() = jsonObject.get("relation").asRelation
                 val commentInfo: CommentInfo
                    get() = jsonObject.get("comment_info").asCommentInfo
                 val showTip: ShowTip?
                    get() = jsonObject.get("show_tip")?.asShowTip
                 val coverPlayIconUrl: String?
                    get() = jsonObject.get("cover_play_icon_url")?.asString
                 val origin: Origin?
                    get() = jsonObject.get("origin")?.asOrigin
                 val attachCard: AttachCard?
                    get() = jsonObject.get("attach_card")?.asAttachCard
                 val addOnCardInfo: List<AddOnCardInfo>?
                    get() = jsonObject.get("add_on_card_info")?.asJsonArray?.map { it.asAddOnCardInfo }
                private val JsonElement.asTopicInfo
                    get() = TopicInfo(this.asJsonObject)
                class TopicInfo (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val topicDetails: List<TopicDetails>
                        get() = jsonObject.get("topic_details").asJsonArray.map { it.asTopicDetails }
                    private val JsonElement.asTopicDetails
                        get() = TopicDetails(this.asJsonObject)
                    class TopicDetails (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val topicId: Number
                            get() = jsonObject.get("topic_id").asNumber
                         val topicName: String
                            get() = jsonObject.get("topic_name").asString
                         val isActivity: Number
                            get() = jsonObject.get("is_activity").asNumber
                         val topicLink: String
                            get() = jsonObject.get("topic_link").asString
                    }
                }
                private val JsonElement.asRelation
                    get() = Relation(this.asJsonObject)
                class Relation (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val status: Number
                        get() = jsonObject.get("status").asNumber
                     val isFollow: Number
                        get() = jsonObject.get("is_follow").asNumber
                     val isFollowed: Number
                        get() = jsonObject.get("is_followed").asNumber
                }
                private val JsonElement.asCommentInfo
                    get() = CommentInfo(this.asJsonObject)
                class CommentInfo (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val commentIds: String
                        get() = jsonObject.get("comment_ids").asString
                     val comments: List<Comments>?
                        get() = jsonObject.get("comments")?.asJsonArray?.map { it.asComments }
                     val emojis: List<Emojis>?
                        get() = jsonObject.get("emojis")?.asJsonArray?.map { it.asEmojis }
                    private val JsonElement.asComments
                        get() = Comments(this.asJsonObject)
                    class Comments (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val uid: Number
                            get() = jsonObject.get("uid").asNumber
                         val name: String
                            get() = jsonObject.get("name").asString
                         val content: String
                            get() = jsonObject.get("content").asString
                    }
                    private val JsonElement.asEmojis
                        get() = Emojis(this.asJsonObject)
                    class Emojis (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val emojiName: String
                            get() = jsonObject.get("emoji_name").asString
                         val url: String
                            get() = jsonObject.get("url").asString
                         val meta: Meta
                            get() = jsonObject.get("meta").asMeta
                        private val JsonElement.asMeta
                            get() = Meta(this.asJsonObject)
                        class Meta (private val jsonObject: JsonObject) {
                            fun getJsonObject()  = jsonObject
                            override fun toString()  = jsonObject.toString()
                             val size: Number
                                get() = jsonObject.get("size").asNumber
                        }
                    }
                }
                private val JsonElement.asShowTip
                    get() = ShowTip(this.asJsonObject)
                class ShowTip (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val delTip: String
                        get() = jsonObject.get("del_tip").asString
                }
                private val JsonElement.asOrigin
                    get() = Origin(this.asJsonObject)
                class Origin (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val topicInfo: TopicInfo
                        get() = jsonObject.get("topic_info").asTopicInfo
                     val usrActionTxt: String?
                        get() = jsonObject.get("usr_action_txt")?.asString
                     val relation: Relation
                        get() = jsonObject.get("relation").asRelation
                     val tags: List<Tags>?
                        get() = jsonObject.get("tags")?.asJsonArray?.map { it.asTags }
                     val showTip: ShowTip
                        get() = jsonObject.get("show_tip").asShowTip
                     val coverPlayIconUrl: String?
                        get() = jsonObject.get("cover_play_icon_url")?.asString
                     val attachCard: AttachCard?
                        get() = jsonObject.get("attach_card")?.asAttachCard
                     val addOnCardInfo: List<AddOnCardInfo>?
                        get() = jsonObject.get("add_on_card_info")?.asJsonArray?.map { it.asAddOnCardInfo }
                     val richText: RichText?
                        get() = jsonObject.get("rich_text")?.asRichText
                    private val JsonElement.asTopicInfo
                        get() = TopicInfo(this.asJsonObject)
                    class TopicInfo (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val topicDetails: List<TopicDetails>
                            get() = jsonObject.get("topic_details").asJsonArray.map { it.asTopicDetails }
                        private val JsonElement.asTopicDetails
                            get() = TopicDetails(this.asJsonObject)
                        class TopicDetails (private val jsonObject: JsonObject) {
                            fun getJsonObject()  = jsonObject
                            override fun toString()  = jsonObject.toString()
                             val topicId: Number
                                get() = jsonObject.get("topic_id").asNumber
                             val topicName: String
                                get() = jsonObject.get("topic_name").asString
                             val isActivity: Number
                                get() = jsonObject.get("is_activity").asNumber
                             val topicLink: String
                                get() = jsonObject.get("topic_link").asString
                        }
                    }
                    private val JsonElement.asRelation
                        get() = Relation(this.asJsonObject)
                    class Relation (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val status: Number
                            get() = jsonObject.get("status").asNumber
                         val isFollow: Number
                            get() = jsonObject.get("is_follow").asNumber
                         val isFollowed: Number
                            get() = jsonObject.get("is_followed").asNumber
                    }
                    private val JsonElement.asTags
                        get() = Tags(this.asJsonObject)
                    class Tags (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val tagType: Number
                            get() = jsonObject.get("tag_type").asNumber
                         val subType: Number
                            get() = jsonObject.get("sub_type").asNumber
                         val icon: String
                            get() = jsonObject.get("icon").asString
                         val text: String
                            get() = jsonObject.get("text").asString
                         val link: String
                            get() = jsonObject.get("link").asString
                         val rid: Number
                            get() = jsonObject.get("rid").asNumber
                         val subModule: String
                            get() = jsonObject.get("sub_module").asString
                    }
                    private val JsonElement.asShowTip
                        get() = ShowTip(this.asJsonObject)
                    class ShowTip (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val delTip: String
                            get() = jsonObject.get("del_tip").asString
                    }
                    private val JsonElement.asAttachCard
                        get() = AttachCard(this.asJsonObject)
                    class AttachCard (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val type: String
                            get() = jsonObject.get("type").asString
                         val headText: String
                            get() = jsonObject.get("head_text").asString
                         val coverUrl: String
                            get() = jsonObject.get("cover_url").asString
                         val coverType: Number
                            get() = jsonObject.get("cover_type").asNumber
                         val title: String
                            get() = jsonObject.get("title").asString
                         val descFirst: String
                            get() = jsonObject.get("desc_first").asString
                         val descSecond: String
                            get() = jsonObject.get("desc_second").asString
                         val jumpUrl: String
                            get() = jsonObject.get("jump_url").asString
                         val button: Button
                            get() = jsonObject.get("button").asButton
                         val oidStr: String
                            get() = jsonObject.get("oid_str").asString
                        private val JsonElement.asButton
                            get() = Button(this.asJsonObject)
                        class Button (private val jsonObject: JsonObject) {
                            fun getJsonObject()  = jsonObject
                            override fun toString()  = jsonObject.toString()
                             val type: Number
                                get() = jsonObject.get("type").asNumber
                             val jumpStyle: JumpStyle
                                get() = jsonObject.get("jump_style").asJumpStyle
                             val jumpUrl: String
                                get() = jsonObject.get("jump_url").asString
                            private val JsonElement.asJumpStyle
                                get() = JumpStyle(this.asJsonObject)
                            class JumpStyle (private val jsonObject: JsonObject) {
                                fun getJsonObject()  = jsonObject
                                override fun toString()  = jsonObject.toString()
                                 val text: String
                                    get() = jsonObject.get("text").asString
                            }
                        }
                    }
                    private val JsonElement.asAddOnCardInfo
                        get() = AddOnCardInfo(this.asJsonObject)
                    class AddOnCardInfo (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val addOnCardShowType: Number
                            get() = jsonObject.get("add_on_card_show_type").asNumber
                         val attachCard: AttachCard
                            get() = jsonObject.get("attach_card").asAttachCard
                        private val JsonElement.asAttachCard
                            get() = AttachCard(this.asJsonObject)
                        class AttachCard (private val jsonObject: JsonObject) {
                            fun getJsonObject()  = jsonObject
                            override fun toString()  = jsonObject.toString()
                             val type: String
                                get() = jsonObject.get("type").asString
                             val headText: String
                                get() = jsonObject.get("head_text").asString
                             val coverUrl: String
                                get() = jsonObject.get("cover_url").asString
                             val coverType: Number
                                get() = jsonObject.get("cover_type").asNumber
                             val title: String
                                get() = jsonObject.get("title").asString
                             val descFirst: String
                                get() = jsonObject.get("desc_first").asString
                             val descSecond: String
                                get() = jsonObject.get("desc_second").asString
                             val jumpUrl: String
                                get() = jsonObject.get("jump_url").asString
                             val button: Button
                                get() = jsonObject.get("button").asButton
                             val oidStr: String
                                get() = jsonObject.get("oid_str").asString
                            private val JsonElement.asButton
                                get() = Button(this.asJsonObject)
                            class Button (private val jsonObject: JsonObject) {
                                fun getJsonObject()  = jsonObject
                                override fun toString()  = jsonObject.toString()
                                 val type: Number
                                    get() = jsonObject.get("type").asNumber
                                 val jumpStyle: JumpStyle
                                    get() = jsonObject.get("jump_style").asJumpStyle
                                 val jumpUrl: String
                                    get() = jsonObject.get("jump_url").asString
                                private val JsonElement.asJumpStyle
                                    get() = JumpStyle(this.asJsonObject)
                                class JumpStyle (private val jsonObject: JsonObject) {
                                    fun getJsonObject()  = jsonObject
                                    override fun toString()  = jsonObject.toString()
                                     val text: String
                                        get() = jsonObject.get("text").asString
                                }
                            }
                        }
                    }
                    private val JsonElement.asRichText
                        get() = RichText(this.asJsonObject)
                    class RichText (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val richDetails: List<RichDetails>
                            get() = jsonObject.get("rich_details").asJsonArray.map { it.asRichDetails }
                        private val JsonElement.asRichDetails
                            get() = RichDetails(this.asJsonObject)
                        class RichDetails (private val jsonObject: JsonObject) {
                            fun getJsonObject()  = jsonObject
                            override fun toString()  = jsonObject.toString()
                             val jumpUri: String
                                get() = jsonObject.get("jump_uri").asString
                             val iconType: Number
                                get() = jsonObject.get("icon_type").asNumber
                             val text: String
                                get() = jsonObject.get("text").asString
                             val origText: String
                                get() = jsonObject.get("orig_text").asString
                        }
                    }
                }
                private val JsonElement.asAttachCard
                    get() = AttachCard(this.asJsonObject)
                class AttachCard (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val type: String
                        get() = jsonObject.get("type").asString
                     val headText: String
                        get() = jsonObject.get("head_text").asString
                     val coverUrl: String
                        get() = jsonObject.get("cover_url").asString
                     val coverType: Number
                        get() = jsonObject.get("cover_type").asNumber
                     val title: String
                        get() = jsonObject.get("title").asString
                     val descFirst: String
                        get() = jsonObject.get("desc_first").asString
                     val descSecond: String
                        get() = jsonObject.get("desc_second").asString
                     val jumpUrl: String
                        get() = jsonObject.get("jump_url").asString
                     val button: Button
                        get() = jsonObject.get("button").asButton
                     val oidStr: String
                        get() = jsonObject.get("oid_str").asString
                    private val JsonElement.asButton
                        get() = Button(this.asJsonObject)
                    class Button (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val type: Number
                            get() = jsonObject.get("type").asNumber
                         val jumpStyle: JumpStyle
                            get() = jsonObject.get("jump_style").asJumpStyle
                         val jumpUrl: String
                            get() = jsonObject.get("jump_url").asString
                        private val JsonElement.asJumpStyle
                            get() = JumpStyle(this.asJsonObject)
                        class JumpStyle (private val jsonObject: JsonObject) {
                            fun getJsonObject()  = jsonObject
                            override fun toString()  = jsonObject.toString()
                             val text: String
                                get() = jsonObject.get("text").asString
                        }
                    }
                }
                private val JsonElement.asAddOnCardInfo
                    get() = AddOnCardInfo(this.asJsonObject)
                class AddOnCardInfo (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val addOnCardShowType: Number
                        get() = jsonObject.get("add_on_card_show_type").asNumber
                     val attachCard: AttachCard
                        get() = jsonObject.get("attach_card").asAttachCard
                    private val JsonElement.asAttachCard
                        get() = AttachCard(this.asJsonObject)
                    class AttachCard (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val type: String
                            get() = jsonObject.get("type").asString
                         val headText: String
                            get() = jsonObject.get("head_text").asString
                         val coverUrl: String
                            get() = jsonObject.get("cover_url").asString
                         val coverType: Number
                            get() = jsonObject.get("cover_type").asNumber
                         val title: String
                            get() = jsonObject.get("title").asString
                         val descFirst: String
                            get() = jsonObject.get("desc_first").asString
                         val descSecond: String
                            get() = jsonObject.get("desc_second").asString
                         val jumpUrl: String
                            get() = jsonObject.get("jump_url").asString
                         val button: Button
                            get() = jsonObject.get("button").asButton
                         val oidStr: String
                            get() = jsonObject.get("oid_str").asString
                        private val JsonElement.asButton
                            get() = Button(this.asJsonObject)
                        class Button (private val jsonObject: JsonObject) {
                            fun getJsonObject()  = jsonObject
                            override fun toString()  = jsonObject.toString()
                             val type: Number
                                get() = jsonObject.get("type").asNumber
                             val jumpStyle: JumpStyle
                                get() = jsonObject.get("jump_style").asJumpStyle
                             val jumpUrl: String
                                get() = jsonObject.get("jump_url").asString
                            private val JsonElement.asJumpStyle
                                get() = JumpStyle(this.asJsonObject)
                            class JumpStyle (private val jsonObject: JsonObject) {
                                fun getJsonObject()  = jsonObject
                                override fun toString()  = jsonObject.toString()
                                 val text: String
                                    get() = jsonObject.get("text").asString
                            }
                        }
                    }
                }
            }
            private val JsonElement.asExtra
                get() = Extra(this.asJsonObject)
            class Extra (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val isPgcFeature: Number
                    get() = jsonObject.get("is_pgc_feature").asNumber
            }
        }
    }
}