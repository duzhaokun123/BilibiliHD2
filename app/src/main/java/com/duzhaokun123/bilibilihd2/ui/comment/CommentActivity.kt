package com.duzhaokun123.bilibilihd2.ui.comment

import android.widget.ArrayAdapter
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.bapis.bilibili.main.community.reply.v1.Mode
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.ActivityCommentBinding
import io.github.duzhaokun123.androidapptemplate.bases.BaseActivity
import io.github.duzhaokun123.androidapptemplate.utils.maxSystemBarsDisplayCutout
import io.github.duzhaokun123.androidapptemplate.utils.maxSystemBarsDisplayCutoutIme

class CommentActivity: BaseActivity<ActivityCommentBinding>(R.layout.activity_comment) {
    lateinit var rootCommentFragment: RootCommentFragment
    var mode = Mode.DEFAULT

    override fun initViews() {
        super.initViews()
        title = "评论"
        supportFragmentManager.beginTransaction().replace(R.id.fl_comment, RootCommentFragment().also { rootCommentFragment = it }).commit()
        baseBinding.spMode.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayOf(Mode.DEFAULT, Mode.UNSPECIFIED, Mode.MAIN_LIST_TIME, Mode.MAIN_LIST_HOT))
    }

    override fun initEvents() {
        super.initEvents()
        baseBinding.btnReload.setOnClickListener {
            rootCommentFragment.setOid(baseBinding.etOid.text.toString().toLong())
            rootCommentFragment.setType(baseBinding.etType.text.toString().toLong())
            rootCommentFragment.setMode(mode)
            rootCommentFragment.reload()
        }
        baseBinding.spMode.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {
            }

            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                mode = baseBinding.spMode.adapter.getItem(position) as Mode
            }
        }
    }

    override fun onApplyWindowInsetsCompat(insets: WindowInsetsCompat) {
        super.onApplyWindowInsetsCompat(insets)
        with(insets.maxSystemBarsDisplayCutoutIme) {
            baseBinding.flComment.updatePadding(left = left, right = right, bottom = bottom)
        }
    }
}