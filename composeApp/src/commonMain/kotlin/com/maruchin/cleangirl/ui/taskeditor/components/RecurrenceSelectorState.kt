package com.maruchin.cleangirl.ui.taskeditor.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.maruchin.cleangirl.data.model.Recurrence
import com.maruchin.cleangirl.data.model.TimeOfDay
import kotlinx.datetime.DayOfWeek

@Stable
class RecurrenceSelectorState(recurrence: Recurrence) {

    private var daily by mutableStateOf(Recurrence.Daily())
    private var weekly by mutableStateOf(Recurrence.Weekly())
    private var monthly by mutableStateOf(Recurrence.Monthly())

    var selectedRecurrence: Recurrence by mutableStateOf(recurrence)
        private set

    val isDailySelected: Boolean
        get() = selectedRecurrence == daily

    val isWeeklySelected: Boolean
        get() = selectedRecurrence == weekly

    val isMonthlySelected: Boolean
        get() = selectedRecurrence == monthly

    init {
        when (recurrence) {
            is Recurrence.Daily -> daily = recurrence
            is Recurrence.Weekly -> weekly = recurrence
            is Recurrence.Monthly -> monthly = recurrence
        }
    }

    fun isTimeOfDaySelected(timeOfDay: TimeOfDay): Boolean = timeOfDay in daily.timesOfDay

    fun isDayOfWeekSelected(dayOfWeek: DayOfWeek): Boolean = dayOfWeek in weekly.daysOfWeek

    fun isDayOfMonthSelected(dayOfMonth: Int): Boolean = dayOfMonth in monthly.daysOfMoth

    fun toggleTimeOfDay(timeOfDay: TimeOfDay) {
        daily = daily.toggleTimeOfDay(timeOfDay)
        selectedRecurrence = daily
    }

    fun toggleDayOfWeek(dayOfWeek: DayOfWeek) {
        weekly = weekly.toggleDayOfWeek(dayOfWeek)
        selectedRecurrence = weekly
    }

    fun toggleDayOfMonth(dayOfMonth: Int) {
        monthly = monthly.toggleDayOfMonth(dayOfMonth)
        selectedRecurrence = monthly
    }

    fun selectDaily() {
        selectedRecurrence = daily
    }

    fun selectWeekly() {
        selectedRecurrence = weekly
    }

    fun selectMonthly() {
        selectedRecurrence = monthly
    }
}

@Composable
fun rememberRecurrenceSelectorState(recurrence: Recurrence = Recurrence.Daily()): RecurrenceSelectorState {
    return remember(recurrence) { RecurrenceSelectorState(recurrence) }
}
