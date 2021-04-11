package com.duzhaokun123.bilibilihd2.ui.login

import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseActivity
import com.duzhaokun123.bilibilihd2.databinding.ActivityLoginBinding
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.generated.Settings

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    override fun initView() {
        setTitle(R.string.login)
        baseBinding.btnLogin.setOnClickListener {
            baseBinding.working = true
            runIO {
                try {
                    UsersMap.put(
                        bilibiliClient.login(
                            baseBinding.tietUsername.text.toString(),
                            baseBinding.tietPassword.text.toString()
                        ).also { Settings.selectedUid = it.userId }
                    )
                    UsersMap.save()
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                    TipUtil.showToast(e.message)
                } finally {
                    runMain {
                        baseBinding.working = false
                    }
                }
            }
        }
    }

    override fun initData() {}
}