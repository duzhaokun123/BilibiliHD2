package com.duzhaokun123.bilibilihd2.ui.main.dynamicholders

import android.content.Context
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.DynamicCard4Binding
import com.duzhaokun123.bilibilihd2.model.DynamicCardModel
import com.duzhaokun123.bilibilihd2.ui.main.DynamicAdapter
import com.duzhaokun123.bilibilihd2.utils.EmoteMap

class DynamicHolderType4(context: Context) :
    DynamicAdapter.BaseDynamicHolder<DynamicCard4Binding, DynamicCardModel.Type4>(context, R.layout.dynamic_card_4) {
    override fun initView(
        contentBinding: DynamicCard4Binding,
        model: DynamicCardModel,
        typedCard: DynamicCardModel.Type4
    ) {

    }

    override fun initData(
        contentBinding: DynamicCard4Binding,
        model: DynamicCardModel,
        typedCard: DynamicCardModel.Type4
    ) {
        contentBinding.card = typedCard
        EmoteMap.emotefy(typedCard.content, contentBinding.tvContent)
    }
}