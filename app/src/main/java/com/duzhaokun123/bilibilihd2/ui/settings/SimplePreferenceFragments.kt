package com.duzhaokun123.bilibilihd2.ui.settings

import android.os.Bundle
import androidx.annotation.XmlRes
import androidx.databinding.MergedDataBinderMapper
import androidx.fragment.app.activityViewModels
import androidx.preference.MultiSelectListPreference
import androidx.preference.Preference
import com.duzhaokun123.bilibilihd2.BuildConfig
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.utils.BrowserUtil
import com.duzhaokun123.bilibilihd2.utils.DanmakuUtil
import com.duzhaokun123.bilibilihd2.utils.DateFormat
import com.duzhaokun123.bilibilihd2.utils.application
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.takisoft.preferencex.PreferenceFragmentCompat
import com.takisoft.preferencex.SimpleMenuPreference
import io.github.duzhaokun123.androidapptemplate.utils.TipUtil

abstract class SimplePreferenceFragment(
    @XmlRes val preferencesResId: Int, val name: CharSequence,
    private vararg val onValueChangeListeners: Pair<String, (preference: Preference, value: Any) -> Boolean>
) :
    PreferenceFragmentCompat() {
    val model by activityViewModels<SettingsActivity.Model>()

    override fun onCreatePreferencesFix(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(preferencesResId, rootKey)
        onValueChangeListeners.forEach {
            findPreference<Preference>(it.first)?.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { p, v -> it.second.invoke(p, v) }
        }
    }

    override fun onResume() {
        super.onResume()
        model.subtitle.value = name
    }
}

@Suppress("UNUSED")
class BilibiliApiFragment : SimplePreferenceFragment(
    R.xml.settings_bilibili_api, application.getText(R.string.bilibili_api),
    "appKey" to { _, v ->
        application.reinitBilibiliClient(userAppKey = v as String)
        true
    },
    "appSec" to { _, v ->
        application.reinitBilibiliClient(userAppSec = v as String)
        true
    }
)

class SettingsMainFragment : SimplePreferenceFragment(R.xml.settings_main, "")

@Suppress("UNUSED")
class DisplayFragment : SimplePreferenceFragment(
    R.xml.settings_display, application.getText(R.string.display),
    "uiMod" to { _, v -> application.reinitUiMod((v as String).toInt()); true }
)

@Suppress("UNUSED")
class AboutFragment :
    SimplePreferenceFragment(R.xml.settings_about, application.getText(R.string.about)) {
    override fun onCreatePreferencesFix(savedInstanceState: Bundle?, rootKey: String?) {
        super.onCreatePreferencesFix(savedInstanceState, rootKey)
        findPreference<Preference>("name")!!.summary = BuildConfig.APPLICATION_ID
        findPreference<Preference>("version")!!.summary =
            "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})"
        findPreference<Preference>("build_type")!!.summary = BuildConfig.BUILD_TYPE
        findPreference<Preference>("build_time")!!.summary =
            DateFormat.format2.format(BuildConfig.BUILD_TIME)
        findPreference<Preference>("project_home")!!.apply {
            summary = BuildConfig.PROJECT_HOME
            onPreferenceClickListener = Preference.OnPreferenceClickListener {
                BrowserUtil.openCustomTab(context, BuildConfig.PROJECT_HOME)
                true
            }
        }
        findPreference<Preference>("donate")!!.apply {
            summary = BuildConfig.DONATE_LINK
            onPreferenceClickListener = Preference.OnPreferenceClickListener {
                BrowserUtil.openCustomTab(context, BuildConfig.DONATE_LINK)
                true
            }
        }
        findPreference<Preference>("about_analytics")!!.setOnPreferenceClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("关于 Microsoft AppCenter")
                .setMessage("若参与分析, 设备信息和应用信息(包括但不限于: Android API 版本, 设备型号, 语言所在地, 应用版本, 会话时长, 功能使用次数. 但不包括: 电话号码, IMEI)将被提交至 Microsoft AppCenter, 并且将被记录在本地文件中.\n"
                )
                .setPositiveButton(android.R.string.ok, null)
                .show()
            true
        }
    }
}

class DanmakuFragment :
    SimplePreferenceFragment(R.xml.settings_danmaku, application.getText(R.string.danmaku)) {
    companion object {
        val shadows = arrayOf("1", "3")
        val strokes = arrayOf("2", "3")
    }

    override fun onCreatePreferencesFix(savedInstanceState: Bundle?, rootKey: String?) {
        super.onCreatePreferencesFix(savedInstanceState, rootKey)
        findPreference<Preference>("danmaku_sync")!!.setOnPreferenceClickListener {
            DanmakuUtil.syncDanmakuSettings()
            TipUtil.showTip(context, "已同步弹幕设置")
            true
        }
        findPreference<MultiSelectListPreference>("danmakuBlockByPlace")!!.apply {
            updateDanmakuBlockByPlaceSummary(this, values)
            onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, v ->
                updateDanmakuBlockByPlaceSummary(this, v as Set<String>)
                true
            }
        }
        findPreference<SimpleMenuPreference>("danmakuStyle")!!.apply {
            updateShadowVisibility(value in shadows)
            updateStrokeVisibility(value in strokes)
            setOnPreferenceChangeListener { _, v ->
                updateShadowVisibility(v in shadows)
                updateStrokeVisibility(v in strokes)
                true
            }
        }
    }

    private fun updateDanmakuBlockByPlaceSummary(p: MultiSelectListPreference, v: Set<String>) {
        p.summary = v.joinToString(", ")
    }

    private fun updateShadowVisibility(v: Boolean) {
        findPreference<Preference>("danmakuShadowDx")!!.isVisible = v
        findPreference<Preference>("danmakuShadowDy")!!.isVisible = v
        findPreference<Preference>("danmakuShadowRadius")!!.isVisible = v
    }

    private fun updateStrokeVisibility(v: Boolean) {
        findPreference<Preference>("danmakuStrokeWidth")!!.isVisible = v
    }
}

class PlayFragment :
    SimplePreferenceFragment(R.xml.settings_play, application.getText(R.string.play))