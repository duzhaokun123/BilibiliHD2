package  com.hiczp.bilibili.api.app.model
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom
@LazyjsonClass class MyInfo (private val jsonObject: JsonObject) {
    companion object   {
        @LazyjsonFrom @JvmStatic fun from(jsonObject: JsonObject)  = MyInfo(jsonObject)
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
         val mid: Number
            get() = jsonObject.get("mid").asNumber
         val name: String
            get() = jsonObject.get("name").asString
         val sign: String
            get() = jsonObject.get("sign").asString
         val coins: Number
            get() = jsonObject.get("coins").asNumber
         val birthday: String
            get() = jsonObject.get("birthday").asString
         val face: String
            get() = jsonObject.get("face").asString
         val faceNftNew: Number
            get() = jsonObject.get("face_nft_new").asNumber
         val sex: Number
            get() = jsonObject.get("sex").asNumber
         val level: Number
            get() = jsonObject.get("level").asNumber
         val rank: Number
            get() = jsonObject.get("rank").asNumber
         val silence: Number
            get() = jsonObject.get("silence").asNumber
         val vip: Vip
            get() = jsonObject.get("vip").asVip
         val emailStatus: Number
            get() = jsonObject.get("email_status").asNumber
         val telStatus: Number
            get() = jsonObject.get("tel_status").asNumber
         val official: Official
            get() = jsonObject.get("official").asOfficial
         val identification: Number
            get() = jsonObject.get("identification").asNumber
         val invite: Invite
            get() = jsonObject.get("invite").asInvite
         val isTourist: Number
            get() = jsonObject.get("is_tourist").asNumber
         val pinPrompting: Number
            get() = jsonObject.get("pin_prompting").asNumber
         val inRegAudit: Number
            get() = jsonObject.get("in_reg_audit").asNumber
         val hasFaceNft: Boolean
            get() = jsonObject.get("has_face_nft").asBoolean
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
             val role: Number
                get() = jsonObject.get("role").asNumber
             val avatarSubscriptUrl: String
                get() = jsonObject.get("avatar_subscript_url").asString
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
                 val textColor: String
                    get() = jsonObject.get("text_color").asString
                 val bgStyle: Number
                    get() = jsonObject.get("bg_style").asNumber
                 val bgColor: String
                    get() = jsonObject.get("bg_color").asString
                 val borderColor: String
                    get() = jsonObject.get("border_color").asString
            }
        }
        private val JsonElement.asOfficial
            get() = Official(this.asJsonObject)
        class Official (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val role: Number
                get() = jsonObject.get("role").asNumber
             val title: String
                get() = jsonObject.get("title").asString
             val desc: String
                get() = jsonObject.get("desc").asString
             val type: Number
                get() = jsonObject.get("type").asNumber
        }
        private val JsonElement.asInvite
            get() = Invite(this.asJsonObject)
        class Invite (private val jsonObject: JsonObject) {
            fun getJsonObject()  = jsonObject
            override fun toString()  = jsonObject.toString()
             val inviteRemind: Number
                get() = jsonObject.get("invite_remind").asNumber
             val display: Boolean
                get() = jsonObject.get("display").asBoolean
        }
    }
}