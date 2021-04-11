package com.duzhaokun123.bilibilihd2.ui.main.dynamicholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.DynamicCard2Binding
import com.duzhaokun123.bilibilihd2.model.DynamicCardModel
import com.duzhaokun123.bilibilihd2.ui.main.DynamicAdapter
import com.duzhaokun123.bilibilihd2.utils.ImageViewUtil

class DynamicHolderType2(context: Context) :
    DynamicAdapter.BaseDynamicHolder<DynamicCard2Binding, DynamicCardModel.Type2>(
        context,
        R.layout.dynamic_card_2
    ) {
    override fun initView(
        contentBinding: DynamicCard2Binding,
        model: DynamicCardModel,
        typedCard: DynamicCardModel.Type2
    ) {
        (0..8).forEach {
            contentBinding.root.findViewWithTag<View>(it.toString()).setOnClickListener(
                typedCard.pictures.getOrNull(it)?.let { picture ->
                    View.OnClickListener { v ->
                        ImageViewUtil.viewImage(context, picture, v as ImageView)
                    }
                } ?: View.OnClickListener { contentBinding.iv0.callOnClick() }
            )
        }
    }

    override fun initData(
        contentBinding: DynamicCard2Binding,
        model: DynamicCardModel,
        typedCard: DynamicCardModel.Type2
    ) {
        contentBinding.card = typedCard
    }
}