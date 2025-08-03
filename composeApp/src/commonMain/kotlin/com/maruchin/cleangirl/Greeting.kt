package com.maruchin.cleangirl

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class Greeting {
    private val platform = getPlatform()

    @OptIn(ExperimentalTime::class)
    fun greet(): String {
        val now = Clock.System.now()
        val timezone = TimeZone.currentSystemDefault()
        val today = now.toLocalDateTime(timezone)
        return "Hello, ${platform.name} - ${today}!"
    }
}