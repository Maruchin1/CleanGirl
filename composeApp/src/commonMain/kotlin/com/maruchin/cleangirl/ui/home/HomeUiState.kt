package com.maruchin.cleangirl.ui.home

import com.maruchin.cleangirl.data.model.Room
import com.maruchin.cleangirl.data.model.User

data class HomeUiState(
    val user: User? = null,
    val rooms: List<Room> = emptyList()
)
