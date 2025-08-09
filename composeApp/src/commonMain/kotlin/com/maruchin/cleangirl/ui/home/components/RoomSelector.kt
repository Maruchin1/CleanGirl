package com.maruchin.cleangirl.ui.home.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maruchin.cleangirl.data.model.Room
import com.maruchin.cleangirl.ui.roomeditor.RoomEditorBottomSheet
import com.maruchin.cleangirl.ui.utils.toImageVector

@Composable
fun RoomSelector(
    rooms: List<Room>,
    currentRoomIndex: Int,
    onRoomChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var isAddingRoom by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .then(modifier),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        rooms.forEachIndexed { index, room ->
            RoomChip(
                room = room,
                isSelected = index == currentRoomIndex,
                onClick = { onRoomChange(index) }
            )
        }
        NewRoomButton(onClick = { isAddingRoom = true })
    }

    if (isAddingRoom) {
        RoomEditorBottomSheet(onClose = { isAddingRoom = false })
    }
}

@Composable
private fun RoomChip(room: Room, isSelected: Boolean, onClick: () -> Unit) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = { Text(text = room.name) },
        leadingIcon = { Icon(imageVector = room.icon.toImageVector(), contentDescription = null) }
    )
}

@Composable
private fun NewRoomButton(onClick: () -> Unit) {
    AssistChip(
        onClick = onClick,
        label = { Text(text = "Nowy Pokój") },
        leadingIcon = { Icon(imageVector = Icons.Rounded.Add, contentDescription = null) }
    )
}
