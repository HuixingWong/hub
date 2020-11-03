package com.hx.hub.di

import com.hx.hub.http.service.LoginService
import com.hx.hub.http.service.ServiceManager
import com.hx.hub.http.service.TrendingService
import com.hx.hub.http.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ServicesModule {

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideTrendingService(retrofit: Retrofit): TrendingService{
        return retrofit.create(TrendingService::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @Provides
    @Singleton
    fun provideServiceManager(userService: UserService, loginService: LoginService, trendingService: TrendingService): ServiceManager {
        return ServiceManager(userService, loginService, trendingService)
    }
}
