package com.duzhaokun123.bilibilihd2.bases

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.duzhaokun123.bilibilihd2.utils.removeFromParent

abstract class BaseSimpleWithHeaderAdapter<ItemBinding : ViewDataBinding>(
    val context: Context,
    @LayoutRes val layoutId: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
    }

    class BaseBindVH<BaseBinding : ViewDataBinding>(val baseBinding: BaseBinding) :
        RecyclerView.ViewHolder(baseBinding.root)

    inner class HeaderHolder(view: View): RecyclerView.ViewHolder(view)

    var headerHolder: HeaderHolder? = null
    var headerView: View = View(context)
        set(value) {
            field = value
            headerHolder?.apply {
                (itemView as FrameLayout).addView(value, 0)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            HeaderHolder(FrameLayout(context).apply { addView(headerView.apply { removeFromParent() }, 0) }).also { headerHolder = it }
        } else {
            val baseBind = DataBindingUtil.inflate<ItemBinding>(
                LayoutInflater.from(context), layoutId, null, false
            )
            BaseBindVH(baseBind)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position != 0) {
            initView((holder as BaseBindVH<ItemBinding>).baseBinding, position - 1)
            initData(holder.baseBinding, position - 1)
        }
    }

    override fun getItemCount() = itemCountNoHeader + 1

    override fun getItemViewType(position: Int) =  if (position != 0) TYPE_ITEM else TYPE_HEADER

    abstract val itemCountNoHeader: Int
    abstract fun initView(baseBinding: ItemBinding, position: Int)
    abstract fun initData(baseBinding: ItemBinding, position: Int)
}