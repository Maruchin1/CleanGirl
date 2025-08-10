package com.maruchin.cleangirl.data.model

import kotlinx.datetime.LocalDate

data class TaskCompletionToggle(
    val roomId: String,
    val taskId: String,
    val date: LocalDate,
    val completed: Boolean
)
