package com.example.cs427_advanced_android

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SkylineServiceApi {
    @Headers("Content-Type: application/json")
    @POST("api/v1/users/login")
    fun login(): String
}