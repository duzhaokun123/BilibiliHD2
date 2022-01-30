package com.duzhaokun123.bilibilihd2.ui.main.dynamicholders

import android.content.Context
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.DynamicCardErrorBinding
import com.duzhaokun123.bilibilihd2.model.DynamicCardModel
import com.duzhaokun123.bilibilihd2.ui.main.DynamicAdapter

class DynamicHolderTypeError(context: Context) :
    DynamicAdapter.BaseDynamicHolder<DynamicCardErrorBinding, DynamicCardModel.TypeError>(context, R.layout.dynamic_card_error) {
    override fun initView(
        contentBinding: DynamicCardErrorBinding,
        model: DynamicCardModel,
        typedCard: DynamicCardModel.TypeError
    ) {

    }

    override fun initData(
        contentBinding: DynamicCardErrorBinding,
        model: DynamicCardModel,
        typedCard: DynamicCardModel.TypeError
    ) {
        contentBinding.model = typedCard
    }
}