package com.duzhaokun123.bilibilihd2.bases

import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.ActivityFlBinding

abstract class BaseSingleFragmentActivity(private val fragmentClass: Class<out Fragment>) :
    io.github.duzhaokun123.androidapptemplate.bases.BaseActivity<ActivityFlBinding>(R.layout.activity_fl) {

    @CallSuper
    override fun initViews() {
        if (isFirstCreate)
            supportFragmentManager.beginTransaction()
                .add(R.id.fl, fragmentClass, null)
                .commit()
    }
}