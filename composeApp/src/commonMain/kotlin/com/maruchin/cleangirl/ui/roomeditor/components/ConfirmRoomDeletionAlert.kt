package com.maruchin.cleangirl.ui.roomeditor.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.maruchin.cleangirl.data.model.Room

@Composable
fun ConfirmRoomDeletionAlert(room: Room, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(Icons.Rounded.Delete, contentDescription = null)
        },
        title = {
            Text(text = "Usunąć ${room.name}?", textAlign = TextAlign.Center)
        },
        text = {
            Text(
                text = "Usunięty zostanie cały pokój, wszystkie zaplanowane dla niego zadania i cała historia.",
                textAlign = TextAlign.Center
            )
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
