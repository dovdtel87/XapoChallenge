package com.dmgdavid2109.xapochallenge.kotlinrepos.domain.repository

import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.model.KotlinRepo
import com.dmgdavid2109.xapochallenge.common.data.Result

interface KotlinRepoRepository {
    suspend fun getRepositories(): Result<List<KotlinRepo>>
}
