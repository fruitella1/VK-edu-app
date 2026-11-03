package com.example.vkapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vkapp.presentation.navigation.AppNavigation
import com.example.vkapp.ui.theme.VkAppTheme
import com.example.vkapp.presentation.viewmodel.AppListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: AppListViewModel = hiltViewModel()
            VkAppTheme {
                AppNavigation(viewModel)
            }
        }
    }
}