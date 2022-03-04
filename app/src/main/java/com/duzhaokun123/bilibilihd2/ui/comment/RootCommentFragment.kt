package com.duzhaokun123.bilibilihd2.ui.comment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.widget.*
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.MutableLiveData
import com.bapis.bilibili.main.community.reply.v1.Mode
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseSimpleCardListSRRVFragment
import com.duzhaokun123.bilibilihd2.databinding.ItemRootCommentBinding
import com.duzhaokun123.bilibilihd2.databinding.LayoutSendRootCommentBinding
import com.duzhaokun123.bilibilihd2.model.RootCommentCardModel
import com.duzhaokun123.bilibilihd2.model.UserModel
import com.duzhaokun123.bilibilihd2.model.toRootCommentCardModel
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.generated.Settings
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.github.duzhaokun123.androidapptemplate.utils.TipUtil
import io.github.duzhaokun123.androidapptemplate.utils.launch
import io.github.duzhaokun123.androidapptemplate.utils.onSuccess
import io.github.duzhaokun123.androidapptemplate.utils.runIOCatching

class RootCommentFragment @JvmOverloads constructor(private val setOid: Long = 0, private val setType: Int = 0) :
    BaseSimpleCardListSRRVFragment<ItemRootCommentBinding, RootCommentCardModel, RootCommentFragment.RootCommentModel>(
        R.layout.item_root_comment,
        Settings.dynamicCardWidthDp.let { run { if (it == 0) 500 else it }.dpToPx() },
        RootCommentModel::class
    ) {
    class RootCommentModel: BaseModel<RootCommentCardModel>() {
        val next = MutableLiveData<Long>(0)
        val oid = MutableLiveData<Long?>(null)
        val type = MutableLiveData<Int?>(null)
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
    private lateinit var layoutSendRootCommentBinding: LayoutSendRootCommentBinding
    private val cvid = View.generateViewId()

    override suspend fun onRefreshIO(): List<RootCommentCardModel>? {
        return runCatching { grpcClidet.reply.mainList(oid, type, 0, mode) }
            .commonOnFailureHandler(context)
            .onSuccess { r ->
                baseModel.next.postValue(r.cursor.next)
                if (r.cursor.next == 0L)
                    runMain { srl.setNoMoreData(true) }
            }.getOrNull()?.repliesList?.toRootCommentCardModel()
    }

    override suspend fun onLoadMorIO(): List<RootCommentCardModel>? {
        return runCatching { grpcClidet.reply.mainList(oid, type, next, mode) }
            .commonOnFailureHandler(context)
            .onSuccess { r ->
                baseModel.next.postValue(r.cursor.next)
                if (r.cursor.next == 0L)
                    runMain { srl.setNoMoreData(true) }
            }.getOrNull()?.repliesList?.toRootCommentCardModel()
    }

    override fun findViews() {
        layoutSendRootCommentBinding = LayoutSendRootCommentBinding.inflate(layoutInflater)
        baseBinding.rl.addView(layoutSendRootCommentBinding.root, RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        })
        baseBinding.srl.updateLayoutParams<RelativeLayout.LayoutParams> {
            addRule(RelativeLayout.ABOVE, layoutSendRootCommentBinding.root.id)
        }

        baseBinding.rl.addView(
            FrameLayout(requireContext()).apply { id = cvid },
            RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
        )
    }
    
    override fun initViews() {
        super.initViews()
        layoutSendRootCommentBinding.spMode.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, arrayOf("默认", "热度", "时间"))
    }

    override fun initEvents() {
        layoutSendRootCommentBinding.btnSend.setOnClickListener {
            val message = layoutSendRootCommentBinding.etComment.text.toString()
            runIOCatching {
                bilibiliClient.mainAPI.sendReply(message = message, oid = oid, type = type).await()
            }.setCommonOnFailureHandler(context)
                .onSuccess {
                    runMain {
                        layoutSendRootCommentBinding.etComment.setText("")
                        val loginResponse = bilibiliClient.loginResponse ?: return@runMain
                        (baseModel.itemModel.value as? MutableList)?.add(0, RootCommentCardModel(
                            UserModel(loginResponse.userId.toString(), loginResponse.userId, null),
                            message, System.currentTimeMillis() / 1000, 0, 0, 0
                        ))
                        adapter?.notifyItemChanged(0)
                        runIOCatching { bilibiliClient.appAPI.myInfo().await() }
                            .setCommonOnFailureHandler(context)
                            .onSuccess { myInfo ->
                                runMain {
                                    (baseModel.itemModel.value as? MutableList)?.set(0, RootCommentCardModel(
                                        UserModel(myInfo.data.name, loginResponse.userId, myInfo.data.face),
                                        message, System.currentTimeMillis() / 1000, 0, 0, 0
                                    ))
                                    adapter?.notifyItemChanged(0)
                                }
                            }.launch()
                    }
                }.launch()
        }
        layoutSendRootCommentBinding.spMode.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position) {
                    0 -> setMode(Mode.DEFAULT)
                    1 -> setMode(Mode.MAIN_LIST_HOT)
                    2 -> setMode(Mode.MAIN_LIST_TIME)
                }
                baseBinding.srl.autoRefresh()
            }
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
            childFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                .replace(cvid, ChildCommentFragment(oid, type, itemModel.rpid))
                .addToBackStack(null)
                .commit()
            requireActivity().supportFragmentManager.beginTransaction()
                .setPrimaryNavigationFragment(this)
                .commit()
        }
    }

    override fun initItemData(
        itemBinding: ItemRootCommentBinding,
        itemModel: RootCommentCardModel,
        position: Int
    ) {
        itemBinding.model = itemModel
        EmoteMap.emotefy(itemModel.content, itemBinding.tvContent)
    }

    fun setOid(oid: Long) {
        baseModel.oid.value = oid
    }

    fun setType(type: Int) {
        baseModel.type.value = type
    }

    fun setMode(mode: Mode) {
        baseModel.mode.value = mode
    }

    fun reload() {
        baseBinding.srl.autoRefresh()
    }
}