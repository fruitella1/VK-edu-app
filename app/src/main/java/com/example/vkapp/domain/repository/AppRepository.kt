package com.example.vkapp.domain.repository

import com.example.vkapp.domain.model.AppInfo

interface AppRepository {
    fun getApps(): List<AppInfo>
}