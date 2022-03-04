package com.duzhaokun123.bilibilihd2.ui.main.dynamicholders

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.core.view.updateLayoutParams
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.DynamicCard2Binding
import com.duzhaokun123.bilibilihd2.model.DynamicCardModel
import com.duzhaokun123.bilibilihd2.ui.main.DynamicAdapter
import com.duzhaokun123.bilibilihd2.utils.EmoteMap
import com.duzhaokun123.bilibilihd2.utils.ImageViewUtil
import com.duzhaokun123.bilibilihd2.utils.dpToPx

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
            typedCard.pictures.getOrNull(i)?.let {
                contentBinding.root.findViewWithTag<View>("$i").apply {
                    setOnClickListener {
                        ImageViewUtil.viewImage(context as Activity, typedCard.pictures, imageViews, i)
                    }
                    updateLayoutParams {
                        height = 120.dpToPx()
                    }
                }
            } ?: run {
                contentBinding.root.findViewWithTag<View>("$i").apply {
                    setOnClickListener(null)
                    updateLayoutParams {
                        height = 0
                    }
                }
            }
        }
    }

    override fun initData(
        contentBinding: DynamicCard2Binding,
        model: DynamicCardModel,
        typedCard: DynamicCardModel.Type2
    ) {
        contentBinding.card = typedCard
        EmoteMap.emotefy(typedCard.desc, contentBinding.tvDesc)
    }
}