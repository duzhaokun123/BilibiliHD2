package com.duzhaokun123.bilibilihd2.bases

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseSimpleAdapter<BaseBinding : ViewDataBinding>(
    val context: Context,
    @LayoutRes val layoutId: Int
) : RecyclerView.Adapter<BaseSimpleAdapter.BaseBindVH<BaseBinding>>() {
    class BaseBindVH<BaseBinding : ViewDataBinding>(val baseBinding: BaseBinding) :
        RecyclerView.ViewHolder(baseBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindVH<BaseBinding> {
        val baseBind = DataBindingUtil.inflate<BaseBinding>(
            LayoutInflater.from(context), layoutId, null, false
        )
        return BaseBindVH(baseBind)
    }

    override fun onBindViewHolder(holder: BaseBindVH<BaseBinding>, position: Int) {
        initView(holder.baseBinding, position)
        initData(holder.baseBinding, position)
    }

    abstract fun initView(baseBinding: BaseBinding, position: Int)
    abstract fun initData(baseBinding: BaseBinding, position: Int)
}