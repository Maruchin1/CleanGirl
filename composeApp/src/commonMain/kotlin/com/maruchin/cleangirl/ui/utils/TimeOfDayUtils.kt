package com.maruchin.cleangirl.ui.utils

import com.maruchin.cleangirl.data.model.TimeOfDay

fun TimeOfDay.toText(): String = when (this) {
    TimeOfDay.MORNING -> "Rano"
    TimeOfDay.AFTERNOON -> "Popołudnie"
    TimeOfDay.EVENING -> "Wieczór"
}
