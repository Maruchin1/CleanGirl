package com.maruchin.cleangirl.ui.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maruchin.cleangirl.data.model.Room
import com.maruchin.cleangirl.data.model.TaskCompletionToggle
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomTaskList(
    room: Room,
    date: LocalDate,
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
            .then(modifier),
        contentPadding = PaddingValues(16.dp),
    ) {
        stickyHeader {
            TaskListHeader(text = "Dzisiaj", modifier = Modifier.animateItem())
        }
        if (todayTasks.isNotEmpty()) {
            items(todayTasks, key = { it.id }) { task ->
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
                    },
                    modifier = Modifier.animateItem().padding(bottom = 8.dp)
                )
            }
        } else {
            item {
                NoTasksForTodayInfo()
            }
        }
        if (otherTasks.isNotEmpty()) {
            stickyHeader {
                TaskListHeader(text = "Pozostałe", modifier = Modifier.animateItem())
            }
            items(otherTasks, key = { it.id }) { task ->
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
                    },
                    modifier = Modifier.animateItem().padding(bottom = 8.dp)
                )
            }
        }
    }
}
