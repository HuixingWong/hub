package com.hx.hub.http.service

import com.hx.hub.entity.UserAccessToken
import com.hx.hub.http.service.bean.LoginRequestModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {

    @POST("authorizations")
    @Headers("Accept: application/json")
    suspend fun authorizations(@Body authRequestModel: LoginRequestModel): Response<UserAccessToken>
}