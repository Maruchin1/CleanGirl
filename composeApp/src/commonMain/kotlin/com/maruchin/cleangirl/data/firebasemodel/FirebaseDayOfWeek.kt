package com.maruchin.cleangirl.data.firebasemodel

import kotlinx.datetime.DayOfWeek
import kotlinx.serialization.Serializable

@Serializable
enum class FirebaseDayOfWeek {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
}

fun FirebaseDayOfWeek.asDomain() = when (this) {
    FirebaseDayOfWeek.Monday -> DayOfWeek.MONDAY
    FirebaseDayOfWeek.Tuesday -> DayOfWeek.TUESDAY
    FirebaseDayOfWeek.Wednesday -> DayOfWeek.WEDNESDAY
    FirebaseDayOfWeek.Thursday -> DayOfWeek.THURSDAY
    FirebaseDayOfWeek.Friday -> DayOfWeek.FRIDAY
    FirebaseDayOfWeek.Saturday -> DayOfWeek.SATURDAY
    FirebaseDayOfWeek.Sunday -> DayOfWeek.SUNDAY
}

fun DayOfWeek.asFirebase() = when (this) {
    DayOfWeek.MONDAY -> FirebaseDayOfWeek.Monday
    DayOfWeek.TUESDAY -> FirebaseDayOfWeek.Tuesday
    DayOfWeek.WEDNESDAY -> FirebaseDayOfWeek.Wednesday
    DayOfWeek.THURSDAY -> FirebaseDayOfWeek.Thursday
    DayOfWeek.FRIDAY -> FirebaseDayOfWeek.Friday
    DayOfWeek.SATURDAY -> FirebaseDayOfWeek.Saturday
    DayOfWeek.SUNDAY -> FirebaseDayOfWeek.Sunday
}