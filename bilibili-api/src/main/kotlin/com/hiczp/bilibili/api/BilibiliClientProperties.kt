package com.hiczp.bilibili.api

/**
 * 客户端固有属性. 包括版本号, 密钥以及硬件编码.
 * 默认值对应 5.37.0(release-b220051) 版本.
 */
interface BilibiliClientProperties {
    /**
     * 默认 UA, 用于大多数访问
     */
    @Suppress("SpellCheckingInspection")
    val defaultUserAgent
        get() = "Mozilla/5.0 BiliDroid/5.37.0 (bbcallen@gmail.com)"

    /**
     * Android 平台的 appKey(该默认值为普通版客户端, 非概念版)
     */
    val appKey
        get() = "1d8b6e7d45233436"

    /**
     * 由反编译 so 文件得到的 appSecret, 与 appKey 必须匹配
     */
    @Suppress("SpellCheckingInspection")
    val appSecret
        get() = "560c52ccd288fed045859ed18bffd973"

    /**
     * 获取视频播放地址使用的 appKey, 与访问其他 RestFulAPI 所用的 appKey 是不一样的
     */
    @Suppress("SpellCheckingInspection")
    val videoAppKey
        get() = "iVGUTjsxvpLeuDCf"

    /**
     * 获取视频播放地址所用的 appSecret
     */
    @Suppress("SpellCheckingInspection")
    val videoAppSecret
        get() = "aHRmhWMLkdeMuILqORnYZocwMBpMEOdt"

    /**
     * 客户端平台
     */
    val platform
        get() = "android"

    /**
     * 客户端类型
     * 此属性在旧版客户端不存在
     */
    val channel
        get() = "html5_app_bili"

    /**
     * 硬件 ID, 尚不明确生成算法
     */
    @Suppress("SpellCheckingInspection")
    val hardwareId
        get() = "aBRoDWAVeRhsA3FDewMzS3lLMwM"

    /**
     * 屏幕尺寸, 大屏手机(已经没有小屏手机了)统一为 xxhdpi
     * 此参数在新版客户端已经较少使用
     */
    val scale
        get() = "xxhdpi"

    /**
     * 版本号
     */
    val version
        get() = "5.37.0.5370000"

    /**
     * 构建版本号
     */
    val build
        get() = "5370000"

    /**
     * 构建版本 ID, 可能是某种 Hash
     */
    val buildVersionId
        get() = "XXD9E43D7A1EBB6669597650E3EE417D9E7F5"
}
