package com.example.vkapp.domain.repository

import com.example.vkapp.domain.model.AppInfo

interface AppRepository {
    suspend fun getApps(): List<AppInfo>
}