package com.hiczp.bilibili.api.retrofit.interceptor

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.hiczp.bilibili.api.retrofit.CommonResponse
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom

@LazyjsonClass
class CommonResponseLazyjson(private val jsonObject: JsonObject) {
    companion object {
        @JvmStatic
        @LazyjsonFrom
        fun from(jsonObject: JsonObject) = CommonResponseLazyjson(jsonObject)
    }

    val code: Int by lazy { jsonObject.get("code").asInt }
    val msg: String? by lazy { jsonObject.get("msg")?.asString }
    val message: String? by lazy { jsonObject.get("message")?.asString }
    val timestamp: Long by lazy { jsonObject.get("timestamp").asLong }
    val data: JsonElement? by lazy { jsonObject.get("data") }
    val tts: Int? by lazy { jsonObject.get("tts")?.asInt }

    fun toCommonResponse() = CommonResponse(code, msg, message, timestamp, data, tts)
}