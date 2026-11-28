package com.example.vkapp.presentation.navigation

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vkapp.R
import com.example.vkapp.presentation.screens.AppCardScreen
import com.example.vkapp.presentation.screens.AppListScreen
import com.example.vkapp.presentation.state.AppDetailsUiState
import com.example.vkapp.presentation.state.AppListUiState
import com.example.vkapp.presentation.viewmodel.AppCardViewModel
import com.example.vkapp.presentation.viewmodel.AppListViewModel

@Composable
fun AppNavigation(viewModel: AppListViewModel) {
    val navController = rememberNavController()
    val state by viewModel.state.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = APP_LIST_SCREEN,
    ) {
        composable(route = APP_LIST_SCREEN) {
            AppListScreen(
                onAppClick = { index ->
                    val app = (state as? AppListUiState.Success)
                        ?.apps
                        ?.getOrNull(index)

                    if (app != null) {
                        navController.navigate("$APP_CARD_SCREEN/${Uri.encode(app.id)}")
                    }
                },
                state = state,
                events = viewModel.events,
                onToolBarClick = viewModel::onToolBarClick,
            )
        }

        composable(
            route = APP_CARD_SCREEN_ROUTE,
            arguments = listOf(
                navArgument(APP_ID) {
                    type = NavType.StringType
                },
            ),
        ) { backStackEntry ->
            val appCardViewModel: AppCardViewModel = hiltViewModel(backStackEntry)
            val installState by appCardViewModel.installState.collectAsStateWithLifecycle()
            val appDetails by appCardViewModel.appDetails.collectAsStateWithLifecycle()

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                when (val details = appDetails) {
                    AppDetailsUiState.Loading -> CircularProgressIndicator()

                    AppDetailsUiState.NotFound ->
                        Text(stringResource(R.string.app_not_found))

                    AppDetailsUiState.Error ->
                        Text(stringResource(R.string.error_message))

                    is AppDetailsUiState.Content -> AppCardScreen(
                        appList = (state as? AppListUiState.Success)?.apps.orEmpty(),
                        appInfo = details.app,
                        installState = installState,
                        onInstallClick = appCardViewModel::onInstallClick,
                        onBackClick = { navController.popBackStack() },
                        onShowCaseClick = { navController.popBackStack() },
                        onWishListClick = appCardViewModel::onWishListClick,
                    )
                }
            }
        }
    }
}

private const val APP_LIST_SCREEN = "AppListScreen"
private const val APP_CARD_SCREEN = "AppCardScreen"
private const val APP_ID = "appId"
private const val APP_CARD_SCREEN_ROUTE = "$APP_CARD_SCREEN/{$APP_ID}"