package  com.hiczp.bilibili.api.passport.model

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import com.hiczp.bilibili.api.getCookie
import com.hiczp.bilibili.api.retrofit.Cookie
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom

@LazyjsonClass
class LoginResponse(private val jsonObject: JsonObject) {
    companion object {
        @LazyjsonFrom
        @JvmStatic
        fun from(jsonObject: JsonObject) = LoginResponse(jsonObject)
    }
    constructor(code: Number,message: String, data: Data,  ts: Long) : this(
        JsonObject().apply {
            addProperty("code", code)
            add("data", data.getJsonObject())
            addProperty("message", message)
            addProperty("ts", ts)
        }
    )

    fun getJsonObject() = jsonObject
    override fun toString() = jsonObject.toString()
    val code: Number
        get() = jsonObject.get("code").asNumber
    val data: Data
        get() = jsonObject.get("data").asData
    val message: String
        get() = jsonObject.get("message").asString
    val ts: Long
        get() = jsonObject.get("ts").asLong
    private val JsonElement.asData
        get() = Data(this.asJsonObject)

    class Data(private val jsonObject: JsonObject) {
        constructor(cookieInfo: CookieInfo, message: String, status: Number, tokenInfo: TokenInfo) : this(JsonObject().apply {
            add("cookie_info", cookieInfo.getJsonObject())
            addProperty("message", message)
            addProperty("status", status)
            add("token_info", tokenInfo.getJsonObject())
        })
        fun getJsonObject() = jsonObject
        override fun toString() = jsonObject.toString()
        val cookieInfo: CookieInfo
            get() = jsonObject.get("cookie_info").asCookieInfo
        val message: String
            get() = jsonObject.get("message").asString
        val status: Number
            get() = jsonObject.get("status").asNumber
        val tokenInfo: TokenInfo
            get() = jsonObject.get("token_info").asTokenInfo
        val url: String
            get() = jsonObject.get("url").asString
        private val JsonElement.asCookieInfo
            get() = CookieInfo(this.asJsonObject)

        class CookieInfo(private val jsonObject: JsonObject) {
            constructor(cookies: List<Cookies>, domains: List<String>) : this(JsonObject().apply {
                add("cookies", cookies.fold(JsonArray()) { array, cookie ->
                    array.add(cookie.getJsonObject())
                    array
                })
                add("domains", domains.fold(JsonArray()) { array, domain ->
                    array.add(domain)
                    array
                })
            })
            fun getJsonObject() = jsonObject
            override fun toString() = jsonObject.toString()
            val cookies: List<Cookies>
                get() = jsonObject.get("cookies").asJsonArray.map { it.asCookies }
            val domains: List<String>
                get() = jsonObject.get("domains").asJsonArray.map { it.asString }
            private val JsonElement.asCookies
                get() = Cookies(this.asJsonObject)
            class Cookies(private val jsonObject: JsonObject) {
                constructor(expires: Long, name: String, value: String) : this(JsonObject().apply {
                    addProperty("expires", expires)
                    addProperty("name", name)
                    addProperty("value", value)
                })

                fun getJsonObject() = jsonObject
                override fun toString() = jsonObject.toString()
                operator fun component1() = expires
                operator fun component2() = name
                operator fun component3() = value

                val expires: Long
                    get() = jsonObject.get("expires").asLong
                val name: String
                    get() = jsonObject.get("name").asString
                val value: String
                    get() = jsonObject.get("value").asString
            }
        }

        private val JsonElement.asTokenInfo
            get() = TokenInfo(this.asJsonObject)

        class TokenInfo(private val jsonObject: JsonObject) {
            constructor(accessToken: String, expiresIn: Long, mid: Long, refreshToken: String) : this(JsonObject().apply {
                addProperty("access_token", accessToken)
                addProperty("expires_in", expiresIn)
                addProperty("mid", mid)
                addProperty("refresh_token", refreshToken)
            })
            fun getJsonObject() = jsonObject
            override fun toString() = jsonObject.toString()
            val accessToken: String
                get() = jsonObject.get("access_token").asString
            val expiresIn: Long
                get() = jsonObject.get("expires_in").asLong
            val mid: Long
                get() = jsonObject.get("mid").asLong
            val refreshToken: String
                get() = jsonObject.get("refresh_token").asString
        }
    }

    //快捷方式
    val userId get() = data.tokenInfo.mid
    val token get() = data.tokenInfo.accessToken

    /**
     * 过期时间 秒
     */
    val expires get() = data.cookieInfo.cookies[0].expires

    val dedeUserID get() = getCookie(Cookie.DEDE_USER_ID)
    val dedeUserIDCkMd5 get() = getCookie(Cookie.DEDE_USER_ID_CKMD5)
    val sessdata get() = getCookie(Cookie.SESSDATA)
    val biliJct get() = getCookie(Cookie.BILI_JCT)
    val sid get() = getCookie(Cookie.SID)
}