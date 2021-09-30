package com.duzhaokun123.bilibilihd2.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SSOCodeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val t = StringBuilder().appendLine().appendLine(intent.toString())
        intent.extras!!.let {
            it.keySet().forEach { k ->
                t.appendLine("$k: ${it.get(k)}")
            }
        setContentView(TextView(this).apply {
            text = t
        })
    }}
}