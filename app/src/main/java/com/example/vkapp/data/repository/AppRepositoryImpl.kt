package com.example.vkapp.data.repository

import com.example.vkapp.data.mapper.toDomain
import com.example.vkapp.data.remote.AppApi
import com.example.vkapp.data.remote.RatingApi
import com.example.vkapp.domain.model.AppInfo
import com.example.vkapp.domain.repository.AppRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: AppApi,
    private val ratingApi: RatingApi
) : AppRepository {

    override suspend fun getApps(): List<AppInfo> {
        return api.getCatalog().map { it.toDomain() }
    }

    override suspend fun getRatingsSequentially(
        ids: List<String>
    ): Map<String, Float> {
        val ratings = mutableMapOf<String, Float>()

        for (id in ids) {
            ratings[id] = ratingApi.getRating(id)
        }

        return ratings
    }

    override suspend fun getRatingsParallel(
        ids: List<String>
    ): Map<String, Float> = coroutineScope {
        ids.map { id ->
            async {
                id to ratingApi.getRating(id)
            }
        }.awaitAll().toMap()
    }
}
