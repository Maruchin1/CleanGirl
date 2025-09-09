package com.maruchin.cleangirl.data.firebasemodel

import com.maruchin.cleangirl.data.model.NewTask
import com.maruchin.cleangirl.data.model.Task
import com.maruchin.cleangirl.data.model.UpdatedTask
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class FirebaseTask(
    val name: String,
    val recurrence: FirebaseRecurrence,
    val daysOfWeek: Set<FirebaseDayOfWeek>,
    val daysOfMonth: Set<Int>,
    val records: Set<LocalDate>
)

fun Map.Entry<String, FirebaseTask>.asDomain() = Task(
    id = key,
    name = value.name,
    recurrence = value.recurrence.asDomain(),
    daysOfWeek = value.daysOfWeek.map { it.asDomain() }.toSet(),
    daysOfMonth = value.daysOfMonth,
    records = value.records
)

fun NewTask.asFirebase() = FirebaseTask(
    name = name,
    recurrence = recurrence.asFirebase(),
    daysOfWeek = daysOfWeek.map { it.asFirebase() }.toSet(),
    daysOfMonth = daysOfMonth,
    records = emptySet()
)

fun UpdatedTask.asFirebase() = mapOf(
    "name" to name,
    "recurrence" to recurrence.asFirebase(),
    "daysOfWeek" to daysOfWeek.map { it.asFirebase() }.toSet(),
    "daysOfMonth" to daysOfMonth,
)
