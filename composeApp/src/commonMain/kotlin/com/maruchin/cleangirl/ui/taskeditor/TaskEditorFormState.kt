package com.maruchin.cleangirl.ui.taskeditor

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.maruchin.cleangirl.core.utils.toggle
import com.maruchin.cleangirl.data.model.NewTask
import com.maruchin.cleangirl.data.model.Recurrence
import com.maruchin.cleangirl.data.model.Room
import com.maruchin.cleangirl.data.model.Task
import com.maruchin.cleangirl.data.model.UpdatedTask
import kotlinx.datetime.DayOfWeek

@Stable
class TaskEditorFormState(
    val taskName: TextFieldState,
    initialRecurrence: Recurrence,
    initialDaysOfWeek: Set<DayOfWeek>,
    initialDaysOfMonth: Set<Int>
) {

    private var daysOfWeek by mutableStateOf(initialDaysOfWeek)
    private var daysOfMonth by mutableStateOf(initialDaysOfMonth)

    var recurrence by mutableStateOf(initialRecurrence)

    val isValid: Boolean
        get() = isTaskNameValid && isRecurrenceValid

    private val isTaskNameValid: Boolean
        get() = taskName.text.isNotBlank()

    private val isRecurrenceValid: Boolean
        get() = when (recurrence) {
            Recurrence.Daily -> true
            Recurrence.Weekly -> daysOfWeek.isNotEmpty()
            Recurrence.Monthly -> daysOfMonth.isNotEmpty()
        }

    fun isDayOfWeekSelected(dayOfWeek: DayOfWeek): Boolean = dayOfWeek in daysOfWeek

    fun isDayOfMonthSelected(dayOfMonth: Int): Boolean = dayOfMonth in daysOfMonth

    fun toggleDayOfWeek(dayOfWeek: DayOfWeek) {
        daysOfWeek = daysOfWeek.toggle(dayOfWeek)
    }

    fun toggleDayOfMonth(dayOfMonth: Int) {
        daysOfMonth = daysOfMonth.toggle(dayOfMonth)
    }

    fun createNewTask(room: Room) = NewTask(
        roomId = room.id,
        name = taskName.text.toString(),
        recurrence = recurrence,
        daysOfWeek = daysOfWeek,
        daysOfMonth = daysOfMonth,
    )

    fun createUpdatedTask(room: Room, task: Task) = UpdatedTask(
        roomId = room.id,
        id = task.id,
        name = taskName.text.toString(),
        recurrence = recurrence,
        daysOfWeek = daysOfWeek,
        daysOfMonth = daysOfMonth,
    )
}

@Composable
fun rememberTaskEditorFormState(
    initialTaskName: String = "",
    initialRecurrence: Recurrence = Recurrence.Daily,
    initialDaysOfWeek: Set<DayOfWeek> = emptySet(),
    initialDaysOfMonth: Set<Int> = emptySet()
): TaskEditorFormState {
    val taskName = rememberTextFieldState(initialTaskName)
    return remember(taskName, initialRecurrence, initialDaysOfMonth) {
        TaskEditorFormState(taskName, initialRecurrence, initialDaysOfWeek, initialDaysOfMonth)
    }
}
