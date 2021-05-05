package com.duzhaokun123.bilibilihd2.ui.play.online

import android.graphics.Color
import android.graphics.PointF
import com.duzhaokun123.bilibilihd.proto.BiliDanmaku
import com.duzhaokun123.bilibilihd2.utils.DanmakuUtil
import com.duzhaokun123.bilibilihd2.utils.DanmakuUtil.toDanmakuType
import com.duzhaokun123.bilibilihd2.utils.okHttpClient
import com.duzhaokun123.danmakuview.Value
import com.duzhaokun123.danmakuview.danmaku.BiliSpecialDanmaku
import com.duzhaokun123.danmakuview.danmaku.SpecialDanmaku
import com.duzhaokun123.danmakuview.interfaces.DanmakuParser
import com.duzhaokun123.danmakuview.model.DanmakuConfig
import com.duzhaokun123.danmakuview.model.Danmakus
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONException

class LazyCidDanmakuParser(
    private val aid: Long,
    private val cid: Long,
    private val durationS: Int
) : DanmakuParser {
    companion object {
        const val BILI_PLAYER_WIDTH = 682.0F
        const val BILI_PLAYER_HEIGHT = 438.0F

        fun initialSpecialDanmakuData(danmaku: SpecialDanmaku) {
            danmaku.drawMode = DanmakuConfig.DrawMode.SHADOW
            val text = danmaku.text.trim { it <= ' ' }
            if (text.startsWith('[')) {
                var textArray: Array<String?>? = null
                try {
                    val jsonArray = JSONArray(text)
                    textArray = arrayOfNulls(jsonArray.length())
                    for (i in textArray.indices) {
                        textArray[i] = jsonArray.getString(i)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                // TODO: 20-12-2
                if (textArray != null && textArray.size >= 5 && textArray[4].isNullOrEmpty()
                        .not()
                ) {
                    danmaku.text = textArray[4]!!
                    danmaku.fillText()
                    var beginX = textArray[0]!!.toFloatOrNull() ?: 0F
                    var beginY = textArray[1]!!.toFloatOrNull() ?: 0F
                    var endX = beginX
                    var endY = beginY
                    val alphaArray = textArray[2]!!.split("-".toRegex()).toTypedArray()
                    val beginAlpha =
                        (Value.ALPHA_MAX * (alphaArray[0].toFloatOrNull() ?: 0F)).toInt()
                    var endAlpha = beginAlpha
                    if (alphaArray.size > 1) {
                        endAlpha = (Value.ALPHA_MAX * (alphaArray[1].toFloatOrNull() ?: 0F)).toInt()
                    }
                    val alphaDuration = (textArray[3]!!.toFloatOrNull() ?: 0F * 1000).toLong()
                    var translationDuration = alphaDuration
                    var translationStartDelay = 0L
                    var rotateY = 0F
                    var rotateZ = 0F
                    if (textArray.size >= 7) {
                        rotateZ = textArray[5]!!.toFloatOrNull() ?: 0F
                        rotateY = textArray[6]!!.toFloatOrNull() ?: 0F
                    }
                    if (textArray.size >= 11) {
                        endX = textArray[7]!!.toFloatOrNull() ?: 0F
                        endY = textArray[8]!!.toFloatOrNull() ?: 0F
                        if (textArray[9].isNullOrEmpty().not())
                            translationDuration = textArray[9]!!.toLong()
                        if (textArray[10].isNullOrEmpty().not())
                            translationStartDelay = (textArray[10]!!.toFloatOrNull() ?: 0F).toLong()

                    }
                    if (textArray[0]!!.contains('.')) {
                        beginX *= BiliSpecialDanmaku.BILI_PLAYER_WIDTH
                    }
                    if (textArray[1]!!.contains('.')) {
                        beginY *= BiliSpecialDanmaku.BILI_PLAYER_HEIGHT
                    }
                    if (textArray.size >= 8 && textArray[7]!!.contains('.')) {
                        endX *= BiliSpecialDanmaku.BILI_PLAYER_WIDTH
                    }
                    if (textArray.size >= 9 && textArray[8]!!.contains('.')) {
                        endY *= BiliSpecialDanmaku.BILI_PLAYER_HEIGHT
                    }
                    danmaku.duration = alphaDuration
//                    danmaku.rotationZ = rotateZ
                    danmaku.keyframes[0F] = Triple(
                        PointF(beginX / BILI_PLAYER_WIDTH, beginY / BILI_PLAYER_HEIGHT),
                        rotateY,
                        beginAlpha
                    )
                    danmaku.keyframes[1F] = Triple(
                        PointF(endX / BILI_PLAYER_WIDTH, endY / BILI_PLAYER_HEIGHT),
                        rotateY,
                        endAlpha
                    )

//                    danmaku.rotationY = rotateY
//                    danmaku.beginX = beginX
//                    danmaku.beginY = beginY
//                    danmaku.endX = endX
//                    danmaku.endY = endY
//                    danmaku.translationDuration = translationDuration
//                    danmaku.translationStartDelay = translationStartDelay
//                    danmaku.beginAlpha = beginAlpha
//                    danmaku.endAlpha = endAlpha
                    if (textArray.size >= 12) {
                        if (textArray[11].isNullOrEmpty().not() && textArray[11].toBoolean()) {
                            danmaku.textShadowColor = Color.TRANSPARENT
                        }
                    }
                    if (textArray.size >= 13) {
                        //TODO 字体 textArray[12]
                    }
//                    if (textArray.size >= 14) {
//                        // Linear.easeIn or Quadratic.easeOut
//                        danmaku.isQuadraticEaseOut = "0" == textArray[13]
//                    }
                    if (textArray.size >= 15) {
                        // 路径数据
                        if ("" != textArray[14]) {
                            val motionPathString = textArray[14]!!.substring(1)
                            if (!motionPathString.isEmpty()) {
                                val pointStrArray =
                                    motionPathString.split("L".toRegex()).toTypedArray()
                                if (pointStrArray.isNotEmpty()) {
                                    val points = Array(pointStrArray.size) { FloatArray(2) }
                                    for (i in pointStrArray.indices) {
                                        val pointArray =
                                            pointStrArray[i].split(",".toRegex()).toTypedArray()
                                        if (pointArray.size >= 2) {
                                            points[i][0] = pointArray[0].toFloatOrNull() ?: 0F
                                            points[i][1] = pointArray[1].toFloatOrNull() ?: 0F
                                        }
                                    }
                                    val t = danmaku.duration.toFloat() / points.size
                                    var a = 0F
                                    points.forEachIndexed { index, floats ->
                                        danmaku.keyframes[a] = Triple(
                                            PointF(
                                                floats[0] / BILI_PLAYER_WIDTH,
                                                floats[1] / BILI_PLAYER_HEIGHT
                                            ),
                                            rotateY,
                                            beginAlpha + (endAlpha - beginAlpha) * (index / points.size)
                                        )
                                        a += t
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    var danmakus: Danmakus? = null
    var hasLost = false
        private set

    override fun parse(): Danmakus {
        if (danmakus != null) return danmakus!!

        val dms = mutableListOf<BiliDanmaku.DanmakuElem>()
        val c = durationS / 360 + 1
        (1..c).forEach { i ->
            val request = Request.Builder()
                .url("https://api.bilibili.com/x/v2/dm/web/seg.so?oid=$cid&pid=$aid&type=1&segment_index=$i")
                .build()
            try {
                val response = okHttpClient.newCall(request).execute().body?.bytes()
                dms.addAll(BiliDanmaku.DmSegMobileReply.parseFrom(response).elemsList)
            } catch (e: Exception) {
                e.printStackTrace()
                hasLost = true
            }
        }

        danmakus = Danmakus()

        for (danmakuElem in dms) {
            val type = danmakuElem.mode.toDanmakuType()
            val color = -0x1000000 or danmakuElem.color
            val danmaku = DanmakuUtil.simpleDanmakuFactory.create(type)
            danmaku.offset = danmakuElem.progress.toLong()
            danmaku.textSize = danmakuElem.fontzsie.toFloat()
            danmaku.textColor = color
            danmaku.textShadowColor = if (color <= Color.BLACK) Color.WHITE else Color.BLACK
            danmaku.text = danmakuElem.content

            if (danmaku is SpecialDanmaku)
                initialSpecialDanmakuData(danmaku)

            danmakus!!.add(danmaku)
        }
        return danmakus!!
    }
}