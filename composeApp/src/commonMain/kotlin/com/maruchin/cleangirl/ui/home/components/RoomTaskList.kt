package com.maruchin.cleangirl.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.maruchin.cleangirl.data.model.Room
import com.maruchin.cleangirl.data.model.TaskCompletionToggle
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomTaskList(
    room: Room,
    date: LocalDate,
    topAppBarScrollBehavior: TopAppBarScrollBehavior,
    onTaskCompleteChange: (TaskCompletionToggle) -> Unit,
    modifier: Modifier = Modifier
) {
    val todayTasks = remember(room, date) {
        room.getTasksFor(date)
    }
    val otherTasks = remember(room, date) {
        room.getTasksNotFor(date)
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
            TaskListHeader(text = "Dzisiaj")
        }
        items(todayTasks) { task ->
            DailyTaskItem(
                task = task,
                isPlannedForToday = true,
                onCompletedChange = { completed ->
                    val taskCompletionToggle = TaskCompletionToggle(
                        roomId = room.id,
                        taskId = task.id,
                        date = date,
                        completed = completed
                    )
                    onTaskCompleteChange(taskCompletionToggle)
                }
            )
        }
        stickyHeader {
            TaskListHeader(text = "Pozostałe", modifier = Modifier.padding(top = 16.dp))
        }
        items(otherTasks) { task ->
            DailyTaskItem(
                task = task,
                isPlannedForToday = false,
                onCompletedChange = { completed ->
                    val taskCompletionToggle = TaskCompletionToggle(
                        roomId = room.id,
                        taskId = task.id,
                        date = date,
                        completed = completed
                    )
                    onTaskCompleteChange(taskCompletionToggle)
                }
            )
        }
    }
}
