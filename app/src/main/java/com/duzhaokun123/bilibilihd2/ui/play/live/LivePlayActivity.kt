package com.duzhaokun123.bilibilihd2.ui.play.live

import android.content.Context
import android.content.Intent
import com.duzhaokun123.bilibilihd2.bases.BasePlayActivity

class LivePlayActivity: BasePlayActivity() {
    companion object {
        private const val EXTRA_CID = "cid"

        fun enter(context: Context, cid: Long) {
            context.startActivity(Intent(context, LivePlayActivity::class.java).apply {
                putExtra(EXTRA_CID, cid)
            })
        }
    }
}