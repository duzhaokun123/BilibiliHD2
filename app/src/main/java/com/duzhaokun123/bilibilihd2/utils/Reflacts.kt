package com.duzhaokun123.bilibilihd2.utils

import java.lang.reflect.Method
import java.lang.reflect.Modifier

val Method.isStatic: Boolean
    get() = Modifier.isStatic(modifiers)

val Method.parameterCountCompat: Int
    get() = parameterTypes.size