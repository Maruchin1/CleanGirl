package com.maruchin.cleangirl.data.model

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
data class Task(
    val id: String,
    val name: String,
    val recurrence: Recurrence,
    val records: List<LocalDate>
) {

    val lastCompleted: LocalDate?
        get() = records.maxOrNull()

    val nextPlanned: LocalDate?
        get() {
            val timezone = TimeZone.currentSystemDefault()
            val dateTime = Clock.System.now().toLocalDateTime(timezone)
            val today = dateTime.date
            val fromDate = (lastCompleted ?: today).plus(1, DateTimeUnit.DAY)
            return when (recurrence) {
                is Recurrence.Daily -> fromDate
                is Recurrence.Weekly -> {
                    val daysOfWeek = recurrence.daysOfWeek
                    (0..6).map { fromDate.plus(it, DateTimeUnit.DAY) }
                        .firstOrNull { it.dayOfWeek in daysOfWeek }
                }

                is Recurrence.Monthly -> {
                    val daysOfMonth = recurrence.daysOfMoth
                    var date = fromDate
                    repeat(31) {
                        if (date.day in daysOfMonth) return date
                        date = date.plus(1, DateTimeUnit.DAY)
                    }
                    null
                }
            }
        }

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
