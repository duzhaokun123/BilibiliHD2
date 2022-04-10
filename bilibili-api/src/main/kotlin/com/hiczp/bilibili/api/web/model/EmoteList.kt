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
     val code: Double by lazy { jsonObject.get("code").asDouble }
     val message: String by lazy { jsonObject.get("message").asString }
     val ttl: Double by lazy { jsonObject.get("ttl").asDouble }
     val data: Data by lazy { jsonObject.get("data").asData }
    private val JsonElement.asData
        get() = Data(this.asJsonObject)
    class Data (private val jsonObject: JsonObject) {
        fun getJsonObject()  = jsonObject
         val userPanelPackages: List<UserPanelPackages> by lazy { jsonObject.get("user_panel_packages").asJsonArray.map { it.asUserPanelPackages } }
         val allPackages: List<AllPackages> by lazy { jsonObject.get("all_packages").asJsonArray.map { it.asAllPackages } }
         val mall: Mall by lazy { jsonObject.get("mall").asMall }
        private val JsonElement.asUserPanelPackages
            get() = UserPanelPackages(this.asJsonObject)
        class UserPanelPackages (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
             val id: Double by lazy { jsonObject.get("id").asDouble }
             val text: String by lazy { jsonObject.get("text").asString }
             val url: String by lazy { jsonObject.get("url").asString }
             val mtime: Double by lazy { jsonObject.get("mtime").asDouble }
             val type: Double by lazy { jsonObject.get("type").asDouble }
             val attr: Double by lazy { jsonObject.get("attr").asDouble }
             val meta: Meta by lazy { jsonObject.get("meta").asMeta }
             val emote: List<Emote> by lazy { jsonObject.get("emote").asJsonArray.map { it.asEmote } }
             val flags: Flags by lazy { jsonObject.get("flags").asFlags }
            private val JsonElement.asMeta
                get() = Meta(this.asJsonObject)
            class Meta (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                 val size: Double by lazy { jsonObject.get("size").asDouble }
                 val itemId: Double by lazy { jsonObject.get("item_id").asDouble }
            }
            private val JsonElement.asEmote
                get() = Emote(this.asJsonObject)
            class Emote (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                 val id: Double by lazy { jsonObject.get("id").asDouble }
                 val packageId: Double by lazy { jsonObject.get("package_id").asDouble }
                 val text: String by lazy { jsonObject.get("text").asString }
                 val url: String by lazy { jsonObject.get("url").asString }
                 val mtime: Double by lazy { jsonObject.get("mtime").asDouble }
                 val type: Double by lazy { jsonObject.get("type").asDouble }
                 val attr: Double by lazy { jsonObject.get("attr").asDouble }
                 val meta: Meta by lazy { jsonObject.get("meta").asMeta }
                 val flags: Flags by lazy { jsonObject.get("flags").asFlags }
                 val gifUrl: String? by lazy { jsonObject.get("gif_url")?.asString }
                private val JsonElement.asMeta
                    get() = Meta(this.asJsonObject)
                class Meta (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                     val size: Double by lazy { jsonObject.get("size").asDouble }
                     val suggest: List<String> by lazy { jsonObject.get("suggest").asJsonArray.map { it.asString } }
                     val alias: String? by lazy { jsonObject.get("alias")?.asString }
                     val gifUrl: String? by lazy { jsonObject.get("gif_url")?.asString }
                }
                private val JsonElement.asFlags
                    get() = Flags(this.asJsonObject)
                class Flags (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                     val unlocked: Boolean by lazy { jsonObject.get("unlocked").asBoolean }
                }
            }
            private val JsonElement.asFlags
                get() = Flags(this.asJsonObject)
            class Flags (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                 val added: Boolean by lazy { jsonObject.get("added").asBoolean }
                 val preview: Boolean? by lazy { jsonObject.get("preview")?.asBoolean }
            }
        }
        private val JsonElement.asAllPackages
            get() = AllPackages(this.asJsonObject)
        class AllPackages (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
             val id: Double by lazy { jsonObject.get("id").asDouble }
             val text: String by lazy { jsonObject.get("text").asString }
             val url: String by lazy { jsonObject.get("url").asString }
             val mtime: Double by lazy { jsonObject.get("mtime").asDouble }
             val type: Double by lazy { jsonObject.get("type").asDouble }
             val attr: Double by lazy { jsonObject.get("attr").asDouble }
             val meta: Meta by lazy { jsonObject.get("meta").asMeta }
             val emote: List<Emote> by lazy { jsonObject.get("emote").asJsonArray.map { it.asEmote } }
             val flags: Flags by lazy { jsonObject.get("flags").asFlags }
            private val JsonElement.asMeta
                get() = Meta(this.asJsonObject)
            class Meta (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                 val size: Double by lazy { jsonObject.get("size").asDouble }
                 val itemId: Double by lazy { jsonObject.get("item_id").asDouble }
                 val vipNoAccessText: String? by lazy { jsonObject.get("vip_no_access_text")?.asString }
                 val itemUrl: String? by lazy { jsonObject.get("item_url")?.asString }
            }
            private val JsonElement.asEmote
                get() = Emote(this.asJsonObject)
            class Emote (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                 val id: Double by lazy { jsonObject.get("id").asDouble }
                 val packageId: Double by lazy { jsonObject.get("package_id").asDouble }
                 val text: String by lazy { jsonObject.get("text").asString }
                 val url: String by lazy { jsonObject.get("url").asString }
                 val mtime: Double by lazy { jsonObject.get("mtime").asDouble }
                 val type: Double by lazy { jsonObject.get("type").asDouble }
                 val attr: Double by lazy { jsonObject.get("attr").asDouble }
                 val meta: Meta by lazy { jsonObject.get("meta").asMeta }
                 val flags: Flags by lazy { jsonObject.get("flags").asFlags }
                 val gifUrl: String? by lazy { jsonObject.get("gif_url")?.asString }
                private val JsonElement.asMeta
                    get() = Meta(this.asJsonObject)
                class Meta (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                     val size: Double by lazy { jsonObject.get("size").asDouble }
                     val suggest: List<String> by lazy { jsonObject.get("suggest").asJsonArray.map { it.asString } }
                     val alias: String? by lazy { jsonObject.get("alias")?.asString }
                     val gifUrl: String? by lazy { jsonObject.get("gif_url")?.asString }
                }
                private val JsonElement.asFlags
                    get() = Flags(this.asJsonObject)
                class Flags (private val jsonObject: JsonObject) {
                    fun getJsonObject()  = jsonObject
                     val unlocked: Boolean by lazy { jsonObject.get("unlocked").asBoolean }
                }
            }
            private val JsonElement.asFlags
                get() = Flags(this.asJsonObject)
            class Flags (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                 val added: Boolean? by lazy { jsonObject.get("added")?.asBoolean }
                 val preview: Boolean? by lazy { jsonObject.get("preview")?.asBoolean }
                 val noAccess: Boolean? by lazy { jsonObject.get("no_access")?.asBoolean }
            }
        }
        private val JsonElement.asMall
            get() = Mall(this.asJsonObject)
        class Mall (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
             val title: String by lazy { jsonObject.get("title").asString }
             val url: String by lazy { jsonObject.get("url").asString }
        }
    }
}