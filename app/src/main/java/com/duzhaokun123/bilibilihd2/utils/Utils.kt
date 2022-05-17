package com.duzhaokun123.bilibilihd2.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView
import com.duzhaokun123.bilibilihd2.Application
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.grpcclient.GrpcClient
import com.duzhaokun123.generated.Settings
import com.google.gson.Gson
import io.github.duzhaokun123.androidapptemplate.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

val application get() = Application.instance

val bilibiliClient get() = Application.bilibiliClient

val grpcClidet by lazy { GrpcClient() }

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
            TipUtil.showTip(context, e)
            onCatch?.invoke(e)
        }
    }
}

fun runNewThread(block: () -> Unit) {
    Thread(block).start()
}

inline fun <reified A : Activity> Context.startActivity() =
    startActivity(Intent(this, A::class.java))

val WindowInsetsCompat.maxSystemBarsDisplayCutout
    get() = getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())

val WindowInsetsCompat.displayCutoutInsets
    get() = getInsets(WindowInsetsCompat.Type.displayCutout())

val WindowInsetsCompat.systemBars
    get() = getInsets(WindowInsetsCompat.Type.systemBars())

fun Number.dpToPx() =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), application.resources.displayMetrics
    ).toInt()

val Toolbar.homeImageView: View?
    get() {
        forEach {
            if (it is AppCompatImageButton) return it
        }
        return null
    }

fun View.removeFromParent() {
    (this.parent as? ViewGroup)?.removeView(this)
}

val isNightMode
    get() = when (Settings.uiMod) {
        AppCompatDelegate.MODE_NIGHT_YES -> true
        AppCompatDelegate.MODE_NIGHT_NO -> false
        else -> isSystemNightMode ?: false
    }

fun Context.getColorCompat(@ColorRes id: Int) = ContextCompat.getColor(this, id)

fun RecyclerView.resetAdapter() {
    adapter = adapter
}

fun Resources.Theme.getAttr(@AttrRes id: Int) =
    TypedValue().apply { resolveAttribute(id, this, true) }

fun <R> RunIOCatchingPending<R>.setCommonOnFailureHandler(context: Context?, extra: ((t: Throwable) -> Unit)? = null) =
    onFailure { t ->
        t.printStackTrace()
        TipUtil.showTip(context, t)
        extra?.invoke(t)
    }

fun <R> RunIOCatchingPending<R>.onSuccessMain(onSuccessMain: suspend CoroutineScope.(R) -> Unit) =
    onSuccess {
        runMain {
            onSuccessMain(it)
        }
    }

fun <R> Result<R>.commonOnFailureHandler(context: Context?, extra: ((t: Throwable) -> Unit)? = null) =
    onFailure { t ->
        t.printStackTrace()
        TipUtil.showTip(context, t)
        extra?.invoke(t)
    }

fun <R> Any.getAnyFieldAs(name: String, clazz: Class<*> = this::class.java): R {
    val f = clazz.getDeclaredField(name)
    f.isAccessible = true
    return f.get(this) as R
}

fun Any.setAnyField(name: String, value: Any?, clazz: Class<*> = this::class.java) {
    val f = clazz.getDeclaredField(name)
    f.isAccessible = true
    f.set(this, value)
}