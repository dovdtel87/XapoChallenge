package com.dmgdavid2109.xapochallenge.kotlinrepos.ui.details

import com.dmgdavid2109.xapochallenge.common.ui.LceViewState
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.model.KotlinRepo

data class KotlinRepoDetailViewState(
    override val isLoading: Boolean = true,
    override val errorMessage: Int? = null,
    val kotlinRepo: KotlinRepo = KotlinRepo("","","", "", "", "")
) : LceViewState
