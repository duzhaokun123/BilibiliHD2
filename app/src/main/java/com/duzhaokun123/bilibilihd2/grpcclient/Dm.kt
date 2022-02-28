package com.duzhaokun123.bilibilihd2.grpcclient

import com.bapis.bilibili.community.service.dm.v1.DMGrpc
import com.bapis.bilibili.community.service.dm.v1.DmSegMobileReply
import com.bapis.bilibili.community.service.dm.v1.DmSegMobileReq

class Dm(private val dmStub: DMGrpc.DMBlockingStub) {
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