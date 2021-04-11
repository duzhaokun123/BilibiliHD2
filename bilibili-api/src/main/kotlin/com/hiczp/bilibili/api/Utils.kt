package com.hiczp.bilibili.api

import com.hiczp.bilibili.api.passport.model.LoginResponse

fun LoginResponse.getCookie(name: String): String? {
    data.cookieInfo.cookies.forEach {
        if (it.name == name) {
            return it.value
        }
    }
    return null
}