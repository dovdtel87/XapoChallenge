package com.dmgdavid2109.xapochallenge.di

import com.dmgdavid2109.xapochallenge.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApiModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(
        @ApiConfig url: String,
        okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .callFactory(okHttpClient)
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.BASIC
            }
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
}
