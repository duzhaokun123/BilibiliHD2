package com.duzhaokun123.bilibilihd2.ui.main.dynamicholders

import android.content.Context
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.DynamicCard64Binding
import com.duzhaokun123.bilibilihd2.model.DynamicCardModel
import com.duzhaokun123.bilibilihd2.ui.main.DynamicAdapter
import com.duzhaokun123.bilibilihd2.utils.BrowserUtil

class DynamicHolderType64(context: Context) :
    DynamicAdapter.BaseDynamicHolder<DynamicCard64Binding, DynamicCardModel.Type64>(
        context, R.layout.dynamic_card_64
    ) {
    override fun initView(
        contentBinding: DynamicCard64Binding,
        model: DynamicCardModel,
        typedCard: DynamicCardModel.Type64
    ) {

    }

    override fun initData(
        contentBinding: DynamicCard64Binding,
        model: DynamicCardModel,
        typedCard: DynamicCardModel.Type64
    ) {
        contentBinding.card = typedCard
    }

    override fun onCardClick(model: DynamicCardModel, typedCard: DynamicCardModel.Type64) {
        BrowserUtil.openInApp(context, "https://www.bilibili.com/read/cv${typedCard.id}")
    }
}