package com.maruchin.cleangirl.data.model

import kotlinx.datetime.LocalDate

data class DailyTasks(val today: List<DailyTask>, val other: List<DailyTask>) {

    companion object {

        fun from(tasks: List<Task>, date: LocalDate): DailyTasks {
            val todayTasks = tasks.filter { it.isPlannedFor(date) }
            val otherTasks = tasks - todayTasks

            return DailyTasks(
                today = todayTasks.map { DailyTask.fromTask(it, date) }
                    .sortedWith(DailyTask.comparator),
                other = otherTasks.map { DailyTask.fromTask(it, date) }
                    .sortedWith(DailyTask.comparator)
            )
        }
    }
}
