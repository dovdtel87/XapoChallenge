package com.dmgdavid2109.xapochallenge.kotlinrepos.domain.repository

import com.dmgdavid2109.xapochallenge.common.data.ListMapper
import com.dmgdavid2109.xapochallenge.common.data.Result
import com.dmgdavid2109.xapochallenge.kotlinrepos.data.api.KotlinReposApi
import com.dmgdavid2109.xapochallenge.kotlinrepos.data.mapper.KotlinRepoMapper
import com.dmgdavid2109.xapochallenge.kotlinrepos.data.model.KotlinRepoDTO
import com.dmgdavid2109.xapochallenge.kotlinrepos.data.repository.KotlinRepoRepositoryImpl
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.model.KotlinRepo
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.repository.KotlinRepoRepository
import com.dmgdavid2109.xapochallenge.kotlinrepos.helpers.beforeEachTestBlocking
import com.dmgdavid2109.xapochallenge.kotlinrepos.helpers.mock
import com.dmgdavid2109.xapochallenge.kotlinrepos.helpers.withTestCoroutine
import io.mockk.coEvery
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import retrofit2.Response

@ExperimentalCoroutinesApi
object RepositoriesRepositoryImplSpec : Spek({
    withTestCoroutine()

    val kotlinReposApi: KotlinReposApi by mock<KotlinReposApi>()
    val kotlinRepoMapper: ListMapper<KotlinRepoDTO, KotlinRepo> = ListMapper(KotlinRepoMapper())

    val repositoriesRepository: KotlinRepoRepository by memoized {
        KotlinRepoRepositoryImpl(
            kotlinReposApi,
            kotlinRepoMapper
        )
    }

    val listRepositoryDto = listOf<KotlinRepoDTO>(
        KotlinRepoDTO(
            "First repository",
            "Contains the description of first repository",
            "https://avatar1.png",
            "dovdtel87",
            "123",
            "45"
        )
    )

    val repository1 = KotlinRepo(
        "First repository",
        "Contains the description of first repository",
        "https://avatar1.png",
        "dovdtel87",
        "123",
        "45"
    )

    describe("getRepositories") {
        context("when it success") {
            lateinit var result : Result<List<KotlinRepo>>
            val expectedResult = Result.Success(listOf(repository1))
            beforeEachTestBlocking {
                coEvery { kotlinReposApi.retrieveRepositories() } returns Response.success(listRepositoryDto)
                result = repositoriesRepository.getRepositories()
            }
            it("retrieves a list of repositories") {
                TestCase.assertEquals(expectedResult, result)
            }
        }
        context("when it fails") {
            lateinit var result : Result<List<KotlinRepo>>
            val anException = Exception()
            val expectedError = Result.Error(anException)
            beforeEachTestBlocking {
                coEvery { kotlinReposApi.retrieveRepositories() } throws anException
                result = repositoriesRepository.getRepositories()
            }
            it("returns an error") {
                TestCase.assertEquals(expectedError, result)
            }
        }
    }
})
