package com.dmgdavid2109.xapochallenge.kotlinrepos.data.api

import com.dmgdavid2109.xapochallenge.kotlinrepos.data.model.KotlinRepoDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KotlinReposApi {
    @GET("repositories")
    suspend fun retrieveRepositories(
        @Query("language") language: String
    ): Response<List<KotlinRepoDTO>>
}
