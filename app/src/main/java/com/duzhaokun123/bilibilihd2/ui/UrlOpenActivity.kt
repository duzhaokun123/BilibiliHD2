package com.duzhaokun123.bilibilihd2.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Button
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseActivity
import com.duzhaokun123.bilibilihd2.databinding.ActivityUrlOpenBinding

class UrlOpenActivity : BaseActivity<ActivityUrlOpenBinding>(R.layout.activity_url_open) {
    companion object {
        const val TAG = "UrlOpenActivity"

        /**
         * 返回启动 Activity 的 Intent, 简介
         */
        val intentFilters =
            mutableSetOf<(parsedIntent: ParsedIntent, context: Context) -> Pair<Intent?, String?>>()
    }

    data class ParsedIntent(
        val raw: Intent,
        val uri: Uri,
        val scheme: String?,
        val host: String?,
        val path: String?,
        val paths: List<String>,
        val queryMap: Map<String, String?>
    )

    @SuppressLint("SetTextI18n")
    override fun initView() {
        val uri = startIntent.data
        if (uri == null) {
            Log.d(TAG, "uri == null")
            baseBinding.tv1.text = "uri == null"
        } else {
            val scheme = uri.scheme
            val host = uri.host
            val path = uri.path
            val paths = try {
                path.orEmpty().substring(1).split("/")
            } catch (e: Exception) {
                emptyList()
            }
            val queryMap = mutableMapOf<String, String?>()
            uri.queryParameterNames.forEach {
                queryMap[it] = uri.getQueryParameter(it)
            }

            val t1 = StringBuilder().apply {
                appendLine(uri)
                appendLine("scheme: $scheme")
                appendLine("host: $host")
                appendLine("path: $path")
                appendLine("paths: $paths")
                queryMap.forEach { (k, v) ->
                    appendLine("query: $k: $v")
                }
            }.toString()

            Log.d(TAG, t1)

            baseBinding.tv1.text = t1

            val parsedIntent = ParsedIntent(startIntent, uri, scheme, host, path, paths, queryMap)
            intentFilters.forEach {
                it.invoke(parsedIntent, this).let { (intent, desc) ->
                    if (intent == null) return@let
                    baseBinding.llTarget.addView(Button(this).apply {
                        isAllCaps = false
                        text = desc ?: intent.toString()
                        setOnClickListener {
                            startActivity(intent)
                        }
                    })
                }
            }
        }
    }

    override fun initData() {

    }
}