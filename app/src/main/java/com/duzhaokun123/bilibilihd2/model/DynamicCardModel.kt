package com.duzhaokun123.bilibilihd2.model

import com.duzhaokun123.bilibilihd2.utils.gson
import com.github.salomonbrys.kotson.fromJson
import com.hiczp.bilibili.api.vc.model.DynamicHistory
import com.hiczp.bilibili.api.vc.model.DynamicNew
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog

const val TYPE_ERROR = -1

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
                    val name = info?.uname
                    val face = info?.face
                    val uid = info?.uid ?: 0
                    val isVip = card.desc.userProfile?.vip?.vipStatus == 1
                    User(name, face, uid, isVip)
                }
                val state = run {
                    val repost = card.desc.repost
                    val view = card.desc.view
                    val comment = card.desc.comment ?: 0
                    val like = card.desc.like
                    State(repost, view, comment, like)
                }
                val wishedType = card.desc.type
                val cardJson = card.card
                val (type, cardObj) = parseTypedCard(wishedType, cardJson)
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
                    val name = info?.uname
                    val face = info?.face
                    val uid = info?.uid ?: 0
                    val isVip = card.desc.userProfile?.vip?.vipStatus == 1
                    User(name, face, uid, isVip)
                }
                val state = run {
                    val repost = card.desc.repost
                    val view = card.desc.view
                    val comment = card.desc.comment ?: 0
                    val like = card.desc.like
                    State(repost, view, comment, like)
                }
                val wishedType = card.desc.type
                val cardJson = card.card
                val (type, cardObj) = parseTypedCard(wishedType, cardJson)
                val time = card.desc.timestamp
                models.add(DynamicCardModel(type, cardObj, cardJson, user, state, time))
            }
            return models
        }

        fun parseTypedCard(type: Int, json: String): Pair<Int, Any> {
            return try {
                type to when (type) {
                    1 -> Type1.parse(gson.fromJson(json))
                    2 -> Type2.parse(gson.fromJson(json))
                    4 -> Type4.parse(gson.fromJson(json))
                    8 -> Type8.parse(gson.fromJson(json))
                    64 -> Type64.parse(DynamicCardType64.from(gson.fromJson(json)))
                    512 -> Type512.parse(gson.fromJson(json))
                    else -> Any()
                }
            } catch (e: Exception) {
                val stackTrace = e.stackTrace.joinToString("\n")
                Crashes.trackError(e, mapOf("message" to e.message, "type" to type.toString()),
                    listOf(ErrorAttachmentLog.attachmentWithBinary(json.toByteArray(),"json", "text/json"),
                        ErrorAttachmentLog.attachmentWithBinary(stackTrace.toByteArray(), "stacktrace", "text/plain")))
                TYPE_ERROR to TypeError(type, e.message, json, stackTrace)
            }
        }
    }

    data class User(
        val name: String?,
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
        val desc: String,
        val dynamic: String
    ) {
        companion object {
            fun parse(dynamicCardType8: DynamicCardType8): Type8 {
                val title = dynamicCardType8.title
                val coverUrl = dynamicCardType8.pic
                val url = dynamicCardType8.jumpUrl
                val desc = dynamicCardType8.desc
                val dynamic = dynamicCardType8.dynamic
                return Type8(title, coverUrl, url, desc, dynamic)
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

    data class Type64(
        val cover: String,
        val title: String,
        val summary: String,
        val id: Long
    ) {
        companion object {
            fun parse(dynamicCardType64: DynamicCardType64): Type64 {
                val cover = dynamicCardType64.imageUrls[0]
                val title = dynamicCardType64.title
                val summary = dynamicCardType64.summary
                val id = dynamicCardType64.id
                return Type64(cover, title, summary, id)
            }
        }
    }

    data class Type1(
        val originType: Int,
        val originJson: String,
        val origin: Any,
        val originUser: User,
        val content: String,
        val time: Long
    ) {
        companion object {

            fun parse(dynamicCardType1: DynamicCardType1): Type1 {
                val wishedOriginType = dynamicCardType1.item.origType
                val originJson = dynamicCardType1.origin
                val (originType, origin) = originJson?.let { parseTypedCard(wishedOriginType, it) }
                    ?: (0 to Any())
                val originUser = dynamicCardType1.originUser?.run {
                    val name = info.uname
                    val face = info.face
                    val uid = info.uid
                    val isVip = vip?.vipStatus == 1
                    User(name, face, uid, isVip)
                } ?: User(null, null, 0, false)
                val content = dynamicCardType1.item.content
                val time = dynamicCardType1.item.timestamp * 1000L
                return Type1(originType, originJson ?: "", origin, originUser, content, time)
            }
        }

        fun toDynamicCardModel(): DynamicCardModel {
            val type = this.originType
            val card = this.origin
            val cardJson = this.originJson
            val user = run {
                val name = this@Type1.originUser.name
                val face = this@Type1.originUser.faceUrl
                val uid = this@Type1.originUser.uid
                val isVip = this@Type1.originUser.isVip
                User(name, face, uid, isVip)
            }
            val state = State(-1, -1, -1, -1)
            val time = this.time
            return DynamicCardModel(type, card, cardJson, user, state, time)
        }
    }

    data class Type512(
        val cover: String,
        val title: String,
        val seasonTitle: String,
        val url: String,
        val ration: Float
    ) {
        companion object {
            fun parse(dynamicCardType512: DynamicCardType512): Type512 {
                val cover = dynamicCardType512.cover
                val title = dynamicCardType512.newDesc
                val seasonTitle = dynamicCardType512.season.title
                val url = dynamicCardType512.url
                val ration = dynamicCardType512.dimension.let { it.height.toFloat() / it.width }
                return Type512(cover, title, seasonTitle, url, ration)
            }
        }
    }

    data class TypeError(
        val type: Int,
        val message: String?,
        val cardJson: String,
        val stackTrace: String
    )
}