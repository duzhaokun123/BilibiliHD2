package  com.hiczp.bilibili.api.main.model
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom
@LazyjsonClass class Season (private val jsonObject: JsonObject) {
    companion object   {
        @LazyjsonFrom @JvmStatic fun from(jsonObject: JsonObject)  = Season(jsonObject)
    }
    fun getJsonObject()  = jsonObject
    override fun toString()  = jsonObject.toString()
     val code: Number
        get() = jsonObject.get("code").asNumber
     val message: String
        get() = jsonObject.get("message").asString
     val result: Result
        get() = jsonObject.get("result").asResult
    private val JsonElement.asResult
        get() = Result(this.asJsonObject)
    class Result (private val jsonObject: JsonObject) {
        fun getJsonObject()  = jsonObject
        override fun toString()  = jsonObject.toString()
         val activity: Activity
            get() = jsonObject.get("activity").asActivity
         val actor: Actor
            get() = jsonObject.get("actor").asActor
         val alias: String
            get() = jsonObject.get("alias").asString
         val allButtons: AllButtons
            get() = jsonObject.get("all_buttons").asAllButtons
         val allUpInfos: AllUpInfos
            get() = jsonObject.get("all_up_infos").asAllUpInfos
         val areas: List<Areas>
            get() = jsonObject.get("areas").asJsonArray.map { it.asAreas }
         val badge: String
            get() = jsonObject.get("badge").asString
         val badgeInfo: BadgeInfo
            get() = jsonObject.get("badge_info").asBadgeInfo
         val badgeType: Number
            get() = jsonObject.get("badge_type").asNumber
         val cover: String
            get() = jsonObject.get("cover").asString
         val detail: String
            get() = jsonObject.get("detail").asString
         val dynamicSubtitle: String
            get() = jsonObject.get("dynamic_subtitle").asString
         val episodes: List<Episodes>
            get() = jsonObject.get("episodes").asJsonArray.map { it.asEpisodes }
         val evaluate: String
            get() = jsonObject.get("evaluate").asString
         val link: String
            get() = jsonObject.get("link").asString
         val mediaBadgeInfo: MediaBadgeInfo
            get() = jsonObject.get("media_badge_info").asMediaBadgeInfo
         val mediaId: Number
            get() = jsonObject.get("media_id").asNumber
         val mode: Number
            get() = jsonObject.get("mode").asNumber
         val modules: List<Modules>
            get() = jsonObject.get("modules").asJsonArray.map { it.asModules }
         val newEp: NewEp
            get() = jsonObject.get("new_ep").asNewEp
         val originName: String
            get() = jsonObject.get("origin_name").asString
         val paster: Paster
            get() = jsonObject.get("paster").asPaster
         val payment: Payment
            get() = jsonObject.get("payment").asPayment
         val publish: Publish
            get() = jsonObject.get("publish").asPublish
         val rating: Rating
            get() = jsonObject.get("rating").asRating
         val record: String
            get() = jsonObject.get("record").asString
         val refineCover: String
            get() = jsonObject.get("refine_cover").asString
         val reserve: Reserve
            get() = jsonObject.get("reserve").asReserve
         val rights: Rights
            get() = jsonObject.get("rights").asRights
         val seasonId: Number
            get() = jsonObject.get("season_id").asNumber
         val seasonTitle: String
            get() = jsonObject.get("season_title").asString
         val seasons: List<Seasons>
            get() = jsonObject.get("seasons").asJsonArray.map { it.asSeasons }
         val series: Series
            get() = jsonObject.get("series").asSeries
         val shareCopy: String
            get() = jsonObject.get("share_copy").asString
         val shareUrl: String
            get() = jsonObject.get("share_url").asString
         val shortLink: String
            get() = jsonObject.get("short_link").asString
         val showSeasonType: Number
            get() = jsonObject.get("show_season_type").asNumber
         val sponsor: Sponsor
            get() = jsonObject.get("sponsor").asSponsor
         val squareCover: String
            get() = jsonObject.get("square_cover").asString
         val staff: Staff
            get() = jsonObject.get("staff").asStaff
         val stat: Stat
            get() = jsonObject.get("stat").asStat
         val status: Number
            get() = jsonObject.get("status").asNumber
         val styles: List<Styles>
            get() = jsonObject.get("styles").asJsonArray.map { it.asStyles }
         val subtitle: String
            get() = jsonObject.get("subtitle").asString
         val testSwitch: TestSwitch
            get() = jsonObject.get("test_switch").asTestSwitch
         val title: String
            get() = jsonObject.get("title").asString
         val total: Number
            get() = jsonObject.get("total").asNumber
         val type: Number
            get() = jsonObject.get("type").asNumber
         val typeDesc: String
            get() = jsonObject.get("type_desc").asString
         val typeName: String
            get() = jsonObject.get("type_name").asString
         val userStatus: UserStatus
            get() = jsonObject.get("user_status").asUserStatus
        private val JsonElement.asActivity
            get() = Activity(this.asJsonObject)
        class Activity (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val cover: String
                get() = jsonObject.get("cover").asString
             val h5Link: String
                get() = jsonObject.get("h5_link").asString
             val id: Number
                get() = jsonObject.get("id").asNumber
             val jumpMode: String
                get() = jsonObject.get("jump_mode").asString
             val link: String
                get() = jsonObject.get("link").asString
             val pendants: List<Pendants>
                get() = jsonObject.get("pendants").asJsonArray.map { it.asPendants }
             val picurl: String
                get() = jsonObject.get("picurl").asString
             val picurlSelected: String
                get() = jsonObject.get("picurl_selected").asString
             val showName: String
                get() = jsonObject.get("show_name").asString
             val threshold: List<Threshold>
                get() = jsonObject.get("threshold").asJsonArray.map { it.asThreshold }
             val title: String
                get() = jsonObject.get("title").asString
             val type: Number
                get() = jsonObject.get("type").asNumber
            private val JsonElement.asPendants
                get() = Pendants(this.asJsonObject)
            class Pendants (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val image: String
                    get() = jsonObject.get("image").asString
                 val name: String
                    get() = jsonObject.get("name").asString
                 val pid: Number
                    get() = jsonObject.get("pid").asNumber
            }
            private val JsonElement.asThreshold
                get() = Threshold(this.asJsonObject)
            class Threshold (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val bp: Number
                    get() = jsonObject.get("bp").asNumber
                 val days: Number
                    get() = jsonObject.get("days").asNumber
                 val daysText: String
                    get() = jsonObject.get("days_text").asString
            }
        }
        private val JsonElement.asActor
            get() = Actor(this.asJsonObject)
        class Actor (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val info: String
                get() = jsonObject.get("info").asString
             val title: String
                get() = jsonObject.get("title").asString
        }
        private val JsonElement.asAllButtons
            get() = AllButtons(this.asJsonObject)
        class AllButtons (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val watchFormal: String
                get() = jsonObject.get("watch_formal").asString
        }
        private val JsonElement.asAllUpInfos
            get() = AllUpInfos(this.asJsonObject)
        class AllUpInfos (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
        }
        private val JsonElement.asAreas
            get() = Areas(this.asJsonObject)
        class Areas (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val id: Number
                get() = jsonObject.get("id").asNumber
             val name: String
                get() = jsonObject.get("name").asString
        }
        private val JsonElement.asBadgeInfo
            get() = BadgeInfo(this.asJsonObject)
        class BadgeInfo (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val bgColor: String
                get() = jsonObject.get("bg_color").asString
             val bgColorNight: String
                get() = jsonObject.get("bg_color_night").asString
             val text: String
                get() = jsonObject.get("text").asString
        }
        private val JsonElement.asEpisodes
            get() = Episodes(this.asJsonObject)
        class Episodes (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val aid: Number
                get() = jsonObject.get("aid").asNumber
             val badge: String
                get() = jsonObject.get("badge").asString
             val badgeInfo: BadgeInfo
                get() = jsonObject.get("badge_info").asBadgeInfo
             val badgeType: Number
                get() = jsonObject.get("badge_type").asNumber
             val bvid: String
                get() = jsonObject.get("bvid").asString
             val cid: Number
                get() = jsonObject.get("cid").asNumber
             val cover: String
                get() = jsonObject.get("cover").asString
             val dimension: Dimension
                get() = jsonObject.get("dimension").asDimension
             val duration: Number
                get() = jsonObject.get("duration").asNumber
             val epIndex: Number
                get() = jsonObject.get("ep_index").asNumber
             val from: String
                get() = jsonObject.get("from").asString
             val id: Number
                get() = jsonObject.get("id").asNumber
             val isViewHide: Boolean
                get() = jsonObject.get("is_view_hide").asBoolean
             val link: String
                get() = jsonObject.get("link").asString
             val longTitle: String
                get() = jsonObject.get("long_title").asString
             val pubTime: Number
                get() = jsonObject.get("pub_time").asNumber
             val pv: Number
                get() = jsonObject.get("pv").asNumber
             val releaseDate: String
                get() = jsonObject.get("release_date").asString
             val report: Report
                get() = jsonObject.get("report").asReport
             val rights: Rights
                get() = jsonObject.get("rights").asRights
             val sectionIndex: Number
                get() = jsonObject.get("section_index").asNumber
             val shareCopy: String
                get() = jsonObject.get("share_copy").asString
             val shareUrl: String
                get() = jsonObject.get("share_url").asString
             val shortLink: String
                get() = jsonObject.get("short_link").asString
             val stat: Stat
                get() = jsonObject.get("stat").asStat
             val status: Number
                get() = jsonObject.get("status").asNumber
             val subtitle: String
                get() = jsonObject.get("subtitle").asString
             val title: String
                get() = jsonObject.get("title").asString
             val vid: String
                get() = jsonObject.get("vid").asString
            private val JsonElement.asBadgeInfo
                get() = BadgeInfo(this.asJsonObject)
            class BadgeInfo (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val bgColor: String
                    get() = jsonObject.get("bg_color").asString
                 val bgColorNight: String
                    get() = jsonObject.get("bg_color_night").asString
                 val text: String
                    get() = jsonObject.get("text").asString
            }
            private val JsonElement.asDimension
                get() = Dimension(this.asJsonObject)
            class Dimension (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val height: Number
                    get() = jsonObject.get("height").asNumber
                 val rotate: Number
                    get() = jsonObject.get("rotate").asNumber
                 val width: Number
                    get() = jsonObject.get("width").asNumber
            }
            private val JsonElement.asReport
                get() = Report(this.asJsonObject)
            class Report (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val aid: String
                    get() = jsonObject.get("aid").asString
                 val epTitle: String
                    get() = jsonObject.get("ep_title").asString
                 val epid: String
                    get() = jsonObject.get("epid").asString
                 val position: String
                    get() = jsonObject.get("position").asString
                 val seasonId: String
                    get() = jsonObject.get("season_id").asString
                 val seasonType: String
                    get() = jsonObject.get("season_type").asString
                 val sectionId: String
                    get() = jsonObject.get("section_id").asString
                 val sectionType: String
                    get() = jsonObject.get("section_type").asString
                 val style: String
                    get() = jsonObject.get("style").asString
            }
            private val JsonElement.asRights
                get() = Rights(this.asJsonObject)
            class Rights (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val allowDemand: Number
                    get() = jsonObject.get("allow_demand").asNumber
                 val allowDm: Number
                    get() = jsonObject.get("allow_dm").asNumber
                 val allowDownload: Number
                    get() = jsonObject.get("allow_download").asNumber
                 val areaLimit: Number
                    get() = jsonObject.get("area_limit").asNumber
            }
            private val JsonElement.asStat
                get() = Stat(this.asJsonObject)
            class Stat (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val coin: Number
                    get() = jsonObject.get("coin").asNumber
                 val danmakus: Number
                    get() = jsonObject.get("danmakus").asNumber
                 val likes: Number
                    get() = jsonObject.get("likes").asNumber
                 val play: Number
                    get() = jsonObject.get("play").asNumber
                 val reply: Number
                    get() = jsonObject.get("reply").asNumber
            }
        }
        private val JsonElement.asMediaBadgeInfo
            get() = MediaBadgeInfo(this.asJsonObject)
        class MediaBadgeInfo (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val bgColor: String
                get() = jsonObject.get("bg_color").asString
             val bgColorNight: String
                get() = jsonObject.get("bg_color_night").asString
             val text: String
                get() = jsonObject.get("text").asString
        }
        private val JsonElement.asModules
            get() = Modules(this.asJsonObject)
        class Modules (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val data: Data
                get() = jsonObject.get("data").asData
             val id: Number
                get() = jsonObject.get("id").asNumber
             val moduleStyle: ModuleStyle
                get() = jsonObject.get("module_style").asModuleStyle
             val more: String?
                get() = jsonObject.get("more")?.asString
             val style: String
                get() = jsonObject.get("style").asString
             val title: String
                get() = jsonObject.get("title").asString
            private val JsonElement.asData
                get() = Data(this.asJsonObject)
            class Data (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val episodes: List<Episodes>?
                    get() = jsonObject.get("episodes")?.asJsonArray?.map { it.asEpisodes }
                 val cover: String?
                    get() = jsonObject.get("cover")?.asString
                 val h5Link: String?
                    get() = jsonObject.get("h5_link")?.asString
                 val id: Number?
                    get() = jsonObject.get("id")?.asNumber
                 val jumpMode: String?
                    get() = jsonObject.get("jump_mode")?.asString
                 val link: String?
                    get() = jsonObject.get("link")?.asString
                 val pendants: List<Pendants>?
                    get() = jsonObject.get("pendants")?.asJsonArray?.map { it.asPendants }
                 val picurl: String?
                    get() = jsonObject.get("picurl")?.asString
                 val picurlSelected: String?
                    get() = jsonObject.get("picurl_selected")?.asString
                 val showName: String?
                    get() = jsonObject.get("show_name")?.asString
                 val threshold: List<Threshold>?
                    get() = jsonObject.get("threshold")?.asJsonArray?.map { it.asThreshold }
                 val title: String?
                    get() = jsonObject.get("title")?.asString
                 val type: Number?
                    get() = jsonObject.get("type")?.asNumber
                private val JsonElement.asEpisodes
                    get() = Episodes(this.asJsonObject)
                class Episodes (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val aid: Number
                        get() = jsonObject.get("aid").asNumber
                     val badge: String
                        get() = jsonObject.get("badge").asString
                     val badgeInfo: BadgeInfo
                        get() = jsonObject.get("badge_info").asBadgeInfo
                     val badgeType: Number
                        get() = jsonObject.get("badge_type").asNumber
                     val bvid: String
                        get() = jsonObject.get("bvid").asString
                     val cid: Number
                        get() = jsonObject.get("cid").asNumber
                     val cover: String
                        get() = jsonObject.get("cover").asString
                     val dimension: Dimension
                        get() = jsonObject.get("dimension").asDimension
                     val duration: Number
                        get() = jsonObject.get("duration").asNumber
                     val epIndex: Number
                        get() = jsonObject.get("ep_index").asNumber
                     val from: String
                        get() = jsonObject.get("from").asString
                     val id: Number
                        get() = jsonObject.get("id").asNumber
                     val isViewHide: Boolean
                        get() = jsonObject.get("is_view_hide").asBoolean
                     val link: String
                        get() = jsonObject.get("link").asString
                     val longTitle: String
                        get() = jsonObject.get("long_title").asString
                     val pubTime: Number
                        get() = jsonObject.get("pub_time").asNumber
                     val pv: Number
                        get() = jsonObject.get("pv").asNumber
                     val releaseDate: String
                        get() = jsonObject.get("release_date").asString
                     val report: Report
                        get() = jsonObject.get("report").asReport
                     val rights: Rights
                        get() = jsonObject.get("rights").asRights
                     val sectionIndex: Number
                        get() = jsonObject.get("section_index").asNumber
                     val shareCopy: String
                        get() = jsonObject.get("share_copy").asString
                     val shareUrl: String
                        get() = jsonObject.get("share_url").asString
                     val shortLink: String
                        get() = jsonObject.get("short_link").asString
                     val stat: Stat
                        get() = jsonObject.get("stat").asStat
                     val status: Number
                        get() = jsonObject.get("status").asNumber
                     val subtitle: String
                        get() = jsonObject.get("subtitle").asString
                     val title: String
                        get() = jsonObject.get("title").asString
                     val vid: String
                        get() = jsonObject.get("vid").asString
                    private val JsonElement.asBadgeInfo
                        get() = BadgeInfo(this.asJsonObject)
                    class BadgeInfo (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val bgColor: String
                            get() = jsonObject.get("bg_color").asString
                         val bgColorNight: String
                            get() = jsonObject.get("bg_color_night").asString
                         val text: String
                            get() = jsonObject.get("text").asString
                    }
                    private val JsonElement.asDimension
                        get() = Dimension(this.asJsonObject)
                    class Dimension (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val height: Number
                            get() = jsonObject.get("height").asNumber
                         val rotate: Number
                            get() = jsonObject.get("rotate").asNumber
                         val width: Number
                            get() = jsonObject.get("width").asNumber
                    }
                    private val JsonElement.asReport
                        get() = Report(this.asJsonObject)
                    class Report (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val aid: String
                            get() = jsonObject.get("aid").asString
                         val epTitle: String
                            get() = jsonObject.get("ep_title").asString
                         val epid: String
                            get() = jsonObject.get("epid").asString
                         val position: String
                            get() = jsonObject.get("position").asString
                         val seasonId: String
                            get() = jsonObject.get("season_id").asString
                         val seasonType: String
                            get() = jsonObject.get("season_type").asString
                         val sectionId: String
                            get() = jsonObject.get("section_id").asString
                         val sectionType: String
                            get() = jsonObject.get("section_type").asString
                         val style: String
                            get() = jsonObject.get("style").asString
                    }
                    private val JsonElement.asRights
                        get() = Rights(this.asJsonObject)
                    class Rights (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val allowDemand: Number
                            get() = jsonObject.get("allow_demand").asNumber
                         val allowDm: Number
                            get() = jsonObject.get("allow_dm").asNumber
                         val allowDownload: Number
                            get() = jsonObject.get("allow_download").asNumber
                         val areaLimit: Number
                            get() = jsonObject.get("area_limit").asNumber
                    }
                    private val JsonElement.asStat
                        get() = Stat(this.asJsonObject)
                    class Stat (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val coin: Number
                            get() = jsonObject.get("coin").asNumber
                         val danmakus: Number
                            get() = jsonObject.get("danmakus").asNumber
                         val likes: Number
                            get() = jsonObject.get("likes").asNumber
                         val play: Number
                            get() = jsonObject.get("play").asNumber
                         val reply: Number
                            get() = jsonObject.get("reply").asNumber
                    }
                }
                private val JsonElement.asPendants
                    get() = Pendants(this.asJsonObject)
                class Pendants (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val image: String
                        get() = jsonObject.get("image").asString
                     val name: String
                        get() = jsonObject.get("name").asString
                     val pid: Number
                        get() = jsonObject.get("pid").asNumber
                }
                private val JsonElement.asThreshold
                    get() = Threshold(this.asJsonObject)
                class Threshold (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val bp: Number
                        get() = jsonObject.get("bp").asNumber
                     val days: Number
                        get() = jsonObject.get("days").asNumber
                     val daysText: String
                        get() = jsonObject.get("days_text").asString
                }
            }
            private val JsonElement.asModuleStyle
                get() = ModuleStyle(this.asJsonObject)
            class ModuleStyle (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val hidden: Number?
                    get() = jsonObject.get("hidden")?.asNumber
                 val line: Number
                    get() = jsonObject.get("line").asNumber
            }
        }
        private val JsonElement.asNewEp
            get() = NewEp(this.asJsonObject)
        class NewEp (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val desc: String
                get() = jsonObject.get("desc").asString
             val id: Number
                get() = jsonObject.get("id").asNumber
             val isNew: Number
                get() = jsonObject.get("is_new").asNumber
             val more: String
                get() = jsonObject.get("more").asString
             val title: String
                get() = jsonObject.get("title").asString
        }
        private val JsonElement.asPaster
            get() = Paster(this.asJsonObject)
        class Paster (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val aid: Number
                get() = jsonObject.get("aid").asNumber
             val allowJump: Number
                get() = jsonObject.get("allow_jump").asNumber
             val cid: Number
                get() = jsonObject.get("cid").asNumber
             val duration: Number
                get() = jsonObject.get("duration").asNumber
             val type: Number
                get() = jsonObject.get("type").asNumber
             val url: String
                get() = jsonObject.get("url").asString
        }
        private val JsonElement.asPayment
            get() = Payment(this.asJsonObject)
        class Payment (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val dialog: Dialog
                get() = jsonObject.get("dialog").asDialog
             val payTip: PayTip
                get() = jsonObject.get("pay_tip").asPayTip
             val payType: PayType
                get() = jsonObject.get("pay_type").asPayType
             val price: String
                get() = jsonObject.get("price").asString
             val reportType: Number
                get() = jsonObject.get("report_type").asNumber
             val tvPrice: String
                get() = jsonObject.get("tv_price").asString
             val vipPromotion: String
                get() = jsonObject.get("vip_promotion").asString
            private val JsonElement.asDialog
                get() = Dialog(this.asJsonObject)
            class Dialog (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val btnRight: BtnRight
                    get() = jsonObject.get("btn_right").asBtnRight
                 val desc: String
                    get() = jsonObject.get("desc").asString
                 val title: String
                    get() = jsonObject.get("title").asString
                private val JsonElement.asBtnRight
                    get() = BtnRight(this.asJsonObject)
                class BtnRight (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val title: String
                        get() = jsonObject.get("title").asString
                     val type: String
                        get() = jsonObject.get("type").asString
                }
            }
            private val JsonElement.asPayTip
                get() = PayTip(this.asJsonObject)
            class PayTip (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val primary: Primary
                    get() = jsonObject.get("primary").asPrimary
                private val JsonElement.asPrimary
                    get() = Primary(this.asJsonObject)
                class Primary (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val angleStyle: Number
                        get() = jsonObject.get("angle_style").asNumber
                     val bgDayColor: String
                        get() = jsonObject.get("bg_day_color").asString
                     val bgLineColor: String
                        get() = jsonObject.get("bg_line_color").asString
                     val bgNightColor: String
                        get() = jsonObject.get("bg_night_color").asString
                     val bgNightLineColor: String
                        get() = jsonObject.get("bg_night_line_color").asString
                     val icon: String
                        get() = jsonObject.get("icon").asString
                     val orderReportParams: OrderReportParams
                        get() = jsonObject.get("order_report_params").asOrderReportParams
                     val report: Report
                        get() = jsonObject.get("report").asReport
                     val showTypeEnum: String
                        get() = jsonObject.get("showTypeEnum").asString
                     val showType: Number
                        get() = jsonObject.get("show_type").asNumber
                     val textColor: String
                        get() = jsonObject.get("text_color").asString
                     val textNightColor: String
                        get() = jsonObject.get("text_night_color").asString
                     val title: String
                        get() = jsonObject.get("title").asString
                     val type: Number
                        get() = jsonObject.get("type").asNumber
                     val url: String
                        get() = jsonObject.get("url").asString
                     val urlOpenType: Number
                        get() = jsonObject.get("url_open_type").asNumber
                     val viewStartTime: Number
                        get() = jsonObject.get("view_start_time").asNumber
                    private val JsonElement.asOrderReportParams
                        get() = OrderReportParams(this.asJsonObject)
                    class OrderReportParams (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val seasonId: String
                            get() = jsonObject.get("season_id").asString
                         val seasonType: String
                            get() = jsonObject.get("season_type").asString
                         val seasonStatus: String
                            get() = jsonObject.get("season_status").asString
                    }
                    private val JsonElement.asReport
                        get() = Report(this.asJsonObject)
                    class Report (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val clickEventId: String
                            get() = jsonObject.get("click_event_id").asString
                         val extends: Extends
                            get() = jsonObject.get("extends").asExtends
                         val showEventId: String
                            get() = jsonObject.get("show_event_id").asString
                        private val JsonElement.asExtends
                            get() = Extends(this.asJsonObject)
                        class Extends (private val jsonObject: JsonObject) {
                            fun getJsonObject()  = jsonObject
                            override fun toString()  = jsonObject.toString()
                             val seasonId: String
                                get() = jsonObject.get("season_id").asString
                             val seasonType: String
                                get() = jsonObject.get("season_type").asString
                             val seasonStatus: String
                                get() = jsonObject.get("season_status").asString
                             val vipDueDate: String
                                get() = jsonObject.get("vip_due_date").asString
                        }
                    }
                }
            }
            private val JsonElement.asPayType
                get() = PayType(this.asJsonObject)
            class PayType (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val allowTicket: Number
                    get() = jsonObject.get("allow_ticket").asNumber
            }
        }
        private val JsonElement.asPublish
            get() = Publish(this.asJsonObject)
        class Publish (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val isFinish: Number
                get() = jsonObject.get("is_finish").asNumber
             val isStarted: Number
                get() = jsonObject.get("is_started").asNumber
             val pubTime: String
                get() = jsonObject.get("pub_time").asString
             val pubTimeShow: String
                get() = jsonObject.get("pub_time_show").asString
             val releaseDateShow: String
                get() = jsonObject.get("release_date_show").asString
             val timeLengthShow: String
                get() = jsonObject.get("time_length_show").asString
             val unknowPubDate: Number
                get() = jsonObject.get("unknow_pub_date").asNumber
             val weekday: Number
                get() = jsonObject.get("weekday").asNumber
        }
        private val JsonElement.asRating
            get() = Rating(this.asJsonObject)
        class Rating (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val count: Number
                get() = jsonObject.get("count").asNumber
             val score: Number
                get() = jsonObject.get("score").asNumber
        }
        private val JsonElement.asReserve
            get() = Reserve(this.asJsonObject)
        class Reserve (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val tip: String
                get() = jsonObject.get("tip").asString
        }
        private val JsonElement.asRights
            get() = Rights(this.asJsonObject)
        class Rights (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val allowBp: Number
                get() = jsonObject.get("allow_bp").asNumber
             val allowBpRank: Number
                get() = jsonObject.get("allow_bp_rank").asNumber
             val allowDownload: Number
                get() = jsonObject.get("allow_download").asNumber
             val allowReview: Number
                get() = jsonObject.get("allow_review").asNumber
             val areaLimit: Number
                get() = jsonObject.get("area_limit").asNumber
             val banAreaShow: Number
                get() = jsonObject.get("ban_area_show").asNumber
             val canWatch: Number
                get() = jsonObject.get("can_watch").asNumber
             val copyright: String
                get() = jsonObject.get("copyright").asString
             val copyrightName: String
                get() = jsonObject.get("copyright_name").asString
             val forbidPre: Number
                get() = jsonObject.get("forbid_pre").asNumber
             val freyaWhite: Number
                get() = jsonObject.get("freya_white").asNumber
             val isCoverShow: Number
                get() = jsonObject.get("is_cover_show").asNumber
             val isPreview: Number
                get() = jsonObject.get("is_preview").asNumber
             val onlyVipDownload: Number
                get() = jsonObject.get("only_vip_download").asNumber
             val resource: String
                get() = jsonObject.get("resource").asString
             val watchPlatform: Number
                get() = jsonObject.get("watch_platform").asNumber
        }
        private val JsonElement.asSeasons
            get() = Seasons(this.asJsonObject)
        class Seasons (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val badge: String
                get() = jsonObject.get("badge").asString
             val badgeInfo: BadgeInfo
                get() = jsonObject.get("badge_info").asBadgeInfo
             val badgeType: Number
                get() = jsonObject.get("badge_type").asNumber
             val cover: String
                get() = jsonObject.get("cover").asString
             val isNew: Number
                get() = jsonObject.get("is_new").asNumber
             val link: String
                get() = jsonObject.get("link").asString
             val newEp: NewEp
                get() = jsonObject.get("new_ep").asNewEp
             val report: Report
                get() = jsonObject.get("report").asReport
             val resource: String
                get() = jsonObject.get("resource").asString
             val seasonId: Number
                get() = jsonObject.get("season_id").asNumber
             val seasonTitle: String
                get() = jsonObject.get("season_title").asString
             val title: String
                get() = jsonObject.get("title").asString
            private val JsonElement.asBadgeInfo
                get() = BadgeInfo(this.asJsonObject)
            class BadgeInfo (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val bgColor: String
                    get() = jsonObject.get("bg_color").asString
                 val bgColorNight: String
                    get() = jsonObject.get("bg_color_night").asString
                 val text: String
                    get() = jsonObject.get("text").asString
            }
            private val JsonElement.asNewEp
                get() = NewEp(this.asJsonObject)
            class NewEp (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val cover: String
                    get() = jsonObject.get("cover").asString
                 val id: Number
                    get() = jsonObject.get("id").asNumber
                 val isNew: Number
                    get() = jsonObject.get("is_new").asNumber
                 val title: String
                    get() = jsonObject.get("title").asString
            }
            private val JsonElement.asReport
                get() = Report(this.asJsonObject)
            class Report (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val seasonId: String
                    get() = jsonObject.get("season_id").asString
                 val seasonType: String
                    get() = jsonObject.get("season_type").asString
            }
        }
        private val JsonElement.asSeries
            get() = Series(this.asJsonObject)
        class Series (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val seriesId: Number
                get() = jsonObject.get("series_id").asNumber
             val seriesTitle: String
                get() = jsonObject.get("series_title").asString
        }
        private val JsonElement.asSponsor
            get() = Sponsor(this.asJsonObject)
        class Sponsor (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val list: List<List_>
                get() = jsonObject.get("list").asJsonArray.map { it.asList }
             val mine: Mine
                get() = jsonObject.get("mine").asMine
             val total: Number
                get() = jsonObject.get("total").asNumber
             val week: Number
                get() = jsonObject.get("week").asNumber
            private val JsonElement.asList
                get() = List_(this.asJsonObject)
            class List_ (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val face: String
                    get() = jsonObject.get("face").asString
                 val msg: String
                    get() = jsonObject.get("msg").asString
                 val uid: Number
                    get() = jsonObject.get("uid").asNumber
                 val uname: String
                    get() = jsonObject.get("uname").asString
            }
            private val JsonElement.asMine
                get() = Mine(this.asJsonObject)
            class Mine (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val amount: Number
                    get() = jsonObject.get("amount").asNumber
                 val msg: String
                    get() = jsonObject.get("msg").asString
                 val rank: Number
                    get() = jsonObject.get("rank").asNumber
            }
        }
        private val JsonElement.asStaff
            get() = Staff(this.asJsonObject)
        class Staff (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val info: String
                get() = jsonObject.get("info").asString
             val title: String
                get() = jsonObject.get("title").asString
        }
        private val JsonElement.asStat
            get() = Stat(this.asJsonObject)
        class Stat (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val coins: Number
                get() = jsonObject.get("coins").asNumber
             val danmakus: Number
                get() = jsonObject.get("danmakus").asNumber
             val favorite: Number
                get() = jsonObject.get("favorite").asNumber
             val favorites: Number
                get() = jsonObject.get("favorites").asNumber
             val followers: String
                get() = jsonObject.get("followers").asString
             val likes: Number
                get() = jsonObject.get("likes").asNumber
             val play: String
                get() = jsonObject.get("play").asString
             val reply: Number
                get() = jsonObject.get("reply").asNumber
             val share: Number
                get() = jsonObject.get("share").asNumber
             val views: Number
                get() = jsonObject.get("views").asNumber
        }
        private val JsonElement.asStyles
            get() = Styles(this.asJsonObject)
        class Styles (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val id: Number
                get() = jsonObject.get("id").asNumber
             val name: String
                get() = jsonObject.get("name").asString
             val url: String
                get() = jsonObject.get("url").asString
        }
        private val JsonElement.asTestSwitch
            get() = TestSwitch(this.asJsonObject)
        class TestSwitch (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val channelEntranceExpAction: Number
                get() = jsonObject.get("channel_entrance_exp_action").asNumber
             val isMergePreviewSection: Boolean
                get() = jsonObject.get("is_merge_preview_section").asBoolean
             val isOgvFavExp: Boolean
                get() = jsonObject.get("is_ogv_fav_exp").asBoolean
             val mergeSeasonEpUpperExp: Number
                get() = jsonObject.get("mergeSeasonEpUpperExp").asNumber
             val movieMarkAction: Number
                get() = jsonObject.get("movie_mark_action").asNumber
             val shortSpaceTitleExp: Number
                get() = jsonObject.get("short_space_title_exp").asNumber
             val wasIosPipExp: Boolean
                get() = jsonObject.get("was_ios_pip_exp").asBoolean
             val wasMergeExp: Boolean
                get() = jsonObject.get("was_merge_exp").asBoolean
             val wasPugvStyleOptimize: Boolean
                get() = jsonObject.get("was_pugv_style_optimize").asBoolean
        }
        private val JsonElement.asUserStatus
            get() = UserStatus(this.asJsonObject)
        class UserStatus (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val follow: Number
                get() = jsonObject.get("follow").asNumber
             val followBubble: Number
                get() = jsonObject.get("follow_bubble").asNumber
             val followStatus: Number
                get() = jsonObject.get("follow_status").asNumber
             val pay: Number
                get() = jsonObject.get("pay").asNumber
             val payFor: Number
                get() = jsonObject.get("pay_for").asNumber
             val progress: Progress
                get() = jsonObject.get("progress").asProgress
             val review: Review
                get() = jsonObject.get("review").asReview
             val sponsor: Number
                get() = jsonObject.get("sponsor").asNumber
             val vip: Number
                get() = jsonObject.get("vip").asNumber
             val vipFrozen: Number
                get() = jsonObject.get("vip_frozen").asNumber
            private val JsonElement.asProgress
                get() = Progress(this.asJsonObject)
            class Progress (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val lastEpId: Number
                    get() = jsonObject.get("last_ep_id").asNumber
                 val lastEpIndex: String
                    get() = jsonObject.get("last_ep_index").asString
                 val lastTime: Number
                    get() = jsonObject.get("last_time").asNumber
            }
            private val JsonElement.asReview
                get() = Review(this.asJsonObject)
            class Review (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val articleUrl: String
                    get() = jsonObject.get("article_url").asString
                 val isOpen: Number
                    get() = jsonObject.get("is_open").asNumber
                 val score: Number
                    get() = jsonObject.get("score").asNumber
            }
        }
    }
}