package com.duzhaokun123.bilibilihd2.ui.main.dynamicholders

import android.content.Context
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.DynamicCard512Binding
import com.duzhaokun123.bilibilihd2.model.DynamicCardModel
import com.duzhaokun123.bilibilihd2.ui.main.DynamicAdapter
import com.duzhaokun123.bilibilihd2.utils.BrowserUtil

class DynamicHolderType512(context: Context) : DynamicAdapter.BaseDynamicHolder<DynamicCard512Binding, DynamicCardModel.Type512>(
context, R.layout.dynamic_card_512
) {
    override fun initView(
        contentBinding: DynamicCard512Binding,
        model: DynamicCardModel,
        typedCard: DynamicCardModel.Type512
    ) {

    }

    override fun initData(
        contentBinding: DynamicCard512Binding,
        model: DynamicCardModel,
        typedCard: DynamicCardModel.Type512
    ) {
        contentBinding.card = typedCard
    }

    override fun onCardClick(model: DynamicCardModel, typedCard: DynamicCardModel.Type512) {
        BrowserUtil.openInApp(context, typedCard.url)
    }
}