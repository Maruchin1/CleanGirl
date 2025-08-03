package com.maruchin.cleangirl.data.model

data class User(
    val id: String,
    val name: String
)

val sampleUser = User(
    id = "1",
    name = "Maruchin"
)
