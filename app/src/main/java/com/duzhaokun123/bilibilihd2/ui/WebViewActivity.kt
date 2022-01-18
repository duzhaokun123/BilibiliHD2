package com.duzhaokun123.bilibilihd2.ui

import android.annotation.SuppressLint
import android.app.PictureInPictureParams
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Rational
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.core.net.toUri
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duzhaokun123.bilibilihd2.CLIENT_USER_AGENT
import com.duzhaokun123.bilibilihd2.DESKTOP_USER_AGENT
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.TABLETS_USER_AGENT
import com.duzhaokun123.bilibilihd2.bases.BaseActivity
import com.duzhaokun123.bilibilihd2.databinding.LayoutWebViewBinding
import com.duzhaokun123.bilibilihd2.utils.*
import kotlinx.coroutines.delay

class WebViewActivity : BaseActivity<LayoutWebViewBinding>(R.layout.layout_web_view) {
    companion object {
        const val EXTRA_UA = "ua"
        const val EXTRA_INTERCEPT_ALL = "intercept_all"
        const val EXTRA_FINISH_WHEN_INTERCEPT = "finish_when_intercept"

        val PIP_PARAMS by lazy {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                PictureInPictureParams.Builder().setAspectRatio(Rational(16, 9)).build()!!
            else
                throw RuntimeException("SDK < (26) should not call this")
        }

        enum class UA(val value: String, val n: String, val id: Int) {
            DESKTOP(DESKTOP_USER_AGENT, "桌面", R.id.item_ua_desktop),
            TABLET(TABLETS_USER_AGENT, "平板", R.id.item_ua_tablet),
            CLIENT(CLIENT_USER_AGENT, "客户端", R.id.item_ua_client)
        }

        const val MODEL_CV_SCRIPT =
            "document.getElementsByClassName(\"read-icon-close\")[0].click();document.getElementsByClassName(\"read-more\")[0].click()"

        private fun newIntent(
            context: Context,
            uri: Uri,
            ua: UA = UA.TABLET,
            interceptAll: Boolean = false,
            finishWhenIntercept: Boolean = false
        ): Intent {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.data = uri
            intent.putExtra(EXTRA_UA, ua)
            intent.putExtra(EXTRA_INTERCEPT_ALL, interceptAll)
            intent.putExtra(EXTRA_FINISH_WHEN_INTERCEPT, finishWhenIntercept)
            return intent
        }

        init {
            UrlOpenActivity.intentFilters.add { parsedIntent, context ->
                when {
                    parsedIntent.host == "article" -> {
                        newIntent(
                            context,
                            "https://www.bilibili.com/read/mobile?id=${parsedIntent.paths[0]}".toUri()
                        ) to "专栏 ${parsedIntent.paths[0]}"
                    }
                    parsedIntent.paths.getOrNull(0) == "read" && parsedIntent.paths.getOrNull(1)?.startsWith("cv") == true -> {
                        newIntent(
                            context,
                            "https://www.bilibili.com/read/mobile?id=${parsedIntent.paths[1].substring(2)}".toUri()
                        ) to "专栏 ${parsedIntent.paths[1].substring(2)}"
                    }
                    else -> null to null
                }
            }
            UrlOpenActivity.intentFilters.add {parsedIntent, context ->
                when {
                    parsedIntent.scheme in listOf("http", "https") -> {
                        val ua =
                            when {
                                parsedIntent.host?.startsWith("passport.") == true -> UA.CLIENT
                                "h5" in parsedIntent.paths || parsedIntent.host?.startsWith("m.") == true -> UA.TABLET
                                else -> UA.DESKTOP
                            }
                        newIntent(context, parsedIntent.uri, ua = ua) to "内置浏览器 ua: ${ua.n}"
                    }
                    parsedIntent.host == "browser" -> {
                    newIntent(context, "${parsedIntent.queryMap["url"]}".toUri(), ua = UA.CLIENT) to "内置浏览器 ua: ${UA.CLIENT.n}"
                }
                    else -> null to null
                }
            }
            UrlOpenActivity.intentFilters.add {parsedIntent, context ->
                if (parsedIntent.host == "b23.tv")
                    newIntent(context, parsedIntent.uri, ua = UA.CLIENT, interceptAll = true, finishWhenIntercept = true) to "内置浏览器 ua: ${UA.CLIENT.n}"
                else null to null
            }
        }
    }

