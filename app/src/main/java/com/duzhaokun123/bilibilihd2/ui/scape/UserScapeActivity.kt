package com.duzhaokun123.bilibilihd2.ui.scape

import android.content.Context
import android.content.Intent
import com.duzhaokun123.annotationProcessor.IntentFilter
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.ActivityUserScapeBinding
import com.duzhaokun123.bilibilihd2.ui.UrlOpenActivity
import com.duzhaokun123.bilibilihd2.utils.BrowserUtil
import io.github.duzhaokun123.androidapptemplate.bases.BaseActivity

class UserScapeActivity : BaseActivity<ActivityUserScapeBinding>(R.layout.activity_user_scape) {
    companion object {
        private const val EXTRA_UID = "uid"

        @IntentFilter
        class UserSpaceIntentFilter: UrlOpenActivity.IIntentFilter {
            override fun handle(
                parsedIntent: UrlOpenActivity.ParsedIntent,
                context: Context
            ): Pair<Intent?, String?> {
                return when (parsedIntent.host) {
                    "space", "author", "space.bilibili.com" ->
                        Intent(context, UserScapeActivity::class.java).apply {
                            putExtra(EXTRA_UID, parsedIntent.paths[0].toLong())
                        } to "用户 ${parsedIntent.paths[0]}"
                    else -> null to null
                }
            }
        }
    }

    override fun initViews() {
        baseBinding.a.text = "uid${startIntent.getLongExtra(EXTRA_UID, 0)}"
        baseBinding.b.setOnClickListener {
            BrowserUtil.openInApp(this, "https://space.bilibili.com/${startIntent.getLongExtra(EXTRA_UID, 0)}")
        }
    }
}