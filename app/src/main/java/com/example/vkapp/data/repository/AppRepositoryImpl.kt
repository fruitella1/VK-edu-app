package com.example.vkapp.data.repository

import com.example.vkapp.data.mapper.toDomain
import com.example.vkapp.data.source.LocalAppDataSource
import com.example.vkapp.domain.model.AppInfo
import com.example.vkapp.domain.repository.AppRepository

class AppRepositoryImpl(private val source: LocalAppDataSource) : AppRepository {
    override fun getApps(): List<AppInfo> {
        return source.getApps().map { it.toDomain() }
    }
}