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
import com.maruchin.cleangirl.ui.taskeditor.TaskEditorFormState
import com.maruchin.cleangirl.ui.utils.toText
import kotlinx.datetime.DayOfWeek

@Composable
fun RecurrenceSelector(formState: TaskEditorFormState, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        RecurrenceTypeSelector(formState = formState)
        AnimatedContent(formState.recurrence::class) { recurrenceType ->
            when (recurrenceType) {
                Recurrence.Daily::class -> Unit
                Recurrence.Weekly::class -> DayOfWeekSelector(formState = formState)
                Recurrence.Monthly::class -> DayOfMonthSelector(formState = formState)
            }
        }
    }
}

@Composable
private fun RecurrenceTypeSelector(formState: TaskEditorFormState) {
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
                selected = formState.isDailySelected,
                onClick = { formState.selectDaily() },
                label = {
                    Text(text = "Dziennie")
                }
            )
            FilterChip(
                selected = formState.isWeeklySelected,
                onClick = { formState.selectWeekly() },
                label = {
                    Text(text = "Tygodniowo")
                }
            )
            FilterChip(
                selected = formState.isMonthlySelected,
                onClick = { formState.selectMonthly() },
                label = {
                    Text(text = "Miesięcznie")
                }
            )
        }
    }
}

@Composable
private fun DayOfWeekSelector(formState: TaskEditorFormState) {
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
                    selected = formState.isDayOfWeekSelected(dayOfWeek),
                    onClick = { formState.toggleDayOfWeek(dayOfWeek) },
                    label = {
                        Text(text = dayOfWeek.toText())
                    }
                )
            }
        }
    }
}

@Composable
private fun DayOfMonthSelector(formState: TaskEditorFormState) {
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
                            selected = formState.isDayOfMonthSelected(dayOfMonth),
                            onClick = { formState.toggleDayOfMonth(dayOfMonth) },
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
