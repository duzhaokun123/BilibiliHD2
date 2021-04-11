package com.hiczp.bilibili.api.weblive

import com.hiczp.bilibili.api.weblive.model.PlayUrl
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface WebLiveAPI {

    /**
     * 根据真实直播间号获取直播视频流
     *
     * @param cid 目标真实直播间号
     * @param platform 直播流格式 h5：hls方式, web：http-flv方式 (链接带有转意)
     * @param qn 画质 80：流畅, 150：高清, 400：蓝光, 10000：原画
     */
    @GET("room/v1/Room/playUrl")
    fun playUrl(
            @Query("cid") cid: Long,
            @Query("platform") platform: String = "web",
            @Query("qn") qn: Int = 10000
    ): Deferred<PlayUrl>
}