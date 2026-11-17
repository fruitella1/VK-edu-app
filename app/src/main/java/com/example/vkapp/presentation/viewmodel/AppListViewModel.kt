package com.example.vkapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkapp.domain.repository.AppRepository
import com.example.vkapp.presentation.events.AppListEvent
import com.example.vkapp.presentation.state.AppListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _state: MutableStateFlow<AppListUiState> =
        MutableStateFlow(AppListUiState.Loading)

    val state: StateFlow<AppListUiState> = _state.asStateFlow()

    private val _eventChannel = Channel<AppListEvent>(Channel.BUFFERED)

    val events: Flow<AppListEvent> = _eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            _state.value = AppListUiState.Loading

            try {
                val apps = repository.getApps()

                _state.value = AppListUiState.Success(apps = apps)

                loadRatings(apps.map { it.id })

            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _state.value = AppListUiState.Error()
            }
        }
    }

    private suspend fun loadRatings(ids: List<String>) {
        // Последовательная загрузка
        try {
            val sequentialTime = measureTimeMillis {
                repository.getRatingsSequentially(ids)
            }

            Log.d("AppListViewModel", "Sequential: $sequentialTime ms")

        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("AppListViewModel", "Sequential loading failed", e)
        }

        // Параллельная загрузка
        try {
            val parallelRatings: Map<String, Float>

            val parallelTime = measureTimeMillis {
                parallelRatings = repository.getRatingsParallel(ids)
            }

            Log.d("AppListViewModel", "Parallel: $parallelTime ms")

            val currentState = _state.value

            if (currentState is AppListUiState.Success) {
                _state.value = currentState.copy(ratings = parallelRatings)
            }

        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("AppListViewModel", "Parallel loading failed", e)
        }
    }

    fun onToolBarClick() {
        viewModelScope.launch {
            _eventChannel.send(AppListEvent.ShowSnackBar)
        }
    }
}