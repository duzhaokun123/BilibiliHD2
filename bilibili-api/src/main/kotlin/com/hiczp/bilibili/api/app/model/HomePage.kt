package  com.hiczp.bilibili.api.app.model
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom
@LazyjsonClass class HomePage (private val jsonObject: JsonObject) {
    companion object   {
        @LazyjsonFrom @JvmStatic fun from(jsonObject: JsonObject)  = HomePage(jsonObject)
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
         val items: List<Items>
            get() = jsonObject.get("items").asJsonArray.map { it.asItems }
         val config: Config
            get() = jsonObject.get("config").asConfig
        private val JsonElement.asItems
            get() = Items(this.asJsonObject)
        class Items (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val cardType: String
                get() = jsonObject.get("card_type").asString
             val cardGoto: String
                get() = jsonObject.get("card_goto").asString
             val goto: String
                get() = jsonObject.get("goto").asString
             val param: String
                get() = jsonObject.get("param").asString
             val cover: String
                get() = jsonObject.get("cover").asString
             val title: String
                get() = jsonObject.get("title").asString
             val uri: String
                get() = jsonObject.get("uri").asString
             val threePoint: ThreePoint
                get() = jsonObject.get("three_point").asThreePoint
             val args: Args
                get() = jsonObject.get("args").asArgs
             val idx: Number
                get() = jsonObject.get("idx").asNumber
             val threePointV2: List<ThreePointV2>
                get() = jsonObject.get("three_point_v2").asJsonArray.map { it.asThreePointV2 }
             val trackId: String
                get() = jsonObject.get("track_id").asString
             val avatar: Avatar?
                get() = jsonObject.get("avatar")?.asAvatar
             val coverLeftText2: String
                get() = jsonObject.get("cover_left_text_2").asString
             val coverLeftText3: String
                get() = jsonObject.get("cover_left_text_3").asString
             val coverBadge: String?
                get() = jsonObject.get("cover_badge")?.asString
             val coverBadgeStyle: CoverBadgeStyle?
                get() = jsonObject.get("cover_badge_style")?.asCoverBadgeStyle
             val desc: String
                get() = jsonObject.get("desc").asString
             val bvid: String?
                get() = jsonObject.get("bvid")?.asString
             val descButton: DescButton?
                get() = jsonObject.get("desc_button")?.asDescButton
             val playerArgs: PlayerArgs?
                get() = jsonObject.get("player_args")?.asPlayerArgs
             val mask: Mask?
                get() = jsonObject.get("mask")?.asMask
             val coverLeftText1: String?
                get() = jsonObject.get("cover_left_text_1")?.asString
             val topRcmdReason: String?
                get() = jsonObject.get("top_rcmd_reason")?.asString
             val officialIcon: Number?
                get() = jsonObject.get("official_icon")?.asNumber
             val canPlay: Number?
                get() = jsonObject.get("can_play")?.asNumber
             val topRcmdReasonStyle: TopRcmdReasonStyle?
                get() = jsonObject.get("top_rcmd_reason_style")?.asTopRcmdReasonStyle
             val adInfo: AdInfo?
                get() = jsonObject.get("ad_info")?.asAdInfo
             val covers: List<String>
                get() = jsonObject.get("covers").asJsonArray.map { it.asString }
            private val JsonElement.asThreePoint
                get() = ThreePoint(this.asJsonObject)
            class ThreePoint (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val dislikeReasons: List<DislikeReasons>
                    get() = jsonObject.get("dislike_reasons").asJsonArray.map { it.asDislikeReasons }
                 val feedbacks: List<Feedbacks>?
                    get() = jsonObject.get("feedbacks")?.asJsonArray?.map { it.asFeedbacks }
                 val watchLater: Number?
                    get() = jsonObject.get("watch_later")?.asNumber
                private val JsonElement.asDislikeReasons
                    get() = DislikeReasons(this.asJsonObject)
                class DislikeReasons (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val id: Number
                        get() = jsonObject.get("id").asNumber
                     val name: String
                        get() = jsonObject.get("name").asString
                     val toast: String
                        get() = jsonObject.get("toast").asString
                }
                private val JsonElement.asFeedbacks
                    get() = Feedbacks(this.asJsonObject)
                class Feedbacks (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val id: Number
                        get() = jsonObject.get("id").asNumber
                     val name: String
                        get() = jsonObject.get("name").asString
                     val toast: String
                        get() = jsonObject.get("toast").asString
                }
            }
            private val JsonElement.asArgs
                get() = Args(this.asJsonObject)
            class Args (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val upId: Number?
                    get() = jsonObject.get("up_id")?.asNumber
                 val upName: String?
                    get() = jsonObject.get("up_name")?.asString
                 val rid: Number?
                    get() = jsonObject.get("rid")?.asNumber
                 val rname: String?
                    get() = jsonObject.get("rname")?.asString
                 val tid: Number?
                    get() = jsonObject.get("tid")?.asNumber
                 val tname: String?
                    get() = jsonObject.get("tname")?.asString
                 val aid: Number?
                    get() = jsonObject.get("aid")?.asNumber
            }
            private val JsonElement.asThreePointV2
                get() = ThreePointV2(this.asJsonObject)
            class ThreePointV2 (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val reasons: List<Reasons>?
                    get() = jsonObject.get("reasons")?.asJsonArray?.map { it.asReasons }
                 val type: String
                    get() = jsonObject.get("type").asString
                 val title: String?
                    get() = jsonObject.get("title")?.asString
                 val icon: String?
                    get() = jsonObject.get("icon")?.asString
                 val subtitle: String?
                    get() = jsonObject.get("subtitle")?.asString
                private val JsonElement.asReasons
                    get() = Reasons(this.asJsonObject)
                class Reasons (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val id: Number
                        get() = jsonObject.get("id").asNumber
                     val name: String
                        get() = jsonObject.get("name").asString
                     val toast: String
                        get() = jsonObject.get("toast").asString
                }
            }
            private val JsonElement.asAvatar
                get() = Avatar(this.asJsonObject)
            class Avatar (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val cover: String
                    get() = jsonObject.get("cover").asString
                 val type: Number?
                    get() = jsonObject.get("type")?.asNumber
                 val uri: String?
                    get() = jsonObject.get("uri")?.asString
                 val event: String?
                    get() = jsonObject.get("event")?.asString
                 val eventV2: String?
                    get() = jsonObject.get("event_v2")?.asString
                 val upId: Number?
                    get() = jsonObject.get("up_id")?.asNumber
            }
            private val JsonElement.asCoverBadgeStyle
                get() = CoverBadgeStyle(this.asJsonObject)
            class CoverBadgeStyle (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val text: String
                    get() = jsonObject.get("text").asString
                 val textColor: String
                    get() = jsonObject.get("text_color").asString
                 val bgColor: String
                    get() = jsonObject.get("bg_color").asString
                 val borderColor: String
                    get() = jsonObject.get("border_color").asString
                 val textColorNight: String
                    get() = jsonObject.get("text_color_night").asString
                 val bgColorNight: String
                    get() = jsonObject.get("bg_color_night").asString
                 val borderColorNight: String
                    get() = jsonObject.get("border_color_night").asString
                 val bgStyle: Number
                    get() = jsonObject.get("bg_style").asNumber
            }
            private val JsonElement.asDescButton
                get() = DescButton(this.asJsonObject)
            class DescButton (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val text: String
                    get() = jsonObject.get("text").asString
                 val uri: String
                    get() = jsonObject.get("uri").asString
                 val event: String
                    get() = jsonObject.get("event").asString
                 val type: Number
                    get() = jsonObject.get("type").asNumber
                 val eventV2: String
                    get() = jsonObject.get("event_v2").asString
            }
            private val JsonElement.asPlayerArgs
                get() = PlayerArgs(this.asJsonObject)
            class PlayerArgs (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val aid: Number
                    get() = jsonObject.get("aid").asNumber
                 val cid: Number
                    get() = jsonObject.get("cid").asNumber
                 val type: String
                    get() = jsonObject.get("type").asString
                 val duration: Number
                    get() = jsonObject.get("duration").asNumber
            }
            private val JsonElement.asMask
                get() = Mask(this.asJsonObject)
            class Mask (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val avatar: Avatar
                    get() = jsonObject.get("avatar").asAvatar
                 val button: Button
                    get() = jsonObject.get("button").asButton
                private val JsonElement.asAvatar
                    get() = Avatar(this.asJsonObject)
                class Avatar (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val cover: String
                        get() = jsonObject.get("cover").asString
                     val text: String
                        get() = jsonObject.get("text").asString
                     val uri: String
                        get() = jsonObject.get("uri").asString
                     val event: String
                        get() = jsonObject.get("event").asString
                     val eventV2: String
                        get() = jsonObject.get("event_v2").asString
                     val upId: Number
                        get() = jsonObject.get("up_id").asNumber
                }
                private val JsonElement.asButton
                    get() = Button(this.asJsonObject)
                class Button (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val text: String
                        get() = jsonObject.get("text").asString
                     val param: String
                        get() = jsonObject.get("param").asString
                     val event: String
                        get() = jsonObject.get("event").asString
                     val type: Number
                        get() = jsonObject.get("type").asNumber
                     val eventV2: String
                        get() = jsonObject.get("event_v2").asString
                }
            }
            private val JsonElement.asTopRcmdReasonStyle
                get() = TopRcmdReasonStyle(this.asJsonObject)
            class TopRcmdReasonStyle (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val text: String
                    get() = jsonObject.get("text").asString
                 val textColor: String
                    get() = jsonObject.get("text_color").asString
                 val bgColor: String
                    get() = jsonObject.get("bg_color").asString
                 val borderColor: String
                    get() = jsonObject.get("border_color").asString
                 val textColorNight: String
                    get() = jsonObject.get("text_color_night").asString
                 val bgColorNight: String
                    get() = jsonObject.get("bg_color_night").asString
                 val borderColorNight: String
                    get() = jsonObject.get("border_color_night").asString
                 val bgStyle: Number
                    get() = jsonObject.get("bg_style").asNumber
            }
            private val JsonElement.asAdInfo
                get() = AdInfo(this.asJsonObject)
            class AdInfo (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val creativeId: Number
                    get() = jsonObject.get("creative_id").asNumber
                 val creativeType: Number
                    get() = jsonObject.get("creative_type").asNumber
                 val cardType: Number
                    get() = jsonObject.get("card_type").asNumber
                 val creativeContent: CreativeContent
                    get() = jsonObject.get("creative_content").asCreativeContent
                 val adCb: String
                    get() = jsonObject.get("ad_cb").asString
                 val resource: Number
                    get() = jsonObject.get("resource").asNumber
                 val source: Number
                    get() = jsonObject.get("source").asNumber
                 val requestId: String
                    get() = jsonObject.get("request_id").asString
                 val isAd: Boolean
                    get() = jsonObject.get("is_ad").asBoolean
                 val cmMark: Number
                    get() = jsonObject.get("cm_mark").asNumber
                 val index: Number
                    get() = jsonObject.get("index").asNumber
                 val isAdLoc: Boolean
                    get() = jsonObject.get("is_ad_loc").asBoolean
                 val cardIndex: Number
                    get() = jsonObject.get("card_index").asNumber
                 val clientIp: String
                    get() = jsonObject.get("client_ip").asString
                 val extra: Extra
                    get() = jsonObject.get("extra").asExtra
                 val creativeStyle: Number
                    get() = jsonObject.get("creative_style").asNumber
                private val JsonElement.asCreativeContent
                    get() = CreativeContent(this.asJsonObject)
                class CreativeContent (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val title: String
                        get() = jsonObject.get("title").asString
                     val description: String
                        get() = jsonObject.get("description").asString
                     val videoId: Number
                        get() = jsonObject.get("video_id").asNumber
                     val imageUrl: String
                        get() = jsonObject.get("image_url").asString
                     val imageMd5: String
                        get() = jsonObject.get("image_md5").asString
                     val url: String
                        get() = jsonObject.get("url").asString
                }
                private val JsonElement.asExtra
                    get() = Extra(this.asJsonObject)
                class Extra (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val actImg: String
                        get() = jsonObject.get("act_img").asString
                     val adContentType: Number
                        get() = jsonObject.get("ad_content_type").asNumber
                     val appstorePriority: Number
                        get() = jsonObject.get("appstore_priority").asNumber
                     val appstoreUrl: String
                        get() = jsonObject.get("appstore_url").asString
                     val bgImg: String
                        get() = jsonObject.get("bg_img").asString
                     val card: Card
                        get() = jsonObject.get("card").asCard
                     val clickArea: Number
                        get() = jsonObject.get("click_area").asNumber
                     val enableDoubleJump: Boolean
                        get() = jsonObject.get("enable_double_jump").asBoolean
                     val enableDownloadDialog: Boolean
                        get() = jsonObject.get("enable_download_dialog").asBoolean
                     val enableH5Alert: Boolean
                        get() = jsonObject.get("enable_h5_alert").asBoolean
                     val enableH5PreLoad: Number
                        get() = jsonObject.get("enable_h5_pre_load").asNumber
                     val enableShare: Boolean
                        get() = jsonObject.get("enable_share").asBoolean
                     val enableStoreDirectLaunch: Number
                        get() = jsonObject.get("enable_store_direct_launch").asNumber
                     val feedbackPanelStyle: Number
                        get() = jsonObject.get("feedback_panel_style").asNumber
                     val fromTrackId: String
                        get() = jsonObject.get("from_track_id").asString
                     val h5PreLoadUrl: String
                        get() = jsonObject.get("h5_pre_load_url").asString
                     val landingpageDownloadStyle: Number
                        get() = jsonObject.get("landingpage_download_style").asNumber
                     val layout: String
                        get() = jsonObject.get("layout").asString
                     val macroReplacePriority: Number
                        get() = jsonObject.get("macro_replace_priority").asNumber
                     val preloadLandingpage: Number
                        get() = jsonObject.get("preload_landingpage").asNumber
                     val productId: Number
                        get() = jsonObject.get("product_id").asNumber
                     val reportTime: Number
                        get() = jsonObject.get("report_time").asNumber
                     val salesType: Number
                        get() = jsonObject.get("sales_type").asNumber
                     val shopId: Number
                        get() = jsonObject.get("shop_id").asNumber
                     val specialIndustry: Boolean
                        get() = jsonObject.get("special_industry").asBoolean
                     val specialIndustryStyle: Number
                        get() = jsonObject.get("special_industry_style").asNumber
                     val specialIndustryTips: String
                        get() = jsonObject.get("special_industry_tips").asString
                     val storeCallupCard: Boolean
                        get() = jsonObject.get("store_callup_card").asBoolean
                     val trackId: String
                        get() = jsonObject.get("track_id").asString
                     val upMid: Number
                        get() = jsonObject.get("up_mid").asNumber
                     val upzoneEntranceReportId: Number
                        get() = jsonObject.get("upzone_entrance_report_id").asNumber
                     val upzoneEntranceType: Number
                        get() = jsonObject.get("upzone_entrance_type").asNumber
                     val useAdWebV2: Boolean
                        get() = jsonObject.get("use_ad_web_v2").asBoolean
                    private val JsonElement.asCard
                        get() = Card(this.asJsonObject)
                    class Card (private val jsonObject: JsonObject) {
                        fun getJsonObject()  = jsonObject
                        override fun toString()  = jsonObject.toString()
                         val adTag: String
                            get() = jsonObject.get("ad_tag").asString
                         val adTagStyle: AdTagStyle
                            get() = jsonObject.get("ad_tag_style").asAdTagStyle
                         val adver: Adver
                            get() = jsonObject.get("adver").asAdver
                         val adverAccountId: Number
                            get() = jsonObject.get("adver_account_id").asNumber
                         val adverLogo: String
                            get() = jsonObject.get("adver_logo").asString
                         val adverMid: Number
                            get() = jsonObject.get("adver_mid").asNumber
                         val adverName: String
                            get() = jsonObject.get("adver_name").asString
                         val adverPageUrl: String
                            get() = jsonObject.get("adver_page_url").asString
                         val callupUrl: String
                            get() = jsonObject.get("callup_url").asString
                         val cardType: Number
                            get() = jsonObject.get("card_type").asNumber
                         val covers: List<Covers>
                            get() = jsonObject.get("covers").asJsonArray.map { it.asCovers }
                         val desc: String
                            get() = jsonObject.get("desc").asString
                         val duration: String
                            get() = jsonObject.get("duration").asString
                         val dynamicText: String
                            get() = jsonObject.get("dynamic_text").asString
                         val extraDesc: String
                            get() = jsonObject.get("extra_desc").asString
                         val extremeTeamIcon: String
                            get() = jsonObject.get("extreme_team_icon").asString
                         val extremeTeamStatus: Boolean
                            get() = jsonObject.get("extreme_team_status").asBoolean
                         val feedbackPanel: FeedbackPanel
                            get() = jsonObject.get("feedback_panel").asFeedbackPanel
                         val foldTime: Number
                            get() = jsonObject.get("fold_time").asNumber
                         val goodsCurPrice: String
                            get() = jsonObject.get("goods_cur_price").asString
                         val goodsOriPrice: String
                            get() = jsonObject.get("goods_ori_price").asString
                         val gradeDenominator: Number
                            get() = jsonObject.get("grade_denominator").asNumber
                         val gradeLevel: Number
                            get() = jsonObject.get("grade_level").asNumber
                         val imaxLandingPageV2: String
                            get() = jsonObject.get("imax_landing_page_v2").asString
                         val jumpUrl: String
                            get() = jsonObject.get("jump_url").asString
                         val liveBookingPopulationThreshold: Number
                            get() = jsonObject.get("live_booking_population_threshold").asNumber
                         val liveRoomArea: String
                            get() = jsonObject.get("live_room_area").asString
                         val liveRoomPopularity: Number
                            get() = jsonObject.get("live_room_popularity").asNumber
                         val liveRoomTitle: String
                            get() = jsonObject.get("live_room_title").asString
                         val liveStreamerFace: String
                            get() = jsonObject.get("live_streamer_face").asString
                         val liveStreamerName: String
                            get() = jsonObject.get("live_streamer_name").asString
                         val liveTagShow: Boolean
                            get() = jsonObject.get("live_tag_show").asBoolean
                         val longDesc: String
                            get() = jsonObject.get("long_desc").asString
                         val oriMarkHidden: Number
                            get() = jsonObject.get("ori_mark_hidden").asNumber
                         val ottJumpUrl: String
                            get() = jsonObject.get("ott_jump_url").asString
                         val priceDesc: String
                            get() = jsonObject.get("price_desc").asString
                         val priceSymbol: String
                            get() = jsonObject.get("price_symbol").asString
                         val starLevel: Number
                            get() = jsonObject.get("star_level").asNumber
                         val supportTransition: Boolean
                            get() = jsonObject.get("support_transition").asBoolean
                         val title: String
                            get() = jsonObject.get("title").asString
                         val transition: String
                            get() = jsonObject.get("transition").asString
                         val underPlayerInteractionStyle: Number
                            get() = jsonObject.get("under_player_interaction_style").asNumber
                         val universalApp: String
                            get() = jsonObject.get("universal_app").asString
                         val video: Video
                            get() = jsonObject.get("video").asVideo
                        private val JsonElement.asAdTagStyle
                            get() = AdTagStyle(this.asJsonObject)
                        class AdTagStyle (private val jsonObject: JsonObject) {
                            fun getJsonObject()  = jsonObject
                            override fun toString()  = jsonObject.toString()
                             val bgBorderColor: String
                                get() = jsonObject.get("bg_border_color").asString
                             val bgColor: String
                                get() = jsonObject.get("bg_color").asString
                             val bgColorNight: String
                                get() = jsonObject.get("bg_color_night").asString
                             val borderColor: String
                                get() = jsonObject.get("border_color").asString
                             val borderColorNight: String
                                get() = jsonObject.get("border_color_night").asString
                             val imgHeight: Number
                                get() = jsonObject.get("img_height").asNumber
                             val imgUrl: String
                                get() = jsonObject.get("img_url").asString
                             val imgWidth: Number
                                get() = jsonObject.get("img_width").asNumber
                             val text: String
                                get() = jsonObject.get("text").asString
                             val textColor: String
                                get() = jsonObject.get("text_color").asString
                             val textColorNight: String
                                get() = jsonObject.get("text_color_night").asString
                             val type: Number
                                get() = jsonObject.get("type").asNumber
                        }
                        private val JsonElement.asAdver
                            get() = Adver(this.asJsonObject)
                        class Adver (private val jsonObject: JsonObject) {
                            fun getJsonObject()  = jsonObject
                            override fun toString()  = jsonObject.toString()
                             val adverDesc: String
                                get() = jsonObject.get("adver_desc").asString
                             val adverId: Number
                                get() = jsonObject.get("adver_id").asNumber
                             val adverLogo: String
                                get() = jsonObject.get("adver_logo").asString
                             val adverName: String
                                get() = jsonObject.get("adver_name").asString
                             val adverPageUrl: String
                                get() = jsonObject.get("adver_page_url").asString
                             val adverType: Number
                                get() = jsonObject.get("adver_type").asNumber
                        }
                        private val JsonElement.asCovers
                            get() = Covers(this.asJsonObject)
                        class Covers (private val jsonObject: JsonObject) {
                            fun getJsonObject()  = jsonObject
                            override fun toString()  = jsonObject.toString()
                             val gifTagShow: Boolean
                                get() = jsonObject.get("gif_tag_show").asBoolean
                             val gifUrl: String
                                get() = jsonObject.get("gif_url").asString
                             val imageHeight: Number
                                get() = jsonObject.get("image_height").asNumber
                             val imageWidth: Number
                                get() = jsonObject.get("image_width").asNumber
                             val loop: Number
                                get() = jsonObject.get("loop").asNumber
                             val url: String
                                get() = jsonObject.get("url").asString
                        }
                        private val JsonElement.asFeedbackPanel
                            get() = FeedbackPanel(this.asJsonObject)
                        class FeedbackPanel (private val jsonObject: JsonObject) {
                            fun getJsonObject()  = jsonObject
                            override fun toString()  = jsonObject.toString()
                             val closeRecTips: String
                                get() = jsonObject.get("close_rec_tips").asString
                             val feedbackPanelDetail: List<FeedbackPanelDetail>
                                get() = jsonObject.get("feedback_panel_detail").asJsonArray.map { it.asFeedbackPanelDetail }
                             val openRecTips: String
                                get() = jsonObject.get("open_rec_tips").asString
                             val panelTypeText: String
                                get() = jsonObject.get("panel_type_text").asString
                             val toast: String
                                get() = jsonObject.get("toast").asString
                            private val JsonElement.asFeedbackPanelDetail
                                get() = FeedbackPanelDetail(this.asJsonObject)
                            class FeedbackPanelDetail (private val jsonObject: JsonObject) {
                                fun getJsonObject()  = jsonObject
                                override fun toString()  = jsonObject.toString()
                                 val iconUrl: String
                                    get() = jsonObject.get("icon_url").asString
                                 val jumpType: Number
                                    get() = jsonObject.get("jump_type").asNumber
                                 val jumpUrl: String
                                    get() = jsonObject.get("jump_url").asString
                                 val moduleId: Number
                                    get() = jsonObject.get("module_id").asNumber
                                 val secondaryPanel: List<SecondaryPanel>?
                                    get() = jsonObject.get("secondary_panel")?.asJsonArray?.map { it.asSecondaryPanel }
                                 val subText: String
                                    get() = jsonObject.get("sub_text").asString
                                 val text: String
                                    get() = jsonObject.get("text").asString
                                private val JsonElement.asSecondaryPanel
                                    get() = SecondaryPanel(this.asJsonObject)
                                class SecondaryPanel (private val jsonObject: JsonObject) {
                                    fun getJsonObject()  = jsonObject
                                    override fun toString()  = jsonObject.toString()
                                     val reasonId: Number
                                        get() = jsonObject.get("reason_id").asNumber
                                     val text: String
                                        get() = jsonObject.get("text").asString
                                }
                            }
                        }
                        private val JsonElement.asVideo
                            get() = Video(this.asJsonObject)
                        class Video (private val jsonObject: JsonObject) {
                            fun getJsonObject()  = jsonObject
                            override fun toString()  = jsonObject.toString()
                             val autoPlay: Boolean
                                get() = jsonObject.get("auto_play").asBoolean
                             val autoPlayValue: Number
                                get() = jsonObject.get("auto_play_value").asNumber
                             val avid: Number
                                get() = jsonObject.get("avid").asNumber
                             val bizId: Number
                                get() = jsonObject.get("biz_id").asNumber
                             val btnDycColor: Boolean
                                get() = jsonObject.get("btn_dyc_color").asBoolean
                             val btnDycTime: Number
                                get() = jsonObject.get("btn_dyc_time").asNumber
                             val cid: Number
                                get() = jsonObject.get("cid").asNumber
                             val cover: String
                                get() = jsonObject.get("cover").asString
                             val epId: Number
                                get() = jsonObject.get("ep_id").asNumber
                             val from: String
                                get() = jsonObject.get("from").asString
                             val fromSpmid: String
                                get() = jsonObject.get("from_spmid").asString
                             val orientation: Number
                                get() = jsonObject.get("orientation").asNumber
                             val page: Number
                                get() = jsonObject.get("page").asNumber
                             val seasonId: Number
                                get() = jsonObject.get("season_id").asNumber
                             val url: String
                                get() = jsonObject.get("url").asString
                        }
                    }
                }
            }
        }
        private val JsonElement.asConfig
            get() = Config(this.asJsonObject)
        class Config (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val column: Number
                get() = jsonObject.get("column").asNumber
             val autoplayCard: Number
                get() = jsonObject.get("autoplay_card").asNumber
             val feedCleanAbtest: Number
                get() = jsonObject.get("feed_clean_abtest").asNumber
             val homeTransferTest: Number
                get() = jsonObject.get("home_transfer_test").asNumber
             val autoRefreshTime: Number
                get() = jsonObject.get("auto_refresh_time").asNumber
             val showInlineDanmaku: Number
                get() = jsonObject.get("show_inline_danmaku").asNumber
             val toast: Toast
                get() = jsonObject.get("toast").asToast
             val isBackToHomepage: Boolean
                get() = jsonObject.get("is_back_to_homepage").asBoolean
             val enableRcmdGuide: Boolean
                get() = jsonObject.get("enable_rcmd_guide").asBoolean
             val inlineSound: Number
                get() = jsonObject.get("inline_sound").asNumber
             val autoRefreshTimeByAppear: Number
                get() = jsonObject.get("auto_refresh_time_by_appear").asNumber
             val autoRefreshTimeByActive: Number
                get() = jsonObject.get("auto_refresh_time_by_active").asNumber
            private val JsonElement.asToast
                get() = Toast(this.asJsonObject)
            class Toast (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
            }
        }
    }
}