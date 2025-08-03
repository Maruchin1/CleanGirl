package com.maruchin.cleangirl

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform