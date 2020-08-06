package com.dmgdavid2109.xapochallenge.di

import com.dmgdavid2109.xapochallenge.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class ApiConfigsModule {

    @Provides
    @ApiConfig
    fun providesApiUrl(): String {
        return BuildConfig.API_URL
    }
}
