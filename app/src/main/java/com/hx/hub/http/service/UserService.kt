package com.hx.hub.http.service

import com.hx.hub.entity.ReceivedEvent
import com.hx.hub.entity.Repo
import com.hx.hub.entity.SearchResult
import com.hx.hub.entity.UserInfo
import com.hx.hub.entity.repodetail.RepoDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("user")
    suspend fun fetchUserOwner(): Response<UserInfo>

    @GET("users/{username}/received_events?")
    suspend fun queryReceivedEvents(@Path("username") username: String,
                                    @Query("page") pageIndex: Int,
                                    @Query("per_page") perPage: Int): List<ReceivedEvent>

    @GET("users/{username}/repos?")
    suspend fun queryRepos(@Path("username") username: String,
                           @Query("page") pageIndex: Int,
                           @Query("per_page") perPage: Int,
                           @Query("sort") sort: String): List<Repo>

    @GET("search/repositories")
    suspend fun search(@Query("q") q: String,
                       @Query("page") pageIndex: Int,
                       @Query("per_page") perPage: Int): SearchResult

    @GET("repos/{owner}/{repo_name}")
    suspend fun queryRepoDetail(@Path("owner") owner: String,
                                @Path("repo_name") repo_name: String): Response<RepoDetail>

}
