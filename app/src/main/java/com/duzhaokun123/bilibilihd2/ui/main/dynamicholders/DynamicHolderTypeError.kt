package com.duzhaokun123.bilibilihd2.ui.main.dynamicholders

import android.content.Context
import android.widget.TextView
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.DynamicCardErrorBinding
import com.duzhaokun123.bilibilihd2.model.DynamicCardModel
import com.duzhaokun123.bilibilihd2.ui.main.DynamicAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DynamicHolderTypeError(context: Context) :
    DynamicAdapter.BaseDynamicHolder<DynamicCardErrorBinding, DynamicCardModel.TypeError>(context, R.layout.dynamic_card_error) {
    override fun initView(
        contentBinding: DynamicCardErrorBinding,
        model: DynamicCardModel,
        typedCard: DynamicCardModel.TypeError
    ) {
        contentBinding.btnException.setOnClickListener {
            MaterialAlertDialogBuilder(context)
                .setTitle(typedCard.message)
                .setMessage(typedCard.stackTrace)
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .show()
                .findViewById<TextView>(R.id.message)?.setTextIsSelectable(true)
        }
        contentBinding.btnJson.setOnClickListener {
            MaterialAlertDialogBuilder(context)
                .setTitle("type: ${typedCard.type}")
                .setMessage(typedCard.cardJson)
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .show()
                .findViewById<TextView>(R.id.message)?.setTextIsSelectable(true)
        }
    }

    override fun initData(
        contentBinding: DynamicCardErrorBinding,
        model: DynamicCardModel,
        typedCard: DynamicCardModel.TypeError
    ) {
        contentBinding.model = typedCard
    }
}