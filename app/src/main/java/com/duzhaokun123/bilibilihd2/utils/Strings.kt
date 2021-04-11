package com.duzhaokun123.bilibilihd2.utils

fun String.replaceAfterInclude(delimiter: Char, replacement: String, missingDelimiterValue: String = this): String {
    val index = indexOf(delimiter)
    return if (index == -1) missingDelimiterValue else replaceRange(index, length, replacement)
}