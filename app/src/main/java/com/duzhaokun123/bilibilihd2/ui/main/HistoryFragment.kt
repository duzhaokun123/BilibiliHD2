package com.duzhaokun123.bilibilihd2.ui.main

import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.lifecycle.MutableLiveData
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseSimpleCardGridSRRVFragment
import com.duzhaokun123.bilibilihd2.databinding.ItemHistoryCardBinding
import com.duzhaokun123.bilibilihd2.model.HistoryCardModel
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.generated.Settings
import com.hiczp.bilibili.api.app.model.History

class HistoryFragment :
    BaseSimpleCardGridSRRVFragment<ItemHistoryCardBinding, HistoryCardModel, HistoryFragment.HistoryModel>(
        R.layout.item_history_card,
        Settings.mainCardWidthDp.let { run { if (it == 0) 500 else it }.dpToPx() },
        HistoryModel::class
    ) {
    class HistoryModel : BaseModel<HistoryCardModel>() {
        val max = MutableLiveData<Long>(0)
        val tabs = MutableLiveData<List<History.Data.Tab>>(emptyList())
        val selectedTab = MutableLiveData("all")
    }

    override suspend fun onRefreshIO(): List<HistoryCardModel>? {
        return runCatching {
            HistoryCardModel.parse(
                bilibiliClient.appAPI.history(business = baseModel.selectedTab.value!!).await()
                    .also {
                        if (it.data.cursor != null)
                            baseModel.max.postValue(it.data.cursor!!.max)
                        else
                            setNoMoreData(true)
                        baseModel.tabs.postValue(it.data.tab)
                    })
        }.commonOnFailureHandler(context).getOrNull()
    }

    override suspend fun onLoadMorIO(): List<HistoryCardModel>? {
        return runCatching {
            HistoryCardModel.parse(
                bilibiliClient.appAPI.history(
                    business = baseModel.selectedTab.value!!, max = baseModel.max.value!!
                ).await().also {
                    if (it.data.cursor != null)
                        baseModel.max.postValue(it.data.cursor!!.max)
                    else
                        setNoMoreData(true)
                })
        }.commonOnFailureHandler(context).getOrNull()
    }

    override fun initItemView(
        itemBinding: ItemHistoryCardBinding, itemModel: HistoryCardModel, position: Int
    ) {
        itemBinding.cv.setOnClickListener {
            itemModel.uri?.let { BrowserUtil.openInApp(context, it) }
        }
        itemBinding.cv.setOnLongClickListener {
            itemBinding.ibTp.callOnClick()
            true
        }
        itemBinding.ibTp.setOnClickListener {
            PopupMenu(requireContext(), itemBinding.ibTp).apply {
                menu.add("检查封面").setOnMenuItemClickListener {
                    ImageViewUtil.viewImage(requireActivity(), itemModel.coverUrl, itemBinding.iv)
                    true
                }
            }.show()
        }
    }

    override fun initItemData(
        itemBinding: ItemHistoryCardBinding, itemModel: HistoryCardModel, position: Int
    ) {
        itemBinding.model = itemModel
    }

    override fun onApplyWindowInsetsCompat(insets: WindowInsetsCompat) {
        super.onApplyWindowInsetsCompat(insets)
        with(insets.maxSystemBarsDisplayCutout) {
            baseBinding.srl.updatePadding(left = left, right = right, bottom = bottom)
            baseBinding.cf.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = bottom
            }
        }
    }

    override fun initData() {
        super.initData()
        baseModel.tabs.observe(this) { activity?.invalidateOptionsMenu() }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        baseModel.tabs.value!!.forEachIndexed { i, tab ->
            menu.add(1, i, i, tab.name).apply {
                isChecked = tab.business == baseModel.selectedTab.value
                setOnMenuItemClickListener {
                    isChecked = true
                    baseModel.selectedTab.value = tab.business
                    srl.autoRefresh()
                    true
                }
            }
        }
        menu.setGroupCheckable(1, true, true)
    }
}