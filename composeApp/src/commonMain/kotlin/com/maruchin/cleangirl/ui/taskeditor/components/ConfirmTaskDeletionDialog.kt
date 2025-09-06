package com.maruchin.cleangirl.ui.taskeditor.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.maruchin.cleangirl.data.model.Task

@Composable
fun ConfirmTaskDeletionDialog(task: Task, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(imageVector = Icons.Rounded.Delete, contentDescription = null)
        },
        title = {
            Text(text = "Usunąć zadanie?")
        },
        text = {
            Text(text = "Zadanie \"${task.name}\" zostanie usunięte wraz z całą jego historią.")
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = "Usuń")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Anuluj")
            }
        }
    )
}
