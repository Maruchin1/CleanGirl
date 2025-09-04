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
fun TaskEditorBottomSheet(roomId: String, onClose: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val viewModel = viewModel { TaskEditorViewModel(roomId) }
    val scope = rememberCoroutineScope()

    ModalBottomSheet(onDismissRequest = onClose, sheetState = sheetState) {
        TaskEditorContent(
            onAddTask = {
                viewModel.saveTask(it)
                scope.launch {
                    sheetState.hide()
                    onClose()
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditorContent(
    onAddTask: (NewTask) -> Unit,
    taskName: TextFieldState = rememberTextFieldState(),
    recurrenceSelectorState: RecurrenceSelectorState = rememberRecurrenceSelectorState()
) {
    Surface(color = BottomSheetDefaults.ContainerColor) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TaskEditorTopBar(
                onSave = {
                    NewTask(
                        name = taskName.text.toString(),
                        recurrence = recurrenceSelectorState.selectedRecurrence
                    ).let(onAddTask)
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
            onAddTask = {},
            recurrenceSelectorState = rememberRecurrenceSelectorState(Recurrence.Daily())
        )
    }
}

@Preview
@Composable
fun TaskEditorContentPreview_Weekly() {
    CleanGirlTheme {
        TaskEditorContent(
            onAddTask = {},
            recurrenceSelectorState = rememberRecurrenceSelectorState(Recurrence.Weekly())
        )
    }
}

@Preview
@Composable
fun TaskEditorContentPreview_Monthly() {
    CleanGirlTheme {
        TaskEditorContent(
            onAddTask = {},
            recurrenceSelectorState = rememberRecurrenceSelectorState(Recurrence.Monthly())
        )
    }
}
