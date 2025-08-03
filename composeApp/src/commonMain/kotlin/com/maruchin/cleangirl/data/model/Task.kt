package com.maruchin.cleangirl.data.model

import kotlinx.datetime.LocalDate

data class Task(
    val id: String,
    val name: String,
    val recurrence: Recurrence,
    val records: List<LocalDate>
) {

    fun isCompleted(day: LocalDate): Boolean {
        return records.any { it == day }
    }

    fun complete(day: LocalDate) = copy(
        records = records + day
    )

    fun isPlannedForDay(day: LocalDate): Boolean {
        return when (recurrence) {
            is Recurrence.Daily -> recurrence.timesOfDay.isNotEmpty()
            is Recurrence.Weekly -> recurrence.daysOfWeek.contains(day.dayOfWeek)
            is Recurrence.Monthly -> recurrence.daysOfMoth.contains(day.day)
        }
    }
}
