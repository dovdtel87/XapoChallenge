package com.dmgdavid2109.xapochallenge.kotlinrepos.ui.list

import com.dmgdavid2109.xapochallenge.common.ui.LceViewModelInputs
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.model.KotlinRepo

interface KotlinRepoListViewModelInput : LceViewModelInputs {
    fun onRepoTapped(repoDetailsItem: KotlinRepo)
}
