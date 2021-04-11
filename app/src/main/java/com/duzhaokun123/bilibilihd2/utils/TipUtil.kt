package com.duzhaokun123.bilibilihd2.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

//TODO
object TipUtil {
    private val map = mutableMapOf<Context, CoordinatorLayout>()

    fun registerCoordinatorLayout(context: Context, coordinatorLayout: CoordinatorLayout?) {
        coordinatorLayout?.let { map[context] = it }
    }

    fun unregisterCoordinatorLayout(context: Context) {
        map.remove(context)
    }

    fun showToast(msg: CharSequence?) {
        runMain {
            Toast.makeText(application, "$msg", Toast.LENGTH_LONG).show()
        }
    }

    fun showToast(@StringRes resId: Int) =
        showToast(application.getText(resId))

    fun showSnackbar(coordinatorLayout: CoordinatorLayout, msg: CharSequence?) {
        runMain {
            Snackbar.make(coordinatorLayout, "$msg", BaseTransientBottomBar.LENGTH_LONG).show()
        }
    }

    fun showSnackbar(coordinatorLayout: CoordinatorLayout, @StringRes resId: Int) =
        showSnackbar(coordinatorLayout, application.getText(resId))

    fun showTip(context: Context?, msg: CharSequence?) {
        if (context == null || (context is Activity && context.window.decorView.isVisible.not())) {
            showToast(msg)
            return
        }
        map[context]?.let {
            showSnackbar(it, msg)
            return
        }
        showToast(msg)
    }

    fun showTip(context: Context?, @StringRes resId: Int) =
        showTip(context, application.getText(resId))
}