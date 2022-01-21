package com.duzhaokun123.bilibilihd2.ui.login

import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.ActivityLoginBinding
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.generated.Settings
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.github.duzhaokun123.androidapptemplate.bases.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    override fun initViews() {
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
                            MaterialAlertDialogBuilder(this@LoginActivity).apply {
                                setTitle("status: ${loginResponse.data.status}")
                                setMessage("${loginResponse.data.message}\n\n(开发者: 然而操作了也不一定有用)")
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
                } catch (e:NoClassDefFoundError){
                    e.printStackTrace()
                    TipUtil.showToast("此设备暂不支持登陆")
                } finally {
                    runMain {
                        baseBinding.working = false
                    }
                }
            }
        }
    }
}