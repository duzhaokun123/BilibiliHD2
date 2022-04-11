package com.duzhaokun123.bilibilihd2.ui.settings

import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
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
import io.github.duzhaokun123.androidapptemplate.bases.BaseFragment
import io.github.duzhaokun123.androidapptemplate.utils.TipUtil

@Suppress("UNUSED")
class UsersFragment : BaseFragment<FragmentUsersBinding>(R.layout.fragment_users) {
    private val model by activityViewModels<SettingsActivity.Model>()

    private lateinit var exportLoginResponse: ActivityResultLauncher<String>
    private lateinit var importLoginResponse: ActivityResultLauncher<Array<String>>

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
                            importLoginResponse.launch(arrayOf("*/*"))
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
        model.loginResponseToExport.observe(this) {
            if (it != null) exportLoginResponse.launch("loginResponse.json")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exportLoginResponse =
            registerForActivityResult(ActivityResultContracts.CreateDocument()) { uri ->
                if (uri == null) return@registerForActivityResult
                requireContext().contentResolver.openOutputStream(uri)?.writer()?.use { out ->
                    try {
                        out.write(gson.toJson(model.loginResponseToExport.value))
                        TipUtil.showTip(context, "导出成功")
                    } catch (e: Exception) {
                        e.printStackTrace()
                        TipUtil.showTip(context, e.message)
                    } finally {
                        model.loginResponseToExport.value = null
                    }
                }
            }
        importLoginResponse =
            registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
                if (uri == null) return@registerForActivityResult
                requireContext().contentResolver.openInputStream(uri)?.reader()?.use { `in` ->
                    try {
                        UsersMap.put(gson.fromJson(`in`))
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