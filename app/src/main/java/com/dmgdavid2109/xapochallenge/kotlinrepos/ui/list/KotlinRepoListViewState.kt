package com.dmgdavid2109.xapochallenge.kotlinrepos.ui.list

import com.dmgdavid2109.xapochallenge.common.ui.LceViewState
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.model.KotlinRepo

data class KotlinRepoListViewState(
    override val isLoading: Boolean = true,
    override val errorMessage: Int? = null,
    val repositoryList: List<KotlinRepo> = emptyList()
) : LceViewState
