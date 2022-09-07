package com.duzhaokun123.bilibilihd2.utils

import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager

fun Window.blurBackground() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) return
    addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
    attributes.blurBehindRadius = 50
    setBackgroundBlurRadius(50)
    val blurEnableListener = { enable: Boolean ->
        setDimAmount(if (enable) 0.1F else 0.6F)
    }
    decorView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
        override fun onViewAttachedToWindow(v: View) {
            windowManager.addCrossWindowBlurEnabledListener(blurEnableListener)
        }

        override fun onViewDetachedFromWindow(v: View) {
            windowManager.removeCrossWindowBlurEnabledListener(blurEnableListener)
        }

    })
    addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
}