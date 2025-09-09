package com.maruchin.cleangirl.data.model

import kotlinx.datetime.DayOfWeek

data class UpdatedTask(
    val roomId: String,
    val id: String,
    val name: String,
    val recurrence: Recurrence,
    val daysOfWeek: Set<DayOfWeek>,
    val daysOfMonth: Set<Int>
)
