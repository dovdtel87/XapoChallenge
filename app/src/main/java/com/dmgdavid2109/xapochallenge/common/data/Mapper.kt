package com.dmgdavid2109.xapochallenge.common.data

interface Mapper<I, O> {
    fun map(input: I): O
}
