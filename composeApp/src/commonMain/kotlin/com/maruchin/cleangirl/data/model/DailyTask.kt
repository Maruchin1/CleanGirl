package com.maruchin.cleangirl.data.model

import kotlinx.datetime.LocalDate

data class DailyTask(
    val id: String,
    val name: String,
    val isCompleted: Boolean,
    val lastCompleted: LocalDate?,
    val nextPlanned: LocalDate?,
) {

    companion object {

        fun fromTask(task: Task, date: LocalDate) = DailyTask(
            id = task.id,
            name = task.name,
            isCompleted = task.isCompleted(date),
            lastCompleted = task.lastCompleted(date),
            nextPlanned = task.nextPlanned(date),
        )
    }
}
