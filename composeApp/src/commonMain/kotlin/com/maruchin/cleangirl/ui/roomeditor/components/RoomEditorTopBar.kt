package com.maruchin.cleangirl.ui.roomeditor.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maruchin.cleangirl.data.model.Room

@Composable
fun RoomEditorTopBar(room: Room?, onDelete: () -> Unit, onSave: () -> Unit) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Pokój",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.weight(1f)
        )
        if (room != null) {
            OutlinedButton(onClick = onDelete, modifier = Modifier.padding(8.dp)) {
                Icon(imageVector = Icons.Rounded.Delete, contentDescription = null)
            }
        }
        Button(onClick = onSave) {
            Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
        }
    }
}
