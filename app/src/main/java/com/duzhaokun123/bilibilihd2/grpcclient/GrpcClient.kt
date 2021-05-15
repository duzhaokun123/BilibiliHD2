package com.duzhaokun123.bilibilihd2.grpcclient

import com.bapis.bilibili.community.service.dm.v1.*
import com.duzhaokun123.bilibilihd2.utils.application
import com.duzhaokun123.bilibilihd2.utils.bilibiliClient
import io.grpc.*
import io.grpc.android.AndroidChannelBuilder
import io.grpc.stub.MetadataUtils
import com.bapis.bilibili.metadata.Metadata as BiliMetadata

class GrpcClient {
    private val xBiliMetadataBin =
        Metadata.Key.of("x-bili-metadata-bin", Metadata.BINARY_BYTE_MARSHALLER)

    private var channel: ManagedChannel? = null

    private lateinit var dmStub: DMGrpc.DMBlockingStub

    fun rebuildChannel() {
        channel?.shutdown()
        channel = AndroidChannelBuilder.forTarget("grpc.biliapi.net")
            .context(application)
            .intercept(MetadataUtils.newAttachHeadersInterceptor(Metadata().apply {
                put(xBiliMetadataBin, BiliMetadata.newBuilder().apply {
                    bilibiliClient.loginResponse?.let { accessKey = it.token }
                }.build().toByteArray())
            }))
            .build()
        dmStub = DMGrpc.newBlockingStub(channel)
    }

    fun dmSegVideo(aid: Long, cid: Long, segmentIndex: Long): DmSegMobileReply {
        val dmSegMobileReply = DmSegMobileReq.newBuilder()
            .setPid(aid)
            .setOid(cid)
            .setType(1)
            .setSegmentIndex(segmentIndex)
            .setTeenagersMode(0)
            .build()
        return dmStub.dmSegMobile(dmSegMobileReply)
    }
}