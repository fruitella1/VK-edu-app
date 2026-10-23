package com.example.vkapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkapp.data.loadApps
import com.example.vkapp.events.AppListEvent
import com.example.vkapp.state.AppListUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppListViewModel : ViewModel() {
    private val _state: MutableStateFlow<AppListUiState> = MutableStateFlow(AppListUiState.Loading)

    val state: StateFlow<AppListUiState> = _state.asStateFlow()

    private val _eventChannel = Channel<AppListEvent>(Channel.BUFFERED)

    val events: Flow<AppListEvent> = _eventChannel.receiveAsFlow()

    init {
        _state.value = AppListUiState.Success(apps = loadApps())
    }

    fun onToolBarClick() {
        viewModelScope.launch {
            _eventChannel.send(AppListEvent.ShowSnackBar)
        }
    }
}