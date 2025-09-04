package com.maruchin.cleangirl.ui.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.FloatingToolbarScrollBehavior
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.maruchin.cleangirl.data.model.Room
import com.maruchin.cleangirl.ui.roomeditor.RoomEditorBottomSheet
import com.maruchin.cleangirl.ui.taskeditor.TaskEditorBottomSheet

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeToolbar(
    currentRoom: Room?,
    scrollBehavior: FloatingToolbarScrollBehavior,
    modifier: Modifier = Modifier
) {
    var isAddingTask by rememberSaveable { mutableStateOf(false) }
    var isEditingRoom by rememberSaveable { mutableStateOf(false) }

    HorizontalFloatingToolbar(
        expanded = true,
        floatingActionButton = {
            FloatingToolbarDefaults.StandardFloatingActionButton(onClick = {}) {
                Icon(imageVector = Icons.Rounded.Timer, contentDescription = null)
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    ) {
        IconButton(onClick = { isAddingTask = true }) {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
        }
        AnimatedVisibility(visible = currentRoom != null) {
            IconButton(onClick = { isEditingRoom = true }) {
                Icon(imageVector = Icons.Rounded.Edit, contentDescription = null)
            }
        }
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Rounded.BarChart, contentDescription = null)
        }
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Rounded.Person, contentDescription = null)
        }
    }

    if (isAddingTask && currentRoom != null) {
        TaskEditorBottomSheet(
            room = currentRoom,
            task = null,
            onClose = { isAddingTask = false })
    }
    if (isEditingRoom) {
        RoomEditorBottomSheet(room = currentRoom, onClose = { isEditingRoom = false })
    }
}
