package com.maruchin.cleangirl.data.model

import kotlinx.datetime.DayOfWeek

sealed interface Recurrence {

    data class Daily(val timesOfDay: Set<TimeOfDay> = emptySet()) : Recurrence {

        fun toggleTimeOfDay(timeOfDay: TimeOfDay): Daily {
            return if (timesOfDay.contains(timeOfDay)) {
                this.copy(timesOfDay = timesOfDay - timeOfDay)
            } else {
                this.copy(timesOfDay = timesOfDay + timeOfDay)
            }
        }
    }

    data class Weekly(val daysOfWeek: Set<DayOfWeek> = emptySet()) : Recurrence {

        fun toggleDayOfWeek(dayOfWeek: DayOfWeek): Weekly {
            return if (daysOfWeek.contains(dayOfWeek)) {
                this.copy(daysOfWeek = daysOfWeek - dayOfWeek)
            } else {
                this.copy(daysOfWeek = daysOfWeek + dayOfWeek)
            }
        }
    }

    data class Monthly(val daysOfMoth: Set<Int> = emptySet()) : Recurrence {

        fun toggleDayOfMonth(dayOfMonth: Int): Monthly {
            return if (daysOfMoth.contains(dayOfMonth)) {
                this.copy(daysOfMoth = daysOfMoth - dayOfMonth)
            } else {
                this.copy(daysOfMoth = daysOfMoth + dayOfMonth)
            }
        }
    }
}
