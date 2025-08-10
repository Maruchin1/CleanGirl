package com.maruchin.cleangirl.ui.home.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TaskListHeader(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = MaterialTheme.typography.headlineSmall, modifier = modifier)
}
