package com.duzhaokun123.bilibilihd2.utils

import android.view.View
import android.view.Window
import androidx.annotation.IntDef
import androidx.annotation.RestrictTo
import androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE
import androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_TOUCH
import androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

/**
 * Q: 为什么有这个? 这鬼 bug 不是修了吗 [link](https://issuetracker.google.com/issues/173203649)
 * E: 说是修了 但 1.5.0 没放出来 1.6.0-alpha 也没修
 */
object WindowInsetsControllerCompatFix {
    /**
     * Determines the behavior of system bars when hiding them by calling [.hide].
     *
     * @hide
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @IntDef(value = [BEHAVIOR_SHOW_BARS_BY_TOUCH, BEHAVIOR_SHOW_BARS_BY_SWIPE, BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE])
    internal annotation class Behavior

    fun setSystemBarsBehavior(window: Window, @Behavior behavior: Int) {
        when (behavior) {
            BEHAVIOR_SHOW_BARS_BY_SWIPE -> {
               window.unsetSystemUiFlag(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
               window.setSystemUiFlag(View.SYSTEM_UI_FLAG_IMMERSIVE)
            }
            BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE -> {
               window.unsetSystemUiFlag(View.SYSTEM_UI_FLAG_IMMERSIVE)
               window.setSystemUiFlag(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
            BEHAVIOR_SHOW_BARS_BY_TOUCH -> window.unsetSystemUiFlag(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            )
        }
    }
   private fun Window.setSystemUiFlag(systemUiFlag: Int) {
        val decorView: View = this.decorView
        decorView.systemUiVisibility = (
                decorView.systemUiVisibility
                        or systemUiFlag)
    }

    private fun Window.unsetSystemUiFlag(systemUiFlag: Int) {
        val decorView: View = this.decorView
        decorView.systemUiVisibility = (
                decorView.systemUiVisibility
                        and systemUiFlag.inv())
    }
}