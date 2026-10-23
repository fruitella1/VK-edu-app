package com.example.vkapp.events

sealed interface AppListEvent {
    object ShowSnackBar: AppListEvent
}