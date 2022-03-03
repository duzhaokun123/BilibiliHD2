package com.duzhaokun123.bilibilihd2.ui.comment

import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.ActivityCommentBinding
import io.github.duzhaokun123.androidapptemplate.bases.BaseActivity
import io.github.duzhaokun123.androidapptemplate.utils.maxSystemBarsDisplayCutoutIme

class CommentActivity: BaseActivity<ActivityCommentBinding>(R.layout.activity_comment) {
    lateinit var rootCommentFragment: RootCommentFragment

    override fun initViews() {
        super.initViews()
        title = "评论"
        supportFragmentManager.beginTransaction().replace(R.id.fl_comment, RootCommentFragment().also { rootCommentFragment = it }).commit()
    }

    override fun initEvents() {
        super.initEvents()
        baseBinding.btnReload.setOnClickListener {
            rootCommentFragment.setOid(baseBinding.etOid.text.toString().toLong())
            rootCommentFragment.setType(baseBinding.etType.text.toString().toInt())
            rootCommentFragment.reload()
        }
    }

    override fun onApplyWindowInsetsCompat(insets: WindowInsetsCompat) {
        super.onApplyWindowInsetsCompat(insets)
        with(insets.maxSystemBarsDisplayCutoutIme) {
            baseBinding.flComment.updatePadding(left = left, right = right, bottom = bottom)
        }
    }
}