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
        context, R.layout.dynamic_card_2
    ) {
    override fun initView(
        contentBinding: DynamicCard2Binding,
        model: DynamicCardModel,
        typedCard: DynamicCardModel.Type2
    ) {
        val imageViews = mutableListOf<ImageView>()
        (0..8).forEach {
            imageViews.add(contentBinding.root.findViewWithTag(it.toString()))
        }
        (0..8).forEach { i ->
            contentBinding.root.findViewWithTag<View>(i.toString()).setOnClickListener(
                typedCard.pictures.getOrNull(i)?.let {
                    View.OnClickListener {
                        ImageViewUtil.viewImage(context, typedCard.pictures, imageViews, i)
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