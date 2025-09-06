package com.maruchin.cleangirl.core.utils

fun <T> Set<T>.toggle(value: T): Set<T> =
    if (contains(value)) this - value else this + value
