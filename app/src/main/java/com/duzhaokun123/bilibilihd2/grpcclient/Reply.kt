package com.duzhaokun123.bilibilihd2.grpcclient

import com.bapis.bilibili.main.community.reply.v1.*

class Reply(private val replyStub: ReplyGrpc.ReplyBlockingStub) {
    fun mainList(oid: Long, type: Long, next: Long = 0, mode: Mode): MainListReply {
        val replyMainListReply = MainListReq.newBuilder()
            .setOid(oid)
            .setType(type)
            .setCursor(CursorReq.newBuilder().setNext(next).setMode(mode).build())
            .build()
        return replyStub.mainList(replyMainListReply)
    }
}