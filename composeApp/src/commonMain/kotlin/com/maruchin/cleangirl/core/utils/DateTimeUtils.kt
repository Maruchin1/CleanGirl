@file:OptIn(ExperimentalTime::class)

package com.maruchin.cleangirl.core.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

val currentTimeMillis: Long
    get() = Clock.System.now().toEpochMilliseconds()

fun Long.toLocalDateTime(): LocalDateTime =
    Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.currentSystemDefault())

fun LocalDateTime.toMillis(): Long =
    this.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
