package com.dmgdavid2109.xapochallenge.kotlinrepos.ui.list

import com.dmgdavid2109.xapochallenge.R
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.model.KotlinRepo
import com.dmgdavid2109.xapochallenge.utils.createFactoryWithNavController
import com.dmgdavid2109.xapochallenge.utils.toFactory
import com.dmgdavid2109.xapochallenge.utils.toLiveData
import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepositoriesListFragmentTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fragmentScenario: FragmentScenario<KotlinRepoListFragment>

    @Test
    fun itDisplaysRepositoriesList() {
        // Given
        val repositoriesListViewState = KotlinRepoListViewState(
            isLoading = false,
            errorMessage = null,
            repositoryList = mutableListOf(
                KotlinRepo(
                    "First repository",
                    "Contains the first repository",
                    "http://avatar.png",
                    "dovtel87",
                    "1987",
                    "14"
                )
            )
        )
        val viewModel = mockk<KotlinRepoListViewModel>(relaxed = true) {
            every { viewState } returns repositoriesListViewState.toLiveData()
        }

        // When
        startFragment(viewModel)

        // Then
        onView(withId(R.id.repository_name)).check(ViewAssertions.matches(ViewMatchers.withText("First repository")))
        onView(withId(R.id.repository_description)).check(ViewAssertions.matches(ViewMatchers.withText("Contains the first repository")))
    }

    @Test
    fun itDisplaysLoadingView() {
        // Given
        val repositoriesListViewState = KotlinRepoListViewState(
            isLoading = true,
            errorMessage = null,
            repositoryList = emptyList()
        )
        val viewModel: KotlinRepoListViewModel = mockk(relaxed = true) {
            every { viewState } returns repositoriesListViewState.toLiveData()
        }

        // When
        startFragment(viewModel)

        // Then
        Espresso.onView(withId(R.id.progress_bar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun itDisplaysErrorView() {
        // Given
        val repositoriesListViewState = KotlinRepoListViewState(
            isLoading = false,
            errorMessage = R.string.generic_error,
            repositoryList = emptyList()
        )
        val viewModel: KotlinRepoListViewModel = mockk(relaxed = true) {
            every { viewState } returns repositoriesListViewState.toLiveData()
        }

        // When
        startFragment(viewModel)

        // Then
        Espresso.onView(withId(R.id.error_description)).check(ViewAssertions.matches(ViewMatchers.withText(R.string.generic_error)))
    }

    private fun startFragment(viewModel: KotlinRepoListViewModel) {
        fragmentScenario = launchFragmentInContainer(
            Bundle(),
            themeResId = R.style.AppTheme,
            factory = createFactoryWithNavController {
                KotlinRepoListFragment(viewModel.toFactory())
            }
        )
    }
}
