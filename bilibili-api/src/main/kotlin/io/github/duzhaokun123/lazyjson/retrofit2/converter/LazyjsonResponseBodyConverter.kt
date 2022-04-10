package io.github.duzhaokun123.lazyjson.retrofit2.converter

import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Converter
import java.lang.reflect.Method

class LazyjsonResponseBodyConverter(private val gson: Gson, private val fromFunction: Method) : Converter<ResponseBody, Any> {
    override fun convert(value: ResponseBody): Any {
        val jsonObject = gson.fromJson(value.charStream(), JsonObject::class.java)
        return fromFunction.invoke(null, jsonObject)
    }
}