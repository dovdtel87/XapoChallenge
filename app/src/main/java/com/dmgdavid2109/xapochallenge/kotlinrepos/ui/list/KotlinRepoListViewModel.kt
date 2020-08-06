package com.dmgdavid2109.xapochallenge.kotlinrepos.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmgdavid2109.xapochallenge.R
import com.dmgdavid2109.xapochallenge.common.ui.Event
import com.dmgdavid2109.xapochallenge.common.ui.ViewStateLiveData
import com.dmgdavid2109.xapochallenge.common.ui.toEvent
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.model.KotlinRepo
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.usecase.GetRepositoriesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class KotlinRepoListViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel(),
    KotlinRepoListViewModelInput {

    private val _viewState = ViewStateLiveData(KotlinRepoListViewState())
    val viewState: LiveData<KotlinRepoListViewState>
        get() = _viewState

    private val _navigateToDetails = MutableLiveData<Event<KotlinRepo>>()
    val navigateToDetails: LiveData<Event<KotlinRepo>>
        get() = _navigateToDetails

    init {
        loadList()
    }

    private fun loadList() {
        viewModelScope.launch {
            showStartLoading()
            val result = getRepositoriesUseCase()
            result.fold(::showError, ::showRepoList)
        }
    }

    override fun onRepoTapped(repoDetailsItem: KotlinRepo) {
        navigateToRepoDetails(repoDetailsItem)
    }

    private fun navigateToRepoDetails(repoDetailsItem: KotlinRepo) {
        _navigateToDetails.value = repoDetailsItem.toEvent()
    }

    private fun showStartLoading() {
        _viewState.updateCurrentState {
            copy(isLoading = true)
        }
    }

    private fun showRepoList(items: List<KotlinRepo>) {
        _viewState.updateCurrentState {
            copy(
                isLoading = false,
                errorMessage = null,
                repositoryList = items
            )
        }
    }

    private fun showError(exception: Exception) {
        _viewState.updateCurrentState {
            copy(
                isLoading = false,
                errorMessage = R.string.generic_error
            )
        }
    }

    override fun retry() {
        loadList()
    }
}
