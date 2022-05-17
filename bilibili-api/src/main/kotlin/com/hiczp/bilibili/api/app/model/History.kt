package  com.hiczp.bilibili.api.app.model
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom
@LazyjsonClass class History (private val jsonObject: JsonObject) {
    companion object   {
        @LazyjsonFrom @JvmStatic fun from(jsonObject: JsonObject)  = History(jsonObject)
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
         val tab: List<Tab>
            get() = jsonObject.get("tab").asJsonArray.map { it.asTab }
         val list: List<List_>
            get() = jsonObject.get("list").asJsonArray.map { it.asList_ }
         val cursor: Cursor?
            get() = jsonObject.get("cursor")?.asCursor
        private val JsonElement.asTab
            get() = Tab(this.asJsonObject)
        class Tab (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val business: String
                get() = jsonObject.get("business").asString
             val name: String
                get() = jsonObject.get("name").asString
             val router: String
                get() = jsonObject.get("router").asString
        }
        private val JsonElement.asList_
            get() = List_(this.asJsonObject)
        class List_ (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val title: String
                get() = jsonObject.get("title").asString
             val cover: String?
                get() = jsonObject.get("cover")?.asString
             val uri: String
                get() = jsonObject.get("uri").asString
             val history: History
                get() = jsonObject.get("history").asHistory
             val videos: Number
                get() = jsonObject.get("videos").asNumber
             val view: Number
                get() = jsonObject.get("view").asNumber
             val name: String?
                get() = jsonObject.get("name")?.asString
             val mid: Number
                get() = jsonObject.get("mid").asNumber
             val goto: String
                get() = jsonObject.get("goto").asString
             val viewAt: Long
                get() = jsonObject.get("view_at").asLong
             val duration: Long?
                get() = jsonObject.get("duration")?.asLong
             val progress: Number?
                get() = jsonObject.get("progress")?.asNumber
             val covers: List<String>?
                get() = jsonObject.get("covers")?.asJsonArray?.map { it.asString }
            private val JsonElement.asHistory
                get() = History(this.asJsonObject)
            class History (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val oid: Number
                    get() = jsonObject.get("oid").asNumber
                 val tp: Number
                    get() = jsonObject.get("tp").asNumber
                 val cid: Number
                    get() = jsonObject.get("cid").asNumber
                 val page: Number
                    get() = jsonObject.get("page").asNumber
                 val part: String?
                    get() = jsonObject.get("part")?.asString
                 val business: String
                    get() = jsonObject.get("business").asString
            }
        }
        private val JsonElement.asCursor
            get() = Cursor(this.asJsonObject)
        class Cursor (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val max: Long
                get() = jsonObject.get("max").asLong
             val maxTp: Number
                get() = jsonObject.get("max_tp").asNumber
             val ps: Number
                get() = jsonObject.get("ps").asNumber
        }
    }
}