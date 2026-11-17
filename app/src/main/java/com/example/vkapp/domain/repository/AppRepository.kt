package com.example.vkapp.domain.repository

import com.example.vkapp.domain.model.AppInfo

interface AppRepository {

    suspend fun getApps(): List<AppInfo>

    suspend fun getRatingsSequentially(ids: List<String>): Map<String, Float>

    suspend fun getRatingsParallel(ids: List<String>): Map<String, Float>
}
