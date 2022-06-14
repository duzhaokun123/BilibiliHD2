package com.duzhaokun123.bilibilihd2.ui.play.live

import android.content.Context
import android.content.Intent
import com.duzhaokun123.bilibilihd2.bases.BasePlayActivity
import com.duzhaokun123.bilibilihd2.ui.UrlOpenActivity
import io.github.duzhaokun123.IntentFilter

class LivePlayActivity : BasePlayActivity() {
    companion object {
        private const val EXTRA_CID = "cid"

        @IntentFilter
        class LiveIntentFilter: UrlOpenActivity.IIntentFilter {
            override fun handle(
                parsedIntent: UrlOpenActivity.ParsedIntent,
                context: Context
            ): Pair<Intent?, String?> {
                 return when (parsedIntent.host) {
                    "live", "live.bilibili.com" ->
                        Intent(context, LivePlayActivity::class.java).apply {
                            putExtra(EXTRA_CID, parsedIntent.paths[0].toLong())
                        } to "直播 ${parsedIntent.paths[0]}"
                    else -> null to null
                }
            }
        }
    }
}