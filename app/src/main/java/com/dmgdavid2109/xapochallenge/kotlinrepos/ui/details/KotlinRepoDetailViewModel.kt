package com.dmgdavid2109.xapochallenge.kotlinrepos.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dmgdavid2109.xapochallenge.common.ui.ViewStateLiveData
import javax.inject.Inject

class KotlinRepoDetailViewModel @Inject constructor() : ViewModel(),
    KotlinRepoDetailViewModelInput {

    private val _viewState = ViewStateLiveData(KotlinRepoDetailViewState())
    val viewState: LiveData<KotlinRepoDetailViewState>
        get() = _viewState

    override fun retry() {

    }
}
