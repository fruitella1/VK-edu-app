package com.example.vkapp.presentation.state

sealed class InstallState {
    data object NotInstalled : InstallState()
    class Installing(val progress: Int) : InstallState()
    data object Installed : InstallState()
}