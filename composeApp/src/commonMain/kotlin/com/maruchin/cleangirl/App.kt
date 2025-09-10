package com.maruchin.cleangirl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.maruchin.cleangirl.ui.home.HomeRoute
import com.maruchin.cleangirl.ui.home.homeScreen
import com.maruchin.cleangirl.ui.home.navigateToHome
import com.maruchin.cleangirl.ui.theme.CleanGirlTheme
import com.maruchin.cleangirl.ui.welcome.WelcomeRoute
import com.maruchin.cleangirl.ui.welcome.welcomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    CleanGirlTheme {
        val navController = rememberNavController()
        val viewModel = viewModel { AppViewModel() }
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val isUserConfigured = uiState.isUserConfigured

        if (isUserConfigured != null) {
            NavHost(
                navController = navController,
                startDestination = if (isUserConfigured) HomeRoute else WelcomeRoute
            ) {
                welcomeScreen(
                    onGoToApp = { navController.navigateToHome() }
                )
                homeScreen()
            }
        }
    }
}