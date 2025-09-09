package com.maruchin.cleangirl.data.model

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
data class Task(
    val id: String,
    val name: String,
    val recurrence: Recurrence,
    val daysOfWeek: Set<DayOfWeek>,
    val daysOfMonth: Set<Int>,
    val records: Set<LocalDate>
) {

    fun lastCompleted(date: LocalDate): LocalDate? {
        return records.filter { it < date }.maxOrNull()
    }

    fun nextPlanned(date: LocalDate): LocalDate? {
        val fromDate = date.plus(1, DateTimeUnit.DAY)
        return when (recurrence) {
            Recurrence.Daily -> fromDate
            Recurrence.Weekly -> {
                (0..6).map { fromDate.plus(it, DateTimeUnit.DAY) }
                    .firstOrNull { it.dayOfWeek in daysOfWeek }
            }

            Recurrence.Monthly -> {
                var date = fromDate
                repeat(31) {
                    if (date.day in daysOfMonth) return date
                    date = date.plus(1, DateTimeUnit.DAY)
                }
                null
            }
        }
    }

    fun isCompleted(date: LocalDate): Boolean {
        return records.any { it == date }
    }

    fun isPlannedFor(date: LocalDate): Boolean = when (recurrence) {
        Recurrence.Daily -> true
        Recurrence.Weekly -> daysOfWeek.contains(date.dayOfWeek)
        Recurrence.Monthly -> daysOfMonth.contains(date.day)
    }
}
