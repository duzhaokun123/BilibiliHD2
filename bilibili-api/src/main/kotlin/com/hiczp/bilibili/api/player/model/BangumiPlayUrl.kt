package  com.hiczp.bilibili.api.player.model
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom
@LazyjsonClass class BangumiPlayUrl (private val jsonObject: JsonObject) {
    companion object   {
        @LazyjsonFrom @JvmStatic fun from(jsonObject: JsonObject)  = BangumiPlayUrl(jsonObject)
    }
    fun getJsonObject()  = jsonObject
    override fun toString()  = jsonObject.toString()
     val acceptFormat: String
        get() = jsonObject.get("accept_format").asString
     val code: Number
        get() = jsonObject.get("code").asNumber
     val seekParam: String
        get() = jsonObject.get("seek_param").asString
     val isPreview: Number
        get() = jsonObject.get("is_preview").asNumber
     val fnval: Number
        get() = jsonObject.get("fnval").asNumber
     val videoProject: Boolean
        get() = jsonObject.get("video_project").asBoolean
     val fnver: Number
        get() = jsonObject.get("fnver").asNumber
     val type: String
        get() = jsonObject.get("type").asString
     val bp: Number
        get() = jsonObject.get("bp").asNumber
     val result: String
        get() = jsonObject.get("result").asString
     val seekType: String
        get() = jsonObject.get("seek_type").asString
     val vipType: Number
        get() = jsonObject.get("vip_type").asNumber
     val from: String
        get() = jsonObject.get("from").asString
     val videoCodecid: Number
        get() = jsonObject.get("video_codecid").asNumber
     val noRexcode: Number
        get() = jsonObject.get("no_rexcode").asNumber
     val format: String
        get() = jsonObject.get("format").asString
     val supportFormats: List<SupportFormats>
        get() = jsonObject.get("support_formats").asJsonArray.map { it.asSupportFormats }
     val message: String
        get() = jsonObject.get("message").asString
     val acceptQuality: List<Number>
        get() = jsonObject.get("accept_quality").asJsonArray.map { it.asNumber }
     val quality: Number
        get() = jsonObject.get("quality").asNumber
     val timelength: Number
        get() = jsonObject.get("timelength").asNumber
     val hasPaid: Boolean
        get() = jsonObject.get("has_paid").asBoolean
     val vipStatus: Number
        get() = jsonObject.get("vip_status").asNumber
     val dash: Dash
        get() = jsonObject.get("dash").asDash
     val acceptDescription: List<String>
        get() = jsonObject.get("accept_description").asJsonArray.map { it.asString }
     val status: Number
        get() = jsonObject.get("status").asNumber
    private val JsonElement.asSupportFormats
        get() = SupportFormats(this.asJsonObject)
    class SupportFormats (private val jsonObject: JsonObject) {
        fun getJsonObject()  = jsonObject
        override fun toString()  = jsonObject.toString()
         val displayDesc: String
            get() = jsonObject.get("display_desc").asString
         val superscript: String
            get() = jsonObject.get("superscript").asString
         val needLogin: Boolean?
            get() = jsonObject.get("need_login")?.asBoolean
         val codecs: List<String>
            get() = jsonObject.get("codecs").asJsonArray.map { it.asString }
         val format: String
            get() = jsonObject.get("format").asString
         val description: String
            get() = jsonObject.get("description").asString
         val needVip: Boolean?
            get() = jsonObject.get("need_vip")?.asBoolean
         val quality: Number
            get() = jsonObject.get("quality").asNumber
         val newDescription: String
            get() = jsonObject.get("new_description").asString
    }
    private val JsonElement.asDash
        get() = Dash(this.asJsonObject)
    class Dash (private val jsonObject: JsonObject) {
        fun getJsonObject()  = jsonObject
        override fun toString()  = jsonObject.toString()
         val duration: Number
            get() = jsonObject.get("duration").asNumber
         val minBufferTime: Number
            get() = jsonObject.get("min_buffer_time").asNumber
         val video: List<Video>
            get() = jsonObject.get("video").asJsonArray.map { it.asVideo }
         val audio: List<Audio>
            get() = jsonObject.get("audio").asJsonArray.map { it.asAudio }
         val dolby: Dolby
            get() = jsonObject.get("dolby").asDolby
        private val JsonElement.asVideo
            get() = Video(this.asJsonObject)
        class Video (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val startWithSap: Number
                get() = jsonObject.get("start_with_sap").asNumber
             val bandwidth: Number
                get() = jsonObject.get("bandwidth").asNumber
             val sar: String
                get() = jsonObject.get("sar").asString
             val codecs: String
                get() = jsonObject.get("codecs").asString
             val baseUrl: String
                get() = jsonObject.get("base_url").asString
             val backupUrl: List<String>
                get() = jsonObject.get("backup_url").asJsonArray.map { it.asString }
             val segmentBase: SegmentBase
                get() = jsonObject.get("segment_base").asSegmentBase
             val frameRate: String
                get() = jsonObject.get("frame_rate").asString
             val codecid: Number
                get() = jsonObject.get("codecid").asNumber
             val size: Number
                get() = jsonObject.get("size").asNumber
             val mimeType: String
                get() = jsonObject.get("mime_type").asString
             val width: Number
                get() = jsonObject.get("width").asNumber
             val startWithSAP: Number
                get() = jsonObject.get("startWithSAP").asNumber
             val id: Number
                get() = jsonObject.get("id").asNumber
             val height: Number
                get() = jsonObject.get("height").asNumber
             val md5: String
                get() = jsonObject.get("md5").asString
            private val JsonElement.asSegmentBase
                get() = SegmentBase(this.asJsonObject)
            class SegmentBase (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val initialization: String
                    get() = jsonObject.get("initialization").asString
                 val indexRange: String
                    get() = jsonObject.get("index_range").asString
            }
        }
        private val JsonElement.asAudio
            get() = Audio(this.asJsonObject)
        class Audio (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val startWithSap: Number
                get() = jsonObject.get("start_with_sap").asNumber
             val bandwidth: Number
                get() = jsonObject.get("bandwidth").asNumber
             val sar: String
                get() = jsonObject.get("sar").asString
             val codecs: String
                get() = jsonObject.get("codecs").asString
             val baseUrl: String
                get() = jsonObject.get("base_url").asString
             val backupUrl: List<String>
                get() = jsonObject.get("backup_url").asJsonArray.map { it.asString }
             val segmentBase: SegmentBase
                get() = jsonObject.get("segment_base").asSegmentBase
             val frameRate: String
                get() = jsonObject.get("frame_rate").asString
             val codecid: Number
                get() = jsonObject.get("codecid").asNumber
             val size: Number
                get() = jsonObject.get("size").asNumber
             val mimeType: String
                get() = jsonObject.get("mime_type").asString
             val width: Number
                get() = jsonObject.get("width").asNumber
             val startWithSAP: Number
                get() = jsonObject.get("startWithSAP").asNumber
             val id: Number
                get() = jsonObject.get("id").asNumber
             val height: Number
                get() = jsonObject.get("height").asNumber
             val md5: String
                get() = jsonObject.get("md5").asString
            private val JsonElement.asSegmentBase
                get() = SegmentBase(this.asJsonObject)
            class SegmentBase (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val initialization: String
                    get() = jsonObject.get("initialization").asString
                 val indexRange: String
                    get() = jsonObject.get("index_range").asString
            }
        }
        private val JsonElement.asDolby
            get() = Dolby(this.asJsonObject)
        class Dolby (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val type: String
                get() = jsonObject.get("type").asString
        }
    }
}