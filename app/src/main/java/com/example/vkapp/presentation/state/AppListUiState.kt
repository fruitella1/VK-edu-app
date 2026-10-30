package com.example.vkapp.presentation.state

import com.example.vkapp.domain.model.AppInfo


sealed class AppListUiState {

    object Loading : AppListUiState()

    class Success(
        val apps: List<AppInfo> = emptyList()
    ) : AppListUiState()

    data class Error(
        val message: String = "error"
    ) : AppListUiState()
}
