package com.duzhaokun123.bilibilihd2.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.use
import com.duzhaokun123.bilibilihd2.R

class RationHeightView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    init {
        context.obtainStyledAttributes(attrs, R.styleable.Ration, defStyleAttr, 0).use {
            ration = it.getFloat(R.styleable.Ration_ration, 0F)
        }
    }

    var ration: Float = 0F
        set(value) {
            if (field != value) {
                field = value
                requestLayout()
            }
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (ration > 0) {
            val width = MeasureSpec.getSize(widthMeasureSpec)
            val height = (width * ration).toInt()
            setMeasuredDimension(width, height)
        } else
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}