package com.dmgdavid2109.xapochallenge.common.ui

fun <T : Any> T.toEvent(): Event<T> {
    return Event(this)
}
