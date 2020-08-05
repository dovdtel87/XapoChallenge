package com.dmgdavid2109.xapochallenge.kotlinrepos.data

import com.dmgdavid2109.xapochallenge.common.data.Mapper
import com.dmgdavid2109.xapochallenge.kotlinrepos.data.api.KotlinReposApi
import com.dmgdavid2109.xapochallenge.kotlinrepos.data.mapper.KotlinRepoMapper
import com.dmgdavid2109.xapochallenge.kotlinrepos.data.model.KotlinRepoDTO
import com.dmgdavid2109.xapochallenge.kotlinrepos.data.repository.KotlinRepoRepositoryImpl
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.model.KotlinRepo
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.repository.KotlinRepoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
abstract class KotlinReposRepositoryModule {

    @Binds
    @ActivityScoped
    internal abstract fun repositoriesRepository(repository: KotlinRepoRepositoryImpl): KotlinRepoRepository

    @Binds
    internal abstract fun mapper(mapper: KotlinRepoMapper): Mapper<KotlinRepoDTO, KotlinRepo>

    companion object {
        @Provides
        @ActivityScoped
        internal fun provideApi(
            retrofit: Retrofit
        ): KotlinReposApi {
            return retrofit.create(KotlinReposApi::class.java)
        }
    }
}
