package com.maruchin.cleangirl.data.model

import kotlinx.datetime.DayOfWeek

data class NewTask(
    val roomId: String,
    val name: String,
    val recurrence: Recurrence,
    val daysOfWeek: Set<DayOfWeek>,
    val daysOfMonth: Set<Int>
)
