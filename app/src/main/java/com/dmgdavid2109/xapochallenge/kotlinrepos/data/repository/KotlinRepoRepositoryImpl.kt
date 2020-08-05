package com.dmgdavid2109.xapochallenge.kotlinrepos.data.repository

import com.dmgdavid2109.xapochallenge.common.data.ListMapper
import com.dmgdavid2109.xapochallenge.common.data.Result
import com.dmgdavid2109.xapochallenge.common.data.toResult
import com.dmgdavid2109.xapochallenge.kotlinrepos.data.api.KotlinReposApi
import com.dmgdavid2109.xapochallenge.kotlinrepos.data.model.KotlinRepoDTO
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.model.KotlinRepo
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.repository.KotlinRepoRepository
import javax.inject.Inject

class RepositoriesRepositoryImpl @Inject constructor(
    private val kotlinReposApi: KotlinReposApi,
    private val repositoryMapper: ListMapper<KotlinRepoDTO, KotlinRepo>
) : KotlinRepoRepository {

    override suspend fun getRepositories(): Result<List<KotlinRepo>> {
        return try {
            kotlinReposApi.retrieveRepositories().toResult { response ->
                repositoryMapper.map(response)
            }
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}
