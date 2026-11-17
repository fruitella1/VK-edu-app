package com.example.vkapp.domain.usecases

import com.example.vkapp.domain.model.AppInfo
import com.example.vkapp.domain.repository.AppRepository
import javax.inject.Inject

class GetAppsUseCase @Inject constructor(private val repository: AppRepository) {
    suspend operator fun invoke(): List<AppInfo> {
        return repository.getApps()
    }
}