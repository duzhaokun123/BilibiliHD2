package com.duzhaokun123.bilibilihd2.ui.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.core.graphics.drawable.toBitmap

open class GlideLoadRoundImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : GlideLoadImageView(context, attrs, defStyleAttr) {
    override fun setImageDrawable(drawable: Drawable?) {
        if (drawable == null) {
            super.setImageDrawable(drawable)
            return
        }
        var width = drawable.intrinsicWidth
        if (width <= 0) width = 1
        var height = drawable.intrinsicHeight
        if (height <= 0) height = 1
        val bitmap = drawable.toBitmap(width, height)
        val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.resources, bitmap).apply {
            setAntiAlias(true)
            isCircular = true
        }
        super.setImageDrawable(roundedBitmapDrawable)
    }
}