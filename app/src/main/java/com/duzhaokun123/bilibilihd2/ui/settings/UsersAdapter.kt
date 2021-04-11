package com.duzhaokun123.bilibilihd2.ui.settings

import androidx.activity.viewModels
import androidx.appcompat.widget.PopupMenu
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseActivity
import com.duzhaokun123.bilibilihd2.bases.BaseSimpleAdapter
import com.duzhaokun123.bilibilihd2.databinding.ItemUserCardBinding
import com.duzhaokun123.bilibilihd2.model.UserModel
import com.duzhaokun123.bilibilihd2.utils.*
import com.hiczp.bilibili.api.passport.model.LoginResponse

class UsersAdapter(activity: BaseActivity<*>, private val usersList: MutableList<LoginResponse>) :
    BaseSimpleAdapter<ItemUserCardBinding>(activity, R.layout.item_user_card) {
    val model by activity.viewModels<SettingsActivity.Model>()

    override fun initView(baseBinding: ItemUserCardBinding, position: Int) {
        baseBinding.cv.setOnClickListener {
            model.selectedUid.value = usersList[position].userId
        }
        baseBinding.cv.setOnLongClickListener {
            PopupMenu(context, it).apply {
                menuInflater.inflate(R.menu.user_adapter_menu, menu)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.item_delete -> {
                            UsersMap.remove(usersList.removeAt(position).userId)
                            UsersMap.save()
                            notifyItemRemoved(position)
                        }
                        R.id.item_export -> {
                            model.loginResponseToExport.value = usersList[position]
                        }
                    }
                    true
                }
            }.show()
            true
        }
    }

    override fun initData(baseBinding: ItemUserCardBinding, position: Int) {
        val userId = usersList[position].userId
        baseBinding.model =
            UserModel("Loading...", userId, null, "UID: $userId")
        runIOCatchingResultRunMain(
            context, { bilibiliClient.appAPI.space(vmId = userId).await() })
        { myInfo ->
            baseBinding.model = UserModel(
                myInfo.data.card.name, userId, myInfo.data.card.face, "UID: $userId"
            )
        }
    }

    override fun getItemCount() = usersList.size
}