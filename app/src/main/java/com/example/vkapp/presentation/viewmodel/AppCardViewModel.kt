
package com.example.vkapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkapp.presentation.state.InstallState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppCardViewModel @Inject constructor() : ViewModel() {

    private val _installState = MutableStateFlow<InstallState>(
        InstallState.NotInstalled
    )
    val installState = _installState.asStateFlow()

    private var installJob: Job? = null

    fun onInstallClick() {
        if (installJob?.isActive == true) {
            installJob?.cancel()
            installJob = null
            _installState.value = InstallState.NotInstalled
        } else {
            _installState.value = InstallState.Installing(0)

            installJob = viewModelScope.launch {
                installApp()
            }
        }
    }

    private suspend fun installApp() {
        for (progress in 5..100 step 5) {
            delay(150)
            _installState.value = InstallState.Installing(progress)
        }

        _installState.value = InstallState.Installed
        installJob = null
    }
}
