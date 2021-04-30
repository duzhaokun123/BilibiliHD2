package com.duzhaokun123.bilibilihd2.ui.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.updatePadding
import androidx.databinding.DataBindingUtil
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.ViewHolorBinding
import com.duzhaokun123.bilibilihd2.utils.BrowserUtil
import com.duzhaokun123.bilibilihd2.utils.dpToPx
import com.duzhaokun123.bilibilihd2.utils.isNightMode
import com.hiczp.bilibili.api.app.model.View

class HonorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    val binding: ViewHolorBinding

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.view_holor, this, true
        )
        updatePadding(10.dpToPx(), 10.dpToPx(), 10.dpToPx(), 10.dpToPx())
    }

    fun setHonor(honor: View.Data.Honor?) {
        if (honor == null) return
        binding.tvHonorText.text = honor.text
        binding.tvHonorTextExtra.text = honor.textExtra
        binding.tvHonorUrl.text = honor.urlText
        val textColor: Int
        val bgColor: Int
        val iconUrl: String
        if (isNightMode) {
            textColor = Color.parseColor(honor.textColorNight)
            bgColor = Color.parseColor(honor.bgColorNight)
            iconUrl = honor.iconNight
        } else {
            textColor = Color.parseColor(honor.textColor)
            bgColor = Color.parseColor(honor.bgColor)
            iconUrl = honor.icon
        }
        binding.tvHonorText.setTextColor(textColor)
        binding.tvHonorTextExtra.setTextColor(textColor)
        binding.tvHonorUrl.setTextColor(textColor)
        setBackgroundColor(bgColor)
        binding.ivHonorIcon.setImageUrl(iconUrl)
        setOnClickListener {
            BrowserUtil.openWebViewActivity(context, honor.url, interceptAll = true)
        }
    }
}