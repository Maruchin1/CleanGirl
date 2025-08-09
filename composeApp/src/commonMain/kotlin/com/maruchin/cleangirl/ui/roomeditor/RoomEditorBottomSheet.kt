package com.maruchin.cleangirl.ui.roomeditor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldLabelPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maruchin.cleangirl.data.model.NewRoom
import com.maruchin.cleangirl.data.model.RoomIcon
import com.maruchin.cleangirl.ui.theme.CleanGirlTheme
import com.maruchin.cleangirl.ui.utils.toImageVector
import com.maruchin.cleangirl.ui.utils.toText
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomEditorBottomSheet(onClose: () -> Unit) {
    val viewModel = viewModel { RoomEditorViewModel() }

    ModalBottomSheet(onDismissRequest = onClose) {
        RoomEditorContent(
            onSave = { newRoom ->
                viewModel.saveRoom(newRoom)
                onClose()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun RoomEditorContent(onSave: (NewRoom) -> Unit) {
    val roomName = rememberTextFieldState(
        initialText = RoomIcon.Home.toText()
    )
    var selectedIcon by rememberSaveable { mutableStateOf(RoomIcon.Home) }

    Surface(color = BottomSheetDefaults.ContainerColor) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Nowy Pokój",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = {
                        val newRoom = NewRoom(
                            name = roomName.text.toString(),
                            icon = selectedIcon
                        )
                        onSave(newRoom)
                    }
                ) {
                    Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
                }
            }
            TextField(
                state = roomName,
                labelPosition = TextFieldLabelPosition.Above(),
                leadingIcon = {
                    Icon(imageVector = selectedIcon.toImageVector(), contentDescription = null)
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp).fillMaxWidth()
            )
            FlowRow(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RoomIcon.entries.forEach { roomIcon ->
                    SuggestionChip(
                        onClick = {
                            roomName.clearText()
                            roomName.edit { append(roomIcon.toText()) }
                            selectedIcon = roomIcon
                        },
                        label = {
                            Text(text = roomIcon.toText())
                        },
                        icon = {
                            Icon(imageVector = roomIcon.toImageVector(), contentDescription = null)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun RoomEditorContentPreview() {
    CleanGirlTheme {
        RoomEditorContent(onSave = {})
    }
}