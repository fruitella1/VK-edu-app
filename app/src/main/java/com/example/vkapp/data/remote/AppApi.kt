package com.example.vkapp.data.remote

import com.example.vkapp.data.dto.AppDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {
    @GET("catalog")
    suspend fun getCatalog(): List<AppDto>

    @GET("app/{id}")
    suspend fun getAppById(@Path("id") id: Int): AppDto
}