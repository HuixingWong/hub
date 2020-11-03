package com.hx.hub.http.service

data class ServiceManager(val userService: UserService,
                          val loginService: LoginService, val trendingService: TrendingService)
