package com.maruchin.cleangirl.ui.welcome

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object WelcomeRoute

fun NavGraphBuilder.welcomeScreen(onGoToApp: () -> Unit) {
    composable<WelcomeRoute> {
        val viewModel = viewModel { WelcomeViewModel() }

        WelcomeScreen(
            onGoToApp = {
                viewModel.saveUserName(it)
                onGoToApp()
            }
        )
    }
}