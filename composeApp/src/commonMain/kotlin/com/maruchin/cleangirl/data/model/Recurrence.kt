package com.maruchin.cleangirl.data.model

import kotlinx.datetime.DayOfWeek

sealed interface Recurrence {

    data class Daily(val timesOfDay: Set<TimeOfDay>) : Recurrence

    data class Weekly(val daysOfWeek: Set<DayOfWeek>) : Recurrence

    data class Monthly(val daysOfMoth: Set<Int>) : Recurrence
}
