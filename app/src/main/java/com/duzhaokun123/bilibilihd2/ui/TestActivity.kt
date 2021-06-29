package com.duzhaokun123.bilibilihd2.ui

import android.content.Intent
import android.net.Uri
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseActivity
import com.duzhaokun123.bilibilihd2.databinding.ActivityTestBinding
import com.duzhaokun123.bilibilihd2.utils.TipUtil

class TestActivity : BaseActivity<ActivityTestBinding>(R.layout.activity_test) {
    override fun initView() {
        title = "test"
        baseBinding.btnUrlStart.setOnClickListener {
            try {
                startActivity(Intent().apply {
                    data = Uri.parse(baseBinding.etUrl.text.toString())
                })
            }catch (e: Exception) {
                e.printStackTrace()
                TipUtil.showToast(e.message)
            }
        }
    }

    override fun initData() {

    }

}