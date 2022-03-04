package com.duzhaokun123.bilibilihd2.ui.main.dynamicholders

import android.content.Context
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.DynamicCard8Binding
import com.duzhaokun123.bilibilihd2.model.DynamicCardModel
import com.duzhaokun123.bilibilihd2.ui.main.DynamicAdapter
import com.duzhaokun123.bilibilihd2.utils.BrowserUtil
import com.duzhaokun123.bilibilihd2.utils.EmoteMap

class DynamicHolderType8(context: Context) :
    DynamicAdapter.BaseDynamicHolder<DynamicCard8Binding, DynamicCardModel.Type8>(context, R.layout.dynamic_card_8) {
    override fun onCardClick(model: DynamicCardModel, typedCard: DynamicCardModel.Type8) {
        BrowserUtil.openInApp(context, (model.card as DynamicCardModel.Type8).url)
    }

    override fun initView(
        contentBinding: DynamicCard8Binding, model: DynamicCardModel, typedCard: DynamicCardModel.Type8
    ) {

    }

    override fun initData(
        contentBinding: DynamicCard8Binding, model: DynamicCardModel, typedCard: DynamicCardModel.Type8
    ) {
        contentBinding.card = typedCard
        EmoteMap.emotefy(typedCard.dynamic, contentBinding.tvDynamic)
    }
}