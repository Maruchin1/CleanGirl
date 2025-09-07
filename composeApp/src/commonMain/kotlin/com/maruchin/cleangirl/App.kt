package com.maruchin.cleangirl

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.maruchin.cleangirl.ui.home.HomeRoute
import com.maruchin.cleangirl.ui.home.homeScreen
import com.maruchin.cleangirl.ui.theme.CleanGirlTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    CleanGirlTheme {
        val navController = rememberNavController()
        viewModel { AppViewModel() }

        NavHost(navController = navController, startDestination = HomeRoute) {
            homeScreen()
        }
    }
}