package  com.hiczp.bilibili.api.app.model
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom
@LazyjsonClass class View (private val jsonObject: JsonObject) {
    companion object   {
        @LazyjsonFrom @JvmStatic fun from(jsonObject: JsonObject)  = View(jsonObject)
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
         val aid: Number
            get() = jsonObject.get("aid").asNumber
         val videos: Number
            get() = jsonObject.get("videos").asNumber
         val tid: Number
            get() = jsonObject.get("tid").asNumber
         val tname: String
            get() = jsonObject.get("tname").asString
         val copyright: Int
            get() = jsonObject.get("copyright").asInt
         val pic: String
            get() = jsonObject.get("pic").asString
         val title: String
            get() = jsonObject.get("title").asString
         val pubdate: Int
            get() = jsonObject.get("pubdate").asInt
         val ctime: Number
            get() = jsonObject.get("ctime").asNumber
         val desc: String
            get() = jsonObject.get("desc").asString
         val state: Number
            get() = jsonObject.get("state").asNumber
         val duration: Number
            get() = jsonObject.get("duration").asNumber
         val rights: Rights
            get() = jsonObject.get("rights").asRights
         val owner: Owner
            get() = jsonObject.get("owner").asOwner
         val stat: Stat
            get() = jsonObject.get("stat").asStat
         val dynamic: String
            get() = jsonObject.get("dynamic").asString
         val cid: Number
            get() = jsonObject.get("cid").asNumber
         val dimension: Dimension
            get() = jsonObject.get("dimension").asDimension
         val shortLinkV2: String
            get() = jsonObject.get("short_link_v2").asString
         val pages: List<Pages>
            get() = jsonObject.get("pages").asJsonArray.map { it.asPages }
         val ownerExt: OwnerExt
            get() = jsonObject.get("owner_ext").asOwnerExt
         val reqUser: ReqUser
            get() = jsonObject.get("req_user").asReqUser
         val tag: List<Tag>
            get() = jsonObject.get("tag").asJsonArray.map { it.asTag }
         val tIcon: TIcon
            get() = jsonObject.get("t_icon").asTIcon
         val elec: Elec
            get() = jsonObject.get("elec").asElec
         val history: History
            get() = jsonObject.get("history").asHistory
         val relates: List<Relates>
            get() = jsonObject.get("relates").asJsonArray.map { it.asRelates }
         val dislikeReasons: List<DislikeReasons>
            get() = jsonObject.get("dislike_reasons").asJsonArray.map { it.asDislikeReasons }
         val dislikeReasonsV2: DislikeReasonsV2
            get() = jsonObject.get("dislike_reasons_v2").asDislikeReasonsV2
         val dmSeg: Number
            get() = jsonObject.get("dm_seg").asNumber
         val cms: List<Cms>
            get() = jsonObject.get("cms").asJsonArray.map { it.asCms }
         val cmConfig: CmConfig
            get() = jsonObject.get("cm_config").asCmConfig
         val shortLink: String
            get() = jsonObject.get("short_link").asString
         val playParam: Number
            get() = jsonObject.get("play_param").asNumber
         val config: Config
            get() = jsonObject.get("config").asConfig
         val shareSubtitle: String
            get() = jsonObject.get("share_subtitle").asString
         val honor: Honor?
            get() = jsonObject.get("honor")?.asHonor
         val bvid: String
            get() = jsonObject.get("bvid").asString
         val likeCustom: LikeCustom
            get() = jsonObject.get("like_custom").asLikeCustom
         val redirectUrl: String?
            get() = jsonObject.get("redirect_url")?.asString
        private val JsonElement.asRights
            get() = Rights(this.asJsonObject)
        class Rights (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val bp: Number
                get() = jsonObject.get("bp").asNumber
             val elec: Number
                get() = jsonObject.get("elec").asNumber
             val download: Number
                get() = jsonObject.get("download").asNumber
             val movie: Number
                get() = jsonObject.get("movie").asNumber
             val pay: Number
                get() = jsonObject.get("pay").asNumber
             val hd5: Number
                get() = jsonObject.get("hd5").asNumber
             val noReprint: Number
                get() = jsonObject.get("no_reprint").asNumber
             val autoplay: Number
                get() = jsonObject.get("autoplay").asNumber
             val ugcPay: Number
                get() = jsonObject.get("ugc_pay").asNumber
             val isCooperation: Number
                get() = jsonObject.get("is_cooperation").asNumber
             val ugcPayPreview: Number
                get() = jsonObject.get("ugc_pay_preview").asNumber
             val noBackground: Number
                get() = jsonObject.get("no_background").asNumber
        }
        private val JsonElement.asOwner
            get() = Owner(this.asJsonObject)
        class Owner (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val mid: Number
                get() = jsonObject.get("mid").asNumber
             val name: String
                get() = jsonObject.get("name").asString
             val face: String
                get() = jsonObject.get("face").asString
        }
        private val JsonElement.asStat
            get() = Stat(this.asJsonObject)
        class Stat (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val aid: Number
                get() = jsonObject.get("aid").asNumber
             val view: Number
                get() = jsonObject.get("view").asNumber
             val danmaku: Number
                get() = jsonObject.get("danmaku").asNumber
             val reply: Number
                get() = jsonObject.get("reply").asNumber
             val favorite: Number
                get() = jsonObject.get("favorite").asNumber
             val coin: Number
                get() = jsonObject.get("coin").asNumber
             val share: Number
                get() = jsonObject.get("share").asNumber
             val nowRank: Number
                get() = jsonObject.get("now_rank").asNumber
             val hisRank: Number
                get() = jsonObject.get("his_rank").asNumber
             val like: Number
                get() = jsonObject.get("like").asNumber
             val dislike: Number
                get() = jsonObject.get("dislike").asNumber
        }
        private val JsonElement.asDimension
            get() = Dimension(this.asJsonObject)
        class Dimension (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val width: Number
                get() = jsonObject.get("width").asNumber
             val height: Number
                get() = jsonObject.get("height").asNumber
             val rotate: Number
                get() = jsonObject.get("rotate").asNumber
        }
        private val JsonElement.asPages
            get() = Pages(this.asJsonObject)
        class Pages (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val cid: Long
                get() = jsonObject.get("cid").asLong
             val page: Int
                get() = jsonObject.get("page").asInt
             val from: String
                get() = jsonObject.get("from").asString
             val part: String
                get() = jsonObject.get("part").asString
             val duration: Int
                get() = jsonObject.get("duration").asInt
             val vid: String
                get() = jsonObject.get("vid").asString
             val weblink: String
                get() = jsonObject.get("weblink").asString
             val dimension: Dimension
                get() = jsonObject.get("dimension").asDimension
             val metas: List<Metas>
                get() = jsonObject.get("metas").asJsonArray.map { it.asMetas }
             val dmlink: String
                get() = jsonObject.get("dmlink").asString
             val dm: Dm
                get() = jsonObject.get("dm").asDm
             val downloadTitle: String
                get() = jsonObject.get("download_title").asString
             val downloadSubtitle: String
                get() = jsonObject.get("download_subtitle").asString
            private val JsonElement.asDimension
                get() = Dimension(this.asJsonObject)
            class Dimension (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val width: Int
                    get() = jsonObject.get("width").asInt
                 val height: Int
                    get() = jsonObject.get("height").asInt
                 val rotate: Int
                    get() = jsonObject.get("rotate").asInt
            }
            private val JsonElement.asMetas
                get() = Metas(this.asJsonObject)
            class Metas (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val quality: Number
                    get() = jsonObject.get("quality").asNumber
                 val format: String
                    get() = jsonObject.get("format").asString
                 val size: Number
                    get() = jsonObject.get("size").asNumber
            }
            private val JsonElement.asDm
                get() = Dm(this.asJsonObject)
            class Dm (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val closed: Boolean
                    get() = jsonObject.get("closed").asBoolean
                 val realName: Boolean
                    get() = jsonObject.get("real_name").asBoolean
                 val count: Number
                    get() = jsonObject.get("count").asNumber
            }
        }
        private val JsonElement.asOwnerExt
            get() = OwnerExt(this.asJsonObject)
        class OwnerExt (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val officialVerify: OfficialVerify
                get() = jsonObject.get("official_verify").asOfficialVerify
             val vip: Vip
                get() = jsonObject.get("vip").asVip
             val fans: Number
                get() = jsonObject.get("fans").asNumber
             val arcCount: String
                get() = jsonObject.get("arc_count").asString
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
                 val vipStatus: Int
                    get() = jsonObject.get("vipStatus").asInt
                 val vipStatusWarn: String
                    get() = jsonObject.get("vipStatusWarn").asString
                 val themeType: Number
                    get() = jsonObject.get("themeType").asNumber
                 val label: Label
                    get() = jsonObject.get("label").asLabel
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
        }
        private val JsonElement.asReqUser
            get() = ReqUser(this.asJsonObject)
        class ReqUser (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val attention: Number
                get() = jsonObject.get("attention").asNumber
             val guestAttention: Number
                get() = jsonObject.get("guest_attention").asNumber
             val favorite: Number
                get() = jsonObject.get("favorite").asNumber
             val like: Number
                get() = jsonObject.get("like").asNumber
             val coin: Number
                get() = jsonObject.get("coin").asNumber
        }
        private val JsonElement.asTag
            get() = Tag(this.asJsonObject)
        class Tag (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val tagId: Number
                get() = jsonObject.get("tag_id").asNumber
             val tagName: String
                get() = jsonObject.get("tag_name").asString
             val cover: String
                get() = jsonObject.get("cover").asString
             val likes: Number
                get() = jsonObject.get("likes").asNumber
             val hates: Number
                get() = jsonObject.get("hates").asNumber
             val liked: Number
                get() = jsonObject.get("liked").asNumber
             val hated: Number
                get() = jsonObject.get("hated").asNumber
             val attribute: Number
                get() = jsonObject.get("attribute").asNumber
             val isActivity: Number
                get() = jsonObject.get("is_activity").asNumber
             val uri: String
                get() = jsonObject.get("uri").asString
             val tagType: String
                get() = jsonObject.get("tag_type").asString
        }
        private val JsonElement.asTIcon
            get() = TIcon(this.asJsonObject)
        class TIcon (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val act: Act
                get() = jsonObject.get("act").asAct
             val new: New
                get() = jsonObject.get("new").asNew
            private val JsonElement.asAct
                get() = Act(this.asJsonObject)
            class Act (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val icon: String
                    get() = jsonObject.get("icon").asString
            }
            private val JsonElement.asNew
                get() = New(this.asJsonObject)
            class New (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val icon: String
                    get() = jsonObject.get("icon").asString
            }
        }
        private val JsonElement.asElec
            get() = Elec(this.asJsonObject)
        class Elec (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val show: Boolean
                get() = jsonObject.get("show").asBoolean
             val total: Number
                get() = jsonObject.get("total").asNumber
             val count: Number
                get() = jsonObject.get("count").asNumber
             val elecNum: Number
                get() = jsonObject.get("elec_num").asNumber
             val list: List<List_>
                get() = jsonObject.get("list").asJsonArray.map { it.asList_ }
             val elecSet: ElecSet
                get() = jsonObject.get("elec_set").asElecSet
            private val JsonElement.asList_
                get() = List_(this.asJsonObject)
            class List_ (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val payMid: Number
                    get() = jsonObject.get("pay_mid").asNumber
                 val rank: Number
                    get() = jsonObject.get("rank").asNumber
                 val trendType: Number
                    get() = jsonObject.get("trend_type").asNumber
                 val message: String
                    get() = jsonObject.get("message").asString
                 val mid: Number
                    get() = jsonObject.get("mid").asNumber
                 val vipInfo: VipInfo
                    get() = jsonObject.get("vip_info").asVipInfo
                 val uname: String
                    get() = jsonObject.get("uname").asString
                 val avatar: String
                    get() = jsonObject.get("avatar").asString
                private val JsonElement.asVipInfo
                    get() = VipInfo(this.asJsonObject)
                class VipInfo (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val vipType: Number
                        get() = jsonObject.get("vipType").asNumber
                     val vipStatus: Number
                        get() = jsonObject.get("vipStatus").asNumber
                     val vipDueMsec: Number
                        get() = jsonObject.get("vipDueMsec").asNumber
                }
            }
            private val JsonElement.asElecSet
                get() = ElecSet(this.asJsonObject)
            class ElecSet (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val elecTheme: Number
                    get() = jsonObject.get("elec_theme").asNumber
                 val rmbRate: Number
                    get() = jsonObject.get("rmb_rate").asNumber
                 val integrityRate: Number
                    get() = jsonObject.get("integrity_rate").asNumber
                 val roundMode: Number
                    get() = jsonObject.get("round_mode").asNumber
                 val elecList: List<ElecList>
                    get() = jsonObject.get("elec_list").asJsonArray.map { it.asElecList }
                private val JsonElement.asElecList
                    get() = ElecList(this.asJsonObject)
                class ElecList (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val title: String
                        get() = jsonObject.get("title").asString
                     val elecNum: Number
                        get() = jsonObject.get("elec_num").asNumber
                     val isCustomize: Number
                        get() = jsonObject.get("is_customize").asNumber
                     val minElec: Number?
                        get() = jsonObject.get("min_elec")?.asNumber
                     val maxElec: Number?
                        get() = jsonObject.get("max_elec")?.asNumber
                }
            }
        }
        private val JsonElement.asHistory
            get() = History(this.asJsonObject)
        class History (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val cid: Number
                get() = jsonObject.get("cid").asNumber
             val progress: Number
                get() = jsonObject.get("progress").asNumber
        }
        private val JsonElement.asRelates
            get() = Relates(this.asJsonObject)
        class Relates (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val pic: String?
                get() = jsonObject.get("pic")?.asString
             val title: String
                get() = jsonObject.get("title").asString
             val stat: Stat
                get() = jsonObject.get("stat").asStat
             val goto: String
                get() = jsonObject.get("goto").asString
             val param: String
                get() = jsonObject.get("param").asString
             val uri: String
                get() = jsonObject.get("uri").asString
             val from: String?
                get() = jsonObject.get("from")?.asString
             val desc: String?
                get() = jsonObject.get("desc")?.asString
             val trackid: String
                get() = jsonObject.get("trackid").asString
             val recThreePoint: RecThreePoint
                get() = jsonObject.get("rec_three_point").asRecThreePoint
             val uniqId: String?
                get() = jsonObject.get("uniq_id")?.asString
             val fromSourceType: Number
                get() = jsonObject.get("from_source_type").asNumber
             val fromSourceId: String
                get() = jsonObject.get("from_source_id").asString
             val dimension: Dimension
                get() = jsonObject.get("dimension").asDimension
             val aid: Number?
                get() = jsonObject.get("aid")?.asNumber
             val owner: Owner?
                get() = jsonObject.get("owner")?.asOwner
             val duration: Int?
                get() = jsonObject.get("duration")?.asInt
             val cid: Number?
                get() = jsonObject.get("cid")?.asNumber
            private val JsonElement.asStat
                get() = Stat(this.asJsonObject)
            class Stat (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val aid: Number
                    get() = jsonObject.get("aid").asNumber
                 val view: Number
                    get() = jsonObject.get("view").asNumber
                 val danmaku: Number
                    get() = jsonObject.get("danmaku").asNumber
                 val reply: Number
                    get() = jsonObject.get("reply").asNumber
                 val favorite: Number
                    get() = jsonObject.get("favorite").asNumber
                 val coin: Number
                    get() = jsonObject.get("coin").asNumber
                 val share: Number
                    get() = jsonObject.get("share").asNumber
                 val nowRank: Number
                    get() = jsonObject.get("now_rank").asNumber
                 val hisRank: Number
                    get() = jsonObject.get("his_rank").asNumber
                 val like: Number
                    get() = jsonObject.get("like").asNumber
                 val dislike: Number
                    get() = jsonObject.get("dislike").asNumber
            }
            private val JsonElement.asRecThreePoint
                get() = RecThreePoint(this.asJsonObject)
            class RecThreePoint (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val dislike: Dislike
                    get() = jsonObject.get("dislike").asDislike
                 val feedback: Feedback
                    get() = jsonObject.get("feedback").asFeedback
                 val watchLater: Boolean?
                    get() = jsonObject.get("watch_later")?.asBoolean
                private val JsonElement.asDislike
                    get() = Dislike(this.asJsonObject)
                class Dislike (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val title: String
                        get() = jsonObject.get("title").asString
                     val pasteText: String
                        get() = jsonObject.get("paste_text").asString
                     val closedPasteText: String
                        get() = jsonObject.get("closed_paste_text").asString
                     val toast: String
                        get() = jsonObject.get("toast").asString
                     val closedToast: String
                        get() = jsonObject.get("closed_toast").asString
                     val subTitle: String?
                        get() = jsonObject.get("sub_title")?.asString
                     val dislikeReason: List<DislikeReason>?
                        get() = jsonObject.get("dislike_reason")?.asJsonArray?.map { it.asDislikeReason }
                    private val JsonElement.asDislikeReason
                        get() = DislikeReason(this.asJsonObject)
                    class DislikeReason (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val id: Number
                            get() = jsonObject.get("id").asNumber
                         val name: String
                            get() = jsonObject.get("name").asString
                    }
                }
                private val JsonElement.asFeedback
                    get() = Feedback(this.asJsonObject)
                class Feedback (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val pasteText: String
                        get() = jsonObject.get("paste_text").asString
                     val closedPasteText: String
                        get() = jsonObject.get("closed_paste_text").asString
                     val toast: String
                        get() = jsonObject.get("toast").asString
                     val closedToast: String
                        get() = jsonObject.get("closed_toast").asString
                     val title: String?
                        get() = jsonObject.get("title")?.asString
                     val subTitle: String?
                        get() = jsonObject.get("sub_title")?.asString
                     val closedSubTitle: String?
                        get() = jsonObject.get("closed_sub_title")?.asString
                     val dislikeReason: List<DislikeReason>?
                        get() = jsonObject.get("dislike_reason")?.asJsonArray?.map { it.asDislikeReason }
                    private val JsonElement.asDislikeReason
                        get() = DislikeReason(this.asJsonObject)
                    class DislikeReason (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val id: Number
                            get() = jsonObject.get("id").asNumber
                         val name: String
                            get() = jsonObject.get("name").asString
                    }
                }
            }
            private val JsonElement.asDimension
                get() = Dimension(this.asJsonObject)
            class Dimension (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val width: Number
                    get() = jsonObject.get("width").asNumber
                 val height: Number
                    get() = jsonObject.get("height").asNumber
                 val rotate: Number
                    get() = jsonObject.get("rotate").asNumber
            }
            private val JsonElement.asOwner
                get() = Owner(this.asJsonObject)
            class Owner (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val mid: Number
                    get() = jsonObject.get("mid").asNumber
                 val name: String
                    get() = jsonObject.get("name").asString
                 val face: String
                    get() = jsonObject.get("face").asString
            }
        }
        private val JsonElement.asDislikeReasons
            get() = DislikeReasons(this.asJsonObject)
        class DislikeReasons (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val reasonId: Number
                get() = jsonObject.get("reason_id").asNumber
             val reasonName: String
                get() = jsonObject.get("reason_name").asString
        }
        private val JsonElement.asDislikeReasonsV2
            get() = DislikeReasonsV2(this.asJsonObject)
        class DislikeReasonsV2 (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val title: String
                get() = jsonObject.get("title").asString
             val subtitle: String
                get() = jsonObject.get("subtitle").asString
             val reasons: List<Reasons>
                get() = jsonObject.get("reasons").asJsonArray.map { it.asReasons }
            private val JsonElement.asReasons
                get() = Reasons(this.asJsonObject)
            class Reasons (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val id: Number
                    get() = jsonObject.get("id").asNumber
                 val mid: Number?
                    get() = jsonObject.get("mid")?.asNumber
                 val name: String
                    get() = jsonObject.get("name").asString
                 val tagId: Number?
                    get() = jsonObject.get("tag_id")?.asNumber
                 val rid: Number?
                    get() = jsonObject.get("rid")?.asNumber
            }
        }
        private val JsonElement.asCms
            get() = Cms(this.asJsonObject)
        class Cms (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val requestId: String
                get() = jsonObject.get("request_id").asString
             val rscId: Number
                get() = jsonObject.get("rsc_id").asNumber
             val srcId: Number
                get() = jsonObject.get("src_id").asNumber
             val isAdLoc: Boolean
                get() = jsonObject.get("is_ad_loc").asBoolean
             val clientIp: String
                get() = jsonObject.get("client_ip").asString
             val index: Number
                get() = jsonObject.get("index").asNumber
             val adInfo: AdInfo
                get() = jsonObject.get("ad_info").asAdInfo
            private val JsonElement.asAdInfo
                get() = AdInfo(this.asJsonObject)
            class AdInfo (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
            }
        }
        private val JsonElement.asCmConfig
            get() = CmConfig(this.asJsonObject)
        class CmConfig (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val adsControl: AdsControl
                get() = jsonObject.get("ads_control").asAdsControl
            private val JsonElement.asAdsControl
                get() = AdsControl(this.asJsonObject)
            class AdsControl (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val hasDanmu: Number
                    get() = jsonObject.get("has_danmu").asNumber
            }
        }
        private val JsonElement.asConfig
            get() = Config(this.asJsonObject)
        class Config (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val relatesTitle: String
                get() = jsonObject.get("relates_title").asString
             val autoplayCountdown: Number
                get() = jsonObject.get("autoplay_countdown").asNumber
             val shareStyle: Number
                get() = jsonObject.get("share_style").asNumber
             val recThreePointStyle: Number
                get() = jsonObject.get("rec_three_point_style").asNumber
             val isAbsoluteTime: Boolean
                get() = jsonObject.get("is_absolute_time").asBoolean
             val feedStyle: String
                get() = jsonObject.get("feed_style").asString
             val hasGuide: Boolean
                get() = jsonObject.get("has_guide").asBoolean
             val feedHasNext: Boolean
                get() = jsonObject.get("feed_has_next").asBoolean
             val localPlay: Number
                get() = jsonObject.get("local_play").asNumber
        }
        private val JsonElement.asHonor
            get() = Honor(this.asJsonObject)
        class Honor (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val icon: String
                get() = jsonObject.get("icon").asString
             val iconNight: String
                get() = jsonObject.get("icon_night").asString
             val text: String
                get() = jsonObject.get("text").asString
             val textExtra: String
                get() = jsonObject.get("text_extra").asString
             val textColor: String
                get() = jsonObject.get("text_color").asString
             val textColorNight: String
                get() = jsonObject.get("text_color_night").asString
             val bgColor: String
                get() = jsonObject.get("bg_color").asString
             val bgColorNight: String
                get() = jsonObject.get("bg_color_night").asString
             val url: String
                get() = jsonObject.get("url").asString
             val urlText: String
                get() = jsonObject.get("url_text").asString
        }
        private val JsonElement.asLikeCustom
            get() = LikeCustom(this.asJsonObject)
        class LikeCustom (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val likeSwitch: Boolean
                get() = jsonObject.get("like_switch").asBoolean
             val fullToHalfProgress: Number
                get() = jsonObject.get("full_to_half_progress").asNumber
             val nonFullProgress: Number
                get() = jsonObject.get("non_full_progress").asNumber
             val updateCount: Number
                get() = jsonObject.get("update_count").asNumber
        }
    }
}