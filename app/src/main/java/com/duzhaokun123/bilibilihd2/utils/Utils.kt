package com.duzhaokun123.bilibilihd2.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.TypedValue
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import androidx.core.view.forEach
import com.duzhaokun123.bilibilihd2.Application
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

val application get() = Application.instance

val bilibiliClient get() = Application.bilibiliClient

val gson by lazy { Gson() }

val okHttpClient by lazy { OkHttpClient() }

fun runIO(block: suspend CoroutineScope.() -> Unit) =
    GlobalScope.launch(Dispatchers.IO, block = block)

fun runMain(block: suspend CoroutineScope.() -> Unit) =
    GlobalScope.launch(Dispatchers.Main, block = block)

fun <T> runIOCatchingResultRunMain(
    context: Context?,
    onIO: suspend () -> T,
    onMain: suspend (t: T) -> Unit
) = runIOCatchingResultRunMain(context, onIO, null, onMain)

fun <T> runIOCatchingResultRunMain(
    context: Context?,
    onIO: suspend () -> T,
    onCatch: (suspend (e: Exception) -> Unit)?,
    onMain: suspend (t: T) -> Unit
) {
    runIO {
        try {
            onIO().let {
                runMain {
                    onMain(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            TipUtil.showTip(context, e.message)
            onCatch?.invoke(e)
        }
    }
}

fun runNewThread(block: () -> Unit) {
    Thread(block).start()
}

inline fun <reified A : Activity> Context.startActivity() =
    startActivity(Intent(this, A::class.java))

val WindowInsetsCompat.systemBars
    get() = getInsets(WindowInsetsCompat.Type.systemBars())

fun Number.dpToPx() =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), application.resources.displayMetrics).toInt()

val Toolbar.homeImageView: View?
    get() {
        forEach {
            if (it is AppCompatImageButton) return it
        }
        return null
    }