package com.maruchin.cleangirl.data.model

data class UpdatedTask(
    val id: String,
    val name: String,
    val recurrence: Recurrence
)
