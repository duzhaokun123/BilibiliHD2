package com.duzhaokun123.bilibilihd2.model

import com.duzhaokun123.bilibilihd2.utils.DateFormat
import com.hiczp.bilibili.api.app.model.History

data class HistoryCardModel(
    val title: String?,
    val desc: String?,
    val coverUrl: String?,
    val uri: String?,
    val badge: String?,
    /**
     * 百分进度
     */
    val progress: Int,
    val hasProgress: Boolean,
) {
    companion object {
        fun parse(history: History): List<HistoryCardModel> {
            val badgeMap = mutableMapOf<String, String>()
            history.data.tab.forEach { tab ->
                badgeMap[tab.business] = tab.name
            }
            val models = mutableListOf<HistoryCardModel>()
            history.data.list.forEach { item ->
                val title = item.title
                val desc = "${item.name} ${DateFormat.format1.format(item.viewAt * 1000L)}"
                val coverUrl = item.cover ?: item.covers?.get(0)
                val uri = item.uri
                val badge = badgeMap[item.history.business]
                val hasProgress = item.progress != null && item.duration != null
                val progress =
                    if (hasProgress) ((item.progress!!.toFloat() / item.duration!!) * 100).toInt() else 0
                models.add(
                    HistoryCardModel(title, desc, coverUrl, uri, badge, progress, hasProgress)
                )
            }
            return models
        }
    }
}