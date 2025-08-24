package com.maruchin.cleangirl.ui.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskListHeader(text: String, modifier: Modifier = Modifier) {
    Surface(modifier = Modifier.fillMaxWidth().then(modifier)) {
        Text(text = text, style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(vertical = 8.dp))
    }
}
