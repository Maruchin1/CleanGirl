package com.maruchin.cleangirl.ui.home

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maruchin.cleangirl.ui.welcome.WelcomeRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavController.navigateToHome() {
    navigate(HomeRoute) {
        popUpTo(WelcomeRoute) {
            inclusive = true
        }
    }
}

fun NavGraphBuilder.homeScreen() {
    composable<HomeRoute> {
        val viewModel = viewModel { HomeViewModel() }
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        HomeScreen(
            state = state,
            onTaskCompleteChange = viewModel::toggleTaskCompleted
        )
    }
}