package com.example.vkapp.data.repository

import com.example.vkapp.data.mapper.toDomain
import com.example.vkapp.data.remote.AppApi
import com.example.vkapp.domain.model.AppInfo
import com.example.vkapp.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val api: AppApi) :
    AppRepository {
    override suspend fun getApps(): List<AppInfo> {
        return api.getCatalog().map { it.toDomain() }
    }
}