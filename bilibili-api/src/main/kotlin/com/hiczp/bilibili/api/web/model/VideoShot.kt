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
     val code: Double by lazy { jsonObject.get("code").asDouble }
     val message: String by lazy { jsonObject.get("message").asString }
     val ttl: Double by lazy { jsonObject.get("ttl").asDouble }
     val data: Data by lazy { jsonObject.get("data").asData }
    private val JsonElement.asData
        get() = Data(this.asJsonObject)
    class Data (private val jsonObject: JsonObject) {
        fun getJsonObject()  = jsonObject
         val pvdata: String by lazy { jsonObject.get("pvdata").asString }
         val imgXLen: Double by lazy { jsonObject.get("img_x_len").asDouble }
         val imgYLen: Double by lazy { jsonObject.get("img_y_len").asDouble }
         val imgXSize: Double by lazy { jsonObject.get("img_x_size").asDouble }
         val imgYSize: Double by lazy { jsonObject.get("img_y_size").asDouble }
         val image: List<String> by lazy { jsonObject.get("image").asJsonArray.map { it.asString } }
         val index: List<Double> by lazy { jsonObject.get("index").asJsonArray.map { it.asDouble } }
    }
}