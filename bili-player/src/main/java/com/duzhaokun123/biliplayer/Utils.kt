package com.duzhaokun123.biliplayer

fun <A, B>  List<Pair<A, B>>.firstList(): List<A> {
    val l = mutableListOf<A>()
    this.forEach { l.add(it.first) }
    return l
}