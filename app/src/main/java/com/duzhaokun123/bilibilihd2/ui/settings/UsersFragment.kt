package com.duzhaokun123.bilibilihd2.ui.settings

import android.content.ActivityNotFoundException
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.FragmentUsersBinding
import com.duzhaokun123.bilibilihd2.model.UserModel
import com.duzhaokun123.bilibilihd2.ui.login.QRLoginActivity
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.generated.Settings
import com.github.salomonbrys.kotson.fromJson
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hiczp.bilibili.api.passport.model.LoginResponse
import io.github.duzhaokun123.androidapptemplate.bases.BaseFragment
import io.github.duzhaokun123.androidapptemplate.utils.TipUtil

@Suppress("UNUSED")
class UsersFragment : BaseFragment<FragmentUsersBinding>(R.layout.fragment_users) {
    private val model by activityViewModels<SettingsActivity.Model>()

    private var exportLoginResponse =
        registerForActivityResult(ActivityResultContracts.CreateDocument()) { uri ->
            if (uri == null) {
                model.loginResponseToExport.value = null
                return@registerForActivityResult
            }
            requireContext().contentResolver.openOutputStream(uri)?.writer()?.use { out ->
                try {
                    out.write(gson.toJson(model.loginResponseToExport.value!!.getJsonObject()))
                    TipUtil.showTip(context, "导出成功")
                } catch (e: Exception) {
                    e.printStackTrace()
                    TipUtil.showTip(context, e.message)
                } finally {
                    model.loginResponseToExport.value = null
                }
            }
        }
    private var importLoginResponse =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            if (uri == null) return@registerForActivityResult
            requireContext().contentResolver.openInputStream(uri)?.reader()?.use { `in` ->
                try {
                    UsersMap.put(LoginResponse(gson.fromJson(`in`)))
                    UsersMap.save()
                    baseBinding.rv.adapter =
                        UsersAdapter(requireBaseActivity(), UsersMap.users.toMutableList())
                    TipUtil.showTip(context, "导入成功")
                } catch (e: Exception) {
                    e.printStackTrace()
                    TipUtil.showTip(context, e.message)
                }
            }
        }

    override fun initViews() {
        baseBinding.btnAdd.setOnClickListener {
            PopupMenu(requireContext(), baseBinding.btnAdd).apply {
                menuInflater.inflate(R.menu.user_add_menu, menu)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.item_login -> {
                            TipUtil.showTip(requireContext(), "不支持账户密码登录")
                        }
                        R.id.item_import -> {
                            runCatching { importLoginResponse.launch(arrayOf("*/*")) }
                                .onFailure {
                                    if (it !is ActivityNotFoundException) throw it
                                    MaterialAlertDialogBuilder(requireContext())
                                        .setTitle("什么奇葩系统")
                                        .setMessage("缺少支持 OPEN_DOCUMENT 的文件管理器\n这说明你的系统甚至没有活动的 com.android.documentsui 或 com.google.android.documentsui \n责备你的 ROM 开发者或你自己")
                                        .show()
                                }
                        }
                        R.id.item_qr -> {
                            requireContext().startActivity<QRLoginActivity>()
                        }
                    }
                    true
                }
            }.show()
        }
        baseBinding.btnClear.setOnClickListener {
            selectUid(0)
        }
        baseBinding.btnRefresh.setOnClickListener {
            if (bilibiliClient.isLogin.not()) {
                TipUtil.showTip(context, "未登录")
            } else {
                runIOCatchingResultRunMain(context, { bilibiliClient.refresh() }) { re ->
                    if (re != null) {
                        UsersMap.put(re)
                        UsersMap.save()
                        selectUid(re.userId)
                    } else {
                        TipUtil.showTip(context, "未知错误")
                    }
                }
            }
        }
        baseBinding.rv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        baseBinding.rv.adapter = UsersAdapter(requireBaseActivity(), UsersMap.users.toMutableList())
    }

    override fun initData() {
        model.selectedUid.observe(this) { uid ->
            if (uid != 0L)
                selectUid(uid)
        }
        model.loginResponseToExport.observe(this) { loginResponse ->
            if (loginResponse != null) {
                runCatching { exportLoginResponse.launch("loginResponse.json") }
                    .onFailure {
                        if (it !is ActivityNotFoundException) throw it
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle("什么奇葩系统")
                            .setMessage("缺少支持 CREATE_DOCUMENT 的文件管理器\n这说明你的系统甚至没有活动的 com.android.documentsui 或 com.google.android.documentsui \n责备你的 ROM 开发者或你自己")
                            .show()
                    }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        model.subtitle.value = getText(R.string.users)
        reloadSelectedUser()
    }

    override fun onDestroy() {
        super.onDestroy()
        model.loginResponseToExport.value = null
    }

    private fun selectUid(uid: Long) {
        model.selectedUid.value = 0
        Settings.selectedUid = uid
        application.reinitBilibiliClient(uid)
        reloadSelectedUser()
    }

    private fun reloadSelectedUser() {
        if (bilibiliClient.loginResponse != null) {
            val loginResponse = bilibiliClient.loginResponse!!
            baseBinding.model =
                UserModel(
                    "Loading...",
                    loginResponse.userId,
                    null,
                    "UID: ${loginResponse.userId}\n过期于: ${DateFormat.format1.format(loginResponse.expires * 1000)}"
                )
            runIOCatchingResultRunMain(
                context, { bilibiliClient.appAPI.myInfo().await() })
            { myInfo ->
                baseBinding.model = UserModel(
                    myInfo.data.name,
                    loginResponse.userId,
                    myInfo.data.face,
                    "UID: ${loginResponse.userId}\n过期于: ${DateFormat.format1.format(loginResponse.expires * 1000)}"
                )
            }
        } else
            baseBinding.model = UserModel("未登录", 0, null, "")
    }
}