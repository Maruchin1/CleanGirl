package com.maruchin.cleangirl.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NoTasksForTodayInfo(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            text = "Możesz odpocząć :) Nie masz nic zaplanowanego na dzisiaj",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
