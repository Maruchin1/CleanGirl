package com.maruchin.cleangirl.ui.taskeditor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maruchin.cleangirl.data.model.NewTask
import com.maruchin.cleangirl.data.model.Recurrence
import com.maruchin.cleangirl.data.model.Room
import com.maruchin.cleangirl.data.model.Task
import com.maruchin.cleangirl.data.model.UpdatedTask
import com.maruchin.cleangirl.ui.taskeditor.components.RecurrenceSelector
import com.maruchin.cleangirl.ui.taskeditor.components.TaskEditorTopBar
import com.maruchin.cleangirl.ui.taskeditor.components.TaskNameField
import com.maruchin.cleangirl.ui.theme.CleanGirlTheme
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditorBottomSheet(room: Room, task: Task?, onClose: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val viewModel = viewModel { TaskEditorViewModel(room) }
    val scope = rememberCoroutineScope()

    fun hideAndClose() = scope.launch {
        sheetState.hide()
        onClose()
    }

    ModalBottomSheet(onDismissRequest = onClose, sheetState = sheetState) {
        TaskEditorContent(
            task = task,
            onAddTask = { newTask ->
                viewModel.addTask(newTask)
                hideAndClose()
            },
            onUpdateTask = { updatedTask ->
                viewModel.updateTask(updatedTask)
                hideAndClose()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditorContent(
    task: Task?,
    onAddTask: (NewTask) -> Unit,
    onUpdateTask: (UpdatedTask) -> Unit,
    formState: TaskEditorFormState = rememberTaskEditorFormState(
        initialTaskName = task?.name.orEmpty(),
        initialRecurrence = task?.recurrence ?: Recurrence.Daily
    )
) {
    Surface(color = BottomSheetDefaults.ContainerColor) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TaskEditorTopBar(
                canSave = formState.isValid,
                onSave = {
                    if (task == null) {
                        onAddTask(formState.createNewTask())
                    } else {
                        onUpdateTask(formState.createUpdatedTask(task))
                    }
                }
            )
            TaskNameField(taskName = formState.taskName)
            RecurrenceSelector(formState = formState)
        }
    }
}

@Preview
@Composable
fun TaskEditorContentPreview_Daily() {
    CleanGirlTheme {
        TaskEditorContent(
            task = null,
            onAddTask = {},
            onUpdateTask = {},
            formState = rememberTaskEditorFormState(initialRecurrence = Recurrence.Daily)
        )
    }
}

@Preview
@Composable
fun TaskEditorContentPreview_Weekly() {
    CleanGirlTheme {
        TaskEditorContent(
            task = null,
            onAddTask = {},
            onUpdateTask = {},
            formState = rememberTaskEditorFormState(initialRecurrence = Recurrence.Weekly())
        )
    }
}

@Preview
@Composable
fun TaskEditorContentPreview_Monthly() {
    CleanGirlTheme {
        TaskEditorContent(
            task = null,
            onAddTask = {},
            onUpdateTask = {},
            formState = rememberTaskEditorFormState(initialRecurrence = Recurrence.Monthly())
        )
    }
}
