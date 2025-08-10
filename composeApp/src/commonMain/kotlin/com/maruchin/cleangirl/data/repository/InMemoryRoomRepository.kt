package com.maruchin.cleangirl.data.repository

import com.maruchin.cleangirl.data.model.NewRoom
import com.maruchin.cleangirl.data.model.Room
import com.maruchin.cleangirl.data.model.TaskCompletionToggle
import com.maruchin.cleangirl.data.model.UpdatedRoom
import com.maruchin.cleangirl.data.model.sampleRoomList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class InMemoryRoomRepository private constructor() : RoomRepository {
    private val roomState = MutableStateFlow(sampleRoomList.associateBy { it.id })

    override val roomsFlow: Flow<List<Room>> = roomState.map { it.values.toList() }

    override suspend fun createRoom(newRoom: NewRoom) {
        val room = Room.from(newRoom)
        roomState.update {
            it + (room.id to room)
        }
    }

    override suspend fun updateRoom(updatedRoom: UpdatedRoom) {
        val room = roomState.value[updatedRoom.id]
        checkNotNull(room) { "Room with id ${updatedRoom.id} not found" }
        val updatedRoom = room.update(updatedRoom)
        roomState.update {
            it + (updatedRoom.id to updatedRoom)
        }
    }

    override suspend fun toggleTaskCompleted(taskCompletionToggle: TaskCompletionToggle) {
        val (roomId) = taskCompletionToggle
        val room = roomState.value[roomId]
        checkNotNull(room) { "Room with id $roomId not found" }
        val updatedRoom = room.toggleTaskCompleted(taskCompletionToggle)
        roomState.update {
            it + (updatedRoom.id to updatedRoom)
        }
    }

    override suspend fun deleteRoom(roomId: String) {
        roomState.update { it - roomId }
    }

    companion object {

        val instance by lazy { InMemoryRoomRepository() }
    }
}
