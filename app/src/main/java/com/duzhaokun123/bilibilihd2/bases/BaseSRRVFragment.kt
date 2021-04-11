package com.duzhaokun123.bilibilihd2.bases

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.FragmentBaseSrrvBinding
import com.scwang.smart.refresh.layout.api.RefreshLayout

/**
 * SRRV: SmartRefresh RecycleView
 */
abstract class BaseSRRVFragment :
    BaseFragment<FragmentBaseSrrvBinding>(R.layout.fragment_base_srrv) {
    var adapter
        get() = baseBinding.rv.adapter
        set(value) {
            baseBinding.rv.adapter = value
        }
    val srl get() = baseBinding.srl

    @CallSuper
    override fun initView() {
        baseBinding.srl.setOnLoadMoreListener(::onLoadMore)
        baseBinding.srl.setOnRefreshListener(::onRefresh)
        baseBinding.rv.layoutManager = initLayoutManager()
    }

    abstract fun onRefresh(refreshLayout: RefreshLayout)
    abstract fun onLoadMore(refreshLayout: RefreshLayout)
    abstract fun initLayoutManager(): RecyclerView.LayoutManager
}