package com.maruchin.cleangirl.ui.taskeditor.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maruchin.cleangirl.data.model.Task

@Composable
fun TaskEditorTopBar(
    task: Task?,
    canSave: Boolean,
    onDelete: () -> Unit,
    onSave: () -> Unit
) {
    var isDeleting by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Zadanie",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.weight(1f)
        )
        AnimatedVisibility(task != null) {
            OutlinedButton(onClick = { isDeleting = true }) {
                Icon(imageVector = Icons.Rounded.Delete, contentDescription = null)
            }
        }
        AnimatedVisibility(canSave) {
            Button(onClick = onSave, enabled = canSave) {
                Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
            }
        }
    }

    if (isDeleting && task != null) {
        ConfirmTaskDeletionDialog(
            task = task,
            onDismiss = { isDeleting = false },
            onConfirm = onDelete
        )
    }
}
