package com.maruchin.cleangirl.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.maruchin.cleangirl.data.model.Room
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomTaskList(
    room: Room,
    date: LocalDate,
    topAppBarScrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    val todayTasks = remember(room, date) {
        room.getTasksForDay(date)
    }
    val otherTasks = remember(room, date) {
        room.getTasksNotForDay(date)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
            .then(modifier),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stickyHeader {
            Text(text = "Dzisiaj", style = MaterialTheme.typography.headlineSmall)
        }
        items(todayTasks) { task ->
            TaskItem(task = task, date = date, onCompletedChange = {})
        }
        stickyHeader {
            Text(
                text = "Pozostałe",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
        items(otherTasks) { task ->
            TaskItem(task = task, date = date, onCompletedChange = {})
        }
    }
}
