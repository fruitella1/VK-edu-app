package com.example.vkapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vkapp.presentation.screens.AppCardScreen
import com.example.vkapp.presentation.screens.AppListScreen
import com.example.vkapp.presentation.state.AppListUiState
import com.example.vkapp.presentation.viewmodel.AppListViewModel

@Composable
fun AppNavigation(viewModel: AppListViewModel) {
    val navController = rememberNavController()

    val state by viewModel.state.collectAsStateWithLifecycle()
    val events = viewModel.events

    NavHost(
        navController = navController,
        startDestination = APP_LIST_SCREEN,
    ) {
        composable(route = APP_LIST_SCREEN) {
            AppListScreen(
                onAppClick = { index -> navController.navigate("$APP_CARD_SCREEN/${index}") },
                state = state,
                events = events,
                onToolBarClick = { viewModel.onToolBarClick() }
            )
        }

        composable(route = APP_CARD_SCREEN_ROUTE, arguments = listOf(navArgument(APP_INDEX) {
            type =
                NavType.IntType
        })) { backStackEntry ->
            val appIndex = backStackEntry.arguments?.getInt(APP_INDEX) ?: -1
            val appList = state as? AppListUiState.Success
            val app = appList?.apps?.getOrNull(appIndex)
            if (app != null) {
                AppCardScreen(
                    appList = appList.apps,
                    appInfo = app,
                    onBackClick = { navController.popBackStack() },
                    onShowCaseClick = { navController.popBackStack() })
            }
        }
    }
}

private const val APP_CARD_SCREEN_ROUTE = "AppCardScreen/{app_index}"
private const val APP_LIST_SCREEN = "AppListScreen"
private const val APP_INDEX = "app_index"
private const val APP_CARD_SCREEN = "AppCardScreen"