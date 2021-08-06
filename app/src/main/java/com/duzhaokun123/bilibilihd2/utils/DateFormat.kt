package com.duzhaokun123.bilibilihd2.utils

import android.annotation.SuppressLint
import com.google.android.exoplayer2.util.Util
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

object DateFormat {
    @SuppressLint("SimpleDateFormat")
    val format1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val format2 = SimpleDateFormat.getDateTimeInstance()

    fun getTimeSForString(time: String): Long {
        var timeS: Long = 0
        val strings = time.split(":").toTypedArray()
        for (string in strings) {
            timeS = timeS * 60 + string.toLong()
        }
        return timeS
    }

    private var formatBuilder: StringBuilder? = null
    private var formatter: Formatter? = null
    fun getStringForTime(timeMs: Long): String {
        if (formatter == null) {
            formatBuilder = StringBuilder()
            formatter = Formatter(formatBuilder, Locale.getDefault())
        }
        return Util.getStringForTime(formatBuilder!!, formatter!!, timeMs)
    }
}