package io.github.duzhaokun123.androidapptemplate.utils

import android.content.res.Configuration
import androidx.core.view.WindowInsetsCompat
import com.duzhaokun123.bilibilihd2.utils.application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

val WindowInsetsCompat.maxSystemBarsDisplayCutout
    get() = getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())

fun runMain(block: suspend CoroutineScope.() -> Unit) =
    GlobalScope.launch(Dispatchers.Main, block = block)

fun runIO(block: suspend CoroutineScope.() -> Unit) =
    GlobalScope.launch(Dispatchers.IO, block = block)

fun runNewThread(name: String? = null, block: () -> Unit) =
    (if (name != null) Thread(block, name) else Thread(block)).start()

val isSystemNightMode
    get() = when (application.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> true
        Configuration.UI_MODE_NIGHT_NO -> false
        else -> null
    }
