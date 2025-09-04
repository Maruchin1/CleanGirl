package com.maruchin.cleangirl.ui.taskeditor.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maruchin.cleangirl.data.model.Recurrence
import com.maruchin.cleangirl.data.model.TimeOfDay
import com.maruchin.cleangirl.ui.utils.toText
import kotlinx.datetime.DayOfWeek

@Composable
fun RecurrenceSelector(
    modifier: Modifier = Modifier,
    state: RecurrenceSelectorState = rememberRecurrenceSelectorState()
) {
    Column(modifier = modifier) {
        RecurrenceTypeSelector(state = state)
        AnimatedContent(state.selectedRecurrence::class) { recurrenceType ->
            when (recurrenceType) {
                Recurrence.Daily::class -> TimeOfDaySelector(state = state)
                Recurrence.Weekly::class -> DayOfWeekSelector(state = state)
                Recurrence.Monthly::class -> DayOfMonthSelector(state = state)
            }
        }
    }
}

@Composable
private fun RecurrenceTypeSelector(state: RecurrenceSelectorState) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = "Powtarzaj co", style = MaterialTheme.typography.titleMedium)
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = state.isDailySelected,
                onClick = { state.selectDaily() },
                label = {
                    Text(text = "Dziennie")
                }
            )
            FilterChip(
                selected = state.isWeeklySelected,
                onClick = { state.selectWeekly() },
                label = {
                    Text(text = "Tygodniowo")
                }
            )
            FilterChip(
                selected = state.isMonthlySelected,
                onClick = { state.selectMonthly() },
                label = {
                    Text(text = "Miesięcznie")
                }
            )
        }
    }
}

@Composable
private fun TimeOfDaySelector(state: RecurrenceSelectorState) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = "Wybierz porę dnia", style = MaterialTheme.typography.titleMedium)
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TimeOfDay.entries.forEach { timeOfDay ->
                FilterChip(
                    selected = state.isTimeOfDaySelected(timeOfDay),
                    onClick = { state.toggleTimeOfDay(timeOfDay) },
                    label = {
                        Text(text = timeOfDay.toText())
                    }
                )
            }
        }
    }
}

@Composable
private fun DayOfWeekSelector(state: RecurrenceSelectorState) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = "Wybierz dni tygodnia", style = MaterialTheme.typography.titleMedium)
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DayOfWeek.entries.forEach { dayOfWeek ->
                FilterChip(
                    selected = state.isDayOfWeekSelected(dayOfWeek),
                    onClick = {
                        state.toggleDayOfWeek(dayOfWeek)
                    },
                    label = {
                        Text(text = dayOfWeek.toText())
                    }
                )
            }
        }
    }
}

@Composable
private fun DayOfMonthSelector(state: RecurrenceSelectorState) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(text = "Wybierz dni miesiąca", style = MaterialTheme.typography.titleMedium)
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            maxItemsInEachRow = 7
        ) {
            (1..31).chunked(7).forEach { week ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (week.size == 7) Arrangement.SpaceBetween else Arrangement.spacedBy(
                        4.dp
                    ),
                ) {
                    week.forEach { dayOfMonth ->
                        FilterChip(
                            selected = state.isDayOfMonthSelected(dayOfMonth),
                            onClick = { state.toggleDayOfMonth(dayOfMonth) },
                            label = {
                                Text(text = dayOfMonth.toString())
                            }
                        )
                    }
                }
            }
        }
    }
}
