package com.duzhaokun123.bilibilihd2

import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.generated.Settings
import com.google.android.exoplayer2.database.ExoDatabaseProvider
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import com.google.android.material.color.DynamicColors
import com.hiczp.bilibili.api.BilibiliClient
import com.hiczp.bilibili.api.BilibiliClientProperties
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import io.github.duzhaokun123.androidapptemplate.utils.TipUtil
import io.github.duzhaokun123.androidapptemplate.utils.launch
import io.github.duzhaokun123.androidapptemplate.utils.onSuccess
import io.github.duzhaokun123.androidapptemplate.utils.runIOCatching


@Suppress("UNUSED")
class Application : android.app.Application() {
    companion object {
        lateinit var instance: Application
            private set
        lateinit var bilibiliClient: BilibiliClient
            private set
        val simpleCache by lazy {
            SimpleCache(
                instance.cacheDir, NoOpCacheEvictor(), ExoDatabaseProvider(instance)
            )
        }
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        logDeviceInfo()

        //init
        Settings.init(this)
        if (Settings.allowAnalytics) {
            Settings.allowAnalytics = true
            AppCenter.start(this, BuildConfig.APP_SECRET, Analytics::class.java, Crashes::class.java)
        }
        UsersMap.reload()
        reinitBilibiliClient()
        reinitUiMod()
        if (Settings.dynamicColor)
            DynamicColors.applyToActivitiesIfAvailable(this)
        DanmakuUtil.syncDanmakuSettings()

        loadEmoteList()
    }

    fun reinitBilibiliClient(
        uid: Long = Settings.selectedUid,
        userAppKey: String = Settings.appKey!!,
        userAppSec: String = Settings.appSec!!
    ) {
        bilibiliClient = BilibiliClient(object : BilibiliClientProperties {
            override val appKey: String
                get() = userAppKey.ifEmpty { super.appKey }
            override val appSecret: String
                get() = userAppSec.ifEmpty { super.appSecret }
        })
        bilibiliClient.loginResponse = UsersMap[uid]
        BrowserUtil.syncLoginResponseCookie()
        grpcClidet.rebuildChannel()
        SeasonAgent.init("https://api.bilibili.com")
    }

    fun reinitUiMod(uiMod: Int = Settings.uiMod) {
        AppCompatDelegate.setDefaultNightMode(
            when (uiMod) {
                1 -> AppCompatDelegate.MODE_NIGHT_NO
                2 -> AppCompatDelegate.MODE_NIGHT_YES
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        )
    }

    fun loadEmoteList() {
        runIOCatching {
            bilibiliClient.webAPI.emoteList("reply").await()
        }.setCommonOnFailureHandler(null)
            .onSuccess {
                it.data.allPackages.forEach { packageData ->
                    EmoteMap.putGroup(packageData.text,
                        packageData.emote.map { emote -> emote.text to emote.url })
                }
                it.data.userPanelPackages.forEach { packageDate ->
                    EmoteMap.putUserGroup(packageDate.text)
                }
                TipUtil.showToast("加载表情列表成功 reply")
            }.launch()
        runIOCatching {
            bilibiliClient.webAPI.emoteList("dynamic").await()
        }.setCommonOnFailureHandler(null)
            .onSuccess {
                it.data.allPackages.forEach { packageData ->
                    EmoteMap.putGroup(packageData.text,
                        packageData.emote.map { emote -> emote.text to emote.url })
                }
                it.data.userPanelPackages.forEach { packageDate ->
                    EmoteMap.putUserGroup(packageDate.text)
                }
                TipUtil.showToast("加载表情列表成功 dynamic")
            }.launch()
    }

    fun logDeviceInfo() {
        Log.d("BilibiliHD2", "DeviceInfo: System: ${Build.HOST}(Android ${Build.VERSION.RELEASE} SDK ${Build.VERSION.SDK_INT})")
        Log.d("BilibiliHD2", "DeviceInfo: Model: ${Build.MODEL}(${Build.DEVICE})")
        Log.d("BilibiliHD2", "DeviceInfo: CPU: ${Build.HARDWARE}(${Build.BOARD})")
        Log.d("BilibiliHD2", "DeviceInfo: SupportedABIs: ${Build.SUPPORTED_ABIS.joinToString(", ")}")
    }
}