package com.hiczp.bilibili.api.web

import com.hiczp.bilibili.api.player.model.VideoPlayUrl
import com.hiczp.bilibili.api.retrofit.interceptor.CommonResponseLazyjson
import com.hiczp.bilibili.api.web.model.*
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
    ): Deferred<CommonResponseLazyjson>

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

    /**
     * 全部表情包
     * [详情](https://github.com/SocialSisterYi/bilibili-API-collect/blob/66d1d1909a233fdf7d539999488d0c00de240692/emoji/list.md#%E8%8E%B7%E5%8F%96%E6%89%80%E6%9C%89%E8%A1%A8%E6%83%85%E5%8C%85%E5%88%97%E8%A1%A8)
     *
     * @param business reply：评论区 dynamic：动态
     */
    @GET("/x/emote/setting/panel")
    fun emoteList(
        @Query("business") business: String
    ): Deferred<EmoteList>

    @GET("/x/player/playurl")
    fun playUrl(
        @Query("avid") avid: Long,
        @Query("cid") cid: Long,
        @Query("qn") qn: Int = 0,
        @Query("fnval") fnval: Int = 16 /* dash */ + 64 /* hdr */ /*+ 128 /* 4k */ + 256 /* 杜比音频 */ + 512 /* 杜比视界 */ + 1024 /* 8k */ */,
        @Query("fnver") fnver: Int = 0,
        @Query("fourk") fourk: Int = 1,
    ): Deferred<VideoPlayUrl>

    @GET("/x/player/v2")
    fun playerV2(
        @Query("aid") aid: Long,
        @Query("cid") cid: Long,
    ): Deferred<PlayerV2>
}