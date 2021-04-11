package com.duzhaokun123.bilibilihd2.ui.scape

import android.content.Context
import android.content.Intent
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseActivity
import com.duzhaokun123.bilibilihd2.databinding.ActivityUserScapeBinding

class UserScapeActivity: BaseActivity<ActivityUserScapeBinding>(R.layout.activity_user_scape) {
    companion object {
        private const val EXTRA_UID = "uid"

        fun enter(context: Context, uid: Long) {
            context.startActivity(Intent(context, UserScapeActivity::class.java).apply {
                putExtra(EXTRA_UID, uid)
            })
        }
    }

    override fun initView() {
        baseBinding.a.text = "uid${startIntent.getLongExtra(EXTRA_UID, 0)}"
    }

    override fun initData() {

    }
}