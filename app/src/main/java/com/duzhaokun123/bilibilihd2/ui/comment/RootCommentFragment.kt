package com.duzhaokun123.bilibilihd2.ui.comment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.MutableLiveData
import com.bapis.bilibili.main.community.reply.v1.Mode
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseSimpleCardGridSRRVFragment
import com.duzhaokun123.bilibilihd2.databinding.ItemRootCommentBinding
import com.duzhaokun123.bilibilihd2.model.RootCommentCardModel
import com.duzhaokun123.bilibilihd2.model.toRootCommentCardModel
import com.duzhaokun123.bilibilihd2.ui.WebViewActivity
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.generated.Settings
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.github.duzhaokun123.androidapptemplate.utils.TipUtil
import io.github.duzhaokun123.androidapptemplate.utils.launch
import io.github.duzhaokun123.androidapptemplate.utils.onSuccess
import io.github.duzhaokun123.androidapptemplate.utils.runIOCatching

class RootCommentFragment @JvmOverloads constructor(private val setOid: Long = 0, private val setType: Long = 0) :
    BaseSimpleCardGridSRRVFragment<ItemRootCommentBinding, RootCommentCardModel, RootCommentFragment.RootCommentModel>(
        R.layout.item_root_comment,
        Settings.dynamicCardWidthDp.let { run { if (it == 0) 500 else it }.dpToPx() },
        RootCommentModel::class
    ) {
    class RootCommentModel: BaseModel<RootCommentCardModel>() {
        val next = MutableLiveData<Long>(0)
        val oid = MutableLiveData<Long?>(null)
        val type = MutableLiveData<Long?>(null)
        val mode = MutableLiveData(Mode.DEFAULT)
    }

    val next
        get() = baseModel.next.value!!
    val oid
        get() = baseModel.oid.value ?: setOid
    val type
        get() = baseModel.type.value ?: setType
    val mode
        get() = baseModel.mode.value!!

    override suspend fun onRefreshIO(): List<RootCommentCardModel>? {
        return runCatching { grpcClidet.reply.mainList(oid, type, 0, mode) }
            .commonOnFailureHandler(context)
            .onSuccess { r ->
                baseModel.next.postValue(r.cursor.next)
            }.getOrNull()?.repliesList?.toRootCommentCardModel()
    }

    override suspend fun onLoadMorIO(): List<RootCommentCardModel>? {
        return runCatching { grpcClidet.reply.mainList(oid, type, next, mode) }
            .commonOnFailureHandler(context)
            .onSuccess { r ->
                baseModel.next.postValue(r.cursor.next)
            }.getOrNull()?.repliesList?.toRootCommentCardModel()
    }

    override fun initViews() {
        super.initViews()
        val etId = View.generateViewId()
        EditText(requireContext()).apply {
            id = etId
        }.also {
            baseBinding.rl.addView(it, RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                addRule(RelativeLayout.CENTER_HORIZONTAL)
            })
        }
        baseBinding.srl.updateLayoutParams<RelativeLayout.LayoutParams> {
            addRule(RelativeLayout.ABOVE, etId)
        }
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
                                bilibiliClient.mainAPI.likeReply(1, oid, itemModel.rpid, type.toInt()).await()
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
                                bilibiliClient.mainAPI.dislikeReply(1, oid, itemModel.rpid, type.toInt()).await()
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
                                    1 -> bilibiliClient.mainAPI.likeReply(0, oid, itemModel.rpid, type.toInt()).await()
                                    2 -> bilibiliClient.mainAPI.dislikeReply(0, oid, itemModel.rpid, type.toInt()).await()
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
                            BrowserUtil.openInApp(requireContext(), "https://www.bilibili.com/h5/comment/report?pageType=${type.toInt()}&oid=$oid&rpid=${itemModel.rpid}")
                            true
                        }
                        R.id.menu_delete -> {
                            runIOCatching { bilibiliClient.mainAPI.delReply(type.toInt(), oid, itemModel.rpid).await() }
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
            TipUtil.showTip(requireContext(), "TODO")
        }
    }

    override fun initItemData(
        itemBinding: ItemRootCommentBinding,
        itemModel: RootCommentCardModel,
        position: Int
    ) {
        itemBinding.model = itemModel
    }

    fun setOid(oid: Long) {
        baseModel.oid.value = oid
    }

    fun setType(type: Long) {
        baseModel.type.value = type
    }

    fun setMode(mode: Mode) {
        baseModel.mode.value = mode
    }

    fun reload() {
        baseBinding.srl.autoRefresh()
    }
}