package  com.duzhaokun123.bilibilihd2.model
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom
@LazyjsonClass class Subtitle (private val jsonObject: JsonObject) {
    companion object   {
        @LazyjsonFrom @JvmStatic fun from(jsonObject: JsonObject)  = Subtitle(jsonObject)
    }
    fun getJsonObject()  = jsonObject
    override fun toString()  = jsonObject.toString()
     val fontSize: Number
        get() = jsonObject.get("font_size").asNumber
     val fontColor: String
        get() = jsonObject.get("font_color").asString
     val backgroundAlpha: Number
        get() = jsonObject.get("background_alpha").asNumber
     val backgroundColor: String
        get() = jsonObject.get("background_color").asString
     val Stroke: String
        get() = jsonObject.get("Stroke").asString
     val body: List<Body>
        get() = jsonObject.get("body").asJsonArray.map { it.asBody }
    private val JsonElement.asBody
        get() = Body(this.asJsonObject)
    class Body (private val jsonObject: JsonObject) {
        fun getJsonObject()  = jsonObject
        override fun toString()  = jsonObject.toString()
         val from: Float
            get() = jsonObject.get("from").asFloat
         val to: Float
            get() = jsonObject.get("to").asFloat
         val location: Int
            get() = jsonObject.get("location").asInt
         val content: String
            get() = jsonObject.get("content").asString
    }
}