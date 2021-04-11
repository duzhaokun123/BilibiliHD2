package com.duzhaokun123.bilibilihd2.ui.play.live

import android.content.Context
import android.content.Intent
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseActivity
import com.duzhaokun123.bilibilihd2.databinding.ActivityOnlinePlayBinding

class LivePlayActivity: BaseActivity<ActivityOnlinePlayBinding>(R.layout.activity_online_play) {
    companion object {
        private const val EXTRA_CID = "cid"

        fun enter(context: Context, cid: Long) {
            context.startActivity(Intent(context, LivePlayActivity::class.java).apply {
                putExtra(EXTRA_CID, cid)
            })
        }
    }
    override fun initView() {
        baseBinding.a.text = "cid${startIntent.getLongExtra(EXTRA_CID, 0)}"
    }

    override fun initData() {

    }
}