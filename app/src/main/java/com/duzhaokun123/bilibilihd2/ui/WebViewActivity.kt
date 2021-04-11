package com.duzhaokun123.bilibilihd2.ui

import android.annotation.SuppressLint
import android.app.PictureInPictureParams
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
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
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duzhaokun123.bilibilihd2.DESKTOP_USER_AGENT
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.TABLETS_USER_AGENT
import com.duzhaokun123.bilibilihd2.bases.BaseActivity
import com.duzhaokun123.bilibilihd2.databinding.LayoutWebViewBinding
import com.duzhaokun123.bilibilihd2.utils.BrowserUtil
import com.duzhaokun123.bilibilihd2.utils.systemBars

class WebViewActivity : BaseActivity<LayoutWebViewBinding>(R.layout.layout_web_view) {
    companion object {
        const val EXTRA_DESKTOP_UA = "desktop_ua"
        const val EXTRA_INTERCEPT_ALL = "intercept_all"
        const val EXTRA_FINISH_WHEN_INTERCEPT = "finish_when_intercept"

        val PIP_PARAMS = PictureInPictureParams.Builder().setAspectRatio(Rational(16, 9)).build()!!
    }

    private val configViewModel: ConfigViewModel by viewModels()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.web_view_activity, menu)
        menu?.findItem(R.id.desktop_ua)?.let { it.isChecked = configViewModel.desktopUA.value!! }
        menu?.findItem(R.id.intercept_all)?.let { it.isChecked = configViewModel.interceptAll.value!! }
        menu?.findItem(R.id.finish_when_intercept)?.let { it.isChecked = configViewModel.finishWhenIntercept.value!! }
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
                enterPictureInPictureMode(PIP_PARAMS)
                true
            }
            R.id.desktop_ua -> {
                configViewModel.desktopUA.value = item.isChecked
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
            configViewModel.desktopUA.value = startIntent.getBooleanExtra(EXTRA_DESKTOP_UA, true)
            configViewModel.interceptAll.value = startIntent.getBooleanExtra(EXTRA_INTERCEPT_ALL, false)
            configViewModel.finishWhenIntercept.value = startIntent.getBooleanExtra(
                EXTRA_FINISH_WHEN_INTERCEPT, false)
        }
        supportActionBar?.let {
            it.setHomeAsUpIndicator(R.drawable.ic_clear)
            it.setDisplayShowHomeEnabled(true)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        baseBinding.wv.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
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
            }
        }
        baseBinding.wv.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                baseBinding.pb.setProgress(newProgress, true)
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                setTitle(title)
                setSubtitle(baseBinding.wv.url)
            }
        }
        configViewModel.desktopUA.observe(this, { desktopUA ->
            if (desktopUA) {
                baseBinding.wv.settings.userAgentString = DESKTOP_USER_AGENT
            } else {
                baseBinding.wv.settings.userAgentString = TABLETS_USER_AGENT
            }
        })
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
        insets.systemBars.let {
            baseBinding.wv.updateLayoutParams<ViewGroup.MarginLayoutParams>() {
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

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean, newConfig: Configuration?) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        if (isInPictureInPictureMode) {
            supportActionBar?.hide()
        } else {
            supportActionBar?.show()
        }
    }

    class ConfigViewModel : ViewModel() {
        val desktopUA: MutableLiveData<Boolean> by lazy {
            MutableLiveData<Boolean>()
        }
        val interceptAll: MutableLiveData<Boolean> by lazy {
            MutableLiveData<Boolean>()
        }
        val finishWhenIntercept: MutableLiveData<Boolean> by lazy {
            MutableLiveData<Boolean>()
        }
    }
}
