package com.duzhaokun123.bilibilihd2.ui.play.live

import android.content.Context
import android.content.Intent
import com.duzhaokun123.bilibilihd2.bases.BasePlayActivity
import com.duzhaokun123.bilibilihd2.ui.UrlOpenActivity

class LivePlayActivity: BasePlayActivity() {
    companion object {
        private const val EXTRA_CID = "cid"

        fun enter(context: Context, cid: Long) {

        }

        init {
            UrlOpenActivity.intentFilters.add {parsedIntent, context ->
                if (parsedIntent.host != "live") return@add null to null
                Intent(context, LivePlayActivity::class.java).apply {
                    putExtra(EXTRA_CID, parsedIntent.paths[0].toLong())
                } to "直播 ${parsedIntent.paths[0]}"
            }
        }
    }
}