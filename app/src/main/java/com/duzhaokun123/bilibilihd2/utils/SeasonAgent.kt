package com.duzhaokun123.bilibilihd2.utils

import android.os.SystemClock
import com.hiczp.bilibili.api.BilibiliClient
import com.hiczp.bilibili.api.main.model.Season
import com.hiczp.bilibili.api.md5
import com.hiczp.bilibili.api.player.PlayerInterceptor
import com.hiczp.bilibili.api.player.model.BangumiPlayUrl
import com.hiczp.bilibili.api.retrofit.interceptor.FailureResponseInterceptor
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

object SeasonAgent {
    fun init(url: String) {
        seasonApi = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(BilibiliClient.gsonConverterFactory as Converter.Factory)
            .addCallAdapterFactory(BilibiliClient.coroutineCallAdapterFactory as CallAdapter.Factory)
            .client(OkHttpClient.Builder().apply {
                addInterceptor(PlayerInterceptor(bilibiliClient.billingClientProperties) { bilibiliClient.loginResponse })
                addInterceptor(FailureResponseInterceptor)
                addNetworkInterceptor(bilibiliClient.httpLoggingInterceptor)
                connectionPool(BilibiliClient.connectionPool)
            }.build())
            .build()
            .create(SeasonApi::class.java)
    }

    lateinit var seasonApi: SeasonApi

    interface SeasonApi {
        /**
         * 获得一个番剧的分季信息(生成番剧页面所需的信息), 包含当前选择的季的分集信息
         * seasonId 或 episodeId 必须有一个, 如果用 episodeId 将跳转到对应的 season 的页面
         * 返回值中, 每个 episode 都有 aid 和 cid
         *
         * @param seasonId 季的唯一标识
         * @param episodeId 集的唯一标识
         */
        @GET("/pgc/view/app/season")
        fun season(
            @Query("season_id") seasonId: Long? = null,
            @Query("ep_id") episodeId: Long? = null,
            @Query("track_path") trackPath: Int? = null
        ): Deferred<Season>

        /**
         * 获得番剧的播放地址
         *
         * @param aid 番剧的唯一标识
         * @param cid 在番剧详情页的返回值里
         * @param seasonType 分季类型, 不明确, 似乎总为 1
         * @param session 其值为 系统已运行时间(ms)的MD5值, 此处的默认值为 JVM 已启动时间, 在 Android 上请使用 SystemClock
         * @param trackPath 不明确
         *
         * @see com.hiczp.bilibili.api.main.MainAPI.season
         */
        @GET("/pgc/player/api/playurl")
        fun bangumiPlayUrl(
            @Query("aid") aid: Long,
            @Query("cid") cid: Long,
            @Query("fnval") fnVal: Int = 16,
            @Query("fnver") fnVer: Int = 0,
            @Query("module") module: String = "bangumi",
            @Query("npcybs") npcybs: Int = 0,
            @Query("qn") qn: Int = 32,
            @Query("season_type") seasonType: Int = 1,
            @Query("session") session: String = SystemClock.uptimeMillis().toString().md5(),
            @Query("track_path") trackPath: Int? = null
        ): Deferred<BangumiPlayUrl>
    }
}