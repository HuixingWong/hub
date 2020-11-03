package com.hx.hub.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hx.hub.BuildConfig
import com.hx.hub.http.interceptor.BasicAuthInterceptor
import com.hx.hub.http.interceptor.UrlIntercepter
import com.hx.hub.repository.UserInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://api.github.com/"
const val BASE_TRENDING_URL = "https://ghapi.huchen.dev/"

@Module
@InstallIn(ApplicationComponent::class)
object HttpModule {
    const val TIME_OUT_SECONDS = 10

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(userRepository: UserInfoRepository): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(
                        TIME_OUT_SECONDS.toLong(),
                        TimeUnit.SECONDS)
                .readTimeout(
                        TIME_OUT_SECONDS.toLong(),
                        TimeUnit.SECONDS)
                .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = when (BuildConfig.DEBUG) {
                                true -> HttpLoggingInterceptor.Level.BODY
                                false -> HttpLoggingInterceptor.Level.NONE
                            }
                        }
                )
                .addInterceptor(BasicAuthInterceptor(mUserInfoRepository = userRepository))
                .addInterceptor(UrlIntercepter())
                .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }
}
