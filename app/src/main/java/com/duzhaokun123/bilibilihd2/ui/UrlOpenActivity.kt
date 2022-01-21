package com.duzhaokun123.bilibilihd2.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.ActivityUrlOpenBinding
import com.google.android.material.button.MaterialButton
import io.github.duzhaokun123.androidapptemplate.bases.BaseActivity

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
    override fun initViews() {
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
                    baseBinding.llTarget.addView(MaterialButton(this, null, R.attr.borderlessButtonStyle).apply {
                        isAllCaps = false
                        text = desc ?: intent.toString()
                        setOnClickListener {
                            startActivity(intent)
                        }
                    })
                }
            }
            if (baseBinding.llTarget.childCount == 0) {
                baseBinding.llTarget.addView(TextView(this).apply {
                    text = "不支持此链接"
                })
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("外部打开")?.apply {
            setIcon(R.drawable.ic_launch)
            setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
            setOnMenuItemClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, startIntent.data))
                true
            }
        }
        return super.onCreateOptionsMenu(menu)
    }
}