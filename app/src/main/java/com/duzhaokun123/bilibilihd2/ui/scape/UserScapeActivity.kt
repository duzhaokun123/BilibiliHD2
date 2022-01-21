package com.duzhaokun123.bilibilihd2.ui.scape

import android.content.Intent
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.ActivityUserScapeBinding
import com.duzhaokun123.bilibilihd2.ui.UrlOpenActivity
import io.github.duzhaokun123.androidapptemplate.bases.BaseActivity

class UserScapeActivity : BaseActivity<ActivityUserScapeBinding>(R.layout.activity_user_scape) {
    companion object {
        private const val EXTRA_UID = "uid"

        init {
            UrlOpenActivity.intentFilters.add { parsedIntent, context ->
                when (parsedIntent.host) {
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
    }
}