package com.duzhaokun123.bilibilihd2.utils

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.duzhaokun123.bilibilihd2.R

fun glideSafeLoadInto(url: String?, target: ImageView) {
    if (url == null) {
        target.setImageDrawable(null)
        return
    }
    try {
        Glide.with(application).load(url).placeholder(R.color.image_background).into(target)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun glideSafeGet(url: String?, onGet: suspend (bitmap: Bitmap) -> Unit) {
    url ?: return
    runNewThread {
        try {
            val bitmap = Glide.with(application).asBitmap().load(url).submit().get()
            runMain {
                onGet(bitmap)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun glideSafeGetSync(url: String?): Bitmap? {
    url ?: return null
    try {
        return Glide.with(application).asBitmap().load(url).submit().get()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}