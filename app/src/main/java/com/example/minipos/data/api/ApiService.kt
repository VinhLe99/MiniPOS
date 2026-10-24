package com.example.minipos.data.api

import com.example.minipos.data.model.LoginRequest
import com.example.minipos.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse
}