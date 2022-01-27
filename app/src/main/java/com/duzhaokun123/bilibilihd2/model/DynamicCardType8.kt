package com.duzhaokun123.bilibilihd2.model


import com.google.gson.annotations.SerializedName

data class DynamicCardType8(
    @SerializedName("aid")
    val aid: Int, // 887535979
    @SerializedName("attribute")
    val attribute: Int, // 0
    @SerializedName("cid")
    val cid: Int, // 322545662
    @SerializedName("copyright")
    val copyright: Int, // 1
    @SerializedName("ctime")
    val ctime: Int, // 1618074082
    @SerializedName("desc")
    val desc: String, // 新频道科技过电：https://space.bilibili.com/693340454电子速谈：https://space.bilibili.com/12564758长期招募翻译：nixiesubs.com/html/2015/recruit.html买衣服：https://www.lttstore.comLMG论坛：https://linustechtips.com
    @SerializedName("dimension")
    val dimension: Dimension,
    @SerializedName("duration")
    val duration: Int, // 1073
    @SerializedName("dynamic")
    val `dynamic`: String,
    @SerializedName("jump_url")
    val jumpUrl: String, // bilibili://video/887535979/?page=1&player_preload=%7B%22cid%22%3A322545662%2C%22expire_time%22%3A1618092234%2C%22file_info%22%3A%7B%22112%22%3A%5B%7B%22filesize%22%3A365823614%2C%22timelength%22%3A1073956%7D%5D%2C%2216%22%3A%5B%7B%22filesize%22%3A57347362%2C%22timelength%22%3A1073877%7D%5D%2C%2232%22%3A%5B%7B%22filesize%22%3A118491136%2C%22timelength%22%3A1073956%7D%5D%2C%2264%22%3A%5B%7B%22filesize%22%3A174592551%2C%22timelength%22%3A1073956%7D%5D%2C%2280%22%3A%5B%7B%22filesize%22%3A225347923%2C%22timelength%22%3A1073956%7D%5D%7D%2C%22fnval%22%3A0%2C%22fnver%22%3A0%2C%22quality%22%3A32%2C%22support_description%22%3A%5B%22%E9%AB%98%E6%B8%85%201080P%2B%22%2C%22%E9%AB%98%E6%B8%85%201080P%22%2C%22%E9%AB%98%E6%B8%85%20720P%22%2C%22%E6%B8%85%E6%99%B0%20480P%22%2C%22%E6%B5%81%E7%95%85%20360P%22%5D%2C%22support_formats%22%3A%5B%22hdflv2%22%2C%22flv%22%2C%22flv720%22%2C%22flv480%22%2C%22mp4%22%5D%2C%22support_quality%22%3A%5B112%2C80%2C64%2C32%2C16%5D%2C%22url%22%3A%22http%3A%5C%2F%5C%2Fupos-sz-mirrorcoso1.bilivideo.com%5C%2Fupgcxcode%5C%2F62%5C%2F56%5C%2F322545662%5C%2F322545662-1-32.flv%3Fe%3Dig8euxZM2rNcNbNa7WdVhoMg7zUVhwdEto8g5X10ugNcXBlqNCNEto8g5gNvNE3DN0B5tZlqNxTEto8BTrNvN05fqx6S5ahE9IMvXBvE2ENvNCImNEVEK9GVqJIwqa80WXIekXRE9IB5QK%3D%3D%26uipk%3D5%26nbs%3D1%26deadline%3D1618095834%26gen%3Dplayurlv2%26os%3Dcoso1bv%26oi%3D2081916024%26trid%3D2c087313c57641aaa50326f214401edcU%26platform%3Dandroid%26upsig%3D4c6724e079b9c26037e3ecae969fd9ea%26uparams%3De%2Cuipk%2Cnbs%2Cdeadline%2Cgen%2Cos%2Coi%2Ctrid%2Cplatform%26mid%3D174438027%26orderid%3D0%2C1%26logo%3D80000000%22%2C%22video_codecid%22%3A7%2C%22video_project%22%3Atrue%7D&player_width=3840&player_height=1920&player_rotate=0
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("pic")
    val pic: String, // https://i1.hdslb.com/bfs/archive/7dee509cbeda4cb18b31b6e3acb325a0b0d1bd63.jpg
//    @SerializedName("player_info")
//    val playerInfo: PlayerInfo,
    @SerializedName("pubdate")
    val pubdate: Int, // 1618074082
    @SerializedName("rights")
    val rights: Rights,
    @SerializedName("short_link")
    val shortLink: String, // https://b23.tv/BV1pK4y1K74P
    @SerializedName("stat")
    val stat: Stat,
    @SerializedName("state")
    val state: Int, // 0
    @SerializedName("tid")
    val tid: Int, // 189
    @SerializedName("title")
    val title: String, // 【官方双语】科技up吐槽观众装机！#linus谈科技
    @SerializedName("tname")
    val tname: String, // 电脑装机
    @SerializedName("videos")
    val videos: Int // 1
) {
    data class Dimension(
        @SerializedName("height")
        val height: Int, // 1920
        @SerializedName("rotate")
        val rotate: Int, // 0
        @SerializedName("width")
        val width: Int // 3840
    )

    data class Owner(
        @SerializedName("face")
        val face: String, // https://i2.hdslb.com/bfs/face/16377ca32f0b4b801bc760862893d8cb986facf3.jpg
        @SerializedName("mid")
        val mid: Int, // 12434430
        @SerializedName("name")
        val name: String // LinusTechTips
    )

    // data class PlayerInfo(...)

    data class Rights(
        @SerializedName("autoplay")
        val autoplay: Int, // 1
        @SerializedName("bp")
        val bp: Int, // 0
        @SerializedName("download")
        val download: Int, // 0
        @SerializedName("elec")
        val elec: Int, // 0
        @SerializedName("hd5")
        val hd5: Int, // 1
        @SerializedName("is_cooperation")
        val isCooperation: Int, // 0
        @SerializedName("movie")
        val movie: Int, // 0
        @SerializedName("no_background")
        val noBackground: Int, // 0
        @SerializedName("no_reprint")
        val noReprint: Int, // 1
        @SerializedName("pay")
        val pay: Int, // 0
        @SerializedName("ugc_pay")
        val ugcPay: Int, // 0
        @SerializedName("ugc_pay_preview")
        val ugcPayPreview: Int // 0
    )

    data class Stat(
        @SerializedName("aid")
        val aid: Int, // 887535979
        @SerializedName("coin")
        val coin: Int, // 137
        @SerializedName("danmaku")
        val danmaku: Int, // 60
        @SerializedName("dislike")
        val dislike: Int, // 0
        @SerializedName("favorite")
        val favorite: Int, // 169
        @SerializedName("his_rank")
        val hisRank: Int, // 0
        @SerializedName("like")
        val like: Int, // 974
        @SerializedName("now_rank")
        val nowRank: Int, // 0
        @SerializedName("reply")
        val reply: Int, // 76
        @SerializedName("share")
        val share: Int, // 51
        @SerializedName("view")
        val view: Int // 14968
    )
}