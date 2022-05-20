package com.duzhaokun123.bilibilihd2.utils

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.LayoutIvOverlayBinding
import com.duzhaokun123.bilibilihd2.ui.BigImageViewActivity
import com.squareup.picasso.Picasso
import com.stfalcon.imageviewer.StfalconImageViewer
import io.github.duzhaokun123.androidapptemplate.utils.TipUtil
import java.io.File

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

    fun viewImage(activity: Activity, imageUrl: String?, imageView: ImageView? = null) =
       viewImage(activity, listOf(imageUrl), listOf(imageView), 0)

    fun viewImage(activity: Activity, imageUrls: List<String?>, imageViews: List<ImageView?>, startPosition: Int) {
        var siv: StfalconImageViewer<*>? = null
        var position = startPosition
        val overlayBinding =
            DataBindingUtil.inflate<LayoutIvOverlayBinding>(
                LayoutInflater.from(activity),
                R.layout.layout_iv_overlay,
                null,
                false
            )
        overlayBinding.tb.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.share -> {
                    withShareImageUri(activity, imageUrls[position]) { uri ->
                        activity.startActivity(Intent.createChooser(Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_STREAM, uri)
                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            setDataAndType(uri, activity.contentResolver.getType(uri))
                        }, activity.getText(R.string.share_to)))
                    }
                    true
                }
                R.id.download -> {
                    imageUrls[position]?.let { it1 -> DownloadUtil.downloadPicture(activity, it1) }
                    true
                }
                R.id.close -> {
                    siv?.close()
                    true
                }
                R.id.open -> {
                    BigImageViewActivity.enter(activity, imageUrls[position]!!)
                    true
                }
                R.id.copy -> {
                    withShareImageUri(activity, imageUrls[position]) { uri ->
                        val clip = activity.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                        clip.setPrimaryClip(ClipData.newUri(activity.contentResolver, "image", uri))
                        TipUtil.showToast("已复制到剪贴板")
                    }
                    true
                }
                else -> false
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(overlayBinding.tb) { v, insets ->
            insets.maxSystemBarsDisplayCutout.let {
                v.updatePadding(left = it.left, right = it.right, top = it.top)
            }
            insets
        }

        StfalconImageViewer.Builder(activity, imageUrls) { imageView, imageUrl ->
            Picasso.get().load(imageUrl).into(imageView)
        }
            .withHiddenStatusBar(false)
            .withTransitionFrom(imageViews[position])
            .withOverlayView(overlayBinding.root)
            .withStartPosition(position)
            .withImageChangeListener {
                position = it
                siv!!.updateTransitionImage(imageViews[position])
            }
            .show().also { siv = it }
    }

    private fun withShareImageUri(context: Context, url: String?, block: (Uri) -> Unit) {
        url ?: return
        runNewThread {
            val file = Glide.with(context).asFile().load(url).submit().get()
            val shareFile = File(
                context.cacheDir, "shareImg${File.separatorChar}share.jpeg"
            ).apply { parentFile!!.mkdirs() }
            file.copyTo(shareFile, overwrite = true)
            val shareUri = FileProvider.getUriForFile(
                context, "com.duzhaokun123.bilibilihd2.fileprovider", shareFile
            )
            block(shareUri)
        }
    }
}
