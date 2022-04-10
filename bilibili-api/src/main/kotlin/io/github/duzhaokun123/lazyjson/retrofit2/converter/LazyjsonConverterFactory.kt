package io.github.duzhaokun123.lazyjson.retrofit2.converter

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonClass
import io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier
import java.lang.reflect.Type

class LazyjsonConverterFactory: Converter.Factory() {
    companion object {
        fun create(gson: Gson): LazyjsonConverterFactory {
            return LazyjsonConverterFactory().apply { this.gson = gson }
        }
    }

    lateinit var gson: Gson

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val clazz = TypeToken.get(type).rawType
        clazz.getAnnotation(LazyjsonClass::class.java) ?: return null
        val fromFunction = clazz.methods.find { it.getAnnotation(LazyjsonFrom::class.java) != null && Modifier.isStatic(it.modifiers) } ?: return null
        return LazyjsonResponseBodyConverter(gson, fromFunction)
    }

    val gsonConverterFactory by lazy { GsonConverterFactory.create(gson) }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        val clazz = TypeToken.get(type).rawType
        clazz.getAnnotation(LazyjsonClass::class.java) ?: return null
        return LazyjsonRequestBodyConverter(gson)
    }
}