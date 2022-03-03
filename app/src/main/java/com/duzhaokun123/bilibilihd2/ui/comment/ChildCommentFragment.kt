package com.duzhaokun123.bilibilihd2.ui.comment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.MutableLiveData
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseSimpleCardListSRRVFragment
import com.duzhaokun123.bilibilihd2.databinding.ItemRootCommentBinding
import com.duzhaokun123.bilibilihd2.databinding.LayoutSendChildCommentBinding
import com.duzhaokun123.bilibilihd2.model.RootCommentCardModel
import com.duzhaokun123.bilibilihd2.model.toRootCommentCardModel
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.generated.Settings
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.github.duzhaokun123.androidapptemplate.utils.TipUtil
import io.github.duzhaokun123.androidapptemplate.utils.launch
import io.github.duzhaokun123.androidapptemplate.utils.onSuccess
import io.github.duzhaokun123.androidapptemplate.utils.runIOCatching

class ChildCommentFragment @JvmOverloads constructor(private val setOid: Long = 0, private val setType: Int = 0, private val setRoot: Long = 0):
    BaseSimpleCardListSRRVFragment<ItemRootCommentBinding, RootCommentCardModel, ChildCommentFragment.ChildCommentModel>(
        R.layout.item_root_comment,
        Settings.dynamicCardWidthDp.let { run { if (it == 0) 500 else it }.dpToPx() },
        ChildCommentModel::class
    ) {
    class ChildCommentModel: BaseModel<RootCommentCardModel>() {
        val next = MutableLiveData<Long>(0)
        val oid = MutableLiveData<Long?>(null)
        val type = MutableLiveData<Int?>(null)
        val root = MutableLiveData<Long?>(null)
    }
    val next
        get() = baseModel.next.value ?: 0
    val oid
        get() = baseModel.oid.value ?: setOid
    val type
        get() = baseModel.type.value ?: setType
    val root
        get() = baseModel.root.value ?: setRoot
    private lateinit var layoutSendChildCommentBinding: LayoutSendChildCommentBinding

    override suspend fun onRefreshIO(): List<RootCommentCardModel>? {
        return runCatching { grpcClidet.reply.detailList(oid, type, root, 0) }
            .commonOnFailureHandler(context)
            .onSuccess { r ->
                baseModel.next.postValue(r.cursor.next)
                if (r.cursor.next == 0L)
                    runMain { srl.setNoMoreData(true) }
            }.getOrNull()?.root?.toRootCommentCardModel()
    }

    override suspend fun onLoadMorIO(): List<RootCommentCardModel>? {
        return runCatching { grpcClidet.reply.detailList(oid, type, root, next) }
            .commonOnFailureHandler(context)
            .onSuccess { r ->
                baseModel.next.postValue(r.cursor.next)
                if (r.cursor.next == 0L)
                    runMain { srl.setNoMoreData(true) }
            }.getOrNull()?.root?.repliesList?.toRootCommentCardModel()
    }

    override fun findViews() {
        layoutSendChildCommentBinding = LayoutSendChildCommentBinding.inflate(layoutInflater)
        baseBinding.rl.addView(layoutSendChildCommentBinding.root, RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        })
        baseBinding.srl.updateLayoutParams<RelativeLayout.LayoutParams> {
            addRule(RelativeLayout.ABOVE, layoutSendChildCommentBinding.root.id)
        }
    }

    override fun initViews() {
        super.initViews()
        baseBinding.root.setBackgroundColor(requireActivity().theme.getAttr(android.R.attr.colorBackground).data)
    }

    override fun initEvents() {
        super.initEvents()
        baseBinding.root.setOnTouchListener { _, _ -> true }
        layoutSendChildCommentBinding.btnSend.setOnClickListener {
            val message = layoutSendChildCommentBinding.etComment.text.toString()
            runIOCatching {
                bilibiliClient.mainAPI.sendReply(message = message, oid = oid, type = type,  root = root, parent = layoutSendChildCommentBinding.tvParent.text.toString().substring(3).toLong()).await()
            }.setCommonOnFailureHandler(context)
                .onSuccess {
                    runMain {
                        layoutSendChildCommentBinding.etComment.setText("")
                        srl.autoRefresh()
                    }
                }.launch()
        }
    }

    override fun initData() {
        if (items.isNotEmpty() && (baseModel.oid.value == null || baseModel.root.value == null || baseModel.type.value == null)) items = emptyList()
        super.initData()
        layoutSendChildCommentBinding.tvParent.text = "to $setRoot"
    }

    override fun initItemView(
        itemBinding: ItemRootCommentBinding,
        itemModel: RootCommentCardModel,
        position: Int
    ) {
        itemBinding.civFace.setOnClickListener {
            BrowserUtil.openInApp(requireContext(), "https://space.bilibili.com/${itemModel.userModel.uid}")
        }
        itemBinding.tvName.setOnClickListener {
            itemBinding.civFace.callOnClick()
        }
        itemBinding.cv.setOnLongClickListener {
            itemBinding.ibTp.callOnClick()
            true
        }
        itemBinding.ibTp.setOnClickListener { view ->
            PopupMenu(requireContext(), view).apply {
                inflate(R.menu.root_comment_menu)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.menu_like -> {
                            runIOCatching {
                                bilibiliClient.mainAPI.likeReply(1, oid, itemModel.rpid, type).await()
                            }.setCommonOnFailureHandler(context)
                                .onSuccess {
                                    itemModel.likeStatus = 1
                                    itemModel.like += 1
                                    runMain {
                                        adapter?.notifyItemChanged(position)
                                    }
                                }.launch()
                            true
                        }
                        R.id.menu_dislike -> {
                            runIOCatching {
                                bilibiliClient.mainAPI.dislikeReply(1, oid, itemModel.rpid, type).await()
                            }.setCommonOnFailureHandler(context)
                                .onSuccess {
                                    if (itemModel.likeStatus == 1)
                                        itemModel.like -= 1
                                    itemModel.likeStatus = 2
                                    runMain {
                                        adapter?.notifyItemChanged(position)
                                    }
                                }.launch()
                            true
                        }
                        R.id.menu_cancel -> {
                            runIOCatching {
                                when(itemModel.likeStatus) {
                                    1 -> bilibiliClient.mainAPI.likeReply(0, oid, itemModel.rpid, type).await()
                                    2 -> bilibiliClient.mainAPI.dislikeReply(0, oid, itemModel.rpid, type).await()
                                    else -> TipUtil.showTip(requireContext(), "无效操作")
                                }
                            }.setCommonOnFailureHandler(context)
                                .onSuccess {
                                    if (itemModel.likeStatus == 1)
                                        itemModel.like -= 1
                                    itemModel.likeStatus = 0
                                    runMain {
                                        adapter?.notifyItemChanged(position)
                                    }
                                }.launch()
                            true
                        }
                        R.id.menu_report -> {
                            BrowserUtil.openInApp(requireContext(), "https://www.bilibili.com/h5/comment/report?pageType=${type}&oid=$oid&rpid=${itemModel.rpid}")
                            true
                        }
                        R.id.menu_delete -> {
                            runIOCatching { bilibiliClient.mainAPI.delReply(type, oid, itemModel.rpid).await() }
                                .setCommonOnFailureHandler(context)
                                .onSuccess {
                                    (baseModel.itemModel.value!! as? MutableList)?.removeAt(position)
                                    runMain {
                                        adapter?.notifyItemRemoved(position)
                                    }
                                }.launch()
                            true
                        }
                        R.id.menu_copy -> {
                            MaterialAlertDialogBuilder(requireContext())
                                .setTitle("复制")
                                .setMessage(itemModel.content)
                                .setPositiveButton("复制全部") { _, _ ->
                                    val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                    val clip = ClipData.newPlainText("reply", itemModel.content)
                                    clipboard.setPrimaryClip(clip)
                                    TipUtil.showTip(requireContext(), "已复制")
                                }
                                .setNegativeButton("取消", null)
                                .show()
                                .findViewById<TextView>(android.R.id.message)?.setTextIsSelectable(true)
                            true
                        }
                        else -> false
                    }
                }
                show()
            }
        }
        itemBinding.cv.setOnClickListener {
            layoutSendChildCommentBinding.tvParent.text = "to ${itemModel.rpid}"
        }
    }

    override fun initItemData(
        itemBinding: ItemRootCommentBinding,
        itemModel: RootCommentCardModel,
        position: Int
    ) {
        itemBinding.model = itemModel
    }
}