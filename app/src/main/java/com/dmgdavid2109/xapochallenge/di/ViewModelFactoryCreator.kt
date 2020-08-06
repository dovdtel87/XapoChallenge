package com.dmgdavid2109.xapochallenge.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object ViewModelFactoryCreator {

    fun <VM : ViewModel> createForViewModel(viewModelCreator: () -> VM): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModelCreator() as T
            }
        }
    }
}
