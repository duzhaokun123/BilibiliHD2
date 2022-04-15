package  com.hiczp.bilibili.api.web.model
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom
@LazyjsonClass class EmoteList (private val jsonObject: JsonObject) {
    companion object   {
        @LazyjsonFrom @JvmStatic fun from(jsonObject: JsonObject)  = EmoteList(jsonObject)
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
         val userPanelPackages: List<UserPanelPackages>
            get() = jsonObject.get("user_panel_packages").asJsonArray.map { it.asUserPanelPackages }
         val allPackages: List<AllPackages>
            get() = jsonObject.get("all_packages").asJsonArray.map { it.asAllPackages }
         val mall: Mall
            get() = jsonObject.get("mall").asMall
        private val JsonElement.asUserPanelPackages
            get() = UserPanelPackages(this.asJsonObject)
        class UserPanelPackages (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val id: Number
                get() = jsonObject.get("id").asNumber
             val text: String
                get() = jsonObject.get("text").asString
             val url: String
                get() = jsonObject.get("url").asString
             val mtime: Number
                get() = jsonObject.get("mtime").asNumber
             val type: Number
                get() = jsonObject.get("type").asNumber
             val attr: Number
                get() = jsonObject.get("attr").asNumber
             val meta: Meta
                get() = jsonObject.get("meta").asMeta
             val emote: List<Emote>
                get() = jsonObject.get("emote").asJsonArray.map { it.asEmote }
             val flags: Flags
                get() = jsonObject.get("flags").asFlags
            private val JsonElement.asMeta
                get() = Meta(this.asJsonObject)
            class Meta (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val size: Number
                    get() = jsonObject.get("size").asNumber
                 val itemId: Number
                    get() = jsonObject.get("item_id").asNumber
            }
            private val JsonElement.asEmote
                get() = Emote(this.asJsonObject)
            class Emote (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val id: Number
                    get() = jsonObject.get("id").asNumber
                 val packageId: Number
                    get() = jsonObject.get("package_id").asNumber
                 val text: String
                    get() = jsonObject.get("text").asString
                 val url: String
                    get() = jsonObject.get("url").asString
                 val mtime: Number
                    get() = jsonObject.get("mtime").asNumber
                 val type: Number
                    get() = jsonObject.get("type").asNumber
                 val attr: Number
                    get() = jsonObject.get("attr").asNumber
                 val meta: Meta
                    get() = jsonObject.get("meta").asMeta
                 val flags: Flags
                    get() = jsonObject.get("flags").asFlags
                 val gifUrl: String?
                    get() = jsonObject.get("gif_url")?.asString
                private val JsonElement.asMeta
                    get() = Meta(this.asJsonObject)
                class Meta (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val size: Number
                        get() = jsonObject.get("size").asNumber
                     val suggest: List<String>
                        get() = jsonObject.get("suggest").asJsonArray.map { it.asString }
                     val alias: String?
                        get() = jsonObject.get("alias")?.asString
                     val gifUrl: String?
                        get() = jsonObject.get("gif_url")?.asString
                }
                private val JsonElement.asFlags
                    get() = Flags(this.asJsonObject)
                class Flags (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val unlocked: Boolean
                        get() = jsonObject.get("unlocked").asBoolean
                }
            }
            private val JsonElement.asFlags
                get() = Flags(this.asJsonObject)
            class Flags (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val added: Boolean
                    get() = jsonObject.get("added").asBoolean
                 val preview: Boolean?
                    get() = jsonObject.get("preview")?.asBoolean
            }
        }
        private val JsonElement.asAllPackages
            get() = AllPackages(this.asJsonObject)
        class AllPackages (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val id: Number
                get() = jsonObject.get("id").asNumber
             val text: String
                get() = jsonObject.get("text").asString
             val url: String
                get() = jsonObject.get("url").asString
             val mtime: Number
                get() = jsonObject.get("mtime").asNumber
             val type: Number
                get() = jsonObject.get("type").asNumber
             val attr: Number
                get() = jsonObject.get("attr").asNumber
             val meta: Meta
                get() = jsonObject.get("meta").asMeta
             val emote: List<Emote>
                get() = jsonObject.get("emote").asJsonArray.map { it.asEmote }
             val flags: Flags
                get() = jsonObject.get("flags").asFlags
            private val JsonElement.asMeta
                get() = Meta(this.asJsonObject)
            class Meta (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val size: Number
                    get() = jsonObject.get("size").asNumber
                 val itemId: Number
                    get() = jsonObject.get("item_id").asNumber
                 val vipNoAccessText: String?
                    get() = jsonObject.get("vip_no_access_text")?.asString
                 val itemUrl: String?
                    get() = jsonObject.get("item_url")?.asString
            }
            private val JsonElement.asEmote
                get() = Emote(this.asJsonObject)
            class Emote (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val id: Number
                    get() = jsonObject.get("id").asNumber
                 val packageId: Number
                    get() = jsonObject.get("package_id").asNumber
                 val text: String
                    get() = jsonObject.get("text").asString
                 val url: String
                    get() = jsonObject.get("url").asString
                 val mtime: Number
                    get() = jsonObject.get("mtime").asNumber
                 val type: Number
                    get() = jsonObject.get("type").asNumber
                 val attr: Number
                    get() = jsonObject.get("attr").asNumber
                 val meta: Meta
                    get() = jsonObject.get("meta").asMeta
                 val flags: Flags
                    get() = jsonObject.get("flags").asFlags
                 val gifUrl: String?
                    get() = jsonObject.get("gif_url")?.asString
                private val JsonElement.asMeta
                    get() = Meta(this.asJsonObject)
                class Meta (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val size: Number
                        get() = jsonObject.get("size").asNumber
                     val suggest: List<String>
                        get() = jsonObject.get("suggest").asJsonArray.map { it.asString }
                     val alias: String?
                        get() = jsonObject.get("alias")?.asString
                     val gifUrl: String?
                        get() = jsonObject.get("gif_url")?.asString
                }
                private val JsonElement.asFlags
                    get() = Flags(this.asJsonObject)
                class Flags (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                    override fun toString()  = jsonObject.toString()
                     val unlocked: Boolean
                        get() = jsonObject.get("unlocked").asBoolean
                }
            }
            private val JsonElement.asFlags
                get() = Flags(this.asJsonObject)
            class Flags (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val added: Boolean?
                    get() = jsonObject.get("added")?.asBoolean
                 val preview: Boolean?
                    get() = jsonObject.get("preview")?.asBoolean
                 val noAccess: Boolean?
                    get() = jsonObject.get("no_access")?.asBoolean
            }
        }
        private val JsonElement.asMall
            get() = Mall(this.asJsonObject)
        class Mall (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val title: String
                get() = jsonObject.get("title").asString
             val url: String
                get() = jsonObject.get("url").asString
        }
    }
}