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

    val code: Int get() = jsonObject.get("code").asInt
    val msg: String? get() = jsonObject.get("msg")?.asString
    val message: String? get() = jsonObject.get("message")?.asString
    val timestamp: Long get() = jsonObject.get("timestamp").asLong
    val data: JsonElement? get() = jsonObject.get("data")
    val tts: Int? get() = jsonObject.get("tts")?.asInt

    fun toCommonResponse() = CommonResponse(code, msg, message, timestamp, data, tts)

    override fun toString() = "CommonResponseLazyjson(code=$code, msg=$msg, message=$message, timestamp=$timestamp, data=$data, tts=$tts)"
}