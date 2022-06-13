package  com.hiczp.bilibili.api.web.model
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom
@LazyjsonClass class PlayerV2 (private val jsonObject: JsonObject) {
    companion object   {
        @LazyjsonFrom @JvmStatic fun from(jsonObject: JsonObject)  = PlayerV2(jsonObject)
    }
    fun getJsonObject()  = jsonObject
    override fun toString()  = jsonObject.toString()
     val code: Number
        get() = jsonObject.get("code").asNumber
     val message: String
        get() = jsonObject.get("message").asString
     val ttl: Number
        get() = jsonObject.get("ttl").asNumber
     val data: Data?
        get() = jsonObject.get("data").asData
    private val JsonElement.asData
        get() = Data(this.asJsonObject)
    class Data (private val jsonObject: JsonObject) {
        fun getJsonObject()  = jsonObject
        override fun toString()  = jsonObject.toString()
         val aid: Number
            get() = jsonObject.get("aid").asNumber
         val bvid: String
            get() = jsonObject.get("bvid").asString
         val allowBp: Boolean
            get() = jsonObject.get("allow_bp").asBoolean
         val cid: Number
            get() = jsonObject.get("cid").asNumber
         val maxLimit: Number
            get() = jsonObject.get("max_limit").asNumber
         val pageNo: Number
            get() = jsonObject.get("page_no").asNumber
         val hasNext: Boolean
            get() = jsonObject.get("has_next").asBoolean
         val answerStatus: Number
            get() = jsonObject.get("answer_status").asNumber
         val blockTime: Number
            get() = jsonObject.get("block_time").asNumber
         val role: String
            get() = jsonObject.get("role").asString
         val lastPlayTime: Number
            get() = jsonObject.get("last_play_time").asNumber
         val lastPlayCid: Number
            get() = jsonObject.get("last_play_cid").asNumber
         val nowTime: Number
            get() = jsonObject.get("now_time").asNumber
         val onlineCount: Number
            get() = jsonObject.get("online_count").asNumber
         val subtitle: Subtitle
            get() = jsonObject.get("subtitle").asSubtitle
         val isUgcPayPreview: Boolean
            get() = jsonObject.get("is_ugc_pay_preview").asBoolean
         val previewToast: String
            get() = jsonObject.get("preview_toast").asString
         val pcdnLoader: PcdnLoader
            get() = jsonObject.get("pcdn_loader").asPcdnLoader
         val options: Options
            get() = jsonObject.get("options").asOptions
         val guideAttention: List<GuideAttention>
            get() = jsonObject.get("guide_attention").asJsonArray.map { it.asGuideAttention }
         val onlineSwitch: OnlineSwitch
            get() = jsonObject.get("online_switch").asOnlineSwitch
         val fawkes: Fawkes
            get() = jsonObject.get("fawkes").asFawkes
        private val JsonElement.asSubtitle
            get() = Subtitle(this.asJsonObject)
        class Subtitle (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val allowSubmit: Boolean
                get() = jsonObject.get("allow_submit").asBoolean
             val lan: String
                get() = jsonObject.get("lan").asString
             val lanDoc: String
                get() = jsonObject.get("lan_doc").asString
             val subtitles: List<Subtitles>
                get() = jsonObject.get("subtitles").asJsonArray.map { it.asSubtitles }
            private val JsonElement.asSubtitles
                get() = Subtitles(this.asJsonObject)
            class Subtitles (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val id: Number
                    get() = jsonObject.get("id").asNumber
                 val lan: String
                    get() = jsonObject.get("lan").asString
                 val lanDoc: String
                    get() = jsonObject.get("lan_doc").asString
                 val isLock: Boolean
                    get() = jsonObject.get("is_lock").asBoolean
                 val subtitleUrl: String
                    get() = jsonObject.get("subtitle_url").asString
            }
        }
        private val JsonElement.asPcdnLoader
            get() = PcdnLoader(this.asJsonObject)
        class PcdnLoader (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val flv: Flv
                get() = jsonObject.get("flv").asFlv
             val dash: Dash
                get() = jsonObject.get("dash").asDash
            private val JsonElement.asFlv
                get() = Flv(this.asJsonObject)
            class Flv (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val labels: Labels
                    get() = jsonObject.get("labels").asLabels
                private val JsonElement.asLabels
                    get() = Labels(this.asJsonObject)
                class Labels (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val pcdnVideoType: String
                        get() = jsonObject.get("pcdn_video_type").asString
                     val pcdnStage: String
                        get() = jsonObject.get("pcdn_stage").asString
                     val pcdnGroup: String
                        get() = jsonObject.get("pcdn_group").asString
                     val pcdnVersion: String
                        get() = jsonObject.get("pcdn_version").asString
                     val pcdnVendor: String
                        get() = jsonObject.get("pcdn_vendor").asString
                }
            }
            private val JsonElement.asDash
                get() = Dash(this.asJsonObject)
            class Dash (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val labels: Labels
                    get() = jsonObject.get("labels").asLabels
                private val JsonElement.asLabels
                    get() = Labels(this.asJsonObject)
                class Labels (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val pcdnVideoType: String
                        get() = jsonObject.get("pcdn_video_type").asString
                     val pcdnStage: String
                        get() = jsonObject.get("pcdn_stage").asString
                     val pcdnGroup: String
                        get() = jsonObject.get("pcdn_group").asString
                     val pcdnVersion: String
                        get() = jsonObject.get("pcdn_version").asString
                     val pcdnVendor: String
                        get() = jsonObject.get("pcdn_vendor").asString
                }
            }
        }
        private val JsonElement.asOptions
            get() = Options(this.asJsonObject)
        class Options (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val is360: Boolean
                get() = jsonObject.get("is_360").asBoolean
             val withoutVip: Boolean
                get() = jsonObject.get("without_vip").asBoolean
        }
        private val JsonElement.asGuideAttention
            get() = GuideAttention(this.asJsonObject)
        class GuideAttention (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val type: Number
                get() = jsonObject.get("type").asNumber
             val from: Number
                get() = jsonObject.get("from").asNumber
             val to: Number
                get() = jsonObject.get("to").asNumber
             val posX: Number
                get() = jsonObject.get("pos_x").asNumber
             val posY: Number
                get() = jsonObject.get("pos_y").asNumber
        }
        private val JsonElement.asOnlineSwitch
            get() = OnlineSwitch(this.asJsonObject)
        class OnlineSwitch (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val enableGrayDashPlayback: String
                get() = jsonObject.get("enable_gray_dash_playback").asString
             val newBroadcast: String
                get() = jsonObject.get("new_broadcast").asString
             val realtimeDm: String
                get() = jsonObject.get("realtime_dm").asString
             val subtitleSubmitSwitch: String
                get() = jsonObject.get("subtitle_submit_switch").asString
        }
        private val JsonElement.asFawkes
            get() = Fawkes(this.asJsonObject)
        class Fawkes (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val configVersion: Number
                get() = jsonObject.get("config_version").asNumber
             val ffVersion: Number
                get() = jsonObject.get("ff_version").asNumber
        }
    }
}