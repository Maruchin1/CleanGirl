package com.maruchin.cleangirl.ui.roomeditor.components

import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.maruchin.cleangirl.data.model.RoomType
import com.maruchin.cleangirl.ui.utils.toImageVector
import com.maruchin.cleangirl.ui.utils.toText

@Composable
fun RoomTypeChip(roomType: RoomType, onClick: () -> Unit) {
    SuggestionChip(
        onClick = onClick,
        label = {
            Text(text = roomType.toText())
        },
        icon = {
            Icon(imageVector = roomType.toImageVector(), contentDescription = null)
        }
    )
}