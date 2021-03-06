package com.dmgdavid2109.xapochallenge.kotlinrepos.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KotlinRepoDTO(
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "avatar") val avatarUrl: String,
    @Json(name = "author") val author: String,
    @Json(name = "stars") val stars: String,
    @Json(name = "forks") val forks: String
)
