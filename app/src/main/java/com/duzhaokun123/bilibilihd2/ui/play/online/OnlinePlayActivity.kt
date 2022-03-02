package com.duzhaokun123.bilibilihd2.ui.play.online

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
import androidx.core.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.duzhaokun123.annotationProcessor.IntentFilter
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BasePlayActivity
import com.duzhaokun123.bilibilihd2.databinding.LayoutOnlineplayIntroBinding
import com.duzhaokun123.bilibilihd2.ui.UrlOpenActivity
import com.duzhaokun123.bilibilihd2.ui.comment.RootCommentFragment
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.biliplayer.model.PlayInfo
import com.duzhaokun123.danmakuview.interfaces.DanmakuParser
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hiczp.bilibili.api.player.model.VideoPlayUrl
import io.github.duzhaokun123.androidapptemplate.utils.TipUtil
import io.github.duzhaokun123.androidapptemplate.utils.maxSystemBarsDisplayCutoutIme
import com.hiczp.bilibili.api.app.model.View as BiliView

class OnlinePlayActivity : BasePlayActivity() {
    companion object {
        private const val EXTRA_AID = "aid"

        @IntentFilter
        class VideoIntentHandler: UrlOpenActivity.IIntentFilter {
            override fun handle(
                parsedIntent: UrlOpenActivity.ParsedIntent,
                context: Context
            ): Pair<Intent?, String?> {
                if (parsedIntent.host !in  listOf("video", "story")) return null to null
                val p1 = parsedIntent.paths.getOrElse(0) { "0" }
                val aid = try {
                    p1.toLong()
                } catch (e: Exception) {
                    p1.toAid()
                }
                return Intent(context, OnlinePlayActivity::class.java).apply {
                    putExtra(EXTRA_AID, aid)
                } to "视频 $aid"
            }
        }

        @IntentFilter
        class VideoIntentHandler2: UrlOpenActivity.IIntentFilter {
            override fun handle(
                parsedIntent: UrlOpenActivity.ParsedIntent,
                context: Context
            ): Pair<Intent?, String?> {
                if (parsedIntent.host != "www.bilibili.com" || parsedIntent.paths.getOrNull(0) != "video") return null to null
                val p1 = parsedIntent.paths.getOrElse(1) { "0" }
                val aid = try {
                    p1.substring(2).toLong()
                } catch (e: Exception) {
                    p1.toAid()
                }
                return Intent(context, OnlinePlayActivity::class.java).apply {
                    putExtra(EXTRA_AID, aid)
                } to "视频 $aid"
            }
        }
    }

    class Model : ViewModel() {
        val relates = MutableLiveData<List<Relate>>(emptyList())
        val biliView = MutableLiveData<BiliView?>(null)
    }

    val aid by lazy { startIntent.getLongExtra(EXTRA_AID, 0) }
    var biliView
        get() = model.biliView.value
        set(value) {
            model.biliView.value = value
        }
    var cid = 0L
    var page = 1
        set(value) {
            if (field != value) {
                field = value
                onSetPage()
            }
        }
    val pageParserMap = mutableMapOf<Int, DanmakuParser>()

    var relateFragment: RelateFragment? = null
    val layoutOnlinePlayIntroBinding by lazy {
        DataBindingUtil.inflate<LayoutOnlineplayIntroBinding>(
            layoutInflater, R.layout.layout_onlineplay_intro, null, false
        )
    }
    lateinit var viewPager2: ViewPager2
    val model by viewModels<Model>()

