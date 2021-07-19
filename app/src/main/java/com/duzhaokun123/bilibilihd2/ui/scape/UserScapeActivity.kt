package com.duzhaokun123.bilibilihd2.ui.scape

import android.content.Context
import android.content.Intent
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseActivity
import com.duzhaokun123.bilibilihd2.databinding.ActivityUserScapeBinding
import com.duzhaokun123.bilibilihd2.ui.UrlOpenActivity

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

    override fun initView() {
        baseBinding.a.text = "uid${startIntent.getLongExtra(EXTRA_UID, 0)}"
    }

    override fun initData() {

    }
}