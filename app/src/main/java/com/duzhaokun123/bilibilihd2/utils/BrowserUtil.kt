package com.duzhaokun123.bilibilihd2.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.webkit.CookieManager
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.ui.UrlOpenActivity
import com.duzhaokun123.bilibilihd2.ui.WebViewActivity
import com.duzhaokun123.bilibilihd2.ui.play.live.LivePlayActivity
import com.duzhaokun123.bilibilihd2.ui.play.online.OnlinePlayActivity
import com.duzhaokun123.bilibilihd2.ui.scape.UserScapeActivity

object BrowserUtil {
    private const val TAG = "BrowserUtil"

    fun openInApp(
        context: Context?, url: String?
    ) {
        if (context == null || url == null) return
        try {
            context.startActivity(Intent(context, UrlOpenActivity::class.java).apply {
                data = Uri.parse(url)
            })
        } catch (e: Exception) {
            e.printStackTrace()
            TipUtil.showToast(e.message)
        }
    }



    fun openInApp(context: Context?, uri: Uri) {
        if (context == null) return

        val scheme = uri.scheme
        val host = uri.host
        val path = uri.path.orEmpty()
        val paths = path.substring(1).split("/")

        Log.d(TAG, uri.toString())
        Log.d(TAG, "scheme: $scheme")
        Log.d(TAG, "host: $host")
        Log.d(TAG, "path: $path")
        Log.d(TAG, "paths: $paths")
        uri.queryParameterNames.forEach {
            Log.d(UrlOpenActivity.TAG, "query: $it: ${uri.getQueryParameter(it)}")
        }

        when (scheme) {
            "bilibili" -> {
                when (host) {
                    "video" -> {
                        OnlinePlayActivity.enter(context, paths.getOrElse(0) { "0" }.toLong())
                    }
                    "article" -> openWebViewActivity(
                        context,
                        "https://www.bilibili.com/read/mobile?id=${paths[0]}"
                    )
                    "live" -> LivePlayActivity.enter(context, paths[0].toLong())
                    "space", "author" -> UserScapeActivity.enter(context, paths[0].toLong())
                    else -> unsupported(uri)
                }
            }
            "http", "https" -> {
                when (host) {
                    "www.bilibili.com" -> {
                        when (paths.getOrNull(0)) {
                            "video" -> {
                                val p1 = paths.getOrNull(1) ?: return
                                runCatching {
                                    OnlinePlayActivity.enter(context, p1.substring(2).toLong())
                                }.exceptionOrNull()?.runCatching {
                                    OnlinePlayActivity.enter(context, p1)
                                }
                            }
                            "bangumi" -> {
                                TipUtil.showToast("番剧 但不支持番剧")
                                openWebViewActivity(context, uri, desktopUA = true)
                            }
                            "read" -> openWebViewActivity(
                                context,
                                "https://www.bilibili.com/read/mobile?id=${paths[1].substring(2)}",
                                desktopUA = false
                            )
                            else -> openWebViewActivity(
                                context, uri, desktopUA = paths.getOrNull(0) != "h5"
                            )
                        }
                    }
                    "space.bilibili.com" -> UserScapeActivity.enter(
                        context, paths.getOrElse(0) { "0" }.toLong()
                    )
                    "live.bilibili.com" -> LivePlayActivity.enter(
                        context, paths.getOrElse(0) { "0" }.toLong()
                    )
                    "b23.tv" -> openWebViewActivity(
                        context, uri,
                        desktopUA = false, finishWhenIntercept = true, interceptAll = true
                    )
                    "m.bilibili.com" -> {
                        when (paths.getOrNull(0)) {
                            "video" -> {
                                val p1 = paths.getOrNull(1) ?: return
                                runCatching {
                                    OnlinePlayActivity.enter(context, p1.substring(2).toLong())
                                }.exceptionOrNull()?.runCatching {
                                    OnlinePlayActivity.enter(context, p1)
                                }
                            }
                            else -> openWebViewActivity(
                                context, uri, desktopUA = false
                            )
                        }
                    }
                    else -> openWebViewActivity(
                        context, uri, desktopUA = paths.getOrNull(0) != "h5"
                    )
                }
            }
            else -> unsupported(uri)
        }
    }

    private fun unsupported(uri: Uri) {
        TipUtil.showToast("不支持 $uri")
    }

    fun openWebViewActivity(
        context: Context?, url: String?, desktopUA: Boolean = false,
        interceptAll: Boolean = false, finishWhenIntercept: Boolean = false
    ) {
        if (url != null)
            openWebViewActivity(context, url.toUri(), desktopUA, interceptAll, finishWhenIntercept)
    }

    fun openWebViewActivity(
        context: Context?, uri: Uri, desktopUA: Boolean = false,
        interceptAll: Boolean = false, finishWhenIntercept: Boolean = false
    ) {
        if (context == null) return
        val intent = Intent(context, WebViewActivity::class.java)
        intent.data = uri
        intent.putExtra(WebViewActivity.EXTRA_DESKTOP_UA, desktopUA)
        intent.putExtra(WebViewActivity.EXTRA_INTERCEPT_ALL, interceptAll)
        intent.putExtra(WebViewActivity.EXTRA_FINISH_WHEN_INTERCEPT, finishWhenIntercept)
        context.startActivity(intent)
    }

    fun openCustomTab(context: Context?, url: String) {
        Log.d(TAG, "openCustomTab: openUrl = $url")
        if (context == null) {
            Log.d(TAG, "openCustomTab: but context == null")
            return
        }
        try {
            CustomTabsIntent.Builder()
                .setDefaultColorSchemeParams(
                    CustomTabColorSchemeParams.Builder()
                        .setToolbarColor(context.getColorCompat(R.color.primaryColor))
                        .build()
                )
                .build()
                .launchUrl(context, Uri.parse(url))
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }

    fun syncLoginResponseCookie() {
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        val loginResponse = bilibiliClient.loginResponse
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.removeAllCookies(null)
        } else {
            cookieManager.removeAllCookie()
        }
        if (loginResponse == null) {
            return
        }
        for (url in loginResponse.data.cookieInfo.domains) {
            for ((_, _, name, value) in loginResponse.data.cookieInfo.cookies) {
                cookieManager.setCookie(url, "$name=$value")
            }
            cookieManager.setCookie(url, "Domain=$url")
            cookieManager.setCookie(url, "Path=/")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.flush()
        }
    }
}