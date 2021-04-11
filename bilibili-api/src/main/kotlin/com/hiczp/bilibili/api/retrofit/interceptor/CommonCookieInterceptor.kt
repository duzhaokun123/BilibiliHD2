package com.hiczp.bilibili.api.retrofit.interceptor

import com.hiczp.bilibili.api.retrofit.ParamExpression
import com.hiczp.bilibili.api.retrofit.forEachNonNull
import okhttp3.Interceptor
import okhttp3.Response

class CommonCookieInterceptor(private vararg val additionCookies: ParamExpression) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            additionCookies.forEachNonNull { name, value ->
                addHeader("Cookie", "$name=$value")
            }
        }.build()
        return chain.proceed(request)
    }
}