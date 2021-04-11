package com.hiczp.bilibili.api.vc.model


import com.google.gson.annotations.SerializedName

data class DynamicNew(
    @SerializedName("code")
    val code: Int, // 0
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("msg")
    val msg: String
) {
    data class Data(
        @SerializedName("attentions")
        val attentions: Attentions,
        @SerializedName("cards")
        val cards: List<Card>,
        @SerializedName("exist_gap")
        val existGap: Int, // 1
        @SerializedName("_gt_")
        val gt: Int, // 0
        @SerializedName("history_offset")
        val historyOffset: Long, // 511225018033489263
        @SerializedName("max_dynamic_id")
        val maxDynamicId: Long, // 511751825840828869
        @SerializedName("mix_light_types")
        val mixLightTypes: String, // 2,8_1
        @SerializedName("new_num")
        val newNum: Int, // 0
        @SerializedName("open_rcmd")
        val openRcmd: Int, // 1
        @SerializedName("update_num")
        val updateNum: Int // 0
    ) {
        data class Attentions(
            @SerializedName("bangumis")
            val bangumis: List<Bangumi>,
            @SerializedName("uids")
            val uids: List<Int>
        ) {
            data class Bangumi(
                @SerializedName("season_id")
                val seasonId: Int, // 173
                @SerializedName("type")
                val type: Int // 1
            )
        }

        data class Card(
            @SerializedName("activity_infos")
            val activityInfos: ActivityInfos,
            @SerializedName("card")
            val card: String, // {"aid":502538682,"attribute":0,"cid":322068173,"copyright":1,"ctime":1617989907,"desc":"在本期视频里，我将介绍美国各家电器商店最后的命运，以及为何它们诸多最后都倒闭了的根本原因。\r\n视频翻译由 @paizhangpi 完成，并由 @WoodEar85 完成，本期视频为翻译交接期的第1期，共5期。\r\n在Patreon上赞助我：www.patreon.com\/8bitguy1\r\n我的官方网站：www.the8bitguy.com\r\n我的中国粉丝社区网站：www.8bitguycn.com\r\n我的中文社团QQ群：518064085","dimension":{"height":1080,"rotate":0,"width":1920},"duration":909,"dynamic":"","jump_url":"bilibili:\/\/video\/502538682\/?page=1&player_preload=%7B%22cid%22%3A322068173%2C%22dash%22%3A%7B%22audio%22%3A%5B%7B%22bandwidth%22%3A67157%2C%22base_url%22%3A%22http%3A%5C%2F%5C%2Fupos-sz-mirrorkodo.bilivideo.com%5C%2Fupgcxcode%5C%2F73%5C%2F81%5C%2F322068173%5C%2F322068173_nb2-1-30216.m4s%3Fe%3Dig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_%26uipk%3D5%26nbs%3D1%26deadline%3D1618001749%26gen%3Dplayurlv2%26os%3Dkodobv%26oi%3D2081916014%26trid%3D6ff431e3c355444fa14d8d92ed503845U%26platform%3Dandroid_b%26upsig%3D0d10568772b68e6c237964d1f9ce3678%26uparams%3De%2Cuipk%2Cnbs%2Cdeadline%2Cgen%2Cos%2Coi%2Ctrid%2Cplatform%26mid%3D174438027%26orderid%3D0%2C1%26logo%3D80000000%22%2C%22codecid%22%3A0%2C%22id%22%3A30216%2C%22size%22%3A7632884%7D%2C%7B%22bandwidth%22%3A132640%2C%22base_url%22%3A%22http%3A%5C%2F%5C%2Fupos-sz-mirrorkodo.bilivideo.com%5C%2Fupgcxcode%5C%2F73%5C%2F81%5C%2F322068173%5C%2F322068173_nb2-1-30232.m4s%3Fe%3Dig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_%26uipk%3D5%26nbs%3D1%26deadline%3D1618001749%26gen%3Dplayurlv2%26os%3Dkodobv%26oi%3D2081916014%26trid%3D6ff431e3c355444fa14d8d92ed503845U%26platform%3Dandroid_b%26upsig%3D809d480a32c2b414c53bbe86750edb32%26uparams%3De%2Cuipk%2Cnbs%2Cdeadline%2Cgen%2Cos%2Coi%2Ctrid%2Cplatform%26mid%3D174438027%26orderid%3D0%2C1%26logo%3D80000000%22%2C%22codecid%22%3A0%2C%22id%22%3A30232%2C%22size%22%3A15075384%7D%2C%7B%22bandwidth%22%3A180181%2C%22base_url%22%3A%22http%3A%5C%2F%5C%2Fupos-sz-mirrorcoso1.bilivideo.com%5C%2Fupgcxcode%5C%2F73%5C%2F81%5C%2F322068173%5C%2F322068173_nb2-1-30280.m4s%3Fe%3Dig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_%26uipk%3D5%26nbs%3D1%26deadline%3D1618001749%26gen%3Dplayurlv2%26os%3Dcoso1bv%26oi%3D2081916014%26trid%3D6ff431e3c355444fa14d8d92ed503845U%26platform%3Dandroid_b%26upsig%3Da309d976ad6d77b5001795ed05111521%26uparams%3De%2Cuipk%2Cnbs%2Cdeadline%2Cgen%2Cos%2Coi%2Ctrid%2Cplatform%26mid%3D174438027%26orderid%3D0%2C1%26logo%3D80000000%22%2C%22codecid%22%3A0%2C%22id%22%3A30280%2C%22size%22%3A20478623%7D%5D%2C%22video%22%3A%5B%7B%22bandwidth%22%3A1174992%2C%22base_url%22%3A%22http%3A%5C%2F%5C%2Fupos-sz-mirrorcoso1.bilivideo.com%5C%2Fupgcxcode%5C%2F73%5C%2F81%5C%2F322068173%5C%2F322068173_nb2-1-30080.m4s%3Fe%3Dig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_%26uipk%3D5%26nbs%3D1%26deadline%3D1618001749%26gen%3Dplayurlv2%26os%3Dcoso1bv%26oi%3D2081916014%26trid%3D6ff431e3c355444fa14d8d92ed503845U%26platform%3Dandroid_b%26upsig%3D8a22ca2041aa67d0549de44d716bb659%26uparams%3De%2Cuipk%2Cnbs%2Cdeadline%2Cgen%2Cos%2Coi%2Ctrid%2Cplatform%26mid%3D174438027%26orderid%3D0%2C1%26logo%3D80000000%22%2C%22codecid%22%3A7%2C%22id%22%3A80%2C%22size%22%3A133523198%7D%2C%7B%22bandwidth%22%3A815874%2C%22base_url%22%3A%22http%3A%5C%2F%5C%2Fupos-sz-mirrorkodo.bilivideo.com%5C%2Fupgcxcode%5C%2F73%5C%2F81%5C%2F322068173%5C%2F322068173-1-30077.m4s%3Fe%3Dig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_%26uipk%3D5%26nbs%3D1%26deadline%3D1618001749%26gen%3Dplayurlv2%26os%3Dkodobv%26oi%3D2081916014%26trid%3D6ff431e3c355444fa14d8d92ed503845U%26platform%3Dandroid_b%26upsig%3D7deaa317c5f5de35a8c5859736b84ffa%26uparams%3De%2Cuipk%2Cnbs%2Cdeadline%2Cgen%2Cos%2Coi%2Ctrid%2Cplatform%26mid%3D174438027%26orderid%3D0%2C1%26logo%3D80000000%22%2C%22codecid%22%3A12%2C%22id%22%3A80%2C%22size%22%3A92713882%7D%2C%7B%22bandwidth%22%3A784840%2C%22base_url%22%3A%22http%3A%5C%2F%5C%2Fupos-sz-mirrorcoso1.bilivideo.com%5C%2Fupgcxcode%5C%2F73%5C%2F81%5C%2F322068173%5C%2F322068173_nb2-1-30064.m4s%3Fe%3Dig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_%26uipk%3D5%26nbs%3D1%26deadline%3D1618001749%26gen%3Dplayurlv2%26os%3Dcoso1bv%26oi%3D2081916014%26trid%3D6ff431e3c355444fa14d8d92ed503845U%26platform%3Dandroid_b%26upsig%3D978021bb4bfeefbd9d0d122495bd1b94%26uparams%3De%2Cuipk%2Cnbs%2Cdeadline%2Cgen%2Cos%2Coi%2Ctrid%2Cplatform%26mid%3D174438027%26orderid%3D0%2C1%26logo%3D80000000%22%2C%22codecid%22%3A7%2C%22id%22%3A64%2C%22size%22%3A89187307%7D%2C%7B%22bandwidth%22%3A793908%2C%22base_url%22%3A%22http%3A%5C%2F%5C%2Fupos-sz-mirrorhw.bilivideo.com%5C%2Fupgcxcode%5C%2F73%5C%2F81%5C%2F322068173%5C%2F322068173-1-30066.m4s%3Fe%3Dig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_%26uipk%3D5%26nbs%3D1%26deadline%3D1618001749%26gen%3Dplayurlv2%26os%3Dhwbv%26oi%3D2081916014%26trid%3D6ff431e3c355444fa14d8d92ed503845U%26platform%3Dandroid_b%26upsig%3D35c51f094fcd2b757e2e359f97493220%26uparams%3De%2Cuipk%2Cnbs%2Cdeadline%2Cgen%2Cos%2Coi%2Ctrid%2Cplatform%26mid%3D174438027%26orderid%3D0%2C1%26logo%3D80000000%22%2C%22codecid%22%3A12%2C%22id%22%3A64%2C%22size%22%3A90217790%7D%2C%7B%22bandwidth%22%3A355289%2C%22base_url%22%3A%22http%3A%5C%2F%5C%2Fupos-sz-mirrorcoso1.bilivideo.com%5C%2Fupgcxcode%5C%2F73%5C%2F81%5C%2F322068173%5C%2F322068173_nb2-1-30032.m4s%3Fe%3Dig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_%26uipk%3D5%26nbs%3D1%26deadline%3D1618001749%26gen%3Dplayurlv2%26os%3Dcoso1bv%26oi%3D2081916014%26trid%3D6ff431e3c355444fa14d8d92ed503845U%26platform%3Dandroid_b%26upsig%3D05883b0a1bc3da68e2d1f9d354c88a53%26uparams%3De%2Cuipk%2Cnbs%2Cdeadline%2Cgen%2Cos%2Coi%2Ctrid%2Cplatform%26mid%3D174438027%26orderid%3D0%2C1%26logo%3D80000000%22%2C%22codecid%22%3A7%2C%22id%22%3A32%2C%22size%22%3A40374236%7D%2C%7B%22bandwidth%22%3A437236%2C%22base_url%22%3A%22http%3A%5C%2F%5C%2Fupos-sz-mirrorcoso1.bilivideo.com%5C%2Fupgcxcode%5C%2F73%5C%2F81%5C%2F322068173%5C%2F322068173-1-30033.m4s%3Fe%3Dig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_%26uipk%3D5%26nbs%3D1%26deadline%3D1618001749%26gen%3Dplayurlv2%26os%3Dcoso1bv%26oi%3D2081916014%26trid%3D6ff431e3c355444fa14d8d92ed503845U%26platform%3Dandroid_b%26upsig%3Debc8feb4b4cb6c8a288751208f62488d%26uparams%3De%2Cuipk%2Cnbs%2Cdeadline%2Cgen%2Cos%2Coi%2Ctrid%2Cplatform%26mid%3D174438027%26orderid%3D0%2C1%26logo%3D80000000%22%2C%22codecid%22%3A12%2C%22id%22%3A32%2C%22size%22%3A49686475%7D%2C%7B%22bandwidth%22%3A217619%2C%22base_url%22%3A%22http%3A%5C%2F%5C%2Fupos-sz-mirrorkodo.bilivideo.com%5C%2Fupgcxcode%5C%2F73%5C%2F81%5C%2F322068173%5C%2F322068173-1-30011.m4s%3Fe%3Dig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_%26uipk%3D5%26nbs%3D1%26deadline%3D1618001749%26gen%3Dplayurlv2%26os%3Dkodobv%26oi%3D2081916014%26trid%3D6ff431e3c355444fa14d8d92ed503845U%26platform%3Dandroid_b%26upsig%3D3ec90a8be2e4685238c23504a290314e%26uparams%3De%2Cuipk%2Cnbs%2Cdeadline%2Cgen%2Cos%2Coi%2Ctrid%2Cplatform%26mid%3D174438027%26orderid%3D0%2C1%26logo%3D80000000%22%2C%22codecid%22%3A12%2C%22id%22%3A16%2C%22size%22%3A24729718%7D%2C%7B%22bandwidth%22%3A169740%2C%22base_url%22%3A%22http%3A%5C%2F%5C%2Fupos-sz-mirrorcoso1.bilivideo.com%5C%2Fupgcxcode%5C%2F73%5C%2F81%5C%2F322068173%5C%2F322068173_nb2-1-30016.m4s%3Fe%3Dig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_%26uipk%3D5%26nbs%3D1%26deadline%3D1618001749%26gen%3Dplayurlv2%26os%3Dcoso1bv%26oi%3D2081916014%26trid%3D6ff431e3c355444fa14d8d92ed503845U%26platform%3Dandroid_b%26upsig%3D3ba3e7909ea61b2e8ea9b15c87602dcf%26uparams%3De%2Cuipk%2Cnbs%2Cdeadline%2Cgen%2Cos%2Coi%2Ctrid%2Cplatform%26mid%3D174438027%26orderid%3D0%2C1%26logo%3D80000000%22%2C%22codecid%22%3A7%2C%22id%22%3A16%2C%22size%22%3A19288889%7D%5D%7D%2C%22expire_time%22%3A1617998149%2C%22file_info%22%3A%7B%2216%22%3A%5B%7B%22filesize%22%3A19288889%2C%22timelength%22%3A909123%7D%5D%2C%2232%22%3A%5B%7B%22filesize%22%3A40374236%2C%22timelength%22%3A909123%7D%5D%2C%2264%22%3A%5B%7B%22filesize%22%3A89187307%2C%22timelength%22%3A909123%7D%5D%2C%2280%22%3A%5B%7B%22filesize%22%3A133523198%2C%22timelength%22%3A909123%7D%5D%7D%2C%22fnval%22%3A208%2C%22fnver%22%3A0%2C%22quality%22%3A32%2C%22support_description%22%3A%5B%22%E9%AB%98%E6%B8%85%201080P%22%2C%22%E9%AB%98%E6%B8%85%20720P%22%2C%22%E6%B8%85%E6%99%B0%20480P%22%2C%22%E6%B5%81%E7%95%85%20360P%22%5D%2C%22support_formats%22%3A%5B%22flv%22%2C%22flv720%22%2C%22flv480%22%2C%22mp4%22%5D%2C%22support_quality%22%3A%5B80%2C64%2C32%2C16%5D%2C%22video_codecid%22%3A7%2C%22video_project%22%3Atrue%7D&player_width=1920&player_height=1080&player_rotate=0","owner":{"face":"https:\/\/i0.hdslb.com\/bfs\/face\/5cb1ce81607546fe36677d5649dd28752abbedb8.jpg","mid":556805805,"name":"The8-BitGuy"},"pic":"https:\/\/i0.hdslb.com\/bfs\/archive\/a5f63e3829ae6a8481e806a34140838414acd250.png","player_info":{"cid":322068173,"dash":{"audio":[{"bandwidth":67157,"base_url":"http:\/\/upos-sz-mirrorkodo.bilivideo.com\/upgcxcode\/73\/81\/322068173\/322068173_nb2-1-30216.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_&uipk=5&nbs=1&deadline=1618001749&gen=playurlv2&os=kodobv&oi=2081916014&trid=6ff431e3c355444fa14d8d92ed503845U&platform=android_b&upsig=0d10568772b68e6c237964d1f9ce3678&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=174438027&orderid=0,1&logo=80000000","codecid":0,"id":30216,"size":7632884},{"bandwidth":132640,"base_url":"http:\/\/upos-sz-mirrorkodo.bilivideo.com\/upgcxcode\/73\/81\/322068173\/322068173_nb2-1-30232.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_&uipk=5&nbs=1&deadline=1618001749&gen=playurlv2&os=kodobv&oi=2081916014&trid=6ff431e3c355444fa14d8d92ed503845U&platform=android_b&upsig=809d480a32c2b414c53bbe86750edb32&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=174438027&orderid=0,1&logo=80000000","codecid":0,"id":30232,"size":15075384},{"bandwidth":180181,"base_url":"http:\/\/upos-sz-mirrorcoso1.bilivideo.com\/upgcxcode\/73\/81\/322068173\/322068173_nb2-1-30280.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_&uipk=5&nbs=1&deadline=1618001749&gen=playurlv2&os=coso1bv&oi=2081916014&trid=6ff431e3c355444fa14d8d92ed503845U&platform=android_b&upsig=a309d976ad6d77b5001795ed05111521&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=174438027&orderid=0,1&logo=80000000","codecid":0,"id":30280,"size":20478623}],"video":[{"bandwidth":1174992,"base_url":"http:\/\/upos-sz-mirrorcoso1.bilivideo.com\/upgcxcode\/73\/81\/322068173\/322068173_nb2-1-30080.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_&uipk=5&nbs=1&deadline=1618001749&gen=playurlv2&os=coso1bv&oi=2081916014&trid=6ff431e3c355444fa14d8d92ed503845U&platform=android_b&upsig=8a22ca2041aa67d0549de44d716bb659&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=174438027&orderid=0,1&logo=80000000","codecid":7,"id":80,"size":133523198},{"bandwidth":815874,"base_url":"http:\/\/upos-sz-mirrorkodo.bilivideo.com\/upgcxcode\/73\/81\/322068173\/322068173-1-30077.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_&uipk=5&nbs=1&deadline=1618001749&gen=playurlv2&os=kodobv&oi=2081916014&trid=6ff431e3c355444fa14d8d92ed503845U&platform=android_b&upsig=7deaa317c5f5de35a8c5859736b84ffa&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=174438027&orderid=0,1&logo=80000000","codecid":12,"id":80,"size":92713882},{"bandwidth":784840,"base_url":"http:\/\/upos-sz-mirrorcoso1.bilivideo.com\/upgcxcode\/73\/81\/322068173\/322068173_nb2-1-30064.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_&uipk=5&nbs=1&deadline=1618001749&gen=playurlv2&os=coso1bv&oi=2081916014&trid=6ff431e3c355444fa14d8d92ed503845U&platform=android_b&upsig=978021bb4bfeefbd9d0d122495bd1b94&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=174438027&orderid=0,1&logo=80000000","codecid":7,"id":64,"size":89187307},{"bandwidth":793908,"base_url":"http:\/\/upos-sz-mirrorhw.bilivideo.com\/upgcxcode\/73\/81\/322068173\/322068173-1-30066.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_&uipk=5&nbs=1&deadline=1618001749&gen=playurlv2&os=hwbv&oi=2081916014&trid=6ff431e3c355444fa14d8d92ed503845U&platform=android_b&upsig=35c51f094fcd2b757e2e359f97493220&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=174438027&orderid=0,1&logo=80000000","codecid":12,"id":64,"size":90217790},{"bandwidth":355289,"base_url":"http:\/\/upos-sz-mirrorcoso1.bilivideo.com\/upgcxcode\/73\/81\/322068173\/322068173_nb2-1-30032.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_&uipk=5&nbs=1&deadline=1618001749&gen=playurlv2&os=coso1bv&oi=2081916014&trid=6ff431e3c355444fa14d8d92ed503845U&platform=android_b&upsig=05883b0a1bc3da68e2d1f9d354c88a53&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=174438027&orderid=0,1&logo=80000000","codecid":7,"id":32,"size":40374236},{"bandwidth":437236,"base_url":"http:\/\/upos-sz-mirrorcoso1.bilivideo.com\/upgcxcode\/73\/81\/322068173\/322068173-1-30033.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_&uipk=5&nbs=1&deadline=1618001749&gen=playurlv2&os=coso1bv&oi=2081916014&trid=6ff431e3c355444fa14d8d92ed503845U&platform=android_b&upsig=ebc8feb4b4cb6c8a288751208f62488d&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=174438027&orderid=0,1&logo=80000000","codecid":12,"id":32,"size":49686475},{"bandwidth":217619,"base_url":"http:\/\/upos-sz-mirrorkodo.bilivideo.com\/upgcxcode\/73\/81\/322068173\/322068173-1-30011.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_&uipk=5&nbs=1&deadline=1618001749&gen=playurlv2&os=kodobv&oi=2081916014&trid=6ff431e3c355444fa14d8d92ed503845U&platform=android_b&upsig=3ec90a8be2e4685238c23504a290314e&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=174438027&orderid=0,1&logo=80000000","codecid":12,"id":16,"size":24729718},{"bandwidth":169740,"base_url":"http:\/\/upos-sz-mirrorcoso1.bilivideo.com\/upgcxcode\/73\/81\/322068173\/322068173_nb2-1-30016.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEuENvNC8aNEVEtEvE9IMvXBvE2ENvNCImNEVEIj0Y2J_aug859IB_&uipk=5&nbs=1&deadline=1618001749&gen=playurlv2&os=coso1bv&oi=2081916014&trid=6ff431e3c355444fa14d8d92ed503845U&platform=android_b&upsig=3ba3e7909ea61b2e8ea9b15c87602dcf&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=174438027&orderid=0,1&logo=80000000","codecid":7,"id":16,"size":19288889}]},"expire_time":1617998149,"file_info":{"16":[{"filesize":19288889,"timelength":909123}],"32":[{"filesize":40374236,"timelength":909123}],"64":[{"filesize":89187307,"timelength":909123}],"80":[{"filesize":133523198,"timelength":909123}]},"fnval":208,"fnver":0,"quality":32,"support_description":["高清 1080P","高清 720P","清晰 480P","流畅 360P"],"support_formats":["flv","flv720","flv480","mp4"],"support_quality":[80,64,32,16],"video_codecid":7,"video_project":true},"pubdate":1617989907,"rights":{"autoplay":1,"bp":0,"download":0,"elec":0,"hd5":0,"is_cooperation":0,"movie":0,"no_background":0,"no_reprint":1,"pay":0,"ugc_pay":0,"ugc_pay_preview":0},"short_link":"https:\/\/b23.tv\/BV1sK411F7mR","stat":{"aid":502538682,"coin":29,"danmaku":7,"dislike":0,"favorite":16,"his_rank":0,"like":55,"now_rank":0,"reply":9,"share":1,"view":339},"state":0,"tid":122,"title":"【8-Bit Guy】美国的电器商店到底发生什么了？（Ep.288）","tname":"野生技术协会","videos":1}
            @SerializedName("desc")
            val desc: Desc,
            @SerializedName("display")
            val display: Display,
            @SerializedName("extend_json")
            val extendJson: String // {"":{"ogv":{"ogv_id":0}},"dispute":{"content":""},"from":{"from":""},"like_icon":{"action":"","action_url":"","end":"","end_url":"","start":"","start_url":""},"topic":{"is_attach_topic":1}}
        ) {
            data class ActivityInfos(
                @SerializedName("details")
                val details: List<Detail>
            ) {
                data class Detail(
                    @SerializedName("detail")
                    val detail: String, // {"is_show":1,"topic_id":10511051,"topic_link":"bilibili:\/\/pegasus\/channel\/10511051?type=topic","topic_name":"打卡挑战"}
                    @SerializedName("type")
                    val type: Int // 1
                )
            }

            data class Desc(
                @SerializedName("acl")
                val acl: Int, // 0
                @SerializedName("bvid")
                val bvid: String, // BV1sK411F7mR
                @SerializedName("comment")
                val comment: Int, // 10
                @SerializedName("dynamic_id")
                val dynamicId: Long, // 511751825840828869
                @SerializedName("dynamic_id_str")
                val dynamicIdStr: String, // 511751825840828869
                @SerializedName("inner_id")
                val innerId: Int, // 0
                @SerializedName("is_liked")
                val isLiked: Int, // 0
                @SerializedName("like")
                val like: Int, // 55
                @SerializedName("orig_dy_id")
                val origDyId: Long, // 0
                @SerializedName("orig_dy_id_str")
                val origDyIdStr: String, // 0
                @SerializedName("orig_type")
                val origType: Int, // 0
                @SerializedName("origin")
                val origin: Origin,
                @SerializedName("pre_dy_id")
                val preDyId: Long, // 0
                @SerializedName("pre_dy_id_str")
                val preDyIdStr: String, // 0
                @SerializedName("r_type")
                val rType: Int, // 1
                @SerializedName("repost")
                val repost: Int, // 0
                @SerializedName("rid")
                val rid: Long, // 502538682
                @SerializedName("rid_str")
                val ridStr: String, // 502538682
                @SerializedName("status")
                val status: Int, // 1
                @SerializedName("stype")
                val stype: Int, // 0
                @SerializedName("timestamp")
                val timestamp: Long, // 1617989907
                @SerializedName("type")
                val type: Int, // 8
                @SerializedName("uid")
                val uid: Int, // 556805805
                @SerializedName("uid_type")
                val uidType: Int, // 1
                @SerializedName("user_profile")
                val userProfile: UserProfile?,
                @SerializedName("view")
                val view: Int // 1480
            ) {
                data class Origin(
                    @SerializedName("acl")
                    val acl: Int, // 0
                    @SerializedName("dynamic_id")
                    val dynamicId: Long, // 141933409116158901
                    @SerializedName("dynamic_id_str")
                    val dynamicIdStr: String, // 141933409116158901
                    @SerializedName("inner_id")
                    val innerId: Int, // 0
                    @SerializedName("like")
                    val like: Int, // 0
                    @SerializedName("orig_dy_id")
                    val origDyId: Int, // 0
                    @SerializedName("orig_dy_id_str")
                    val origDyIdStr: String, // 0
                    @SerializedName("pre_dy_id")
                    val preDyId: Int, // 0
                    @SerializedName("pre_dy_id_str")
                    val preDyIdStr: String, // 0
                    @SerializedName("r_type")
                    val rType: Int, // 0
                    @SerializedName("repost")
                    val repost: Int, // 34027
                    @SerializedName("rid")
                    val rid: Int, // 4089610
                    @SerializedName("rid_str")
                    val ridStr: String, // 4089610
                    @SerializedName("status")
                    val status: Int, // 1
                    @SerializedName("stype")
                    val stype: Int, // 0
                    @SerializedName("timestamp")
                    val timestamp: Int, // 1531884847
                    @SerializedName("type")
                    val type: Int, // 4200
                    @SerializedName("uid")
                    val uid: Int, // 102999485
                    @SerializedName("uid_type")
                    val uidType: Int, // 1
                    @SerializedName("view")
                    val view: Int // 90672320
                )

                data class UserProfile(
                    @SerializedName("card")
                    val card: Card,
                    @SerializedName("decorate_card")
                    val decorateCard: DecorateCard,
                    @SerializedName("info")
                    val info: Info,
                    @SerializedName("level_info")
                    val levelInfo: LevelInfo,
                    @SerializedName("pendant")
                    val pendant: Pendant,
                    @SerializedName("rank")
                    val rank: String, // 10000
                    @SerializedName("sign")
                    val sign: String, // The 8-Bit Guy唯一官方账号，专注于旧电脑的翻新维修。商务合作详见公告。
                    @SerializedName("vip")
                    val vip: Vip
                ) {
                    data class Card(
                        @SerializedName("official_verify")
                        val officialVerify: OfficialVerify
                    ) {
                        data class OfficialVerify(
                            @SerializedName("desc")
                            val desc: String,
                            @SerializedName("type")
                            val type: Int // -1
                        )
                    }

                    data class DecorateCard(
                        @SerializedName("big_card_url")
                        val bigCardUrl: String, // https://i0.hdslb.com/bfs/garb/item/5298a58563b5f8b469aeda359a7bfdefbaf1c286.png
                        @SerializedName("card_type")
                        val cardType: Int, // 2
                        @SerializedName("card_type_name")
                        val cardTypeName: String, // 免费
                        @SerializedName("card_url")
                        val cardUrl: String, // https://i0.hdslb.com/bfs/garb/item/5298a58563b5f8b469aeda359a7bfdefbaf1c286.png
                        @SerializedName("expire_time")
                        val expireTime: Int, // 0
                        @SerializedName("fan")
                        val fan: Fan,
                        @SerializedName("id")
                        val id: Int, // 1992
                        @SerializedName("image_enhance")
                        val imageEnhance: String, // https://i0.hdslb.com/bfs/garb/item/5298a58563b5f8b469aeda359a7bfdefbaf1c286.png
                        @SerializedName("item_id")
                        val itemId: Int, // 1992
                        @SerializedName("item_type")
                        val itemType: Int, // 3
                        @SerializedName("jump_url")
                        val jumpUrl: String, // https://www.bilibili.com/h5/mall/fans/recommend/2010?navhide=1&mid=161775300&from=dynamic
                        @SerializedName("mid")
                        val mid: Int, // 161775300
                        @SerializedName("name")
                        val name: String, // 明日方舟粉丝专属
                        @SerializedName("uid")
                        val uid: Int // 161775300
                    ) {
                        data class Fan(
                            @SerializedName("color")
                            val color: String, // #006cff
                            @SerializedName("is_fan")
                            val isFan: Int, // 1
                            @SerializedName("num_desc")
                            val numDesc: String, // 000001
                            @SerializedName("number")
                            val number: Int // 1
                        )
                    }

                    data class Info(
                        @SerializedName("face")
                        val face: String, // https://i0.hdslb.com/bfs/face/5cb1ce81607546fe36677d5649dd28752abbedb8.jpg
                        @SerializedName("uid")
                        val uid: Long, // 556805805
                        @SerializedName("uname")
                        val uname: String // The8-BitGuy
                    )

                    data class LevelInfo(
                        @SerializedName("current_level")
                        val currentLevel: Int // 4
                    )

                    data class Pendant(
                        @SerializedName("expire")
                        val expire: Int, // 0
                        @SerializedName("image")
                        val image: String,
                        @SerializedName("image_enhance")
                        val imageEnhance: String,
                        @SerializedName("image_enhance_frame")
                        val imageEnhanceFrame: String,
                        @SerializedName("name")
                        val name: String,
                        @SerializedName("pid")
                        val pid: Int // 0
                    )

                    data class Vip(
                        @SerializedName("avatar_subscript")
                        val avatarSubscript: Int, // 0
                        @SerializedName("avatar_subscript_url")
                        val avatarSubscriptUrl: String,
                        @SerializedName("label")
                        val label: Label,
                        @SerializedName("nickname_color")
                        val nicknameColor: String,
                        @SerializedName("role")
                        val role: Int, // 0
                        @SerializedName("themeType")
                        val themeType: Int, // 0
                        @SerializedName("vipDueDate")
                        val vipDueDate: Long, // 1592755200000
                        @SerializedName("vipStatus")
                        val vipStatus: Int, // 0
                        @SerializedName("vipType")
                        val vipType: Int // 1
                    ) {
                        data class Label(
                            @SerializedName("bg_color")
                            val bgColor: String,
                            @SerializedName("bg_style")
                            val bgStyle: Int, // 0
                            @SerializedName("border_color")
                            val borderColor: String,
                            @SerializedName("label_theme")
                            val labelTheme: String,
                            @SerializedName("path")
                            val path: String,
                            @SerializedName("text")
                            val text: String,
                            @SerializedName("text_color")
                            val textColor: String
                        )
                    }
                }
            }

            data class Display(
                @SerializedName("add_on_card_info")
                val addOnCardInfo: List<AddOnCardInfo>,
                @SerializedName("attach_card")
                val attachCard: AttachCard,
                @SerializedName("comment_info")
                val commentInfo: CommentInfo,
                @SerializedName("cover_play_icon_url")
                val coverPlayIconUrl: String, // https://i0.hdslb.com/bfs/album/2269afa7897830b397797ebe5f032b899b405c67.png
                @SerializedName("like_info")
                val likeInfo: LikeInfo,
                @SerializedName("origin")
                val origin: Origin,
                @SerializedName("relation")
                val relation: Relation,
                @SerializedName("show_tip")
                val showTip: ShowTip,
                @SerializedName("tags")
                val tags: List<Tag>,
                @SerializedName("topic_info")
                val topicInfo: TopicInfo,
                @SerializedName("usr_action_txt")
                val usrActionTxt: String // 投稿了视频
            ) {
                data class AddOnCardInfo(
                    @SerializedName("add_on_card_show_type")
                    val addOnCardShowType: Int, // 2
                    @SerializedName("attach_card")
                    val attachCard: AttachCard
                ) {
                    data class AttachCard(
                        @SerializedName("button")
                        val button: Button,
                        @SerializedName("cover_type")
                        val coverType: Int, // 1
                        @SerializedName("cover_url")
                        val coverUrl: String, // https://i0.hdslb.com/bfs/garb/2f0485003a9743489241aa7577599f358514d86d.png
                        @SerializedName("desc_first")
                        val descFirst: String, // 包括头像挂件等10件装扮内容
                        @SerializedName("desc_second")
                        val descSecond: String, // 1.3万人已购买
                        @SerializedName("head_text")
                        val headText: String, // 相关装扮
                        @SerializedName("jump_url")
                        val jumpUrl: String, // https://www.bilibili.com/h5/mall/suit/detail?navhide=1&id=4447
                        @SerializedName("oid_str")
                        val oidStr: String, // 4447
                        @SerializedName("title")
                        val title: String, // C酱です
                        @SerializedName("type")
                        val type: String // decoration
                    ) {
                        data class Button(
                            @SerializedName("jump_style")
                            val jumpStyle: JumpStyle,
                            @SerializedName("jump_url")
                            val jumpUrl: String, // https://www.bilibili.com/h5/mall/suit/detail?navhide=1&id=4447
                            @SerializedName("type")
                            val type: Int // 1
                        ) {
                            data class JumpStyle(
                                @SerializedName("icon")
                                val icon: String,
                                @SerializedName("text")
                                val text: String // 去看看
                            )
                        }
                    }
                }

                data class AttachCard(
                    @SerializedName("button")
                    val button: Button,
                    @SerializedName("cover_type")
                    val coverType: Int, // 1
                    @SerializedName("cover_url")
                    val coverUrl: String, // https://i0.hdslb.com/bfs/garb/2f0485003a9743489241aa7577599f358514d86d.png
                    @SerializedName("desc_first")
                    val descFirst: String, // 包括头像挂件等10件装扮内容
                    @SerializedName("desc_second")
                    val descSecond: String, // 1.3万人已购买
                    @SerializedName("head_text")
                    val headText: String, // 相关装扮
                    @SerializedName("jump_url")
                    val jumpUrl: String, // https://www.bilibili.com/h5/mall/suit/detail?navhide=1&id=4447
                    @SerializedName("oid_str")
                    val oidStr: String, // 4447
                    @SerializedName("title")
                    val title: String, // C酱です
                    @SerializedName("type")
                    val type: String // decoration
                ) {
                    data class Button(
                        @SerializedName("jump_style")
                        val jumpStyle: JumpStyle,
                        @SerializedName("jump_url")
                        val jumpUrl: String, // https://www.bilibili.com/h5/mall/suit/detail?navhide=1&id=4447
                        @SerializedName("type")
                        val type: Int // 1
                    ) {
                        data class JumpStyle(
                            @SerializedName("icon")
                            val icon: String,
                            @SerializedName("text")
                            val text: String // 去看看
                        )
                    }
                }

                data class CommentInfo(
                    @SerializedName("comments")
                    val comments: List<Comment>,
                    @SerializedName("emojis")
                    val emojis: List<Emoji>
                ) {
                    data class Comment(
                        @SerializedName("content")
                        val content: String, // 没你可爱[doge]
                        @SerializedName("name")
                        val name: String, // 炫冰FrezenIce
                        @SerializedName("uid")
                        val uid: Int // 50943700
                    )

                    data class Emoji(
                        @SerializedName("emoji_name")
                        val emojiName: String, // [doge]
                        @SerializedName("meta")
                        val meta: Meta,
                        @SerializedName("url")
                        val url: String // https://i0.hdslb.com/bfs/emote/3087d273a78ccaff4bb1e9972e2ba2a7583c9f11.png
                    ) {
                        data class Meta(
                            @SerializedName("size")
                            val size: Int // 1
                        )
                    }
                }

                data class LikeInfo(
                    @SerializedName("display_text")
                    val displayText: String, // 赞了
                    @SerializedName("like_users")
                    val likeUsers: List<LikeUser>
                ) {
                    data class LikeUser(
                        @SerializedName("uid")
                        val uid: Int, // 13839671
                        @SerializedName("uname")
                        val uname: String // uni_JZ-LSX
                    )
                }

                data class Origin(
                    @SerializedName("relation")
                    val relation: Relation
                ) {
                    data class Relation(
                        @SerializedName("is_follow")
                        val isFollow: Int, // 0
                        @SerializedName("is_followed")
                        val isFollowed: Int, // 0
                        @SerializedName("status")
                        val status: Int // 1
                    )
                }

                data class Relation(
                    @SerializedName("is_follow")
                    val isFollow: Int, // 1
                    @SerializedName("is_followed")
                    val isFollowed: Int, // 0
                    @SerializedName("status")
                    val status: Int // 2
                )

                data class ShowTip(
                    @SerializedName("del_tip")
                    val delTip: String // 要删除动态吗？
                )

                data class Tag(
                    @SerializedName("icon")
                    val icon: String, // https://i0.hdslb.com/bfs/album/4c1880a3e9d5fd2c72b339929a73a4b83d2bab93.png
                    @SerializedName("link")
                    val link: String, // bilibili://pegasus/channel/10511051?type=topic&topic_from=topic-card&name=%E6%89%93%E5%8D%A1%E6%8C%91%E6%88%98
                    @SerializedName("rid")
                    val rid: Int, // 10511051
                    @SerializedName("sub_module")
                    val subModule: String, // topic
                    @SerializedName("sub_type")
                    val subType: Int, // 1
                    @SerializedName("tag_type")
                    val tagType: Int, // 3
                    @SerializedName("text")
                    val text: String // 打卡挑战
                )

                data class TopicInfo(
                    @SerializedName("topic_details")
                    val topicDetails: List<TopicDetail>
                ) {
                    data class TopicDetail(
                        @SerializedName("is_activity")
                        val isActivity: Int, // 0
                        @SerializedName("topic_id")
                        val topicId: Int, // 5794
                        @SerializedName("topic_link")
                        val topicLink: String, // https://www.bilibili.com/tag/5794/feed
                        @SerializedName("topic_name")
                        val topicName: String // 美国
                    )
                }
            }
        }
    }
}