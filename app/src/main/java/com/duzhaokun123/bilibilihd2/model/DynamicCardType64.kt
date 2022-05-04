package  com.duzhaokun123.bilibilihd2.model
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom
@LazyjsonClass class DynamicCardType64 (private val jsonObject: JsonObject) {
    companion object   {
        @LazyjsonFrom @JvmStatic fun from(jsonObject: JsonObject)  = DynamicCardType64(jsonObject)
    }
    fun getJsonObject()  = jsonObject
    override fun toString()  = jsonObject.toString()
     val id: Long
        get() = jsonObject.get("id").asLong
     val category: Category
        get() = jsonObject.get("category").asCategory
     val categories: List<Categories>
        get() = jsonObject.get("categories").asJsonArray.map { it.asCategories }
     val title: String
        get() = jsonObject.get("title").asString
     val summary: String
        get() = jsonObject.get("summary").asString
     val bannerUrl: String
        get() = jsonObject.get("banner_url").asString
     val templateId: Number
        get() = jsonObject.get("template_id").asNumber
     val state: Number
        get() = jsonObject.get("state").asNumber
     val author: Author
        get() = jsonObject.get("author").asAuthor
     val reprint: Number
        get() = jsonObject.get("reprint").asNumber
     val imageUrls: List<String>
        get() = jsonObject.get("image_urls").asJsonArray.map { it.asString }
     val publishTime: Number
        get() = jsonObject.get("publish_time").asNumber
     val ctime: Number
        get() = jsonObject.get("ctime").asNumber
     val stats: Stats
        get() = jsonObject.get("stats").asStats
     val attributes: Number
        get() = jsonObject.get("attributes").asNumber
     val words: Number
        get() = jsonObject.get("words").asNumber
     val originImageUrls: List<String>
        get() = jsonObject.get("origin_image_urls").asJsonArray.map { it.asString }
     val isLike: Boolean
        get() = jsonObject.get("is_like").asBoolean
     val media: Media
        get() = jsonObject.get("media").asMedia
     val applyTime: String
        get() = jsonObject.get("apply_time").asString
     val checkTime: String
        get() = jsonObject.get("check_time").asString
     val original: Number
        get() = jsonObject.get("original").asNumber
     val actId: Number
        get() = jsonObject.get("act_id").asNumber
     val coverAvid: Number
        get() = jsonObject.get("cover_avid").asNumber
     val type: Number
        get() = jsonObject.get("type").asNumber
    private val JsonElement.asCategory
        get() = Category(this.asJsonObject)
    class Category (private val jsonObject: JsonObject) {
        fun getJsonObject()  = jsonObject
        override fun toString()  = jsonObject.toString()
         val id: Number
            get() = jsonObject.get("id").asNumber
         val parentId: Number
            get() = jsonObject.get("parent_id").asNumber
         val name: String
            get() = jsonObject.get("name").asString
    }
    private val JsonElement.asCategories
        get() = Categories(this.asJsonObject)
    class Categories (private val jsonObject: JsonObject) {
        fun getJsonObject()  = jsonObject
        override fun toString()  = jsonObject.toString()
         val id: Number
            get() = jsonObject.get("id").asNumber
         val parentId: Number
            get() = jsonObject.get("parent_id").asNumber
         val name: String
            get() = jsonObject.get("name").asString
    }
    private val JsonElement.asAuthor
        get() = Author(this.asJsonObject)
    class Author (private val jsonObject: JsonObject) {
        fun getJsonObject()  = jsonObject
        override fun toString()  = jsonObject.toString()
         val mid: Number
            get() = jsonObject.get("mid").asNumber
         val name: String
            get() = jsonObject.get("name").asString
         val face: String
            get() = jsonObject.get("face").asString
         val pendant: Pendant
            get() = jsonObject.get("pendant").asPendant
         val officialVerify: OfficialVerify
            get() = jsonObject.get("official_verify").asOfficialVerify
         val nameplate: Nameplate
            get() = jsonObject.get("nameplate").asNameplate
         val vip: Vip
            get() = jsonObject.get("vip").asVip
        private val JsonElement.asPendant
            get() = Pendant(this.asJsonObject)
        class Pendant (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val pid: Number
                get() = jsonObject.get("pid").asNumber
             val name: String
                get() = jsonObject.get("name").asString
             val image: String
                get() = jsonObject.get("image").asString
             val expire: Number
                get() = jsonObject.get("expire").asNumber
        }
        private val JsonElement.asOfficialVerify
            get() = OfficialVerify(this.asJsonObject)
        class OfficialVerify (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val type: Number
                get() = jsonObject.get("type").asNumber
             val desc: String
                get() = jsonObject.get("desc").asString
        }
        private val JsonElement.asNameplate
            get() = Nameplate(this.asJsonObject)
        class Nameplate (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val nid: Number
                get() = jsonObject.get("nid").asNumber
             val name: String
                get() = jsonObject.get("name").asString
             val image: String
                get() = jsonObject.get("image").asString
             val imageSmall: String
                get() = jsonObject.get("image_small").asString
             val level: String
                get() = jsonObject.get("level").asString
             val condition: String
                get() = jsonObject.get("condition").asString
        }
        private val JsonElement.asVip
            get() = Vip(this.asJsonObject)
        class Vip (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val type: Number
                get() = jsonObject.get("type").asNumber
             val status: Number
                get() = jsonObject.get("status").asNumber
             val dueDate: Number
                get() = jsonObject.get("due_date").asNumber
             val vipPayType: Number
                get() = jsonObject.get("vip_pay_type").asNumber
             val themeType: Number
                get() = jsonObject.get("theme_type").asNumber
             val label: Label
                get() = jsonObject.get("label").asLabel
             val avatarSubscript: Number
                get() = jsonObject.get("avatar_subscript").asNumber
             val nicknameColor: String
                get() = jsonObject.get("nickname_color").asString
            private val JsonElement.asLabel
                get() = Label(this.asJsonObject)
            class Label (private val jsonObject: JsonObject) {
                fun getJsonObject()  = jsonObject
                override fun toString()  = jsonObject.toString()
                 val path: String
                    get() = jsonObject.get("path").asString
                 val text: String
                    get() = jsonObject.get("text").asString
                 val labelTheme: String
                    get() = jsonObject.get("label_theme").asString
            }
        }
    }
    private val JsonElement.asStats
        get() = Stats(this.asJsonObject)
    class Stats (private val jsonObject: JsonObject) {
        fun getJsonObject()  = jsonObject
        override fun toString()  = jsonObject.toString()
         val view: Number
            get() = jsonObject.get("view").asNumber
         val favorite: Number
            get() = jsonObject.get("favorite").asNumber
         val like: Number
            get() = jsonObject.get("like").asNumber
         val dislike: Number
            get() = jsonObject.get("dislike").asNumber
         val reply: Number
            get() = jsonObject.get("reply").asNumber
         val share: Number
            get() = jsonObject.get("share").asNumber
         val coin: Number
            get() = jsonObject.get("coin").asNumber
         val dynamic: Number
            get() = jsonObject.get("dynamic").asNumber
    }
    private val JsonElement.asMedia
        get() = Media(this.asJsonObject)
    class Media (private val jsonObject: JsonObject) {
        fun getJsonObject()  = jsonObject
        override fun toString()  = jsonObject.toString()
         val score: Number
            get() = jsonObject.get("score").asNumber
         val mediaId: Number
            get() = jsonObject.get("media_id").asNumber
         val title: String
            get() = jsonObject.get("title").asString
         val cover: String
            get() = jsonObject.get("cover").asString
         val area: String
            get() = jsonObject.get("area").asString
         val typeId: Number
            get() = jsonObject.get("type_id").asNumber
         val typeName: String
            get() = jsonObject.get("type_name").asString
         val spoiler: Number
            get() = jsonObject.get("spoiler").asNumber
         val seasonId: Number
            get() = jsonObject.get("season_id").asNumber
    }
}