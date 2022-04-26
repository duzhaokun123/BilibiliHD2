package  com.hiczp.bilibili.api.main.model
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom
@LazyjsonClass class SendReplyResponse (private val jsonObject: JsonObject) {
    companion object   {
        @LazyjsonFrom @JvmStatic fun from(jsonObject: JsonObject)  = SendReplyResponse(jsonObject)
    }
    fun getJsonObject()  = jsonObject
    override fun toString()  = jsonObject.toString()
     val code: Number
        get() = jsonObject.get("code").asNumber
     val message: String
        get() = jsonObject.get("message").asString
     val ttl: Number
        get() = jsonObject.get("ttl").asNumber
     val data: Data
        get() = jsonObject.get("data").asData
    private val JsonElement.asData
        get() = Data(this.asJsonObject)
    class Data (private val jsonObject: JsonObject) {
        fun getJsonObject()  = jsonObject
        override fun toString()  = jsonObject.toString()
         val successAction: Number
            get() = jsonObject.get("success_action").asNumber
         val successToast: String
            get() = jsonObject.get("success_toast").asString
         val needCaptcha: Boolean
            get() = jsonObject.get("need_captcha").asBoolean
         val needCaptchaV2: Boolean
            get() = jsonObject.get("need_captcha_v2").asBoolean
         val url: String
            get() = jsonObject.get("url").asString
         val urlV2: String
            get() = jsonObject.get("url_v2").asString
         val rpid: Long
            get() = jsonObject.get("rpid").asLong
         val rpidStr: String
            get() = jsonObject.get("rpid_str").asString
         val dialog: Number
            get() = jsonObject.get("dialog").asNumber
         val dialogStr: String
            get() = jsonObject.get("dialog_str").asString
         val root: Number
            get() = jsonObject.get("root").asNumber
         val rootStr: String
            get() = jsonObject.get("root_str").asString
         val parent: Number
            get() = jsonObject.get("parent").asNumber
         val parentStr: String
            get() = jsonObject.get("parent_str").asString
         val reply: Reply
            get() = jsonObject.get("reply").asReply
        private val JsonElement.asReply
            get() = Reply(this.asJsonObject)
        class Reply (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val rpid: Number
                get() = jsonObject.get("rpid").asNumber
             val oid: Number
                get() = jsonObject.get("oid").asNumber
             val type: Number
                get() = jsonObject.get("type").asNumber
             val mid: Number
                get() = jsonObject.get("mid").asNumber
             val root: Number
                get() = jsonObject.get("root").asNumber
             val parent: Number
                get() = jsonObject.get("parent").asNumber
             val dialog: Number
                get() = jsonObject.get("dialog").asNumber
             val count: Number
                get() = jsonObject.get("count").asNumber
             val rcount: Number
                get() = jsonObject.get("rcount").asNumber
             val state: Number
                get() = jsonObject.get("state").asNumber
             val fansgrade: Number
                get() = jsonObject.get("fansgrade").asNumber
             val attr: Number
                get() = jsonObject.get("attr").asNumber
             val ctime: Long
                get() = jsonObject.get("ctime").asLong
             val rpidStr: String
                get() = jsonObject.get("rpid_str").asString
             val rootStr: String
                get() = jsonObject.get("root_str").asString
             val parentStr: String
                get() = jsonObject.get("parent_str").asString
             val like: Number
                get() = jsonObject.get("like").asNumber
             val action: Number
                get() = jsonObject.get("action").asNumber
             val member: Member
                get() = jsonObject.get("member").asMember
             val content: Content
                get() = jsonObject.get("content").asContent
             val assist: Number
                get() = jsonObject.get("assist").asNumber
             val folder: Folder
                get() = jsonObject.get("folder").asFolder
             val upAction: UpAction
                get() = jsonObject.get("up_action").asUpAction
             val showFollow: Boolean
                get() = jsonObject.get("show_follow").asBoolean
             val invisible: Boolean
                get() = jsonObject.get("invisible").asBoolean
             val replyControl: ReplyControl
                get() = jsonObject.get("reply_control").asReplyControl
            private val JsonElement.asMember
                get() = Member(this.asJsonObject)
            class Member (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val mid: String
                    get() = jsonObject.get("mid").asString
                 val uname: String
                    get() = jsonObject.get("uname").asString
                 val sex: String
                    get() = jsonObject.get("sex").asString
                 val sign: String
                    get() = jsonObject.get("sign").asString
                 val avatar: String
                    get() = jsonObject.get("avatar").asString
                 val rank: String
                    get() = jsonObject.get("rank").asString
                 val DisplayRank: String
                    get() = jsonObject.get("DisplayRank").asString
                 val faceNftNew: Number
                    get() = jsonObject.get("face_nft_new").asNumber
                 val isSeniorMember: Number
                    get() = jsonObject.get("is_senior_member").asNumber
                 val levelInfo: LevelInfo
                    get() = jsonObject.get("level_info").asLevelInfo
                 val pendant: Pendant
                    get() = jsonObject.get("pendant").asPendant
                 val nameplate: Nameplate
                    get() = jsonObject.get("nameplate").asNameplate
                 val officialVerify: OfficialVerify
                    get() = jsonObject.get("official_verify").asOfficialVerify
                 val vip: Vip
                    get() = jsonObject.get("vip").asVip
                 val following: Number
                    get() = jsonObject.get("following").asNumber
                 val isFollowed: Number
                    get() = jsonObject.get("is_followed").asNumber
                 val userSailing: UserSailing
                    get() = jsonObject.get("user_sailing").asUserSailing
                 val isContractor: Boolean
                    get() = jsonObject.get("is_contractor").asBoolean
                 val contractDesc: String
                    get() = jsonObject.get("contract_desc").asString
                private val JsonElement.asLevelInfo
                    get() = LevelInfo(this.asJsonObject)
                class LevelInfo (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val currentLevel: Number
                        get() = jsonObject.get("current_level").asNumber
                     val currentMin: Number
                        get() = jsonObject.get("current_min").asNumber
                     val currentExp: Number
                        get() = jsonObject.get("current_exp").asNumber
                     val nextExp: Number
                        get() = jsonObject.get("next_exp").asNumber
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
                private val JsonElement.asNameplate
                    get() = Nameplate(this.asJsonObject)
                class Nameplate (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val nid: Number
                        get() = jsonObject.get("nid").asNumber
                     val name: String
                        get() = jsonObject.get("name").asString
                     val image: String
                        get() = jsonObject.get("image").asString
                     val imageSmall: String
                        get() = jsonObject.get("image_small").asString
                     val level: String
                        get() = jsonObject.get("level").asString
                     val condition: String
                        get() = jsonObject.get("condition").asString
                }
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
                private val JsonElement.asVip
                    get() = Vip(this.asJsonObject)
                class Vip (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val vipType: Number
                        get() = jsonObject.get("vipType").asNumber
                     val vipDueDate: Number
                        get() = jsonObject.get("vipDueDate").asNumber
                     val dueRemark: String
                        get() = jsonObject.get("dueRemark").asString
                     val accessStatus: Number
                        get() = jsonObject.get("accessStatus").asNumber
                     val vipStatus: Number
                        get() = jsonObject.get("vipStatus").asNumber
                     val vipStatusWarn: String
                        get() = jsonObject.get("vipStatusWarn").asString
                     val themeType: Number
                        get() = jsonObject.get("themeType").asNumber
                     val label: Label
                        get() = jsonObject.get("label").asLabel
                     val avatarSubscript: Number
                        get() = jsonObject.get("avatar_subscript").asNumber
                     val nicknameColor: String
                        get() = jsonObject.get("nickname_color").asString
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
                private val JsonElement.asUserSailing
                    get() = UserSailing(this.asJsonObject)
                class UserSailing (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                }
            }
            private val JsonElement.asContent
                get() = Content(this.asJsonObject)
            class Content (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val message: String
                    get() = jsonObject.get("message").asString
                 val ipv6: String
                    get() = jsonObject.get("ipv6").asString
                 val plat: Number
                    get() = jsonObject.get("plat").asNumber
                 val device: String
                    get() = jsonObject.get("device").asString
                 val jumpUrl: JumpUrl
                    get() = jsonObject.get("jump_url").asJumpUrl
                 val maxLine: Number
                    get() = jsonObject.get("max_line").asNumber
                private val JsonElement.asJumpUrl
                    get() = JumpUrl(this.asJsonObject)
                class JumpUrl (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                }
            }
            private val JsonElement.asFolder
                get() = Folder(this.asJsonObject)
            class Folder (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val hasFolded: Boolean
                    get() = jsonObject.get("has_folded").asBoolean
                 val isFolded: Boolean
                    get() = jsonObject.get("is_folded").asBoolean
                 val rule: String
                    get() = jsonObject.get("rule").asString
            }
            private val JsonElement.asUpAction
                get() = UpAction(this.asJsonObject)
            class UpAction (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val like: Boolean
                    get() = jsonObject.get("like").asBoolean
                 val reply: Boolean
                    get() = jsonObject.get("reply").asBoolean
            }
            private val JsonElement.asReplyControl
                get() = ReplyControl(this.asJsonObject)
            class ReplyControl (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
            }
        }
    }
}