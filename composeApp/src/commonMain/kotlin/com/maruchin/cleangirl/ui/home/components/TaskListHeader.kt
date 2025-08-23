package com.maruchin.cleangirl.ui.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TaskListHeader(text: String, modifier: Modifier = Modifier) {
    Surface(modifier = Modifier.fillMaxWidth().then(modifier)) {
        Text(text = text, style = MaterialTheme.typography.headlineSmall)
    }
}
