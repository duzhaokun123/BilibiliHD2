package com.duzhaokun123.bilibilihd2.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat

object ShareUtil {
    fun shareText(context: Context, text: String) {
        val shareIntent = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }, null)
        ContextCompat.startActivity(context, shareIntent, null)
    }

    fun shareUrl(context: Context, url: String, title: String?) {
        val shareIntent = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, url)
            putExtra(Intent.EXTRA_TITLE, title)
            data = Uri.parse(url)
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            type = "text/plain"
        }, null)
        ContextCompat.startActivity(context, shareIntent, null)
    }
}