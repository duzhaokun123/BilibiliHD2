package com.duzhaokun123.bilibilihd2.utils

import android.app.Dialog

fun Dialog.blurBackground(): Dialog {
    window?.blurBackground()
    return this
}
