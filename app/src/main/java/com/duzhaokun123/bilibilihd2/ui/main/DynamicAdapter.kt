package com.duzhaokun123.bilibilihd2.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.DynamicCardRootBinding
import com.duzhaokun123.bilibilihd2.model.DynamicCardModel
import com.duzhaokun123.bilibilihd2.model.TYPE_ERROR
import com.duzhaokun123.bilibilihd2.ui.main.dynamicholders.*
import com.duzhaokun123.bilibilihd2.utils.BrowserUtil
import com.duzhaokun123.bilibilihd2.utils.getColorCompat
import com.google.android.material.color.MaterialColors

class DynamicAdapter(private val dynamicFragment: DynamicFragment) :
    RecyclerView.Adapter<DynamicAdapter.BaseDynamicHolder<out ViewDataBinding, out Any>>() {
    companion object {
        /**
         * 在这里注册的必须实现`(Context)`构造器
         */
        val supportedTypes = mapOf(
            TYPE_ERROR to DynamicHolderTypeError::class.java,
            1 to DynamicHolderType1::class.java,
            2 to DynamicHolderType2::class.java,
            4 to DynamicHolderType4::class.java,
            8 to DynamicHolderType8::class.java,
            64 to DynamicHolderType64::class.java,
            512 to DynamicHolderType512::class.java,
            1024 to DynamicHolderType1024::class.java,
        )
    }

    val context
        get() = dynamicFragment.context

    val model by dynamicFragment.activityViewModels<DynamicFragment.DynamicModel>()

    val dynamicModel
        get() = model.dynamicModel.value!!

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseDynamicHolder<out ViewDataBinding, out Any> {
        return supportedTypes.getOrElse(
            viewType
        ) { DynamicHolderType0::class.java }.getConstructor(Context::class.java).newInstance(context)
    }

    override fun onBindViewHolder(
        holder: BaseDynamicHolder<out ViewDataBinding, out Any>,
        position: Int
    ) {
        holder.setModel(dynamicModel[position])
    }

    override fun getItemCount() = model.dynamicModel.value!!.size

    override fun getItemViewType(position: Int) = dynamicModel[position].type

    abstract class BaseDynamicHolder<ContentBinding : ViewDataBinding, TypedCard>(
        val context: Context,
        @LayoutRes contextLayoutId: Int,
        val rootBinding: DynamicCardRootBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.dynamic_card_root, null, false
        )
    ) : RecyclerView.ViewHolder(rootBinding.root) {
        val contentBinding: ContentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), contextLayoutId, rootBinding.flContent, true
        )

        fun setModel(model: DynamicCardModel) {
            rootBinding.model = model
            rootBinding.civFace.setOnClickListener {
                BrowserUtil.openInApp(context, "bilibili://space/${model.user.uid}")
            }
            rootBinding.tvName.setOnClickListener { rootBinding.civFace.callOnClick() }
            val typedCard = model.card as TypedCard
            rootBinding.cv.setOnClickListener { onCardClick(model, typedCard) }
            if (model.user.isVip)
                rootBinding.tvName.setTextColor(MaterialColors.harmonizeWithPrimary(context, context.getColorCompat(R.color.biliPink)))
            initView(contentBinding, model, typedCard)
            initData(contentBinding, model, typedCard)
        }


        abstract fun initView(
            contentBinding: ContentBinding,
            model: DynamicCardModel,
            typedCard: TypedCard
        )

        abstract fun initData(
            contentBinding: ContentBinding,
            model: DynamicCardModel,
            typedCard: TypedCard
        )

        open fun onCardClick(model: DynamicCardModel, typedCard: TypedCard) {}
    }
}