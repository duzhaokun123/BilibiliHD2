package com.duzhaokun123.bilibilihd2.model

import com.bapis.bilibili.main.community.reply.v1.ReplyInfo

class RootCommentCardModel(
    val userModel: UserModel,
    val content: String,
    val ctime: Long,
    var like: Int,
    // 1: liked, 0: not liked, 2: disliked
    var likeStatus: Int,
    val rpid: Long,
)

fun List<ReplyInfo>.toRootCommentCardModel(): List<RootCommentCardModel> {
    return map {
        RootCommentCardModel(
            UserModel(
                it.member.name, it.mid, it.member.face, "", it.member.level.toInt()
            ), it.content.message, it.ctime, it.like.toInt(), it.replyControl.action.toInt(), it.id
        )
    }
}