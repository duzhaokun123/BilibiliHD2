package com.duzhaokun123.bilibilihd2.ui.login

import androidx.appcompat.app.AlertDialog
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
                    val loginResponse = bilibiliClient.login(
                        baseBinding.tietUsername.text.toString(),
                        baseBinding.tietPassword.text.toString()
                    )
                    if (loginResponse.data.status == 0) {
                        Settings.selectedUid = loginResponse.userId
                        UsersMap.put(loginResponse)
                        UsersMap.save()
                        finish()
                    } else {
                        runMain {
                            AlertDialog.Builder(this@LoginActivity).apply {
                                setTitle("status: ${loginResponse.data.status}")
                                setMessage("${loginResponse.data.message}\n然而操作了也没用")
                                    .setPositiveButton(android.R.string.ok) { _, _ ->
                                        BrowserUtil.openInApp(
                                            this@LoginActivity, loginResponse.data.url
                                        )
                                    }
                            }.show()
                        }
                    }
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