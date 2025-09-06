package com.maruchin.cleangirl.ui.taskeditor

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.maruchin.cleangirl.data.model.NewTask
import com.maruchin.cleangirl.data.model.Recurrence
import com.maruchin.cleangirl.data.model.Task
import com.maruchin.cleangirl.data.model.UpdatedTask
import kotlinx.datetime.DayOfWeek

@Stable
class TaskEditorFormState(
    val taskName: TextFieldState,
    initialRecurrence: Recurrence,
) {

    private var daily by mutableStateOf(Recurrence.Daily)
    private var weekly by mutableStateOf(Recurrence.Weekly())
    private var monthly by mutableStateOf(Recurrence.Monthly())

    var recurrence by mutableStateOf(initialRecurrence)
        private set

    val isDailySelected: Boolean
        get() = recurrence == daily

    val isWeeklySelected: Boolean
        get() = recurrence == weekly

    val isMonthlySelected: Boolean
        get() = recurrence == monthly

    val isValid: Boolean
        get() = taskName.text.isNotBlank() && recurrence.isValid

    init {
        when (initialRecurrence) {
            is Recurrence.Daily -> daily = initialRecurrence
            is Recurrence.Weekly -> weekly = initialRecurrence
            is Recurrence.Monthly -> monthly = initialRecurrence
        }
    }

    fun isDayOfWeekSelected(dayOfWeek: DayOfWeek): Boolean = dayOfWeek in weekly.daysOfWeek

    fun isDayOfMonthSelected(dayOfMonth: Int): Boolean = dayOfMonth in monthly.daysOfMoth

    fun toggleDayOfWeek(dayOfWeek: DayOfWeek) {
        weekly = weekly.toggleDayOfWeek(dayOfWeek)
        recurrence = weekly
    }

    fun toggleDayOfMonth(dayOfMonth: Int) {
        monthly = monthly.toggleDayOfMonth(dayOfMonth)
        recurrence = monthly
    }

    fun selectDaily() {
        recurrence = daily
    }

    fun selectWeekly() {
        recurrence = weekly
    }

    fun selectMonthly() {
        recurrence = monthly
    }

    fun createNewTask() = NewTask(
        name = taskName.text.toString(),
        recurrence = recurrence
    )

    fun createUpdatedTask(task: Task) = UpdatedTask(
        id = task.id,
        name = taskName.text.toString(),
        recurrence = recurrence
    )
}

@Composable
fun rememberTaskEditorFormState(
    initialTaskName: String = "",
    initialRecurrence: Recurrence = Recurrence.Daily
): TaskEditorFormState {
    val taskName = rememberTextFieldState(initialTaskName)
    return remember(taskName) {
        TaskEditorFormState(taskName, initialRecurrence)
    }
}
