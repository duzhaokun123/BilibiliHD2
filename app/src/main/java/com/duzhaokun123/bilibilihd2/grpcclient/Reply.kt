package com.duzhaokun123.bilibilihd2.grpcclient

import com.bapis.bilibili.main.community.reply.v1.*

class Reply(private val replyStub: ReplyGrpc.ReplyBlockingStub) {
    fun mainList(oid: Long, type: Int, next: Long = 0, mode: Mode): MainListReply {
        val replyMainListReply = MainListReq.newBuilder()
            .setOid(oid)
            .setType(type.toLong())
            .setCursor(CursorReq.newBuilder().setNext(next).setMode(mode).build())
            .build()
        return replyStub.mainList(replyMainListReply)
    }

    fun detailList(oid: Long, type: Int,root: Long, next: Long = 0): DetailListReply {
        val replyDetailListReply = DetailListReq.newBuilder()
            .setOid(oid)
            .setType(type.toLong())
            .setRoot(root)
            .setCursor(CursorReq.newBuilder().setNext(next).build())
            .build()
        return replyStub.detailList(replyDetailListReply)
    }

}