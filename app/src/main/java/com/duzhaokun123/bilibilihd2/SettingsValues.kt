package com.duzhaokun123.bilibilihd2

import io.github.duzhaokun123.SettingsValue
import io.github.duzhaokun123.StringSet

@SettingsValue("emptySet()")
val users = StringSet()

@SettingsValue("0L")
val selectedUid = 0L

@SettingsValue("")
val appKey = ""

@SettingsValue("")
val appSec = ""

@SettingsValue("0", saveAsString = true)
val uiMod = 0

@SettingsValue("500", saveAsString = true)
val mainCardWidthDp = 500

@SettingsValue("700", saveAsString = true)
val dynamicCardWidthDp = 700

@SettingsValue("false")
val danmakuOnTop = false

@SettingsValue("false")
val dynamicColor = false

@SettingsValue("false")
val danmakuAllowDanmakuOverlapping = false

@SettingsValue("1F")
val danmakuDurationCoeff = 1F

@SettingsValue("0", saveAsString = true)
val danmakuStyle = 0

@SettingsValue("0F", saveAsString = true)
val danmakuShadowDx = 0F

@SettingsValue("0F", saveAsString = true)
val danmakuShadowDy = 0F

@SettingsValue("5F", saveAsString = true)
val danmakuShadowRadius = 5F

@SettingsValue("1F", saveAsString = true)
val danmakuStrokeWidth = 1F

@SettingsValue("1F", saveAsString = true)
val danmakuTextSize = 1F

@SettingsValue("40", saveAsString = true)
val danmakuLineHeight = 40

@SettingsValue("0", saveAsString = true)
val danmakuMarginTop = 0

@SettingsValue("0", saveAsString = true)
val danmakuMarginBottom = 0

@SettingsValue("emptySet()")
val danmakuBlockByPlace = StringSet()

@SettingsValue("0", saveAsString = true)
val danmakuTypefaceUse = 0

@SettingsValue("true")
val allowAnalytics = true

@SettingsValue("0", saveAsString = true)
val playPauseTime = 0

@SettingsValue("-1", saveAsString = true)
val onlinePlayQuality = -1

@SettingsValue("false")
val playerDebug = false