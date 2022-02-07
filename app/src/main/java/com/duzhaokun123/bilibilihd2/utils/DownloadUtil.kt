package com.duzhaokun123.bilibilihd2.utils

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.FileUtils
import android.provider.MediaStore
import com.bumptech.glide.Glide
import com.duzhaokun123.bilibilihd2.R
import io.github.duzhaokun123.androidapptemplate.utils.TipUtil
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

object DownloadUtil {
    fun downloadPicture(context: Context, url: String) {
        runNewThread {
            var exception: Exception? = null
            var success = false
            val inputStream = FileInputStream(Glide.with(context).asFile().load(url).submit().get())
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                try {
                    FileOutputStream(File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "bilibili HD${File.separatorChar}${System.currentTimeMillis()}").apply {
                        parentFile!!.mkdirs()
                    }).use {
                        inputStream.copyTo(it)
                        success = true
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    exception = e
                } finally {
                    inputStream.close()
                }
            } else {
                val resolver = context.contentResolver
                val pictureCollection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
                val pictureValues = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, System.currentTimeMillis().toString())
                    put(MediaStore.Images.Media.RELATIVE_PATH, "${Environment.DIRECTORY_PICTURES}/bilibili HD")
                    put(MediaStore.Images.Media.IS_PENDING, 1)
                    put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg") // FIXME: 20-12-2 为什么一定是 jpeg
                }
                val pictureContentUri = resolver.insert(pictureCollection, pictureValues)!!
                resolver.openFileDescriptor(pictureContentUri, "w", null)!!.use {
                    FileUtils.copy(inputStream.fd, it.fileDescriptor)
                }
                pictureValues.clear()
                pictureValues.put(MediaStore.Images.Media.IS_PENDING, 0)
                resolver.update(pictureContentUri, pictureValues, null, null)
                success = true
            }
            if (success) {
                runMain { TipUtil.showToast(R.string.saved) }
            } else {
                runMain {
                    TipUtil.showToast(exception?.message ?: "unknown exception")
                }
            }
        }
    }
}