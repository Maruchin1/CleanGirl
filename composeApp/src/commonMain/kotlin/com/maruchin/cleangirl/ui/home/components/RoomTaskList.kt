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
import com.maruchin.cleangirl.data.model.DailyTasks
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
    val dailyTasks = remember(room, date) {
        DailyTasks.from(room.tasks, date)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        contentPadding = PaddingValues(16.dp),
    ) {
        if (dailyTasks.today.isNotEmpty()) {
            stickyHeader {
                TaskListHeader(text = "Dzisiaj", modifier = Modifier.animateItem())
            }
            items(dailyTasks.today, key = { it.task.id }) { task ->
                DailyTaskItem(
                    room = room,
                    task = task,
                    isPlannedForToday = true,
                    onCompletedChange = { completed ->
                        TaskCompletionToggle(
                            roomId = room.id,
                            taskId = task.task.id,
                            date = date,
                            completed = completed
                        ).let(onTaskCompleteChange)
                    },
                    modifier = Modifier.animateItem().padding(bottom = 8.dp)
                )
            }
        }
        if (dailyTasks.other.isNotEmpty()) {
            stickyHeader {
                TaskListHeader(text = "Pozostałe", modifier = Modifier.animateItem())
            }
            items(dailyTasks.other, key = { it.task.id }) { task ->
                DailyTaskItem(
                    room = room,
                    task = task,
                    isPlannedForToday = false,
                    onCompletedChange = {},
                    modifier = Modifier.animateItem().padding(bottom = 8.dp)
                )
            }
        }
    }
}
