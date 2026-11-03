package com.example.vkapp.presentation.events

sealed interface AppListEvent {
    object ShowSnackBar: AppListEvent
}