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

@SettingsValue
val danmakuOnTop = false

@SettingsValue
val dynamicColor = false

@SettingsValue
val danmakuAllowDanmakuOverlapping = false

@SettingsValue
val danmakuDurationCoeff = 1F

@SettingsValue(saveAsString = true)
val danmakuStyle = 0

@SettingsValue(saveAsString = true)
val danmakuShadowDx = 0F

@SettingsValue(saveAsString = true)
val danmakuShadowDy = 0F

@SettingsValue(saveAsString = true)
val danmakuShadowRadius = 5F

@SettingsValue(saveAsString = true)
val danmakuStrokeWidth = 1F

@SettingsValue(saveAsString = true)
val danmakuTextSize = 1F

@SettingsValue(saveAsString = true)
val danmakuLineHeight = 40

@SettingsValue(saveAsString = true)
val danmakuMarginTop = 0

@SettingsValue(saveAsString = true)
val danmakuMarginBottom = 0

@SettingsValue
val danmakuBlockByPlace = StringSet()

@SettingsValue(saveAsString = true)
val danmakuTypefaceUse = 0

@SettingsValue
val allowAnalytics = true

@SettingsValue(saveAsString = true)
val playPauseTime = 0

@SettingsValue(saveAsString = true)
val onlinePlayQuality = -1