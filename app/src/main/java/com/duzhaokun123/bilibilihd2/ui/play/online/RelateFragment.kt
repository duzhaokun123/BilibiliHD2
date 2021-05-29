package com.duzhaokun123.bilibilihd2.ui.play.online

import android.content.Context
import android.graphics.Rect
import android.view.View
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
import com.duzhaokun123.bilibilihd2.utils.dpToPx
import com.duzhaokun123.bilibilihd2.utils.maxSystemBarsDisplayCutout
import com.duzhaokun123.bilibilihd2.utils.resetAdapter
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
                outRect.set(0, 5.dpToPx(), 0, 5.dpToPx())
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
            baseBinding.relate = relates[position]
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
    val cover: String?
) {
    companion object {
        fun parse(biliRelates: Collection<BiliView.Data.Relate>): List<Relate> {
            val re = mutableListOf<Relate>()
            biliRelates.forEach { biliRelate ->
                val title = biliRelate.title
                val cover = biliRelate.pic
                re.add(Relate(title, cover))
            }
            return re
        }
    }
}
