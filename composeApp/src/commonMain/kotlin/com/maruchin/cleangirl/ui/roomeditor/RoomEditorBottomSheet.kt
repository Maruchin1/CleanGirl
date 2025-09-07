package com.maruchin.cleangirl.ui.roomeditor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maruchin.cleangirl.data.model.NewRoom
import com.maruchin.cleangirl.data.model.Room
import com.maruchin.cleangirl.data.model.RoomType
import com.maruchin.cleangirl.data.model.UpdatedRoom
import com.maruchin.cleangirl.data.model.sampleRoomLivingRoom
import com.maruchin.cleangirl.ui.roomeditor.components.ConfirmRoomDeletionAlert
import com.maruchin.cleangirl.ui.roomeditor.components.RoomEditorTopBar
import com.maruchin.cleangirl.ui.roomeditor.components.RoomNameField
import com.maruchin.cleangirl.ui.roomeditor.components.RoomTypeSelector
import com.maruchin.cleangirl.ui.theme.CleanGirlTheme
import com.maruchin.cleangirl.ui.utils.toText
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomEditorBottomSheet(room: Room?, onClose: () -> Unit) {
    val viewModel = viewModel { RoomEditorViewModel() }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    fun hideAndClose() = scope.launch {
        sheetState.hide()
        onClose()
    }

    ModalBottomSheet(sheetState = sheetState, onDismissRequest = onClose) {
        RoomEditorContent(
            room = room,
            onAdd = { newRoom ->
                viewModel.addRoom(newRoom)
                hideAndClose()
            },
            onEdit = { updatedRoom ->
                viewModel.updateRoom(updatedRoom)
                hideAndClose()
            },
            onDelete = { room ->
                viewModel.deleteRoom(room)
                hideAndClose()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun RoomEditorContent(
    room: Room?,
    onAdd: (NewRoom) -> Unit,
    onEdit: (UpdatedRoom) -> Unit,
    onDelete: (Room) -> Unit
) {
    val roomName = rememberTextFieldState(
        initialText = room?.name ?: RoomType.Default.toText()
    )
    var roomType by rememberSaveable(room) { mutableStateOf(room?.type ?: RoomType.Default) }
    var confirmRoomDeletion by rememberSaveable { mutableStateOf(false) }

    Surface(color = BottomSheetDefaults.ContainerColor) {
        Column(modifier = Modifier.fillMaxWidth()) {
            RoomEditorTopBar(
                room = room,
                onDelete = { confirmRoomDeletion = true },
                onSave = {
                    if (room == null) {
                        NewRoom(
                            name = roomName.text.toString(),
                            type = roomType
                        ).let(onAdd)
                    } else {
                        UpdatedRoom(
                            id = room.id,
                            name = roomName.text.toString(),
                            type = roomType
                        ).let(onEdit)
                    }
                }
            )
            RoomNameField(
                roomName = roomName,
                roomType = roomType,
            )
            RoomTypeSelector(
                onSelectRoom = {
                    roomName.clearText()
                    roomName.edit { append(it.toText()) }
                    roomType = it
                }
            )
        }
    }

    if (room != null && confirmRoomDeletion) {
        ConfirmRoomDeletionAlert(
            room = room,
            onDismiss = { confirmRoomDeletion = false },
            onConfirm = {
                confirmRoomDeletion = false
                onDelete(room)
            }
        )
    }
}

@Preview
@Composable
private fun RoomEditorContentPreview_NewRoom() {
    CleanGirlTheme {
        RoomEditorContent(room = null, onAdd = {}, onEdit = {}, onDelete = {})
    }
}

@Preview
@Composable
private fun RoomEditorContentPreview_EditRoom() {
    CleanGirlTheme {
        RoomEditorContent(room = sampleRoomLivingRoom, onAdd = {}, onEdit = {}, onDelete = {})
    }
}