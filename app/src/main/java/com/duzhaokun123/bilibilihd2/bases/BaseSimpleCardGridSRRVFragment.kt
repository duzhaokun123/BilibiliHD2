package com.duzhaokun123.bilibilihd2.bases

import android.graphics.Rect
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.Px
import androidx.core.view.updatePadding
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duzhaokun123.bilibilihd2.utils.dpToPx
import com.duzhaokun123.bilibilihd2.utils.runIO
import com.duzhaokun123.bilibilihd2.utils.runMain
import com.scwang.smart.refresh.layout.api.RefreshLayout
import kotlin.reflect.KClass

abstract class BaseSimpleCardGridSRRVFragment<ItemBinding : ViewDataBinding, ItemModel, ModelClass : BaseSimpleCardGridSRRVFragment.BaseModel<ItemModel>>(
    @LayoutRes private val itemLayoutId: Int,
    @Px private val hopeCardWidth: Int,
    modelClass: KClass<ModelClass>
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
    override fun initView() {
        super.initView()
        baseBinding.rv.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
            ) {
                outRect.set(2.dpToPx(), 2.dpToPx(), 2.dpToPx(), 2.dpToPx())
            }
        })
        baseBinding.rv.addOnLayoutChangeListener { v, _, _, _, _, _, _, _, _ ->
            val l = v.width / hopeCardWidth
            val lm = baseBinding.rv.layoutManager as GridLayoutManager
            if (l == 0) {
                lm.spanCount = 1
                v.updatePadding(left = 0, right = 0)
            } else {
                lm.spanCount = l
                val p = (v.width - hopeCardWidth * l) / 2
                if (l == 1 && 2 * p <= hopeCardWidth / 3) {
                    v.updatePadding(left = 0, right = 0)
                } else {
                    v.updatePadding(left = p, right = p)
                }
            }
        }
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
        override fun initView(baseBinding: ItemBinding, position: Int) {
            initItemView(baseBinding, items[position], position)
        }

        override fun initData(baseBinding: ItemBinding, position: Int) {
            initItemData(baseBinding, items[position], position)
        }

        override fun getItemCount() = items.size
    }
}