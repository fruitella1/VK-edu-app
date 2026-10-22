package com.example.vkapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vkapp.data.loadApps
import com.example.vkapp.screens.AppCardScreen
import com.example.vkapp.screens.AppListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = APP_LIST_SCREEN,
    ) {
        composable(route = APP_LIST_SCREEN) {
            AppListScreen(onAppClick = { index -> navController.navigate("$APP_CARD_SCREEN/${index}") })
        }

        composable(route = APP_CARD_SCREEN_ROUTE, arguments = listOf(navArgument(APP_INDEX) {
            type =
                NavType.IntType
        })) { backStackEntry ->
            val appIndex = backStackEntry.arguments?.getInt(APP_INDEX) ?: -1
            val appList = loadApps()
            val app = appList.getOrNull(appIndex)
            if (app != null) {
                AppCardScreen(
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