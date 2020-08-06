package com.dmgdavid2109.xapochallenge.kotlinrepos.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dmgdavid2109.xapochallenge.common.ui.ViewStateLiveData
import com.dmgdavid2109.xapochallenge.di.ViewModelFactoryCreator
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.model.KotlinRepo
import javax.inject.Inject

class KotlinRepoDetailViewModel(
    private val kotlinRepo: KotlinRepo
) : ViewModel(),
    KotlinRepoDetailViewModelInput {

    private val _viewState = ViewStateLiveData(KotlinRepoDetailViewState()).apply {
        updateCurrentState {
            copy(kotlinRepo = this@KotlinRepoDetailViewModel.kotlinRepo)
        }
    }
    val viewState: LiveData<KotlinRepoDetailViewState>
        get() = _viewState

    override fun retry() {

    }

    class Factory @Inject constructor() {
        fun create(kotlinRepo: KotlinRepo) =
            ViewModelFactoryCreator.createForViewModel { KotlinRepoDetailViewModel(kotlinRepo) }
    }
}
