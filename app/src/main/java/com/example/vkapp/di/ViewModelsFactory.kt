package com.example.vkapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.vkapp.domain.repository.AppRepository
import com.example.vkapp.presentation.viewmodel.AppListViewModel

class ViewModelsFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(AppListViewModel::class.java)) {
            return AppListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}