package com.duzhaokun123.bilibilihd2.model

import com.duzhaokun123.bilibilihd2.utils.gson
import com.github.salomonbrys.kotson.fromJson
import com.hiczp.bilibili.api.vc.model.DynamicHistory
import com.hiczp.bilibili.api.vc.model.DynamicNew

data class DynamicCardModel(
    val type: Int,
    val card: Any,
    val cardJson: String,
    val user: User,
    val state: State,
    val time: Long
) {
    companion object {
        fun parse(dynamicNew: DynamicNew): List<DynamicCardModel> {
            val models = mutableListOf<DynamicCardModel>()
            dynamicNew.data.cards.forEach { card ->
                val user = run {
                    val info = card.desc.userProfile?.info
                    val name = info?.uname ?: ""
                    val face = info?.face
                    val uid = info?.uid ?: 0
                    val isVip = card.desc.userProfile?.vip?.vipStatus == 1
                    User(name, face, uid, isVip)
                }
                val state = run {
                    val repost = card.desc.repost
                    val view = card.desc.view
                    val comment = card.desc.comment
                    val like = card.desc.like
                    State(repost, view, comment, like)
                }
                val type = card.desc.type
                val cardJson = card.card
                val cardObj = when (type) {
                    2 -> Type2.parse(gson.fromJson(cardJson))
                    4 -> Type4.parse(gson.fromJson(cardJson))
                    8 -> Type8.parse(gson.fromJson(cardJson))
                    else -> Any()
                }
                val time = card.desc.timestamp
                models.add(DynamicCardModel(type, cardObj, cardJson, user, state, time))
            }
            return models
        }

        fun parse(dynamicHistory: DynamicHistory): List<DynamicCardModel> {
            val models = mutableListOf<DynamicCardModel>()
            dynamicHistory.data.cards.forEach { card ->
                val user = run {
                    val info = card.desc.userProfile?.info
                    val name = info?.uname ?: ""
                    val face = info?.face
                    val uid = info?.uid ?: 0
                    val isVip = card.desc.userProfile?.vip?.vipStatus == 1
                    User(name, face, uid, isVip)
                }
                val state = run {
                    val repost = card.desc.repost
                    val view = card.desc.view
                    val comment = card.desc.comment
                    val like = card.desc.like
                    State(repost, view, comment, like)
                }
                val type = card.desc.type
                val cardJson = card.card
                val cardObj = when (type) {
                    2 -> Type2.parse(gson.fromJson(cardJson))
                    4 -> Type4.parse(gson.fromJson(cardJson))
                    8 -> Type8.parse(gson.fromJson(cardJson))
                    else -> Any()
                }
                val time = card.desc.timestamp
                models.add(DynamicCardModel(type, cardObj, cardJson, user, state, time))
            }
            return models
        }
    }

    data class User(
        val name: String,
        val faceUrl: String?,
        val uid: Long,
        val isVip: Boolean
    )

    data class State(
        val repost: Int,
        val view: Int,
        val comment: Int,
        val like: Int
    )

    data class Type2(
        val desc: String,
        val pictures: List<String>
    ) {
        companion object {
            fun parse(dynamicCardType2: DynamicCardType2): Type2 {
                val desc = dynamicCardType2.item.description
                val pictures = mutableListOf<String>()
                dynamicCardType2.item.pictures.forEach { picture ->
                    pictures.add(picture.imgSrc)
                }
                return Type2(desc, pictures)
            }
        }
    }

    data class Type8(
        val title: String,
        val coverUrl: String,
        val url: String,
        val desc: String
    ) {
        companion object {
            fun parse(dynamicCardType8: DynamicCardType8): Type8 {
                val title = dynamicCardType8.title
                val coverUrl = dynamicCardType8.pic
                val url = dynamicCardType8.jumpUrl
                val desc = dynamicCardType8.desc
                return Type8(title, coverUrl, url, desc)
            }
        }
    }

    data class Type4(
        val content: String
    ) {
        companion object {
            fun parse(dynamicCardType4: DynamicCardType4): Type4 {
                return Type4(dynamicCardType4.item.content)
            }
        }
    }
}