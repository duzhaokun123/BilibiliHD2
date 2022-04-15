package  com.hiczp.bilibili.api.web.model
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom
@LazyjsonClass class VideoShot (private val jsonObject: JsonObject) {
    companion object   {
        @LazyjsonFrom @JvmStatic fun from(jsonObject: JsonObject)  = VideoShot(jsonObject)
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
         val pvdata: String
            get() = jsonObject.get("pvdata").asString
         val imgXLen: Number
            get() = jsonObject.get("img_x_len").asNumber
         val imgYLen: Number
            get() = jsonObject.get("img_y_len").asNumber
         val imgXSize: Number
            get() = jsonObject.get("img_x_size").asNumber
         val imgYSize: Number
            get() = jsonObject.get("img_y_size").asNumber
         val image: List<String>
            get() = jsonObject.get("image").asJsonArray.map { it.asString }
         val index: List<Number>
            get() = jsonObject.get("index").asJsonArray.map { it.asNumber }
    }
}