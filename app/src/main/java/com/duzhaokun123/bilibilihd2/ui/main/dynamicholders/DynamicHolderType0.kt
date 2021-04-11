package com.duzhaokun123.bilibilihd2.ui.main.dynamicholders

import android.content.Context
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.DynamicCard0Binding
import com.duzhaokun123.bilibilihd2.model.DynamicCardModel
import com.duzhaokun123.bilibilihd2.ui.main.DynamicAdapter

class DynamicHolderType0(context: Context) :
    DynamicAdapter.BaseDynamicHolder<DynamicCard0Binding, Any>(context, R.layout.dynamic_card_0) {
    override fun initView(
        contentBinding: DynamicCard0Binding, model: DynamicCardModel, typedCard: Any
    ) {}

    override fun initData(
        contentBinding: DynamicCard0Binding, model: DynamicCardModel, typedCard: Any
    ) {
        contentBinding.model = model
    }
}