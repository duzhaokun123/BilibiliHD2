package com.duzhaokun123.bilibilihd2.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.duzhaokun123.bilibilihd2.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

fun glideSafeLoadInto(url: String?, target: ImageView, context: Context = application) {
    if (url == null) {
        target.setImageDrawable(null)
        return
    }
    try {
        val transformation = object : Transformation {
            override fun transform(source: Bitmap): Bitmap {
                val targetWidth = target.width
                if (source.width == 0) return source
                val ratio = targetWidth.toFloat() / source.width.toFloat()
                val targetHeight = (source.height * ratio).toInt()
                val result = runCatching{ Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false) }.getOrDefault(source)
                if (result != source) {
                    source.recycle()
                }
                return result
            }

            override fun key() = "transformation desired width"
        }
        Picasso.get().load(url)
            .placeholder(ColorDrawable(context.getColorCompat(R.color.image_background)))
            .transform(transformation).into(target)
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