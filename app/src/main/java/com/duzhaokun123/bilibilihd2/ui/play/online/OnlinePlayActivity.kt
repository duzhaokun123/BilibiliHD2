package com.duzhaokun123.bilibilihd2.ui.play.online

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.RelativeLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BasePlayActivity
import com.duzhaokun123.bilibilihd2.databinding.LayoutOnlineplayIntroBinding
import com.duzhaokun123.bilibilihd2.ui.reply.RootReplyFragment
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.biliplayer.model.PlayInfo
import com.duzhaokun123.danmakuview.interfaces.DanmakuParser
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hiczp.bilibili.api.player.model.VideoPlayUrl
import com.hiczp.bilibili.api.app.model.View as BiliView

class OnlinePlayActivity : BasePlayActivity() {
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

    val aid by lazy { startIntent.getLongExtra(EXTRA_AID, 0) }
    var biliView: BiliView? = null
    var cid = 0L
    var page = 1
        set(value) {
            field = value
            onSetPage()
        }
    val pageParserMap = mutableMapOf<Int, DanmakuParser>()

    var relateFragment: RelateFragment? = null
    val layoutOnlinePlayIntroBinding by lazy {
        DataBindingUtil.inflate<LayoutOnlineplayIntroBinding>(
            layoutInflater, R.layout.layout_onlineplay_intro, null, false
        )
    }

    override fun initView() {
        super.initView()
        supportActionBar?.title = "av$aid"
        when (baseBinding.rhv.tag) {
            "1" -> {
                val tabLayoutId = View.generateViewId()
                val tabLayout = TabLayout(this).apply {
                    id = tabLayoutId
                }
                val viewPager2 = ViewPager2(this).apply {
                    adapter = PagerAdapter(this@OnlinePlayActivity)
                }
                baseBinding.rl.addView(tabLayout,
                    RelativeLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                        addRule(RelativeLayout.BELOW, R.id.rhv)
                        addRule(RelativeLayout.ALIGN_START, R.id.rhv)
                        addRule(RelativeLayout.ALIGN_END, R.id.rhv)
                    })
                baseBinding.rl.addView(viewPager2,
                    RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT).apply {
                        addRule(RelativeLayout.BELOW, tabLayoutId)
                        addRule(RelativeLayout.ALIGN_START, R.id.rhv)
                        addRule(RelativeLayout.ALIGN_END, R.id.rhv)
                        addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
                    })
                TabLayoutMediator(tabLayout, viewPager2) { tab, p ->
                    if (p == 0)
                        tab.text = "简介"
                    else
                        tab.text = "评论"
                }.attach()
            }
            "2" -> {
                baseBinding.rl.addView(layoutOnlinePlayIntroBinding.root,
                    RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT).apply {
                        addRule(RelativeLayout.BELOW, R.id.rhv)
                        addRule(RelativeLayout.ALIGN_START, R.id.rhv)
                        addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
                        addRule(RelativeLayout.ALIGN_END, R.id.rhv)
                    })
                val tabLayoutId = View.generateViewId()
                val tabLayout = TabLayout(this).apply {
                    id = tabLayoutId
                    ViewCompat.setOnApplyWindowInsetsListener(this) { v, vi ->
                        vi.systemBars.let {
                            v.updatePadding(top = it.top, right = it.right)
                        }
                        vi
                    }
                }
                val viewPager2 = ViewPager2(this).apply {
                    adapter = PagerAdapter(this@OnlinePlayActivity)
                }
                baseBinding.rl.addView(tabLayout,
                    RelativeLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                        addRule(RelativeLayout.END_OF, R.id.rhv)
                        addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE)
                    })
                baseBinding.rl.addView(viewPager2,
                    RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT).apply {
                        addRule(RelativeLayout.BELOW, tabLayoutId)
                        addRule(RelativeLayout.ALIGN_START, tabLayoutId)
                        addRule(RelativeLayout.ALIGN_END, tabLayoutId)
                        addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
                    })
                TabLayoutMediator(tabLayout, viewPager2) { tab, p ->
                    if (p == 0)
                        tab.text = "相关"
                    else
                        tab.text = "评论"
                }.attach()
            }
        }
    }

    override fun initData() {
        super.initData()
        if (biliView == null) {
            runIOCatchingResultRunMain(this, {
                bilibiliClient.appAPI.view(aid = aid).await()
            }) {
                biliView = it
                setCoverUrl(biliView!!.data.pic)
                supportActionBar?.title = biliView!!.data.title
                layoutOnlinePlayIntroBinding.biliView = biliView
                updateVideoPlayUrl()
            }
        }
    }

    override fun onGetShare() = biliView?.data?.title to "https://bilibili.com/video/$aid"

    override fun beforeReinitLayout() {
        super.beforeReinitLayout()
        layoutOnlinePlayIntroBinding.root.removeFromParent()
    }

    private fun updateVideoPlayUrl() {
        cid = biliView!!.data.pages[page - 1].cid
        runIOCatchingResultRunMain(this,
            { bilibiliClient.playerAPI.videoPlayUrl(cid = cid, aid = aid).await() })
        {
            setVideoPlayUrl(it)
        }
    }

    private fun setVideoPlayUrl(videoPlayUrl: VideoPlayUrl) {
        val title = biliView!!.data.title
        val pageTitle = biliView!!.data.pages[page - 1].part
        val hasAudio = videoPlayUrl.data.dash.audio != null
        val sources = mutableListOf<PlayInfo.Source>()
        videoPlayUrl.data.dash.video.forEach { video ->
            val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(video.baseUrl))
            val audioSource =
                if (hasAudio) ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(videoPlayUrl.data.dash.audio!![0].baseUrl))
                else null
            val mergedSource =
                if (hasAudio) MergingMediaSource(videoSource, audioSource!!) else videoSource
            val backups = mutableListOf<MediaSource>()
            video.backupUrl.forEach { backup ->
                val bv = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(backup))
                val mb = if (hasAudio) MergingMediaSource(bv, audioSource!!) else bv
                backups.add(mb)
            }
            val name = videoPlayUrl.data.acceptDescription.getOrNull(
                videoPlayUrl.data.acceptQuality.indexOf(video.id)
            ) ?: video.id.toString()
            sources.add(PlayInfo.Source(name, mergedSource, backups))
        }
        val danmakuParser = pageParserMap[page]
            ?: LazyCidDanmakuParser(aid, cid, biliView!!.data.pages[page - 1].duration).also {
                pageParserMap[page] = it
            }
        biliPlayerView.playInfo = PlayInfo(
            title, pageTitle, sources, danmakuParser, biliView!!.data.pages.size > page
        )
    }

    private fun onSetPage() {

    }

    inner class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount() = 2

        override fun createFragment(position: Int): Fragment {
            return if (position == 0)
                RelateFragment().apply {
                    if (this@OnlinePlayActivity.baseBinding.rhv.tag == "1")
                        header = layoutOnlinePlayIntroBinding.root
                    relates = emptyList()
                }.also {
                    relateFragment = it
                }
            else
                RootReplyFragment()
        }
    }

    override fun onApplyWindowInsetsCompat(insets: WindowInsetsCompat) {
        super.onApplyWindowInsetsCompat(insets)
        insets.systemBars.let {
            layoutOnlinePlayIntroBinding.llRoot.updatePadding(bottom = if (baseBinding.rhv.tag == "2") it.bottom else 0)
        }
    }
}