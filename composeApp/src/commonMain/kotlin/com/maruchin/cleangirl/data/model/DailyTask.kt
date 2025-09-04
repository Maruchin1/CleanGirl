package com.maruchin.cleangirl.data.model

import kotlinx.datetime.LocalDate

data class DailyTask(
    val task: Task,
    val isCompleted: Boolean,
    val lastCompleted: LocalDate?,
    val nextPlanned: LocalDate?,
) {

    companion object {

        fun fromTask(task: Task, date: LocalDate) = DailyTask(
            task = task,
            isCompleted = task.isCompleted(date),
            lastCompleted = task.lastCompleted(date),
            nextPlanned = task.nextPlanned(date),
        )

        val comparator = compareBy<DailyTask> { it.isCompleted }
            .thenBy { it.task.name.lowercase() }
    }
}
