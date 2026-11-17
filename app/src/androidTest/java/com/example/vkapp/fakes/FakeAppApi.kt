package com.example.vkapp.fakes

import com.example.vkapp.data.dto.AppDto
import com.example.vkapp.data.remote.AppApi

class FakeAppApi : AppApi {

    var catalog: List<AppDto> = emptyList()
    var catalogException: Throwable? = null

    var appsById: Map<Int, AppDto> = emptyMap()

    override suspend fun getCatalog(): List<AppDto> {
        catalogException?.let { throw it }
        return catalog
    }

    override suspend fun getAppById(id: Int): AppDto =
        appsById[id] ?: throw NoSuchElementException("No app with id=$id")
}