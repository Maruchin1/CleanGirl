package com.maruchin.cleangirl.data.firebasemodel

import com.maruchin.cleangirl.data.model.Recurrence
import kotlinx.serialization.Serializable

@Serializable
enum class FirebaseRecurrence {
    Daily, Weekly, Monthly;
}

fun FirebaseRecurrence.asDomain() = when (this) {
    FirebaseRecurrence.Daily -> Recurrence.Daily
    FirebaseRecurrence.Weekly -> Recurrence.Weekly
    FirebaseRecurrence.Monthly -> Recurrence.Monthly
}

fun Recurrence.asFirebase() = when (this) {
    Recurrence.Daily -> FirebaseRecurrence.Daily
    Recurrence.Weekly -> FirebaseRecurrence.Weekly
    Recurrence.Monthly -> FirebaseRecurrence.Monthly
}
