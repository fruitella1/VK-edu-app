package com.example.vkapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.vkapp.data.repository.AppRepositoryImpl
import com.example.vkapp.data.source.LocalAppDataSource
import com.example.vkapp.di.ViewModelsFactory
import com.example.vkapp.presentation.navigation.AppNavigation
import com.example.vkapp.ui.theme.VkAppTheme
import com.example.vkapp.presentation.viewmodel.AppListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val source = LocalAppDataSource()
        val repository = AppRepositoryImpl(source)
        val viewModel: AppListViewModel by viewModels {
            ViewModelsFactory(repository)
        }
        setContent {
            VkAppTheme {
                AppNavigation(viewModel)
            }
        }
    }
}