package com.duzhaokun123.bilibilihd2.ui.main

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import android.webkit.*
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import com.duzhaokun123.bilibilihd2.DESKTOP_USER_AGENT
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseFragment
import com.duzhaokun123.bilibilihd2.databinding.LayoutWebViewBinding
import com.duzhaokun123.bilibilihd2.utils.*

class DynamicWebFragment : BaseFragment<LayoutWebViewBinding>(R.layout.layout_web_view) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val a = super.onCreateView(inflater, container, savedInstanceState)
        savedInstanceState?.let { baseBinding.wv.restoreState(it) }
        return a
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        baseBinding.wv.apply {
            settings.apply {
                javaScriptEnabled = true
                blockNetworkImage = false
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                userAgentString = DESKTOP_USER_AGENT
                domStorageEnabled = true
            }
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView, request: WebResourceRequest
                ): Boolean {
                    BrowserUtil.openInApp(requireContext(), request.url.toString())
                    return true
                }

                override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                    baseBinding.pb.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView, url: String) {
                    baseBinding.pb.visibility = View.INVISIBLE
                }
            }
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView, newProgress: Int) {
                    baseBinding.pb.setProgress(newProgress, true)
                }
            }
        }
    }

    override fun initData() {
        if (isFirstCreate) baseBinding.wv.loadUrl("https://t.bilibili.com")
    }

    override fun onApplyWindowInsetsCompat(insets: WindowInsetsCompat) {
        insets.systemBars.let {
            baseBinding.wv.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = it.bottom
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        baseBinding.wv.saveState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dynamic_web_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_reload) {
            baseBinding.wv.reload()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}