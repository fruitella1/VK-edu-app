package com.example.vkapp.presentation.state

import com.example.vkapp.domain.model.AppInfo

sealed interface AppDetailsUiState {
    data object Loading : AppDetailsUiState
    data object NotFound : AppDetailsUiState
    data object Error : AppDetailsUiState
    data class Content(val app: AppInfo) : AppDetailsUiState
}