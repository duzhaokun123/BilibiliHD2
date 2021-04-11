package com.duzhaokun123.bilibilihd2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.duzhaokun123.bilibilihd2.utils.BrowserUtil

class UrlOpenActivity : AppCompatActivity() {
    companion object {
        const val TAG = "UrlOpenActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.data?.let { BrowserUtil.openInApp(this, it) }
        finish()
    }
}