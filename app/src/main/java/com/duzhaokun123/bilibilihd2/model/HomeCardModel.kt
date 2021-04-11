package com.duzhaokun123.bilibilihd2.model

import com.hiczp.bilibili.api.app.model.HomePage

data class HomeCardModel(
    val title: String?,
    val desc: String?,
    val coverUrl: String?,
    val faceUrl: String?,
    val uri: String?,
    val upUri: String?,
    val coverLeftText1: String?,
    val coverLeftText2: String?,
    val coverLeftText3: String?,
    val badge: String?,
    val isAd: Boolean
) {
    companion object {

        fun parse(homePage: HomePage): List<HomeCardModel> {
            val modes = mutableListOf<HomeCardModel>()
            homePage.data.items.forEach { item ->
                var title: String? = "不支持的类型 ${item.cardType}"
                var desc: String? = ""
                var coverUrl: String? = null
                var faceUrl: String? = null
                var uri: String? = null
                var upUri: String? = null
                var coverLeftText1: String? = null
                var coverLeftText2: String? = null
                var coverLeftText3: String? = null
                var badge: String? = null
                var isAd = false

                when (item.cardType) {
                    "large_cover_v1" -> {
                        title = item.title
                        desc = item.desc
                        coverUrl = item.cover
                        faceUrl = item.avatar?.cover
                        uri = item.uri
                        upUri = item.avatar?.uri
                        coverLeftText1 = item.coverLeftText1
                        coverLeftText2 = item.coverLeftText2
                        coverLeftText3 = item.coverLeftText3
                        badge = item.coverBadge
                        isAd = false
                    }
                    "cm_v1" -> {
                        item.adInfo?.creativeContent?.let { creativeContent ->
                            title = creativeContent.title
                            desc = creativeContent.description
                            coverUrl = creativeContent.imageUrl
                            uri = creativeContent.url
                            isAd = item.adInfo!!.isAd
                        }
                    }
                    "three_item_h_v3" -> {
                        title = item.title
                        desc = item.desc
                        coverUrl = item.covers[0]
                        faceUrl = item.avatar?.cover
                        uri = item.uri
                        upUri = item.avatar?.uri
                        coverLeftText2 = item.coverLeftText2
                        coverLeftText3 = item.coverLeftText3
                        badge = "专栏"
                        isAd = false

                    }
                }

                modes.add(
                    HomeCardModel(
                        title, desc, coverUrl, faceUrl, uri, upUri, coverLeftText1, coverLeftText2,
                        coverLeftText3, badge, isAd
                    )
                )
            }
            return modes
        }
    }
}