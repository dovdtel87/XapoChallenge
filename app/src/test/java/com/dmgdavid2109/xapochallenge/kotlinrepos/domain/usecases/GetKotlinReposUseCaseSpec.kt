package com.dmgdavid2109.xapochallenge.kotlinrepos.domain.usecases

import com.dmgdavid2109.xapochallenge.common.data.Result
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.model.KotlinRepo
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.repository.KotlinRepoRepository
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.usecase.GetRepositoriesUseCase
import com.dmgdavid2109.xapochallenge.kotlinrepos.helpers.beforeEachTestBlocking
import com.dmgdavid2109.xapochallenge.kotlinrepos.helpers.mock
import com.dmgdavid2109.xapochallenge.kotlinrepos.helpers.withTestCoroutine
import io.mockk.coEvery
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

@ExperimentalCoroutinesApi
object GetKotlinReposUseCaseSpec : Spek({
    withTestCoroutine()

    lateinit var result : Result<List<KotlinRepo>>

    val kotlinRepoRepository: KotlinRepoRepository by mock<KotlinRepoRepository>()
    val useCase: GetRepositoriesUseCase by memoized {
        GetRepositoriesUseCase(
            kotlinRepoRepository
        )
    }

    val repository1 = KotlinRepo(
        "First repository",
        "Contains the description of first repository",
        "https://avatar1.png",
        "dovdtel87",
        "123",
        "45"
    )

    describe("invoke") {
        context("when it success") {
            val expectedResult = Result.Success(listOf(repository1))
            beforeEachTestBlocking {
                coEvery { kotlinRepoRepository.getRepositories() } returns Result.Success(
                    listOf(
                        repository1
                    )
                )
                result = useCase.invoke()
            }
            it("retrieves a list of kotlin repositories") {
                TestCase.assertEquals(expectedResult, result)
            }
        }
        context("when it fails") {
            val exception = Exception("An exception")
            val expectedError = Result.Error(exception)
            beforeEachTestBlocking {
                coEvery { kotlinRepoRepository.getRepositories() } returns Result.Error(exception)
                result = useCase.invoke()
            }
            it("returns an error") {
                TestCase.assertEquals(expectedError, result)
            }
        }
    }
})
