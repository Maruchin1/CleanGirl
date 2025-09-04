package com.maruchin.cleangirl.ui.taskeditor.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskEditorTopBar(canSave: Boolean, onSave: () -> Unit) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Zadanie",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.weight(1f)
        )
        Button(onClick = onSave, enabled = canSave) {
            Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
        }
    }
}
