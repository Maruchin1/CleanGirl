package com.maruchin.cleangirl.ui.roomeditor.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldLabelPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maruchin.cleangirl.data.model.RoomType
import com.maruchin.cleangirl.ui.utils.toImageVector

@Composable
fun RoomNameField(roomName: TextFieldState, roomType: RoomType, modifier: Modifier = Modifier) {
    TextField(
        state = roomName,
        labelPosition = TextFieldLabelPosition.Above(),
        leadingIcon = {
            Icon(imageVector = roomType.toImageVector(), contentDescription = null)
        },
        modifier = Modifier.fillMaxWidth().then(modifier)
    )
}
