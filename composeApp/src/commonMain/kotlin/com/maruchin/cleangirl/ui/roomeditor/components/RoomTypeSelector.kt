package com.maruchin.cleangirl.ui.roomeditor.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maruchin.cleangirl.data.model.RoomType

@Composable
fun RoomTypeSelector(onSelectRoom: (RoomType) -> Unit) {
    FlowRow(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RoomType.entries.forEach { roomType ->
            RoomTypeChip(
                roomType = roomType,
                onClick = { onSelectRoom(roomType) }
            )
        }
    }
}
