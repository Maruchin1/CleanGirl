package com.maruchin.cleangirl.data.repository

import com.maruchin.cleangirl.data.firebasemodel.FirebaseRoom
import com.maruchin.cleangirl.data.mapper.toFirebaseRoom
import com.maruchin.cleangirl.data.mapper.toRoom
import com.maruchin.cleangirl.data.model.NewRoom
import com.maruchin.cleangirl.data.model.NewTask
import com.maruchin.cleangirl.data.model.Room
import com.maruchin.cleangirl.data.model.TaskCompletionToggle
import com.maruchin.cleangirl.data.model.UpdatedRoom
import com.maruchin.cleangirl.data.model.UpdatedTask
import com.maruchin.cleangirl.data.model.sampleRoomList
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.CollectionReference
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class InMemoryRoomRepository private constructor(
    private val userRepository: UserRepository = FirebaseUserRepository.instance
) : RoomRepository {
    private val firestore = Firebase.firestore

    private val rooms: CollectionReference
        get() {
            val user = userRepository.user
            checkNotNull(user) { "User is not signed in" }
            return firestore.collection("users")
                .document(user.id)
                .collection("rooms")
        }

    private val roomState = MutableStateFlow(sampleRoomList.associateBy { it.id })

    override val roomsFlow: Flow<List<Room>> =
        rooms.snapshots.map { it.documents }.map { snapshots ->
            snapshots.map { it.toRoom() }
        }

    override suspend fun createRoom(newRoom: NewRoom) {
        val firebaseRoom = newRoom.toFirebaseRoom()
        roomsCollection().add(FirebaseRoom.serializer(), firebaseRoom)
    }

    override suspend fun updateRoom(updatedRoom: UpdatedRoom) {
        val firebaseRoom = updatedRoom.toFirebaseRoom()
        roomsCollection().document(updatedRoom.id).update(FirebaseRoom.serializer(), firebaseRoom)
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
        roomsCollection().document(roomId).delete()
    }

    override suspend fun addTask(roomId: String, newTask: NewTask) {
        val room = roomState.value[roomId]
        checkNotNull(room) { "Room with id $room not found" }
        val romWithNewTask = room.addTask(newTask)
        roomState.update {
            it + (romWithNewTask.id to romWithNewTask)
        }
    }

    override suspend fun updateTask(roomId: String, updatedTask: UpdatedTask) {
        val room = roomState.value[roomId]
        checkNotNull(room) { "Room with id $room not found" }
        val romWithUpdatedTask = room.updateTask(updatedTask)
        roomState.update {
            it + (romWithUpdatedTask.id to romWithUpdatedTask)
        }
    }

    override suspend fun deleteTask(roomId: String, taskId: String) {
        val room = roomState.value[roomId]
        checkNotNull(room) { "Room with id $room not found" }
        val romWithUpdatedTask = room.deleteTask(taskId)
        roomState.update {
            it + (romWithUpdatedTask.id to romWithUpdatedTask)
        }
    }

    private suspend fun roomsCollection(): CollectionReference {
        val user = userRepository.userFlow.first()
        checkNotNull(user) { "User is not signed in" }
        return firestore.collection("users")
            .document(user.id)
            .collection("rooms")
    }

    companion object {

        val instance by lazy { InMemoryRoomRepository() }
    }
}
