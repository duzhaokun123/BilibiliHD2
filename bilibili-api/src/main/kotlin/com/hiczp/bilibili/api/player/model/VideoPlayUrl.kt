package  com.hiczp.bilibili.api.player.model
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom
@LazyjsonClass class VideoPlayUrl (private val jsonObject: JsonObject) {
    companion object   {
        @LazyjsonFrom @JvmStatic fun from(jsonObject: JsonObject)  = VideoPlayUrl(jsonObject)
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
         val quality: Number
            get() = jsonObject.get("quality").asNumber
         val format: String
            get() = jsonObject.get("format").asString
         val timelength: Number
            get() = jsonObject.get("timelength").asNumber
         val acceptFormat: String
            get() = jsonObject.get("accept_format").asString
         val acceptDescription: List<String>
            get() = jsonObject.get("accept_description").asJsonArray.map { it.asString }
         val acceptQuality: List<Number>
            get() = jsonObject.get("accept_quality").asJsonArray.map { it.asNumber }
         val videoCodecid: Number
            get() = jsonObject.get("video_codecid").asNumber
         val fnver: Number
            get() = jsonObject.get("fnver").asNumber
         val fnval: Number
            get() = jsonObject.get("fnval").asNumber
         val videoProject: Boolean
            get() = jsonObject.get("video_project").asBoolean
         val dash: Dash?
            get() = jsonObject.get("dash")?.asDash
        private val JsonElement.asDash
            get() = Dash(this.asJsonObject)
        class Dash (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val video: List<Video>
                get() = jsonObject.get("video").asJsonArray.map { it.asVideo }
             val audio: List<Audio>?
                get() = jsonObject.get("audio")?.asJsonArray?.map { it.asAudio }
            private val JsonElement.asVideo
                get() = Video(this.asJsonObject)
            class Video (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val id: Number
                    get() = jsonObject.get("id").asNumber
                 val baseUrl: String
                    get() = jsonObject.get("base_url").asString
                 val backupUrl: List<String>?
                    get() = jsonObject.get("backup_url")?.asJsonArray?.map { it.asString }
                 val bandwidth: Number
                    get() = jsonObject.get("bandwidth").asNumber
                 val codecid: Number
                    get() = jsonObject.get("codecid").asNumber
                 val size: Number
                    get() = jsonObject.get("size").asNumber
            }
            private val JsonElement.asAudio
                get() = Audio(this.asJsonObject)
            class Audio (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val id: Number
                    get() = jsonObject.get("id").asNumber
                 val baseUrl: String
                    get() = jsonObject.get("base_url").asString
                 val backupUrl: List<String>
                    get() = jsonObject.get("backup_url").asJsonArray.map { it.asString }
                 val bandwidth: Number
                    get() = jsonObject.get("bandwidth").asNumber
                 val codecid: Number
                    get() = jsonObject.get("codecid").asNumber
                 val size: Number
                    get() = jsonObject.get("size").asNumber
            }
        }
    }
}