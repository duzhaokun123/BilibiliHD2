package com.duzhaokun123.bilibilihd2

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.biliplayer.BiliPlayerView
import com.duzhaokun123.generated.Settings
import com.google.android.exoplayer2.database.ExoDatabaseProvider
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import com.hiczp.bilibili.api.BilibiliClient
import com.hiczp.bilibili.api.BilibiliClientProperties
import com.hiczp.bilibili.api.md5
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.crashes.CrashesListener
import com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog
import com.microsoft.appcenter.crashes.model.ErrorReport
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

        //init
        Settings.init(this)
        if (Settings.allowAnalytics) {
            Settings.allowAnalytics = true
            if (BuildConfig.APP_SECRET.md5() != "a601348934a98cf5033b58097bd186f1") {
                TipUtil.showToast("错误的 APP_SECRET")
            } else {
                Crashes.setListener(object : CrashesListener {
                    override fun shouldProcess(report: ErrorReport) = true

                    override fun shouldAwaitUserConfirmation() = false

                    override fun getErrorAttachments(report: ErrorReport): Iterable<ErrorAttachmentLog> {
                        val process = Runtime.getRuntime().exec("logcat -d")
                        val processOutput = process.inputStream.bufferedReader().readText()
                        val re = mutableListOf(ErrorAttachmentLog.attachmentWithBinary(processOutput.toByteArray(), "logcat.txt", "text/plain"))
                        re.add(ErrorAttachmentLog.attachmentWithBinary(report.stackTrace.toByteArray(), "stacktrace.txt", "text/plain"))
                        return re
                    }

                    override fun onBeforeSending(report: ErrorReport) {}

                    override fun onSendingFailed(report: ErrorReport, e: Exception) {}

                    override fun onSendingSucceeded(report: ErrorReport) {}
                })
                AppCenter.start(this, BuildConfig.APP_SECRET, Analytics::class.java, Crashes::class.java)
            }
        }
        UsersMap.reload()
        reinitBilibiliClient()
        reinitUiMod()
//        if (Settings.dynamicColor)
//            DynamicColors.applyToActivitiesIfAvailable(this)
        DanmakuUtil.syncDanmakuSettings()
        BiliPlayerView.isDebug = Settings.playerDebug

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
}