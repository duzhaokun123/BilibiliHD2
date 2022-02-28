package com.duzhaokun123.bilibilihd2.ui.comment

import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.MutableLiveData
import com.bapis.bilibili.main.community.reply.v1.Mode
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseSimpleCardGridSRRVFragment
import com.duzhaokun123.bilibilihd2.databinding.ItemRootCommentBinding
import com.duzhaokun123.bilibilihd2.model.RootCommentCardModel
import com.duzhaokun123.bilibilihd2.model.toRootCommentCardModel
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.generated.Settings
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
            .commonOnFailureHandler(requireContext())
            .onSuccess { r ->
                baseModel.next.postValue(r.cursor.next)
            }.getOrNull()?.repliesList?.toRootCommentCardModel()
    }

    override suspend fun onLoadMorIO(): List<RootCommentCardModel>? {
        return runCatching { grpcClidet.reply.mainList(oid, type, next, mode) }
            .commonOnFailureHandler(requireContext())
            .onSuccess { r ->
                baseModel.next.postValue(r.cursor.next)
            }.getOrNull()?.repliesList?.toRootCommentCardModel()
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
                            }.setCommonOnFailureHandler(requireContext())
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
                            }.setCommonOnFailureHandler(requireContext())
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
                            }.setCommonOnFailureHandler(requireContext())
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
                            TipUtil.showTip(requireContext(), "TODO")
                            true
                        }
                        R.id.menu_delete -> {
                            runIOCatching { bilibiliClient.mainAPI.delReply(type.toInt(), oid, itemModel.rpid).await() }
                                .setCommonOnFailureHandler(requireContext())
                                .onSuccess {
                                    (baseModel.itemModel.value!! as? MutableList)?.removeAt(position)
                                    runMain {
                                        adapter?.notifyItemRemoved(position)
                                    }
                                }.launch()
                            true
                        }
                        else -> false
                    }
                }
                show()
            }
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