package  com.hiczp.bilibili.api.weblive.model
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom
@LazyjsonClass class PlayUrl (private val jsonObject: JsonObject) {
    companion object   {
        @LazyjsonFrom @JvmStatic fun from(jsonObject: JsonObject)  = PlayUrl(jsonObject)
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
         val currentQuality: Number
            get() = jsonObject.get("current_quality").asNumber
         val acceptQuality: List<String>
            get() = jsonObject.get("accept_quality").asJsonArray.map { it.asString }
         val currentQn: Number
            get() = jsonObject.get("current_qn").asNumber
         val qualityDescription: List<QualityDescription>
            get() = jsonObject.get("quality_description").asJsonArray.map { it.asQualityDescription }
         val durl: List<Durl>
            get() = jsonObject.get("durl").asJsonArray.map { it.asDurl }
        private val JsonElement.asQualityDescription
            get() = QualityDescription(this.asJsonObject)
        class QualityDescription (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val qn: Number
                get() = jsonObject.get("qn").asNumber
             val desc: String
                get() = jsonObject.get("desc").asString
        }
        private val JsonElement.asDurl
            get() = Durl(this.asJsonObject)
        class Durl (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val url: String
                get() = jsonObject.get("url").asString
             val length: Number
                get() = jsonObject.get("length").asNumber
             val order: Number
                get() = jsonObject.get("order").asNumber
             val streamType: Number
                get() = jsonObject.get("stream_type").asNumber
             val p2pType: Number
                get() = jsonObject.get("p2p_type").asNumber
        }
    }
}