    override fun initViews() {
        super.initViews()
        supportActionBar?.title = "av$aid"
        when (baseBinding.rhv.tag) {
            "1" -> {
                val tabLayoutId = View.generateViewId()
                val tabLayout = TabLayout(this).apply {
                    id = tabLayoutId
                }
                if (layoutOnlinePlayIntroBinding.root.layoutParams != null)
                    layoutOnlinePlayIntroBinding.root.updateLayoutParams {
                        width = MATCH_PARENT
                        height = WRAP_CONTENT
                    }
                viewPager2 = ViewPager2(this).apply {
                    adapter = PagerAdapter(this@OnlinePlayActivity)
                }
                baseBinding.rl.addView(tabLayout,
                    ConstraintLayout.LayoutParams(MATCH_CONSTRAINT, WRAP_CONTENT).apply {
                        topToBottom = R.id.rhv
                        startToStart = R.id.rhv
                        endToEnd = R.id.rhv
                    })
                baseBinding.rl.addView(viewPager2,
                    ConstraintLayout.LayoutParams(MATCH_CONSTRAINT, MATCH_CONSTRAINT).apply {
                        topToBottom = tabLayoutId
                        startToStart = R.id.rhv
                        endToEnd = R.id.rhv
                        bottomToBottom = PARENT_ID
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
                    ConstraintLayout.LayoutParams(MATCH_CONSTRAINT, MATCH_CONSTRAINT).apply {
                        topToBottom = R.id.rhv
                        startToStart = R.id.rhv
                        endToEnd = R.id.rhv
                        bottomToBottom = PARENT_ID
                    })
                val tabLayoutId = View.generateViewId()
                val tabLayout = TabLayout(this).apply {
                    id = tabLayoutId
                    ViewCompat.setOnApplyWindowInsetsListener(this) { v, vi ->
                        vi.maxSystemBarsDisplayCutout.let {
                            v.updatePadding(top = it.top, right = it.right)
                        }
                        vi
                    }
                }
                viewPager2 = ViewPager2(this).apply {
                    adapter = PagerAdapter(this@OnlinePlayActivity)
                }
                baseBinding.rl.addView(tabLayout,
                    ConstraintLayout.LayoutParams(MATCH_CONSTRAINT, WRAP_CONTENT).apply {
                        startToEnd = R.id.rhv
                        endToEnd = PARENT_ID
                    })
                baseBinding.rl.addView(viewPager2,
                    ConstraintLayout.LayoutParams(MATCH_CONSTRAINT, MATCH_CONSTRAINT).apply {
                        topToBottom = tabLayoutId
                        startToStart = tabLayoutId
                        endToEnd = tabLayoutId
                        bottomToBottom = PARENT_ID
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
            }) { biliView ->
                this.biliView = biliView
                setCoverUrl(biliView.data.pic)
                supportActionBar?.title = biliView.data.title

                layoutOnlinePlayIntroBinding.biliView = biliView
                biliView.data.tag.forEach { tag ->
                    layoutOnlinePlayIntroBinding.cgTags.addView(Chip(this, null, R.attr.filterChip).apply {
                        text = tag.tagName
                        isCheckable = false
                        setOnClickListener {
                            BrowserUtil.openInApp(
                                this@OnlinePlayActivity,
                                "https://www.bilibili.com/v/channel/${tag.tagId}"
                            )
                        }
                    }, WRAP_CONTENT, WRAP_CONTENT)
                }
                biliView.data.pages.forEach { p ->
                    layoutOnlinePlayIntroBinding.rgPages.addView(RadioButton(this).apply {
                        text = p.part
                        id = p.page
                        buttonDrawable = null
                        setBackgroundResource(R.drawable.rb_video_page_bg)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            setTextColor(resources.getColorStateList(R.color.rb_video_page_text, theme))
                        }
                        setPadding(10.dpToPx())
                    }, ViewGroup.MarginLayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                        rightMargin = 5.dpToPx()
                    })
                    if (p.page == page)
                        layoutOnlinePlayIntroBinding.rgPages.check(p.page)
                }
                layoutOnlinePlayIntroBinding.rgPages.setOnCheckedChangeListener { _, page ->
                    this.page = page
                }
                layoutOnlinePlayIntroBinding.tvLike.apply {
                    text = biliView.data.stat.like.toString()
                    setOnClickListener {
                        runIOCatchingResultRunMain(this@OnlinePlayActivity,
                            { bilibiliClient.appAPI.like(aid = aid, like = 0).await()}) {
                            TipUtil.showTip(this@OnlinePlayActivity, it.data.toast)
                        }
                    }
                }
                layoutOnlinePlayIntroBinding.tvDislike.setOnClickListener {
                    runIOCatchingResultRunMain(this@OnlinePlayActivity,
                        { bilibiliClient.appAPI.dislike(aid = aid, dislike = 0).await()}) {}
                }
                layoutOnlinePlayIntroBinding.rvUp.setOnClickListener {
                    BrowserUtil.openInApp(this, "bilibili://space/${biliView.data.owner.mid}")
                }
                model.relates.value = Relate.parse(biliView.data.relates?: emptyList())

                updateVideoPlayUrl()
            }
        } else
            supportActionBar?.title = biliView!!.data.title
    }

    override fun onGetShare() = biliView?.data?.title to "https://bilibili.com/video/av$aid"

    override fun beforeReinitLayout() {
        super.beforeReinitLayout()
        layoutOnlinePlayIntroBinding.root.removeFromParent()
    }

    override fun onNextClick() {
        if (page <= biliView!!.data.pages.size) {
            page++
            updateVideoPlayUrl()
        }
    }

    private fun updateVideoPlayUrl() {
        cid = biliView!!.data.pages[page - 1].cid
        runIOCatchingResultRunMain(this,
            { bilibiliClient.playerAPI.videoPlayUrl(cid = cid, aid = aid).await() })
        {
            setVideoPlayUrl(it)
            if (biliPlayerView.player.isPlaying)
                start()
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
        if (hasAudio)
            sources.add(
                PlayInfo.Source("audio only",
                    ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(MediaItem.fromUri(videoPlayUrl.data.dash.audio!![0].baseUrl)),
                    emptyList()
                )
            )

        val danmakuParser = pageParserMap[page]
            ?: LazyCidDanmakuParser(aid, cid, biliView!!.data.pages[page - 1].duration).also {
                pageParserMap[page] = it
            }
        biliPlayerView.playInfo = PlayInfo(
            title, pageTitle, sources, danmakuParser, biliView!!.data.pages.size > page
        )
    }

    private fun onSetPage() {
        updateVideoPlayUrl()
        layoutOnlinePlayIntroBinding.rgPages.check(page)
    }

    inner class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount() = 2

        override fun createFragment(position: Int): Fragment {
            return if (position == 0)
                RelateFragment().apply {
                    if (this@OnlinePlayActivity.baseBinding.rhv.tag == "1")
                        header = layoutOnlinePlayIntroBinding.root
                }.also {
                    relateFragment = it
                }
            else
                RootCommentFragment(aid, 1)
        }
    }

    override fun onApplyWindowInsetsCompat(insets: WindowInsetsCompat) {
        super.onApplyWindowInsetsCompat(insets)
        insets.maxSystemBarsDisplayCutout.let {
            layoutOnlinePlayIntroBinding.llRoot.updatePadding(bottom = if (baseBinding.rhv.tag == "2") it.bottom else 0)
        }
        with(insets.maxSystemBarsDisplayCutoutIme) {
            viewPager2.updatePadding(left = left, right = right, bottom = bottom)
        }
    }

    override fun onFirstPlay() {
        addHistory()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (played)
            addHistory()
    }

    private fun addHistory() {
        val time = biliPlayerView.player.contentPosition / 1000
        runIOCatchingResultRunMain(this,
            {bilibiliClient.webAPI.heartbeat(aid, cid = cid, playedTime = time).await()}) {}
    }
}