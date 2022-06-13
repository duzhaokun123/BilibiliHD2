package com.duzhaokun123.bilibilihd2.bilisubtitle

import com.google.android.exoplayer2.text.Cue
import com.google.android.exoplayer2.text.Subtitle

class BiliSubtitle(subtitle: com.duzhaokun123.bilibilihd2.model.Subtitle) : Subtitle {
    companion object {
        const val MIME_TYPE = "application/x-bilibili-subtitle"
    }

    private val cues = mutableListOf<Pair<LongRange, Cue>>()
    private val eventTimes = mutableListOf<Long>()

    init {
        subtitle.body.forEach { body ->
            val from = (body.from * 1000_000).toLong()
            val to = (body.to * 1000_000).toLong()
            cues.add(from..to to Cue.Builder().setText(body.content).build())
            eventTimes.add(from)
            eventTimes.add(to)
        }
    }

    override fun getNextEventTimeIndex(timeUs: Long): Int {
        return eventTimes.indexOfFirst { timeUs <= it }
    }

    override fun getEventTimeCount() = eventTimes.size

    override fun getEventTime(index: Int): Long {
        return eventTimes[index]
    }

    override fun getCues(timeUs: Long): List<Cue> {
        return cues.filter { timeUs in it.first }.map { it.second }
    }
}