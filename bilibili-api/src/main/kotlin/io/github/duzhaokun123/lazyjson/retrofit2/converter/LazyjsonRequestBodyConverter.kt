package io.github.duzhaokun123.lazyjson.retrofit2.converter

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Converter

class LazyjsonRequestBodyConverter(private val gson: Gson): Converter<Any, RequestBody> {
    companion object {
        val MEDIA_TYPE: MediaType = "application/json; charset=UTF-8".toMediaType()
    }

    override fun convert(value: Any): RequestBody {
        val json = gson.toJson(value.javaClass.getDeclaredMethod("getJsonObject").invoke(value))
        return json.toRequestBody(MEDIA_TYPE)
    }
}