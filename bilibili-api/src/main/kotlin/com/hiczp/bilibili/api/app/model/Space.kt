package  com.hiczp.bilibili.api.app.model
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom
@LazyjsonClass class Space (private val jsonObject: JsonObject) {
    companion object   {
        @LazyjsonFrom @JvmStatic fun from(jsonObject: JsonObject)  = Space(jsonObject)
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
         val relation: Number
            get() = jsonObject.get("relation").asNumber
         val guestRelation: Number
            get() = jsonObject.get("guest_relation").asNumber
         val defaultTab: String
            get() = jsonObject.get("default_tab").asString
         val setting: Setting
            get() = jsonObject.get("setting").asSetting
         val tab: Tab
            get() = jsonObject.get("tab").asTab
         val card: Card
            get() = jsonObject.get("card").asCard
         val images: Images
            get() = jsonObject.get("images").asImages
         val live: Live
            get() = jsonObject.get("live").asLive
         val series: Series
            get() = jsonObject.get("series").asSeries
         val playGame: PlayGame
            get() = jsonObject.get("play_game").asPlayGame
         val article: Article
            get() = jsonObject.get("article").asArticle
         val favourite: Favourite
            get() = jsonObject.get("favourite").asFavourite
         val season: Season
            get() = jsonObject.get("season").asSeason
         val coinArchive: CoinArchive
            get() = jsonObject.get("coin_archive").asCoinArchive
         val likeArchive: LikeArchive
            get() = jsonObject.get("like_archive").asLikeArchive
         val audios: Audios
            get() = jsonObject.get("audios").asAudios
         val vipSpaceLabel: VipSpaceLabel
            get() = jsonObject.get("vip_space_label").asVipSpaceLabel
         val tab2: List<Tab2>
            get() = jsonObject.get("tab2").asJsonArray.map { it.asTab2 }
        private val JsonElement.asSetting
            get() = Setting(this.asJsonObject)
        class Setting (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val channel: Number
                get() = jsonObject.get("channel").asNumber
             val favVideo: Number
                get() = jsonObject.get("fav_video").asNumber
             val coinsVideo: Number
                get() = jsonObject.get("coins_video").asNumber
             val likesVideo: Number
                get() = jsonObject.get("likes_video").asNumber
             val bangumi: Number
                get() = jsonObject.get("bangumi").asNumber
             val playedGame: Number
                get() = jsonObject.get("played_game").asNumber
             val groups: Number
                get() = jsonObject.get("groups").asNumber
             val comic: Number
                get() = jsonObject.get("comic").asNumber
             val bbq: Number
                get() = jsonObject.get("bbq").asNumber
             val dressUp: Number
                get() = jsonObject.get("dress_up").asNumber
             val disableFollowing: Number
                get() = jsonObject.get("disable_following").asNumber
             val livePlayback: Number
                get() = jsonObject.get("live_playback").asNumber
             val closeSpaceMedal: Number
                get() = jsonObject.get("close_space_medal").asNumber
             val onlyShowWearing: Number
                get() = jsonObject.get("only_show_wearing").asNumber
             val disableShowSchool: Number
                get() = jsonObject.get("disable_show_school").asNumber
             val disableShowNft: Number
                get() = jsonObject.get("disable_show_nft").asNumber
        }
        private val JsonElement.asTab
            get() = Tab(this.asJsonObject)
        class Tab (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val archive: Boolean
                get() = jsonObject.get("archive").asBoolean
             val article: Boolean
                get() = jsonObject.get("article").asBoolean
             val clip: Boolean
                get() = jsonObject.get("clip").asBoolean
             val album: Boolean
                get() = jsonObject.get("album").asBoolean
             val favorite: Boolean
                get() = jsonObject.get("favorite").asBoolean
             val bangumi: Boolean
                get() = jsonObject.get("bangumi").asBoolean
             val coin: Boolean
                get() = jsonObject.get("coin").asBoolean
             val like: Boolean
                get() = jsonObject.get("like").asBoolean
             val community: Boolean
                get() = jsonObject.get("community").asBoolean
             val dynamic: Boolean
                get() = jsonObject.get("dynamic").asBoolean
             val audios: Boolean
                get() = jsonObject.get("audios").asBoolean
             val shop: Boolean
                get() = jsonObject.get("shop").asBoolean
             val mall: Boolean
                get() = jsonObject.get("mall").asBoolean
             val ugcSeason: Boolean
                get() = jsonObject.get("ugc_season").asBoolean
             val comic: Boolean
                get() = jsonObject.get("comic").asBoolean
             val cheese: Boolean
                get() = jsonObject.get("cheese").asBoolean
             val subComic: Boolean
                get() = jsonObject.get("sub_comic").asBoolean
             val activity: Boolean
                get() = jsonObject.get("activity").asBoolean
             val series: Boolean
                get() = jsonObject.get("series").asBoolean
        }
        private val JsonElement.asCard
            get() = Card(this.asJsonObject)
        class Card (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val mid: String
                get() = jsonObject.get("mid").asString
             val name: String
                get() = jsonObject.get("name").asString
             val approve: Boolean
                get() = jsonObject.get("approve").asBoolean
             val sex: String
                get() = jsonObject.get("sex").asString
             val rank: String
                get() = jsonObject.get("rank").asString
             val face: String
                get() = jsonObject.get("face").asString
             val DisplayRank: String
                get() = jsonObject.get("DisplayRank").asString
             val regtime: Number
                get() = jsonObject.get("regtime").asNumber
             val spacesta: Number
                get() = jsonObject.get("spacesta").asNumber
             val birthday: String
                get() = jsonObject.get("birthday").asString
             val place: String
                get() = jsonObject.get("place").asString
             val description: String
                get() = jsonObject.get("description").asString
             val article: Number
                get() = jsonObject.get("article").asNumber
             val fans: Number
                get() = jsonObject.get("fans").asNumber
             val friend: Number
                get() = jsonObject.get("friend").asNumber
             val attention: Number
                get() = jsonObject.get("attention").asNumber
             val sign: String
                get() = jsonObject.get("sign").asString
             val levelInfo: LevelInfo
                get() = jsonObject.get("level_info").asLevelInfo
             val pendant: Pendant
                get() = jsonObject.get("pendant").asPendant
             val nameplate: Nameplate
                get() = jsonObject.get("nameplate").asNameplate
             val officialVerify: OfficialVerify
                get() = jsonObject.get("official_verify").asOfficialVerify
             val professionVerify: ProfessionVerify
                get() = jsonObject.get("profession_verify").asProfessionVerify
             val vip: Vip
                get() = jsonObject.get("vip").asVip
             val silence: Number
                get() = jsonObject.get("silence").asNumber
             val endTime: Number
                get() = jsonObject.get("end_time").asNumber
             val silenceUrl: String
                get() = jsonObject.get("silence_url").asString
             val likes: Likes
                get() = jsonObject.get("likes").asLikes
             val achieve: Achieve
                get() = jsonObject.get("achieve").asAchieve
             val pendantUrl: String
                get() = jsonObject.get("pendant_url").asString
             val pendantTitle: String
                get() = jsonObject.get("pendant_title").asString
             val prInfo: PrInfo
                get() = jsonObject.get("pr_info").asPrInfo
             val relation: Relation
                get() = jsonObject.get("relation").asRelation
             val isDeleted: Number
                get() = jsonObject.get("is_deleted").asNumber
             val honours: Honours
                get() = jsonObject.get("honours").asHonours
             val liveFansWearing: LiveFansWearing
                get() = jsonObject.get("live_fans_wearing").asLiveFansWearing
             val profession: Profession
                get() = jsonObject.get("profession").asProfession
             val school: School
                get() = jsonObject.get("school").asSchool
             val spaceTag: List<SpaceTag>
                get() = jsonObject.get("space_tag").asJsonArray.map { it.asSpaceTag }
             val faceNftNew: Number
                get() = jsonObject.get("face_nft_new").asNumber
             val hasFaceNft: Boolean
                get() = jsonObject.get("has_face_nft").asBoolean
             val nftId: String
                get() = jsonObject.get("nft_id").asString
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
                 val identity: Number
                    get() = jsonObject.get("identity").asNumber
                 val seniorInquiry: SeniorInquiry
                    get() = jsonObject.get("senior_inquiry").asSeniorInquiry
                private val JsonElement.asSeniorInquiry
                    get() = SeniorInquiry(this.asJsonObject)
                class SeniorInquiry (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val inquiryText: String
                        get() = jsonObject.get("inquiry_text").asString
                     val inquiryUrl: String
                        get() = jsonObject.get("inquiry_url").asString
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
                 val role: Number
                    get() = jsonObject.get("role").asNumber
                 val title: String
                    get() = jsonObject.get("title").asString
            }
            private val JsonElement.asProfessionVerify
                get() = ProfessionVerify(this.asJsonObject)
            class ProfessionVerify (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val icon: String
                    get() = jsonObject.get("icon").asString
                 val showDesc: String
                    get() = jsonObject.get("show_desc").asString
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
            private val JsonElement.asLikes
                get() = Likes(this.asJsonObject)
            class Likes (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val likeNum: Number
                    get() = jsonObject.get("like_num").asNumber
                 val skrTip: String
                    get() = jsonObject.get("skr_tip").asString
            }
            private val JsonElement.asAchieve
                get() = Achieve(this.asJsonObject)
            class Achieve (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val isDefault: Boolean
                    get() = jsonObject.get("is_default").asBoolean
                 val image: String
                    get() = jsonObject.get("image").asString
                 val achieveUrl: String
                    get() = jsonObject.get("achieve_url").asString
            }
            private val JsonElement.asPrInfo
                get() = PrInfo(this.asJsonObject)
            class PrInfo (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
            }
            private val JsonElement.asRelation
                get() = Relation(this.asJsonObject)
            class Relation (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val status: Number
                    get() = jsonObject.get("status").asNumber
            }
            private val JsonElement.asHonours
                get() = Honours(this.asJsonObject)
            class Honours (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val colour: Colour
                    get() = jsonObject.get("colour").asColour
                private val JsonElement.asColour
                    get() = Colour(this.asJsonObject)
                class Colour (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val dark: String
                        get() = jsonObject.get("dark").asString
                     val normal: String
                        get() = jsonObject.get("normal").asString
                }
            }
            private val JsonElement.asLiveFansWearing
                get() = LiveFansWearing(this.asJsonObject)
            class LiveFansWearing (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val showDefaultIcon: Boolean
                    get() = jsonObject.get("show_default_icon").asBoolean
                 val medalJumpUrl: String
                    get() = jsonObject.get("medal_jump_url").asString
            }
            private val JsonElement.asProfession
                get() = Profession(this.asJsonObject)
            class Profession (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
            }
            private val JsonElement.asSchool
                get() = School(this.asJsonObject)
            class School (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
            }
            private val JsonElement.asSpaceTag
                get() = SpaceTag(this.asJsonObject)
            class SpaceTag (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val type: String
                    get() = jsonObject.get("type").asString
                 val title: String
                    get() = jsonObject.get("title").asString
                 val textColor: String
                    get() = jsonObject.get("text_color").asString
                 val nightTextColor: String
                    get() = jsonObject.get("night_text_color").asString
                 val backgroundColor: String
                    get() = jsonObject.get("background_color").asString
                 val nightBackgroundColor: String
                    get() = jsonObject.get("night_background_color").asString
                 val uri: String
                    get() = jsonObject.get("uri").asString
                 val icon: String
                    get() = jsonObject.get("icon").asString
            }
        }
        private val JsonElement.asImages
            get() = Images(this.asJsonObject)
        class Images (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val imgUrl: String
                get() = jsonObject.get("imgUrl").asString
             val nightImgurl: String
                get() = jsonObject.get("night_imgurl").asString
             val digitalInfo: DigitalInfo
                get() = jsonObject.get("digital_info").asDigitalInfo
            private val JsonElement.asDigitalInfo
                get() = DigitalInfo(this.asJsonObject)
            class DigitalInfo (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val active: Boolean
                    get() = jsonObject.get("active").asBoolean
            }
        }
        private val JsonElement.asLive
            get() = Live(this.asJsonObject)
        class Live (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val roomStatus: Number
                get() = jsonObject.get("roomStatus").asNumber
             val roundStatus: Number
                get() = jsonObject.get("roundStatus").asNumber
             val liveStatus: Number
                get() = jsonObject.get("liveStatus").asNumber
             val url: String
                get() = jsonObject.get("url").asString
             val title: String
                get() = jsonObject.get("title").asString
             val cover: String
                get() = jsonObject.get("cover").asString
             val online: Number
                get() = jsonObject.get("online").asNumber
             val roomid: Number
                get() = jsonObject.get("roomid").asNumber
             val broadcastType: Number
                get() = jsonObject.get("broadcast_type").asNumber
             val onlineHidden: Number
                get() = jsonObject.get("online_hidden").asNumber
             val link: String
                get() = jsonObject.get("link").asString
        }
        private val JsonElement.asSeries
            get() = Series(this.asJsonObject)
        class Series (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
        }
        private val JsonElement.asPlayGame
            get() = PlayGame(this.asJsonObject)
        class PlayGame (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val count: Number
                get() = jsonObject.get("count").asNumber
             val item: List<Item>
                get() = jsonObject.get("item").asJsonArray.map { it.asItem }
            private val JsonElement.asItem
                get() = Item(this.asJsonObject)
            class Item (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val id: Number
                    get() = jsonObject.get("id").asNumber
                 val name: String
                    get() = jsonObject.get("name").asString
                 val icon: String
                    get() = jsonObject.get("icon").asString
                 val grade: Number
                    get() = jsonObject.get("grade").asNumber
                 val uri: String
                    get() = jsonObject.get("uri").asString
            }
        }
        private val JsonElement.asArticle
            get() = Article(this.asJsonObject)
        class Article (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val count: Number
                get() = jsonObject.get("count").asNumber
             val listsCount: Number
                get() = jsonObject.get("lists_count").asNumber
        }
        private val JsonElement.asFavourite
            get() = Favourite(this.asJsonObject)
        class Favourite (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val count: Number
                get() = jsonObject.get("count").asNumber
             val item: List<Item>
                get() = jsonObject.get("item").asJsonArray.map { it.asItem }
            private val JsonElement.asItem
                get() = Item(this.asJsonObject)
            class Item (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val mediaId: Number
                    get() = jsonObject.get("media_id").asNumber
                 val fid: Number
                    get() = jsonObject.get("fid").asNumber
                 val mid: Number
                    get() = jsonObject.get("mid").asNumber
                 val name: String
                    get() = jsonObject.get("name").asString
                 val maxCount: Number
                    get() = jsonObject.get("max_count").asNumber
                 val curCount: Number
                    get() = jsonObject.get("cur_count").asNumber
                 val attenCount: Number
                    get() = jsonObject.get("atten_count").asNumber
                 val state: Number
                    get() = jsonObject.get("state").asNumber
                 val ctime: Number
                    get() = jsonObject.get("ctime").asNumber
                 val mtime: Number
                    get() = jsonObject.get("mtime").asNumber
                 val cover: List<Cover>?
                    get() = jsonObject.get("cover")?.asJsonArray?.map { it.asCover }
                private val JsonElement.asCover
                    get() = Cover(this.asJsonObject)
                class Cover (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val aid: Number
                        get() = jsonObject.get("aid").asNumber
                     val pic: String
                        get() = jsonObject.get("pic").asString
                     val type: Number
                        get() = jsonObject.get("type").asNumber
                }
            }
        }
        private val JsonElement.asSeason
            get() = Season(this.asJsonObject)
        class Season (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val count: Number
                get() = jsonObject.get("count").asNumber
             val item: List<Item>
                get() = jsonObject.get("item").asJsonArray.map { it.asItem }
            private val JsonElement.asItem
                get() = Item(this.asJsonObject)
            class Item (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val title: String
                    get() = jsonObject.get("title").asString
                 val cover: String
                    get() = jsonObject.get("cover").asString
                 val uri: String
                    get() = jsonObject.get("uri").asString
                 val param: String
                    get() = jsonObject.get("param").asString
                 val goto: String
                    get() = jsonObject.get("goto").asString
                 val finish: Number
                    get() = jsonObject.get("finish").asNumber
                 val index: String
                    get() = jsonObject.get("index").asString
                 val mtime: Number
                    get() = jsonObject.get("mtime").asNumber
                 val newestEpIndex: String
                    get() = jsonObject.get("newest_ep_index").asString
                 val isStarted: Number
                    get() = jsonObject.get("is_started").asNumber
                 val isFinish: String
                    get() = jsonObject.get("is_finish").asString
                 val newestEpId: String
                    get() = jsonObject.get("newest_ep_id").asString
                 val totalCount: String
                    get() = jsonObject.get("total_count").asString
                 val attention: String
                    get() = jsonObject.get("attention").asString
            }
        }
        private val JsonElement.asCoinArchive
            get() = CoinArchive(this.asJsonObject)
        class CoinArchive (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val count: Number
                get() = jsonObject.get("count").asNumber
        }
        private val JsonElement.asLikeArchive
            get() = LikeArchive(this.asJsonObject)
        class LikeArchive (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val count: Number
                get() = jsonObject.get("count").asNumber
             val item: List<Item>
                get() = jsonObject.get("item").asJsonArray.map { it.asItem }
            private val JsonElement.asItem
                get() = Item(this.asJsonObject)
            class Item (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val title: String
                    get() = jsonObject.get("title").asString
                 val subtitle: String
                    get() = jsonObject.get("subtitle").asString
                 val tname: String
                    get() = jsonObject.get("tname").asString
                 val cover: String
                    get() = jsonObject.get("cover").asString
                 val uri: String
                    get() = jsonObject.get("uri").asString
                 val param: String
                    get() = jsonObject.get("param").asString
                 val goto: String
                    get() = jsonObject.get("goto").asString
                 val length: String
                    get() = jsonObject.get("length").asString
                 val duration: Number
                    get() = jsonObject.get("duration").asNumber
                 val isPopular: Boolean
                    get() = jsonObject.get("is_popular").asBoolean
                 val isSteins: Boolean
                    get() = jsonObject.get("is_steins").asBoolean
                 val isUgcpay: Boolean
                    get() = jsonObject.get("is_ugcpay").asBoolean
                 val isCooperation: Boolean
                    get() = jsonObject.get("is_cooperation").asBoolean
                 val isPgc: Boolean
                    get() = jsonObject.get("is_pgc").asBoolean
                 val isLivePlayback: Boolean
                    get() = jsonObject.get("is_live_playback").asBoolean
                 val play: Number
                    get() = jsonObject.get("play").asNumber
                 val danmaku: Number
                    get() = jsonObject.get("danmaku").asNumber
                 val ctime: Number
                    get() = jsonObject.get("ctime").asNumber
                 val ugcPay: Number
                    get() = jsonObject.get("ugc_pay").asNumber
                 val state: Boolean
                    get() = jsonObject.get("state").asBoolean
                 val videos: Number
                    get() = jsonObject.get("videos").asNumber
            }
        }
        private val JsonElement.asAudios
            get() = Audios(this.asJsonObject)
        class Audios (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val count: Number
                get() = jsonObject.get("count").asNumber
        }
        private val JsonElement.asVipSpaceLabel
            get() = VipSpaceLabel(this.asJsonObject)
        class VipSpaceLabel (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val showExpire: Boolean
                get() = jsonObject.get("show_expire").asBoolean
        }
        private val JsonElement.asTab2
            get() = Tab2(this.asJsonObject)
        class Tab2 (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val title: String
                get() = jsonObject.get("title").asString
             val param: String
                get() = jsonObject.get("param").asString
        }
    }
}