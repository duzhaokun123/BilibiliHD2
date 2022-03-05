package com.duzhaokun123.bilibilihd2.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.ColorInt
import androidx.annotation.Px

fun Bitmap.resize(@Px width: Int,@Px height: Int) =
    Bitmap.createScaledBitmap(this, width, height, false)

fun ColorBitmap(@ColorInt color: Int, @Px width: Int = 1,@Px height: Int = 1): Bitmap {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    canvas.drawColor(color)
    return bitmap
}