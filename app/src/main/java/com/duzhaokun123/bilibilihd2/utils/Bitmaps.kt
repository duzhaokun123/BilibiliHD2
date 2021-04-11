package com.duzhaokun123.bilibilihd2.utils

import android.graphics.Bitmap
import androidx.annotation.Px

fun Bitmap.resize(@Px width: Int,@Px height: Int) =
    Bitmap.createScaledBitmap(this, width, height, false)