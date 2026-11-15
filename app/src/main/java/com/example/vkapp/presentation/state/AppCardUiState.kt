package com.example.vkapp.presentation.state

import com.example.vkapp.domain.model.AppInfo

sealed class AppCardUiState {

    object Loading : AppCardUiState()

    class Success(
        val app: AppInfo, val installState: InstallState
    ) : AppCardUiState()

    data class Error(
        val message: String = "error"
    ) : AppCardUiState()
}