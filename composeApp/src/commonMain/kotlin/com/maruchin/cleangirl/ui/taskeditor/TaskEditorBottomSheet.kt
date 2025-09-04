package com.maruchin.cleangirl.ui.taskeditor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
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
import com.maruchin.cleangirl.ui.taskeditor.components.RecurrenceSelectorState
import com.maruchin.cleangirl.ui.taskeditor.components.TaskEditorTopBar
import com.maruchin.cleangirl.ui.taskeditor.components.TaskNameField
import com.maruchin.cleangirl.ui.taskeditor.components.rememberRecurrenceSelectorState
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
    taskName: TextFieldState = rememberTextFieldState(task?.name.orEmpty()),
    recurrenceSelectorState: RecurrenceSelectorState = rememberRecurrenceSelectorState(
        task?.recurrence ?: Recurrence.Daily.default
    )
) {
    Surface(color = BottomSheetDefaults.ContainerColor) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TaskEditorTopBar(
                canSave = taskName.text.isNotBlank() &&
                        recurrenceSelectorState.selectedRecurrence.isValid,
                onSave = {
                    if (task == null) {
                        NewTask(
                            name = taskName.text.toString(),
                            recurrence = recurrenceSelectorState.selectedRecurrence
                        ).let(onAddTask)
                    } else {
                        UpdatedTask(
                            id = task.id,
                            name = taskName.text.toString(),
                            recurrence = recurrenceSelectorState.selectedRecurrence
                        ).let(onUpdateTask)
                    }
                }
            )
            TaskNameField(taskName = taskName)
            RecurrenceSelector(state = recurrenceSelectorState)
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
            recurrenceSelectorState = rememberRecurrenceSelectorState(Recurrence.Daily.default)
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
            recurrenceSelectorState = rememberRecurrenceSelectorState(Recurrence.Weekly.default)
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
            recurrenceSelectorState = rememberRecurrenceSelectorState(Recurrence.Monthly.default)
        )
    }
}
