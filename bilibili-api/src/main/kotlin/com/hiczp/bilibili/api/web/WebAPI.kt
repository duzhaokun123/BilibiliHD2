package com.hiczp.bilibili.api.web

import com.hiczp.bilibili.api.retrofit.CommonResponse
import com.hiczp.bilibili.api.web.model.VideoShot
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface WebAPI {

    /**
     * 上报视频播放心跳
     * 间隔15秒一次
     *
     * @param aid aid bvid 二选一
     * @param bvid aid bvid 二选一
     * @param cid 用于识别分P
     * @param playedTime 视频播放进度 (秒)
     */
    @POST("/x/click-interface/web/heartbeat")
    @FormUrlEncoded
    fun heartbeat(
        @Field("aid") aid: Long? = null,
        @Field("bvid") bvid: String? = null,
        @Field("cid") cid: Long? = null,
        @Field("played_time") playedTime: Long
    ): Deferred<CommonResponse>

    /**
     * 视频快照
     * [详情](https://github.com/SocialSisterYi/bilibili-API-collect/blob/master/video/snapshot.md#%E8%A7%86%E9%A2%91%E5%BF%AB%E7%85%A7)
     *
     * @param cid 默认1P
     */
    @GET("/x/player/videoshot")
    fun videoshot(
        @Query("aid") aid: Long,
        @Query("cid") cid: Long? = null,
        @Query("index") index: Int = 0
    ): Deferred<VideoShot>
}