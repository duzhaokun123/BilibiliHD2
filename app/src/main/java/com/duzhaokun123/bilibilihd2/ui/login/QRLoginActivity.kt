package com.duzhaokun123.bilibilihd2.ui.login

import android.content.Intent
import android.net.Uri
import androidx.activity.viewModels
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.ActivityQrLoginBinding
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.generated.Settings
import com.github.salomonbrys.kotson.fromJson
import com.github.salomonbrys.kotson.get
import com.google.gson.JsonObject
import com.google.zxing.BarcodeFormat
import com.hiczp.bilibili.api.passport.model.LoginResponse
import com.hiczp.bilibili.api.passport.model.QRLoginUrl
import com.hiczp.bilibili.api.retrofit.Cookie
import com.journeyapps.barcodescanner.BarcodeEncoder
import io.github.duzhaokun123.androidapptemplate.bases.BaseActivity
import io.github.duzhaokun123.androidapptemplate.utils.TipUtil
import io.github.duzhaokun123.androidapptemplate.utils.launch
import io.github.duzhaokun123.androidapptemplate.utils.onSuccess
import io.github.duzhaokun123.androidapptemplate.utils.runIOCatching
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class QRLoginActivity: BaseActivity<ActivityQrLoginBinding>(R.layout.activity_qr_login) {
    class Model : ViewModel() {
        val qrLoginUrl = MutableLiveData<QRLoginUrl?>(null)
        val status = MutableLiveData("")
    }

    val model by viewModels<Model>()

    override fun initEvents() {
        baseBinding.btnRefresh.setOnClickListener { reload() }
        baseBinding.btnOpen.setOnClickListener {
            startActivity(Intent().apply {
                data = Uri.parse("bilibili://browser?url=${model.qrLoginUrl.value?.data?.url}")
            })
        }
    }

    override fun initData() {
        model.qrLoginUrl.observe(this) {
            it ?: return@observe
            val size = 160.dpToPx()
            baseBinding.ivQr.setImageBitmap(BarcodeEncoder().encodeBitmap(it.data.url, BarcodeFormat.QR_CODE, size, size))
        }
        model.status.observe(this) {
            baseBinding.tvState.text = it
        }

        runIO {
            while (true) {
                if (isDestroyed) break
                delay(500)
                if (model.qrLoginUrl.value == null) {
                    model.status.postValue("等待加载")
                    continue
                }
                val request = Request.Builder()
                    .url("https://passport.bilibili.com/qrcode/getLoginInfo")
                    .post("oauthKey=${model.qrLoginUrl.value!!.data.oauthKey}".toRequestBody("application/x-www-form-urlencoded".toMediaType()))
                    .build()
                val call = okHttpClient.newCall(request)
                runCatching { call.execute() }
                    .onFailure { t ->
                        t.printStackTrace()
                        TipUtil.showTip(this@QRLoginActivity, t.localizedMessage)
                        model.status.postValue("网络错误")
                    }.onSuccess { r ->
                        val json = gson.fromJson<JsonObject>(r.body!!.charStream())
                        val status = json["status"].asBoolean
                        val data = json["data"]
                        if (status) {
                            model.status.postValue("登录中...")
                            val expires = data["url"].asString.toUri().getQueryParameter("Expires")!!.toLong() + System.currentTimeMillis() / 1000
                            val headers = r.headers("Set-Cookie")
                            val cookies = listOf(
                                Cookie.BILI_JCT,
                                Cookie.DEDE_USER_ID,
                                Cookie.DEDE_USER_ID_CKMD5,
                                Cookie.SESSDATA,
                                Cookie.SID
                            )
                                .associateWith { name -> headers.find { it.startsWith(name) }!!.let { it.substring(name.length + 1, it.indexOf(';')) } }
                            val request2 = Request.Builder()
                                .url("https://passport.bilibili.com/login/app/third?appkey=27eb53fc9058f8c3&api=https%3A%2F%2Fwww.mcbbs.net%2Ftemplate%2Fmcbbs%2Fimage%2Fspecial_photo_bg.png&sign=04224646d1fea004e79606d3b038c84a")
                                .get()
                                .apply {
                                    cookies.forEach { (k, v) ->
                                        addHeader("Cookie", "$k=$v")
                                    }
                                }
                                .build()
                            val call2 = okHttpClient.newCall(request2)
                            runCatching { call2.execute() }
                                .onFailure { t ->
                                    t.printStackTrace()
                                    TipUtil.showTip(this@QRLoginActivity, t.localizedMessage)
                                    model.status.postValue("网络错误")
                                }.onSuccess { r2 ->
                                    val request3 = Request.Builder()
                                        .url(gson.fromJson<JsonObject>(r2.body!!.charStream())["data"]["confirm_uri"].asString)
                                        .get()
                                        .apply {
                                            cookies.forEach { (k, v) ->
                                                addHeader("Cookie", "$k=$v")
                                            }
                                        }
                                        .build()
                                    val call3 = okHttpClient.newCall(request3)
                                    runCatching { call3.execute() }
                                        .onFailure { t ->
                                            t.printStackTrace()
                                            TipUtil.showTip(this@QRLoginActivity, t.localizedMessage)
                                            model.status.postValue("网络错误")
                                        }.onSuccess { r3 ->
                                            val url = r3.request.url.toString().toUri()
                                            val accessKey = url.getQueryParameter("access_key")!!
                                            val uid = cookies[Cookie.DEDE_USER_ID]!!.toLong()
                                            val loginResponse = LoginResponse(
                                                0, "",
                                                LoginResponse.Data(
                                                    LoginResponse.Data.CookieInfo(
                                                        listOf(
                                                            LoginResponse.Data.CookieInfo.Cookies(expires, /* 0, */ Cookie.BILI_JCT, cookies[Cookie.BILI_JCT]!!),
                                                            LoginResponse.Data.CookieInfo.Cookies(expires, /* 0, */ Cookie.DEDE_USER_ID, cookies[Cookie.DEDE_USER_ID]!!),
                                                            LoginResponse.Data.CookieInfo.Cookies(expires, /* 0, */ Cookie.DEDE_USER_ID_CKMD5, cookies[Cookie.DEDE_USER_ID_CKMD5]!!),
                                                            LoginResponse.Data.CookieInfo.Cookies(expires, /* 1, */ Cookie.SESSDATA, cookies[Cookie.SESSDATA]!!),
                                                            LoginResponse.Data.CookieInfo.Cookies(expires, /* 0, */ Cookie.SID, cookies[Cookie.SID]!!),
                                                        ),
                                                        listOf(".bilibili.com", ".biligame.com", ".bigfun.cn", ".bigfunapp.cn", ".dreamcast.hk")
                                                    ), "", 0, LoginResponse.Data.TokenInfo(
                                                        accessKey, expires, uid, "null"
                                                    )
                                                ),0
                                            )
                                            bilibiliClient.loginResponse = loginResponse
                                            Settings.selectedUid = uid
                                            UsersMap.put(loginResponse)
                                            UsersMap.save()
                                            model.status.postValue("登录成功")
                                            finish()
                                        }
                                }
                        } else {
                            when(data.asInt) {
                                -4 -> model.status.postValue("等待扫描")
                                -5 -> model.status.postValue("等待确认")
                                else -> model.status.postValue("${json["message"]?.asString}")
                            }
                        }
                    }
            }
        }

        reload()
    }

    private fun reload() {
        runIOCatching { bilibiliClient.passportAPI.getQRLoginUrl().await() }
            .setCommonOnFailureHandler(this)
            .onSuccess { r ->
                model.qrLoginUrl.postValue(r)
            }.launch()
    }
}