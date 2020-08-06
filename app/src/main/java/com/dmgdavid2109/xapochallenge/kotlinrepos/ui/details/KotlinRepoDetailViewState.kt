package com.dmgdavid2109.xapochallenge.kotlinrepos.ui.details

import com.dmgdavid2109.xapochallenge.common.ui.LceViewState

data class KotlinRepoDetailViewState(
    override val isLoading: Boolean = true,
    override val errorMessage: Int? = null,
    val name: String = "",
    val description: String = "",
    val author: String = "",
    val starts: String = "",
    val forks: String = ""
) : LceViewState
