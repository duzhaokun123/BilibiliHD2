package com.duzhaokun123.bilibilihd2.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object DateFormat {
    @SuppressLint("SimpleDateFormat")
    val format1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val format2 = SimpleDateFormat.getDateTimeInstance()
}