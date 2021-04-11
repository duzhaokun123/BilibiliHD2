package com.duzhaokun123.bilibilihd2.ui.settings

import android.os.Bundle
import androidx.annotation.XmlRes
import androidx.fragment.app.activityViewModels
import androidx.preference.Preference
import com.duzhaokun123.bilibilihd2.BuildConfig
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.utils.BrowserUtil
import com.duzhaokun123.bilibilihd2.utils.DateFormat
import com.duzhaokun123.bilibilihd2.utils.application
import com.takisoft.preferencex.PreferenceFragmentCompat

abstract class SimplePreferenceFragment(
    @XmlRes val preferencesResId: Int, val name: CharSequence,
    private vararg val onValueChangeListeners: Pair<String, (preference: Preference, value: Any) -> Boolean>
) :
    PreferenceFragmentCompat() {
    val model by activityViewModels<SettingsActivity.Model>()

    override fun onCreatePreferencesFix(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(preferencesResId, rootKey)
        onValueChangeListeners.forEach {
            findPreference<Preference>(it.first)?.setOnPreferenceChangeListener(it.second)
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
    }
}