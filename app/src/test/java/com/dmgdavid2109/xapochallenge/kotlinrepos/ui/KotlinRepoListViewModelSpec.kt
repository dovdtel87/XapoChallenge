package com.dmgdavid2109.xapochallenge.kotlinrepos.ui

import com.dmgdavid2109.xapochallenge.common.data.Result
import com.dmgdavid2109.xapochallenge.R
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.model.KotlinRepo
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.usecase.GetRepositoriesUseCase
import com.dmgdavid2109.xapochallenge.kotlinrepos.helpers.getValueTest
import com.dmgdavid2109.xapochallenge.kotlinrepos.helpers.mock
import com.dmgdavid2109.xapochallenge.kotlinrepos.helpers.withInstantTaskExecutor
import com.dmgdavid2109.xapochallenge.kotlinrepos.helpers.withTestCoroutine
import com.dmgdavid2109.xapochallenge.kotlinrepos.ui.list.KotlinRepoListViewModel
import com.dmgdavid2109.xapochallenge.kotlinrepos.ui.list.KotlinRepoListViewState
import io.mockk.coEvery
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

@ExperimentalCoroutinesApi
class KotlinRepoListViewModelSpec : Spek({
    withInstantTaskExecutor()
    withTestCoroutine()

    val getRepositoriesUseCase: GetRepositoriesUseCase by mock<GetRepositoriesUseCase>()

    val viewModel: KotlinRepoListViewModel by memoized {
        KotlinRepoListViewModel(
            getRepositoriesUseCase
        )
    }

    val repository1 = KotlinRepo(
        "First repository",
        "Contains the description of first repository",
        "https://avatar1.png"
    )

    describe("init") {
        context("when list successfully retrieved") {
            val expectedViewState =
                KotlinRepoListViewState(
                    false,
                    null,
                    listOf(repository1)
                )
            beforeEachTest {
                coEvery { getRepositoriesUseCase.invoke() } returns Result.Success(listOf(repository1))
            }
            it("displays the correct result") {
                assertEquals(expectedViewState, viewModel.viewState.getValueTest())
            }
        }

        context("when there is an error") {
            val expectedViewStateError =
                KotlinRepoListViewState(
                    false,
                    R.string.generic_error,
                    emptyList()
                )
            beforeEachTest {
                coEvery { getRepositoriesUseCase.invoke() } returns Result.Error(Exception())
            }
            it("displays an error") {
                assertEquals(expectedViewStateError, viewModel.viewState.getValueTest())
            }
        }
    }
})
