package com.example.vkapp.presentation.state

import com.example.vkapp.domain.model.AppInfo

sealed class AppListUiState {

    object Loading : AppListUiState()

    data class Success(
        val apps: List<AppInfo> = emptyList(),
        val ratings: Map<String, Float> = emptyMap()
    ) : AppListUiState()

    data class Error(
        val message: String = "error"
    ) : AppListUiState()
}