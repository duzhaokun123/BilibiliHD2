package com.duzhaokun123.bilibilihd2.utils

import android.graphics.Bitmap
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.widget.TextView
import com.bapis.bilibili.main.community.reply.v1.Emote

/**
 * 不知道为什么叫 emote 不叫 emoji
 */
object EmoteMap {
    val emoteRegex = Regex("\\[.*?]")
    private val map = mutableMapOf<String, String>()
    private val groupMap = mutableMapOf<String, MutableMap<String, String>>()
    private val userGroups = mutableListOf<String>()

    fun putGroup(group: String, emote: Pair<String, String>) {
        groupMap.getOrPut(group) { mutableMapOf() }[emote.first] = emote.second
        put(emote.first, emote.second)
    }

    fun putGroup(group: String, emote: Collection<Pair<String, String>>) {
        emote.forEach { putGroup(group, it) }
    }

    fun getGroup(group: String): MutableMap<String, String>? {
        return groupMap[group]
    }

    @Synchronized
    fun putUserGroup(group: String) {
        if (group !in userGroups) {
            userGroups.add(group)
        }
    }

    fun getUserGroups(): List<String> {
        return userGroups
    }

    fun put(emote: Pair<String, Emote>) {
        put(emote.first, emote.second)
    }

    @JvmName("put1")
    fun put(emote: Pair<String, String>) {
        put(emote.first, emote.second)
    }

    fun put(key: String, emote: Emote) {
        put(key, emote.url)
    }

    fun put(key: String, emote: String) {
        map[key] = emote
    }

    fun put(emotes: Collection<Pair<String, Emote>>) {
        emotes.forEach(::put)
    }

    @JvmName("put1")
    fun put(emotes: Collection<Pair<String, String>>) {
        emotes.forEach(::put)
    }

    fun getEmote(key: String): String? {
        return map[key]
    }

    fun getBitmap(key: String): Bitmap? {
        return glideSafeGetSync(map[key])
    }

    fun emotefy(text: String, textView: TextView) {
        val ssb = SpannableStringBuilder(text)
        textView.text = ssb
        runNewThread {
            emoteRegex.findAll(text).forEach {
                val value = it.value
                val range = it.range
                val bitmap = getBitmap(value) ?: return@forEach
                ssb.setSpan(
                    ImageSpan(textView.context, bitmap, ImageSpan.ALIGN_BASELINE),
                    range.first,
                    range.last + 1,
                    SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                runMain { textView.text = ssb }
            }
        }
    }
}