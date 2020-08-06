package com.dmgdavid2109.xapochallenge.kotlinrepos.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KotlinRepo(
    val name: String,
    val description: String,
    val avatar: String,
    val author: String,
    val starts: String,
    val forks: String
) : Parcelable
