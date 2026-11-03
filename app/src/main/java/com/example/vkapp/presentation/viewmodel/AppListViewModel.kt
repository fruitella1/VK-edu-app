package com.example.vkapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkapp.domain.repository.AppRepository
import com.example.vkapp.presentation.events.AppListEvent
import com.example.vkapp.presentation.state.AppListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(repository: AppRepository) : ViewModel() {
    private val _state: MutableStateFlow<AppListUiState> = MutableStateFlow(AppListUiState.Loading)

    val state: StateFlow<AppListUiState> = _state.asStateFlow()

    private val _eventChannel = Channel<AppListEvent>(Channel.BUFFERED)

    val events: Flow<AppListEvent> = _eventChannel.receiveAsFlow()

    init {
        _state.value = AppListUiState.Success(apps = repository.getApps())
    }

    fun onToolBarClick() {
        viewModelScope.launch {
            _eventChannel.send(AppListEvent.ShowSnackBar)
        }
    }
}