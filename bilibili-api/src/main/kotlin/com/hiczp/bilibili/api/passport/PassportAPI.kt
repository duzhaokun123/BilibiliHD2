package com.hiczp.bilibili.api.passport

import com.hiczp.bilibili.api.passport.model.*
import com.hiczp.bilibili.api.retrofit.interceptor.CommonResponseLazyjson
import kotlinx.coroutines.Deferred
import retrofit2.http.*
import java.util.*

/**
 * 用户鉴权相关的接口
 */
@Suppress("DeferredIsResult")
interface PassportAPI {
    /**
     * 除了 accessToken, 其他全部都是 cookie 的值
     */
    @Suppress("SpellCheckingInspection")
    @POST("/api/v2/oauth2/revoke")
    @FormUrlEncoded
    fun revoke(
            @Field("DedeUserID") dedeUserId: String? = null,
            @Field("DedeUserID__ckMd5") ckMd5: String? = null,
            @Field("SESSDATA") sessData: String? = null,
            @Field("access_token") accessToken: String,
            @Field("bili_jct") biliJct: String? = null,
            @Field("sid") sid: String? = null
    ): Deferred<CommonResponseLazyjson>

    /**
     * 将所有 cookie 以 Map 形式传入
     */
    @POST("/api/v2/oauth2/revoke")
    @FormUrlEncoded
    fun revoke(
            @FieldMap cookieMap: Map<String, String> = Collections.emptyMap(),
            @Field("access_token") accessToken: String
    ): Deferred<CommonResponseLazyjson>

    @POST("/x/passport-login/oauth2/refresh_token")
    @FormUrlEncoded
    fun refreshToken(
        @Field("access_token") accessToken: String,
        @Field("refresh_token") refreshToken: String,
        @FieldMap cookieMap: Map<String, String>
    ): Deferred<LoginResponse>

    /**
     * 申请二维码URL及扫码密钥
     */
    @GET("/qrcode/getLoginUrl")
    fun getQRLoginUrl(): Deferred<QRLoginUrl>
}
