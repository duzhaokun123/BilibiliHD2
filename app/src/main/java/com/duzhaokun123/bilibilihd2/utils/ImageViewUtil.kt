package com.duzhaokun123.bilibilihd2.utils

import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.LayoutIvOverlayBinding
import com.github.chrisbanes.photoview.PhotoView
import com.stfalcon.imageviewer.StfalconImageViewer
import java.io.File
import kotlin.math.max
import kotlin.math.min

object ImageViewUtil {
    fun ImageView.setBiliLevel(level: Int) {
        when (level) {
            0 -> this.setImageResource(R.drawable.ic_user_level_0)
            1 -> this.setImageResource(R.drawable.ic_user_level_1)
            2 -> this.setImageResource(R.drawable.ic_user_level_2)
            3 -> this.setImageResource(R.drawable.ic_user_level_3)
            4 -> this.setImageResource(R.drawable.ic_user_level_4)
            5 -> this.setImageResource(R.drawable.ic_user_level_5)
            6 -> this.setImageResource(R.drawable.ic_user_level_6)
        }
    }

    fun viewImage(pImage: String?, pImageView: ImageView) =
        viewImage(pImageView.context, pImage, pImageView)

    fun viewImage(context: Context, pImage: String?, pImageView: ImageView? = null) {
        if (pImage == null) return
        var siv: StfalconImageViewer<*>? = null
        val overlayBinding =
            DataBindingUtil.inflate<LayoutIvOverlayBinding>(
                LayoutInflater.from(context),
                R.layout.layout_iv_overlay,
                null,
                false
            )
        overlayBinding.tb.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.share -> {
                    runNewThread {
                        val shareUri: Uri
                        val srcFile = Glide.with(context).asFile().load(pImage).submit().get()
                        val shareFile = File(
                            context.cacheDir,
                            "shareImg${File.separatorChar}share.jpeg"
                        ).apply { parentFile!!.mkdirs() } // FIXME: 20-11-2 你凭什么认为一定是 jpeg 格式
                        srcFile.copyTo(shareFile, overwrite = true)
                        shareUri = FileProvider.getUriForFile(
                            context,
                            "com.duzhaokun123.bilibilihd2.fileprovider",
                            shareFile
                        )
                        context.startActivity(Intent.createChooser(Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_STREAM, shareUri)
                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            setDataAndType(shareUri, context.contentResolver.getType(shareUri))
                        }, context.getText(R.string.share_to)))
                    }
                    true
                }
                R.id.download -> {
                    DownloadUtil.downloadPicture(context, pImage)
                    true
                }
                R.id.close -> {
                    siv?.close()
                    true
                }
                else -> false
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(overlayBinding.tb) { v, insets ->
            insets.systemBars.let {
                v.updatePadding(left = it.left, right = it.right, top = it.top)
            }
            insets
        }
        StfalconImageViewer.Builder(context, arrayOf(pImage)) { imageView, image ->
            if (imageView is PhotoView) {
                Glide.with(context).load(image).addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        TipUtil.showToast(e?.message)
                        return true
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (resource is BitmapDrawable) {
                            runNewThread {
                                val bitmap =
                                    Glide.with(context).asBitmap().load(image).submit().get()
                                val sX = imageView.measuredWidth / bitmap.width.toFloat()
                                val sY = imageView.measuredHeight / bitmap.height.toFloat()
                                val scale = min(sX, sY)
                                runMain {
                                    imageView.apply {
                                        scaleType = ImageView.ScaleType.CENTER
                                        setImageBitmap(bitmap)
                                        maximumScale = max(5F, scale)
                                        mediumScale = (maximumScale + scale) / 2
                                        minimumScale = scale
                                        this@apply.scale = scale
                                    }
                                }
                            }
                        }
                        return false
                    }
                }).into(imageView)
            } else {
                Glide.with(context).load(image).into(imageView)
            }
        }
            .withHiddenStatusBar(false)
            .withTransitionFrom(pImageView)
            .withOverlayView(overlayBinding.root)
            .show().also { siv = it }
    }
}
