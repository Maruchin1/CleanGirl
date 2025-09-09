package com.maruchin.cleangirl.data.repository

import com.maruchin.cleangirl.data.model.NewRoom
import com.maruchin.cleangirl.data.model.NewTask
import com.maruchin.cleangirl.data.model.Room
import com.maruchin.cleangirl.data.model.TaskCompletionToggle
import com.maruchin.cleangirl.data.model.UpdatedRoom
import com.maruchin.cleangirl.data.model.UpdatedTask
import kotlinx.coroutines.flow.Flow

interface RoomRepository {

    val roomsFlow: Flow<List<Room>>

    suspend fun createRoom(newRoom: NewRoom)

    suspend fun updateRoom(updatedRoom: UpdatedRoom)

    suspend fun deleteRoom(roomId: String)

    suspend fun addTask(newTask: NewTask)

    suspend fun updateTask(updatedTask: UpdatedTask)

    suspend fun toggleTaskCompleted(taskCompletionToggle: TaskCompletionToggle)

    suspend fun deleteTask(roomId: String, taskId: String)
}
