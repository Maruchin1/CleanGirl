package com.maruchin.cleangirl.ui.home.utils

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.maruchin.cleangirl.data.model.Room

@Composable
fun rememberRoomPagerState(rooms: List<Room>): PagerState {
    var previousRooms by remember { mutableStateOf(rooms) }

    return remember(rooms.size) {
        val initialPage = when {
            previousRooms.isNotEmpty() && rooms.size > previousRooms.size -> rooms.lastIndex
            else -> 0
        }
        previousRooms = rooms
        PagerState(currentPage = initialPage) { rooms.size }
    }
}
