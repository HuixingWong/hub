package com.hx.hub.http.service

import com.hx.hub.entity.TrendingRepo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val URL_TRENDING = "url_name:trending"
interface TrendingService {


    @Headers(URL_TRENDING)
    @GET("repositories")
    suspend fun fetchTrendingRepos(@Query("language")language: String? = null,
                                   @Query("since") since : String? = "daily",
                                   @Query("spoken_language_code") spoken_language_code
                                       : String? = null): Response<List<TrendingRepo>>

}