    private val configViewModel: ConfigViewModel by viewModels()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.web_view_activity, menu)
        menu?.findItem(configViewModel.ua.value!!.id)?.isChecked = true
        menu?.findItem(R.id.intercept_all)
            ?.let { it.isChecked = configViewModel.interceptAll.value!! }
        menu?.findItem(R.id.finish_when_intercept)
            ?.let { it.isChecked = configViewModel.finishWhenIntercept.value!! }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.isCheckable) {
            item.isChecked = item.isChecked.not()
        }
        return when (item.itemId) {
            R.id.open_in_browser -> {
                baseBinding.wv.url?.let { BrowserUtil.openCustomTab(this, it) }
                true
            }
            R.id.reload -> {
                baseBinding.wv.reload()
                true
            }
            R.id.stop -> {
                baseBinding.wv.stopLoading()
                true
            }
            R.id.pip -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    enterPictureInPictureMode(PIP_PARAMS)
                } else {
                    TipUtil.showToast("SDK < O (26) 不支持画中画")
                }
                true
            }
            R.id.item_ua_desktop -> {
                configViewModel.ua.value = UA.DESKTOP
                true
            }
            R.id.item_ua_tablet -> {
                configViewModel.ua.value = UA.TABLET
                true
            }
            R.id.item_ua_client -> {
                configViewModel.ua.value = UA.CLIENT
                true
            }
            R.id.intercept_all -> {
                configViewModel.interceptAll.value = item.isChecked
                true
            }
            R.id.finish_when_intercept -> {
                configViewModel.finishWhenIntercept.value = item.isChecked
                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isFirstCreate) {
            configViewModel.ua.value = startIntent.getSerializableExtra(EXTRA_UA) as? UA ?: UA.DESKTOP
            configViewModel.interceptAll.value =
                startIntent.getBooleanExtra(EXTRA_INTERCEPT_ALL, false)
            configViewModel.finishWhenIntercept.value = startIntent.getBooleanExtra(
                EXTRA_FINISH_WHEN_INTERCEPT, false
            )
        }
        supportActionBar?.let {
            it.setHomeAsUpIndicator(R.drawable.ic_clear)
            it.setDisplayShowHomeEnabled(true)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        baseBinding.wv.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return if ("bilibili" == request?.url?.scheme || configViewModel.interceptAll.value!!) {
                    val intent = Intent(this@WebViewActivity, UrlOpenActivity::class.java)
                    intent.data = request?.url
                    startActivity(intent)
                    if (configViewModel.finishWhenIntercept.value!!) {
                        finish()
                    }
                    true
                } else {
                    false
                }

            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                title = url
                baseBinding.pb.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                baseBinding.pb.visibility = View.INVISIBLE

                if (url != null && "read/mobile" in url) {
                    runMain {
                        delay(500)
                        baseBinding.wv.evaluateJavascript(MODEL_CV_SCRIPT, null)
                    }
                }
            }
        }
        baseBinding.wv.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    baseBinding.pb.setProgress(newProgress, true)
                } else {
                    baseBinding.pb.progress = newProgress
                }
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                setTitle(title)
                setSubtitle(baseBinding.wv.url)
            }
        }
        configViewModel.ua.observe(this) { ua ->
            baseBinding.wv.settings.userAgentString = ua.value
        }
        baseBinding.wv.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            useWideViewPort = true
            setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = false
        }
    }

    override fun initData() {
        startIntent.dataString?.let { baseBinding.wv.loadUrl(it) }
    }

    override fun onApplyWindowInsetsCompat(insets: WindowInsetsCompat) {
        super.onApplyWindowInsetsCompat(insets)
        insets.maxSystemBarsDisplayCutout.let {
            baseBinding.wv.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = it.bottom
            }
        }
    }

    override fun onBackPressed() {
        if (baseBinding.wv.canGoBack()) {
            baseBinding.wv.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration?
    ) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        if (isInPictureInPictureMode) {
            supportActionBar?.hide()
        } else {
            supportActionBar?.show()
        }
    }

    class ConfigViewModel : ViewModel() {
        val ua = MutableLiveData<UA>()
        val interceptAll = MutableLiveData<Boolean>()
        val finishWhenIntercept = MutableLiveData<Boolean>()
    }
}
