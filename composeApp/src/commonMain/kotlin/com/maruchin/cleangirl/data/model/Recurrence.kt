package com.maruchin.cleangirl.data.model

import com.maruchin.cleangirl.core.utils.toggle
import kotlinx.datetime.DayOfWeek

sealed interface Recurrence {

    val isValid: Boolean
        get() = when (this) {
            is Daily -> true
            is Weekly -> daysOfWeek.isNotEmpty()
            is Monthly -> daysOfMoth.isNotEmpty()
        }

    data object Daily : Recurrence

    data class Weekly(val daysOfWeek: Set<DayOfWeek> = emptySet()) : Recurrence {

        fun toggleDayOfWeek(dayOfWeek: DayOfWeek): Weekly =
            copy(daysOfWeek = daysOfWeek.toggle(dayOfWeek))

    }

    data class Monthly(val daysOfMoth: Set<Int> = emptySet()) : Recurrence {

        fun toggleDayOfMonth(dayOfMonth: Int): Monthly =
            copy(daysOfMoth = daysOfMoth.toggle(dayOfMonth))
    }
}
