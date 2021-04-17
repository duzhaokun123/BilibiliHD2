package com.duzhaokun123.bilibilihd2

import com.duzhaokun123.annotationProcessor.SettingsValue
import com.duzhaokun123.annotationProcessor.StringSet

@SettingsValue
val users = StringSet()

@SettingsValue
val selectedUid = 0L

@SettingsValue
val appKey = ""

@SettingsValue
val appSec = ""

@SettingsValue(saveAsString = true)
val uiMod = 0

@SettingsValue(saveAsString = true)
val mainCardWidthDp = 500

@SettingsValue(saveAsString = true)
val dynamicCardWidthDp = 700