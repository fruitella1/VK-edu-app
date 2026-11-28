package com.example.vkapp.domain.repository

import com.example.vkapp.domain.model.AppInfo
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    suspend fun getApps(): List<AppInfo>

    suspend fun getRatingsSequentially(ids: List<String>): Map<String, Float>

    suspend fun getRatingsParallel(ids: List<String>): Map<String, Float>

    fun observeAppDetails(id: String): Flow<AppInfo?>

    suspend fun toggleWishList(id: String)
}
