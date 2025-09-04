package com.maruchin.cleangirl.data.model

import kotlinx.datetime.DayOfWeek

sealed interface Recurrence {

    val isValid: Boolean
        get() = when (this) {
            is Daily -> timesOfDay.isNotEmpty()
            is Weekly -> daysOfWeek.isNotEmpty()
            is Monthly -> daysOfMoth.isNotEmpty()
        }

    data class Daily(val timesOfDay: Set<TimeOfDay>) : Recurrence {

        fun toggleTimeOfDay(timeOfDay: TimeOfDay): Daily {
            return if (timesOfDay.contains(timeOfDay)) {
                this.copy(timesOfDay = timesOfDay - timeOfDay)
            } else {
                this.copy(timesOfDay = timesOfDay + timeOfDay)
            }
        }

        companion object {

            val default = Daily(setOf(TimeOfDay.MORNING))
        }
    }

    data class Weekly(val daysOfWeek: Set<DayOfWeek>) : Recurrence {

        fun toggleDayOfWeek(dayOfWeek: DayOfWeek): Weekly {
            return if (daysOfWeek.contains(dayOfWeek)) {
                this.copy(daysOfWeek = daysOfWeek - dayOfWeek)
            } else {
                this.copy(daysOfWeek = daysOfWeek + dayOfWeek)
            }
        }

        companion object {

            val default = Weekly(setOf(DayOfWeek.MONDAY))
        }
    }

    data class Monthly(val daysOfMoth: Set<Int>) : Recurrence {

        fun toggleDayOfMonth(dayOfMonth: Int): Monthly {
            return if (daysOfMoth.contains(dayOfMonth)) {
                this.copy(daysOfMoth = daysOfMoth - dayOfMonth)
            } else {
                this.copy(daysOfMoth = daysOfMoth + dayOfMonth)
            }
        }

        companion object {

            val default = Monthly(setOf(1))
        }
    }
}
