package com.example.vkapp.state

import com.example.vkapp.data.AppInfo


sealed class AppListUiState {

    object Loading : AppListUiState()

    class Success(
        val apps: List<AppInfo> = emptyList()
    ) : AppListUiState()

    data class Error(
        val message: String = "error"
    ) : AppListUiState()
}
