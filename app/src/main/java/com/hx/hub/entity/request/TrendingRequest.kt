package com.hx.hub.entity.request

data class TrendingRequestData(var language: String = "kotlin",
                               var since: String  = "daily", var spokenLanguage: String ?= null)