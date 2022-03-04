package com.duzhaokun123.bilibilihd2

import androidx.appcompat.app.AppCompatDelegate
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.generated.Settings
import com.google.android.exoplayer2.database.ExoDatabaseProvider
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import com.google.android.material.color.DynamicColors
import com.hiczp.bilibili.api.BilibiliClient
import com.hiczp.bilibili.api.BilibiliClientProperties
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
        UsersMap.reload()
        reinitBilibiliClient()
        reinitUiMod()
        if (Settings.dynamicColor)
            DynamicColors.applyToActivitiesIfAvailable(this)

        loadEmoteList()
    }

    fun reinitBilibiliClient(
        uid: Long = Settings.selectedUid,
        userAppKey: String = Settings.appKey!!,
        userAppSec: String = Settings.appSec!!
    ) {
        bilibiliClient = BilibiliClient(object : BilibiliClientProperties {
            override val appKey: String
                get() = if (userAppKey.isEmpty()) super.appKey else userAppKey
            override val appSecret: String
                get() = if (userAppSec.isEmpty()) super.appSecret else userAppSec
        })
        bilibiliClient.loginResponse = UsersMap[uid]
        BrowserUtil.syncLoginResponseCookie()
        grpcClidet.rebuildChannel()
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
                    EmoteMap.put(packageData.emote.map { emote ->
                        emote.text to emote.url
                    })
                }
                TipUtil.showToast("加载表情列表成功 reply")
            }.launch()
        runIOCatching {
            bilibiliClient.webAPI.emoteList("dynamic").await()
        }.setCommonOnFailureHandler(null)
            .onSuccess {
                it.data.allPackages.forEach { packageData ->
                    EmoteMap.put(packageData.emote.map { emote ->
                        emote.text to emote.url
                    })
                }
                TipUtil.showToast("加载表情列表成功 dynamic")
            }.launch()
    }
}