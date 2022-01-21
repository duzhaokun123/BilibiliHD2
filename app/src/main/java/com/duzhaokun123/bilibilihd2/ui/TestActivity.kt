package com.duzhaokun123.bilibilihd2.ui

import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.ActivityTestBinding
import com.duzhaokun123.bilibilihd2.utils.TipUtil
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import io.github.duzhaokun123.androidapptemplate.bases.BaseActivity

class TestActivity : BaseActivity<ActivityTestBinding>(R.layout.activity_test) {
    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result ->
        if(result.contents == null) {
            TipUtil.showToast("Cancelled")
        } else {
            startActivity(Intent(this, UrlOpenActivity::class.java).apply {
                data = result.contents.toUri()
            })
        }
    }

    override fun initViews() {
        title = "test"
        baseBinding.btnUrlStart.setOnClickListener {
            try {
                startActivity(Intent().apply {
                    data = Uri.parse(baseBinding.etUrl.text.toString())
                })
            } catch (e: Exception) {
                e.printStackTrace()
                TipUtil.showToast(e.message)
            }
        }
        baseBinding.btnScan.setOnClickListener { barcodeLauncher.launch(ScanOptions().apply {
            setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        }) }
    }
}