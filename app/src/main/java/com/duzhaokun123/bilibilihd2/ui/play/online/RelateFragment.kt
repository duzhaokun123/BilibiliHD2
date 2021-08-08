package com.duzhaokun123.bilibilihd2.ui.play.online

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseFragment
import com.duzhaokun123.bilibilihd2.bases.BaseSimpleWithHeaderAdapter
import com.duzhaokun123.bilibilihd2.databinding.ItemRelateCardBinding
import com.duzhaokun123.bilibilihd2.databinding.LayoutRecycleViewBinding
import com.duzhaokun123.bilibilihd2.utils.*
import com.hiczp.bilibili.api.app.model.View as BiliView

class RelateFragment : BaseFragment<LayoutRecycleViewBinding>(R.layout.layout_recycle_view) {
    lateinit var relates: List<Relate>
    var header: View? = null
    val model by activityViewModels<OnlinePlayActivity.Model>()

    override fun initView() {
        baseBinding.rv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        baseBinding.rv.adapter = Adapter(requireContext()).apply {
            headerView = header ?: headerView
        }
    }

    override fun initData() {
        baseBinding.rv.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
            ) {
                outRect.set(0, 2.dpToPx(), 0, 2.dpToPx())
            }
        })
        model.relates.observe(this) {relates ->
            this.relates = relates
            baseBinding.rv.resetAdapter()
        }
    }

    inner class Adapter(context: Context) : BaseSimpleWithHeaderAdapter<ItemRelateCardBinding>(
        context, R.layout.item_relate_card
    ) {
        override val itemCountNoHeader: Int
            get() =
                relates.size

        override fun initView(baseBinding: ItemRelateCardBinding, position: Int) {

        }

        override fun initData(baseBinding: ItemRelateCardBinding, position: Int) {
            relates[position].let { relate ->
                baseBinding.relate = relate
                baseBinding.cv.setOnClickListener {
                    BrowserUtil.openInApp(context, relate.url)
                }
                baseBinding.cv.setOnLongClickListener {
                    baseBinding.ibTp.callOnClick()
                    true
                }
                baseBinding.ibTp.setOnClickListener {
                    PopupMenu(requireContext(), baseBinding.ibTp).apply {
                        menu.add("检查封面").setOnMenuItemClickListener {
                            ImageViewUtil.viewImage(requireContext(), relate.cover, baseBinding.ivCover)
                            true
                        }
                    }.show()
                }
            }
        }
    }

    override fun onApplyWindowInsetsCompat(insets: WindowInsetsCompat) {
        insets.maxSystemBarsDisplayCutout.let {
            baseBinding.rv.updatePadding(left = it.left, right = it.right , bottom = it.bottom)
        }
    }
}

data class Relate(
    val title: String?,
    val cover: String?,
    val duration: String,
    val l1: String,
    val l2: String,
    val url: String
) {
    companion object {
        fun parse(biliRelates: Collection<BiliView.Data.Relate>): List<Relate> {
            val re = mutableListOf<Relate>()
            biliRelates.forEach { biliRelate ->
                val title = biliRelate.title
                val cover = biliRelate.pic
                val duration = biliRelate.duration.takeIf { it != 0 }?.let { DateFormat.getStringForTime(it * 1000L) } ?: ""
                val l1 = biliRelate.owner?.name.takeUnless { it.isNullOrEmpty() }?.let { "up:$it" }?: "ad"
                val l2 = biliRelate.stat.view.takeIf { it != 0 }?.let { "play:$it danmaku:${biliRelate.stat.danmaku}" } ?: ""
                val url = biliRelate.uri
                re.add(Relate(title, cover, duration, l1, l2, url))
            }
            return re
        }
    }
}
