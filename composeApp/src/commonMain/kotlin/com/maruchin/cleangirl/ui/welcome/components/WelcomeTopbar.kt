package com.maruchin.cleangirl.ui.welcome.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LargeFlexibleTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun WelcomeTopBar(modifier: Modifier = Modifier) {
    LargeFlexibleTopAppBar(
        title = {
            Text(text = "Clean Girl")
        },
        subtitle = {
            Text(text = "Uporządkuj swój dzień")
        },
        titleHorizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    )
}