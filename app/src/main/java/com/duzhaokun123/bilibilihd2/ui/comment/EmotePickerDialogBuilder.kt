package com.duzhaokun123.bilibilihd2.ui.comment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseSimpleAdapter
import com.duzhaokun123.bilibilihd2.databinding.DialogEmotePickerBinding
import com.duzhaokun123.bilibilihd2.databinding.ItemEmoteBinding
import com.duzhaokun123.bilibilihd2.databinding.LayoutRecycleViewBinding
import com.duzhaokun123.bilibilihd2.utils.EmoteMap
import com.duzhaokun123.bilibilihd2.utils.blurBackground
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import io.github.duzhaokun123.androidapptemplate.bases.BaseFragment

class EmotePickerDialogBuilder(activity: FragmentActivity) : MaterialAlertDialogBuilder(activity) {
    private val dialogEmotePickerBinding by lazy {
        DialogEmotePickerBinding.inflate(LayoutInflater.from(context))
    }
    private val groups = EmoteMap.getUserGroups()
    private var broadcastReceiver: BroadcastReceiver? = null

    var onEmoteSelected: ((emote: String) -> Unit)? = null

    init {
        setTitle("选择表情")
        setView(dialogEmotePickerBinding.root)
        dialogEmotePickerBinding.vp.adapter = PageAdapter(activity)
        TabLayoutMediator(dialogEmotePickerBinding.tl, dialogEmotePickerBinding.vp) { tab, position ->
            tab.text = groups[position]
        }.attach()
        setOnDismissListener {
            broadcastReceiver?.let {
                LocalBroadcastManager.getInstance(context).unregisterReceiver(it)
            }
        }
    }

    override fun show(): AlertDialog {
        val d = super.show()
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context, p1: Intent) {
                p1.getStringExtra("emote")?.let {
                    onEmoteSelected?.invoke(it)
                    d.dismiss()
                }
            }
        }
        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver!!, IntentFilter("emote_selected"))
        d.blurBackground()
        return d
    }

    inner class PageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount() = groups.size

        override fun createFragment(position: Int): Fragment {
            return EmoteListFragment(groups[position])
        }
    }

    class EmoteListFragment @JvmOverloads constructor(private val name: String = ""): BaseFragment<LayoutRecycleViewBinding>(R.layout.layout_recycle_view) {
        override fun initViews() {
            baseBinding.rv.layoutManager = GridLayoutManager(context, 5)
            baseBinding.rv.adapter = EmoteListAdapter(requireContext(), name)
        }
    }

    class EmoteListAdapter(context: Context, name: String): BaseSimpleAdapter<ItemEmoteBinding>(context, R.layout.item_emote) {
        private val emoteList = EmoteMap.getGroup(name)?.toList() ?: listOf()

        override fun initViews(baseBinding: ItemEmoteBinding, position: Int) {
            baseBinding.ll.setOnClickListener {
                LocalBroadcastManager.getInstance(context).sendBroadcast(Intent("emote_selected").putExtra("emote", emoteList[position].first))
            }
        }

        override fun initData(baseBinding: ItemEmoteBinding, position: Int) {
            baseBinding.tvEmote.text = emoteList[position].first
            baseBinding.ivEmote.setImageUrl(emoteList[position].second)
        }

        override fun getItemCount() = emoteList.size

    }
}