package com.duzhaokun123.bilibilihd2.ui.settings

import androidx.activity.viewModels
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseSingleFragmentActivity
import com.duzhaokun123.bilibilihd2.utils.*
import com.hiczp.bilibili.api.passport.model.LoginResponse

class SettingsActivity : BaseSingleFragmentActivity(SettingsMainFragment::class.java) {
    class Model : ViewModel() {
        val subtitle = MutableLiveData<CharSequence>("")
        val selectedUid = MutableLiveData(bilibiliClient.loginResponse?.userId ?: 0)
        val loginResponseToExport = MutableLiveData<LoginResponse?>(null)
    }

    val model by viewModels<Model>()

    override fun initView() {
        super.initView()
        setTitle(R.string.settings)
        setSubtitle(model.subtitle.value)
    }

    override fun initData() {
        model.subtitle.observe(this) {
            setSubtitle(it)
        }
    }

    override fun onApplyWindowInsetsCompat(insets: WindowInsetsCompat) {
        super.onApplyWindowInsetsCompat(insets)
        insets.maxSystemBarsDisplayCutout.let {
            baseBinding.fl.updatePadding(bottom = it.bottom)
        }
    }
}