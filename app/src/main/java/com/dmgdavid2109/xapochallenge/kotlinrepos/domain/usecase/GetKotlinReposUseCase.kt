package com.dmgdavid2109.xapochallenge.kotlinrepos.domain.usecase

import com.dmgdavid2109.xapochallenge.common.data.Result
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.model.KotlinRepo
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.repository.KotlinRepoRepository
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val kotlinRepoRepository: KotlinRepoRepository
) {
    suspend operator fun invoke(): Result<List<KotlinRepo>> {
        return kotlinRepoRepository.getRepositories()
    }
}
