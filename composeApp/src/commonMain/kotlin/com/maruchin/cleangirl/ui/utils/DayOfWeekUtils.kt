package com.maruchin.cleangirl.ui.utils

import kotlinx.datetime.DayOfWeek

fun DayOfWeek.toText(): String = when (this) {
    DayOfWeek.MONDAY -> "Poniedziałek"
    DayOfWeek.TUESDAY -> "Wtorek"
    DayOfWeek.WEDNESDAY -> "Środa"
    DayOfWeek.THURSDAY -> "Czwartek"
    DayOfWeek.FRIDAY -> "Piątek"
    DayOfWeek.SATURDAY -> "Sobota"
    DayOfWeek.SUNDAY -> "Niedziela"
}
