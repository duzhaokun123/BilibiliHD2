package com.duzhaokun123.bilibilihd2.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updatePadding
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.ActivityBivBinding
import com.duzhaokun123.bilibilihd2.utils.getColorCompat
import com.duzhaokun123.bilibilihd2.utils.maxSystemBarsDisplayCutout
import com.duzhaokun123.bilibilihd2.utils.runMain
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import io.github.duzhaokun123.androidapptemplate.bases.BaseActivity
import kotlin.math.max
import kotlin.math.min

class BigImageViewActivity : BaseActivity<ActivityBivBinding>(
    R.layout.activity_biv,
    Config.TRANSPARENT_TOOL_BAR, Config.LAYOUT_NO_TOOL_BAR, Config.LAYOUT_MATCH_HORI
) {
    companion object {
        private const val EXTRA_URL = "url"

        fun enter(context: Context, url: String) {
            context.startActivity(Intent(context, BigImageViewActivity::class.java).apply {
                putExtra(EXTRA_URL, url)
            })
        }
    }

    override fun initViews() {
        title = null
        rootBinding.rootTb.navigationIcon?.setTint(getColorCompat(android.R.color.white))
        with(WindowInsetsControllerCompat(window, window.decorView)) {
            isAppearanceLightNavigationBars = false
            isAppearanceLightStatusBars = false
        }
    }

    override fun initData() {
        val url = startIntent.getStringExtra(EXTRA_URL) ?: return
        val imageView = baseBinding.pv
        Picasso.get().load(url).transform(object : Transformation {
            override fun transform(source: Bitmap): Bitmap {
                val sX = imageView.measuredWidth / source.width.toFloat()
                val sY = imageView.measuredHeight / source.height.toFloat()
                val scale = min(sX, sY)
                runMain {
                    imageView.apply {
                        scaleType = ImageView.ScaleType.CENTER
                        setImageBitmap(source)
                        maximumScale = max(5F, scale)
                        mediumScale = (maximumScale + scale) / 2
                        minimumScale = scale
                        this@apply.scale = scale
                    }
                }
                return source
            }

            override fun key() = "big_image_view"

        }).into(imageView)
    }

    override fun onApplyWindowInsetsCompat(insets: WindowInsetsCompat) {
        super.onApplyWindowInsetsCompat(insets)
        with(insets.maxSystemBarsDisplayCutout) {
            rootBinding.rootAbl.updatePadding(left = left, right = right)
        }
    }
}