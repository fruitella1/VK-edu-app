package com.example.vkapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.vkapp.navigation.AppNavigation
import com.example.vkapp.ui.theme.VkAppTheme
import com.example.vkapp.viewmodel.AppListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: AppListViewModel by viewModels()
        setContent {
            VkAppTheme {
                AppNavigation(viewModel)
            }
        }
    }
}