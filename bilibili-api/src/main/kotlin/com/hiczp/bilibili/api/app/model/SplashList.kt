package com.hiczp.bilibili.api.app.model


import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class SplashList(
    @SerializedName("code")
    var code: Int, // 0
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("message")
    var message: String, // 0
    @SerializedName("ttl")
    var ttl: Int // 1
) {
    data class Data(
        @SerializedName("list")
        var list: List<List_>?,
        @SerializedName("max_time")
        var maxTime: Int, // 4
        @SerializedName("min_interval")
        var minInterval: Int, // 14400
        @SerializedName("pull_interval")
        var pullInterval: Int, // 900
        @SerializedName("show")
        var show: List<Show>?
    ) {
        data class List_ (
            @SerializedName("ad_cb")
            var adCb: String, // CAYQBhgGIAAwADihB0IeMTYwMjMzNTAxMjY5N3ExNzJhMjJhNTdhM3E4MjYySNnu75TRLlIJ6KW/5a6J5biCaABwAIABFrIBIG8hCmVt2bcTFKLVdyFMpRfLzFe6G7MpMGW1JY8KRUz12AMG
            @SerializedName("begin_time")
            var beginTime: Int, // 1514736000
            @SerializedName("card_type")
            var cardType: Int, // 15
            @SerializedName("client_ip")
            var clientIp: String, // 124.89.86.218
            @SerializedName("cm_mark")
            var cmMark: Int, // 1
            @SerializedName("duration")
            var duration: Int, // 3
            @SerializedName("enable_background_download")
            var enableBackgroundDownload: Boolean, // true
            @SerializedName("end_time")
            var endTime: Long, // 2524579200
            @SerializedName("extra")
            var extra: Extra,
            @SerializedName("hash")
            var hash: String, // c9a91a253a995394c71b7c6e27dc2f12
            @SerializedName("id")
            var id: Int, // 6
            @SerializedName("is_ad")
            var isAd: Boolean, // false
            @SerializedName("is_ad_loc")
            var isAdLoc: Boolean, // true
            @SerializedName("logo_hash")
            var logoHash: String,
            @SerializedName("logo_url")
            var logoUrl: String,
            @SerializedName("request_id")
            var requestId: String, // 1602335012697q172a22a57a3q8262
            @SerializedName("resource_id")
            var resourceId: Int, // 926
            @SerializedName("schema")
            var schema: String, // tbopen://m.taobao.com/tbopen/index.html?action=ali.open.nav&module=h5&bootImage=0&source=alimama&appkey=27696150&appName=bilibili&packageName=tv.danmaku.bili&backURL=bilibili%3A%2F%2Fblank%3Fcm_mark%3Dtaobao&h5Url=https%3A%2F%2Fequity.tmall.com%2Ftm%3FagentId%3D747001%26_bind%3Dtrue%26bc_fl_src%3Dtmall_market_llb_1_911625%26llbPlatform%3D_pube%26llbOsd%3D0
            @SerializedName("schema_callup_white_list")
            var schemaCallupWhiteList: List<String>,
            @SerializedName("schema_package_name")
            var schemaPackageName: String, // com.taobao.taobao
            @SerializedName("schema_title")
            var schemaTitle: String, // 立即查看
            @SerializedName("skip")
            var skip: Int, // 0
            @SerializedName("source")
            var source: Int, // 929
            @SerializedName("thumb")
            var thumb: String, // https://i0.hdslb.com/bfs/sycp/tmaterial/201805/fe75ae6d56f75971bef3a2b0d5d384b5.png
            @SerializedName("type")
            var type: Int, // 0
            @SerializedName("universal_app")
            var universalApp: String, // tbopen://m.taobao.com/tbopen/index.html?action=ali.open.nav&module=h5&bootImage=0&source=alimama&appkey=27696150&appName=bilibili&packageName=tv.danmaku.bili&backURL=bilibili%3A%2F%2Fblank%3Fcm_mark%3Dtaobao&h5Url=https%3A%2F%2Fequity.tmall.com%2Ftm%3FagentId%3D747001%26_bind%3Dtrue%26bc_fl_src%3Dtmall_market_llb_1_911625%26llbPlatform%3D_pube%26llbOsd%3D0
            @SerializedName("uri")
            var uri: String?,
            @SerializedName("uri_title")
            var uriTitle: String,
            @SerializedName("video_hash")
            var videoHash: String, // 72b21573b17aade17b981b5f70aceb37
            @SerializedName("video_height")
            var videoHeight: Int, // 1680
            @SerializedName("video_url")
            var videoUrl: String?, // http://upos-sz-staticks3.bilivideo.com/ssaxcode/m200930a21j0bg8zat7mu71xx7zcbr58-1-SPLASH.mp4
            @SerializedName("video_width")
            var videoWidth: Int // 1080
        ) {
            data class Extra(
                @SerializedName("click_area")
                var clickArea: Int, // 0
                @SerializedName("click_urls")
                var clickUrls: List<String>,
                @SerializedName("download_whitelist")
                var downloadWhitelist: List<JsonObject>,
                @SerializedName("open_whitelist")
                var openWhitelist: List<String>,
                @SerializedName("preload_landingpage")
                var preloadLandingpage: Int, // 0
                @SerializedName("report_time")
                var reportTime: Int, // 0
                @SerializedName("sales_type")
                var salesType: Int, // 41
                @SerializedName("share_info")
                var shareInfo: JsonObject,
                @SerializedName("shop_id")
                var shopId: Int, // 0
                @SerializedName("show_urls")
                var showUrls: List<Any>,
                @SerializedName("special_industry")
                var specialIndustry: Boolean, // false
                @SerializedName("topview_pic_url")
                var topviewPicUrl: String,
                @SerializedName("topview_video_url")
                var topviewVideoUrl: String,
                @SerializedName("track_id")
                var trackId: String, // -jGJtKzDG6K6qQgx4SyVSQ6x94UKjTwDNoc4-1VguTI9Huamq_Nny-zftw-CiNrDAxgj560hKGtJtMIEbFpQyKzmUy5tDIlpkQqJjEVVIdA%3D
                @SerializedName("up_mid")
                var upMid: Int, // 0
                @SerializedName("upzone_entrance_report_id")
                var upzoneEntranceReportId: Int, // 0
                @SerializedName("upzone_entrance_type")
                var upzoneEntranceType: Int, // 0
                @SerializedName("use_ad_web_v2")
                var useAdWebV2: Boolean // false
            )
        }

        data class Show(
            @SerializedName("etime")
            var etime: Int, // 1602345599
            @SerializedName("id")
            var id: Int, // 2650
            @SerializedName("stime")
            var stime: Int // 1602259200
        )
    }
}