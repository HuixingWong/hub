package com.hx.hub.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.hx.hub.repository.UserInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserInfoRepository(sharedPreferences: SharedPreferences): UserInfoRepository {
        return UserInfoRepository.getInstance(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("DEFAULT_SP", Context.MODE_PRIVATE)
    }
}
