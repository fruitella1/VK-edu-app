package com.example.vkapp.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkapp.domain.model.AppInfo
import com.example.vkapp.domain.repository.AppRepository
import com.example.vkapp.presentation.state.AppDetailsUiState
import com.example.vkapp.presentation.state.InstallState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppCardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: AppRepository,
) : ViewModel() {

    private val _installState = MutableStateFlow<InstallState>(
        InstallState.NotInstalled
    )
    val installState = _installState.asStateFlow()

    private var installJob: Job? = null

    private val appId = checkNotNull(savedStateHandle.get<String>("appId"))

    val appDetails: StateFlow<AppDetailsUiState> = repository
        .observeAppDetails(appId)
        .map<AppInfo?, AppDetailsUiState> { app ->
            if (app == null) {
                AppDetailsUiState.NotFound
            } else {
                AppDetailsUiState.Content(app)
            }
        }
        .catch { emit(AppDetailsUiState.Error) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AppDetailsUiState.Loading,
        )

    fun onWishListClick(){
       viewModelScope.launch {
           repository.toggleWishList(appId)
       }
    }

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
