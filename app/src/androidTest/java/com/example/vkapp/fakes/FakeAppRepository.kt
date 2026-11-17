package com.example.vkapp.fakes

import com.example.vkapp.domain.model.AppInfo
import com.example.vkapp.domain.repository.AppRepository
import kotlinx.coroutines.CompletableDeferred

class FakeAppRepository : AppRepository {
    var result: List<AppInfo> = emptyList()

    var exception: Throwable? = null

    var gate: CompletableDeferred<Unit>? = null

    var callCount: Int = 0
        private set

    override suspend fun getApps(): List<AppInfo> {
        callCount++
        gate?.await()
        exception?.let { throw it }
        return result
    }
}