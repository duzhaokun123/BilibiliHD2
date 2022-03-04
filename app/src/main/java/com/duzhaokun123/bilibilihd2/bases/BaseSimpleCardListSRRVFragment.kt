package com.duzhaokun123.bilibilihd2.bases

import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.Px
import androidx.core.graphics.ColorUtils
import androidx.core.view.updatePadding
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.utils.dpToPx
import com.duzhaokun123.bilibilihd2.utils.getAttr
import com.duzhaokun123.bilibilihd2.utils.runIO
import com.duzhaokun123.bilibilihd2.utils.runMain
import com.scwang.smart.refresh.layout.api.RefreshLayout
import io.material.catalog.tableofcontents.GridDividerDecoration
import kotlin.reflect.KClass

abstract class BaseSimpleCardListSRRVFragment<ItemBinding : ViewDataBinding, ItemModel, ModelClass : BaseSimpleCardListSRRVFragment.BaseModel<ItemModel>>(
    @LayoutRes private val itemLayoutId: Int,
    @Px private val hopeCardWidth: Int,
    modelClass: KClass<ModelClass>,
    // null: default R.attr.colorOnSurface at 2%
    private val dividerColor: Int? = null
) : BaseSRRVFragment() {
    abstract class BaseModel<ItemModel> : ViewModel() {
        val itemModel = MutableLiveData<List<ItemModel>>(emptyList())
    }

    val baseModel by createViewModelLazy(modelClass, { requireActivity().viewModelStore },
        { requireActivity().defaultViewModelProviderFactory })
    var items
        get() = baseModel.itemModel.value!!
        set(value) {
            baseModel.itemModel.value = value
        }

    @CallSuper
    override fun onRefresh(refreshLayout: RefreshLayout) {
        runIO {
            val n = onRefreshIO()
            runMain {
                if (n == null) srl.finishRefresh(false)
                else {
                    items = n
                    srl.finishRefresh()
                    adapter = adapter
                }
            }
        }
    }

    @CallSuper
    override fun onLoadMore(refreshLayout: RefreshLayout) {
        runIO {
            val n = onLoadMorIO()
            runMain {
                if (n == null) srl.finishLoadMore(false)
                else {
                    val oldCount = items.size
                    items = items + n
                    srl.finishLoadMore()
                    adapter!!.notifyItemRangeInserted(oldCount, n.size)
                }
            }
        }
    }

    override fun initLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(context, 1)
    }

    @CallSuper
    override fun initViews() {
        super.initViews()
        baseBinding.rv.addOnLayoutChangeListener { v, _, _, _, _, _, _, _, _ ->
            var a = (v.width - hopeCardWidth) / 2
            if (a < 0) a = 0
            if (a * 2 <= hopeCardWidth / 3) a = 0
            v.updatePadding(left = a, right = a)
        }
        runCatching { baseBinding.rv.removeItemDecorationAt(0) }
        baseBinding.rv.addItemDecoration(
            GridDividerDecoration(1.dpToPx(), dividerColor ?: ColorUtils.setAlphaComponent(requireContext().theme.getAttr(
                R.attr.colorOnSurface).data, (255 * 0.12).toInt()), 1), 0)
    }

    @CallSuper
    override fun initData() {
        adapter = Adapter()
        if (items.isEmpty()) srl.autoRefresh()
    }

    fun setNoMoreData(v: Boolean) {
        srl.setNoMoreData(v)
    }

    /* null 加载失败*/
    abstract suspend fun onRefreshIO(): List<ItemModel>?
    abstract suspend fun onLoadMorIO(): List<ItemModel>?
    abstract fun initItemView(itemBinding: ItemBinding, itemModel: ItemModel, position: Int)
    abstract fun initItemData(itemBinding: ItemBinding, itemModel: ItemModel, position: Int)

    inner class Adapter : BaseSimpleAdapter<ItemBinding>(requireContext(), itemLayoutId) {
        override fun initViews(baseBinding: ItemBinding, position: Int) {
            initItemView(baseBinding, items[position], position)
        }

        override fun initData(baseBinding: ItemBinding, position: Int) {
            initItemData(baseBinding, items[position], position)
        }

        override fun getItemCount() = items.size
    }
}