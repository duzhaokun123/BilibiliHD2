package com.duzhaokun123.bilibilihd2.ui.play.online

import android.content.Context
import android.content.Intent
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseActivity
import com.duzhaokun123.bilibilihd2.databinding.ActivityOnlinePlayBinding
import com.duzhaokun123.bilibilihd2.utils.toAid

class OnlinePlayActivity : BaseActivity<ActivityOnlinePlayBinding>(R.layout.activity_online_play) {
    companion object {
        private const val EXTRA_AID = "aid"

        fun enter(context: Context, aid: Long) {
            context.startActivity(Intent(context, OnlinePlayActivity::class.java).apply {
                putExtra(EXTRA_AID, aid)
            })
        }

        fun enter(context: Context, bvid: String) =
            enter(context, bvid.toAid())
    }

    override fun initView() {
        baseBinding.a.text = "av${startIntent.getLongExtra(EXTRA_AID, 0)}"
    }

    override fun initData() {

    }
}