package com.maruchin.cleangirl.data.repository

import com.maruchin.cleangirl.core.utils.toggle
import com.maruchin.cleangirl.data.firebasemodel.FirebaseRoomCollection
import com.maruchin.cleangirl.data.firebasemodel.asDomain
import com.maruchin.cleangirl.data.firebasemodel.asFirebase
import com.maruchin.cleangirl.data.model.NewRoom
import com.maruchin.cleangirl.data.model.NewTask
import com.maruchin.cleangirl.data.model.Room
import com.maruchin.cleangirl.data.model.TaskCompletionToggle
import com.maruchin.cleangirl.data.model.UpdatedRoom
import com.maruchin.cleangirl.data.model.UpdatedTask
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.DatabaseReference
import dev.gitlive.firebase.database.database
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate

class FirebaseRoomRepository private constructor(
    private val userRepository: UserRepository = FirebaseUserRepository.instance
) : RoomRepository {
    private val database =
        Firebase.database("https://clean-girl-9f8e3-default-rtdb.europe-west1.firebasedatabase.app/")
            .reference()

    private val roomsRef: DatabaseReference
        get() {
            val user = userRepository.user
            checkNotNull(user) { "User must be not null" }
            return database.child("users").child(user.id).child("rooms")
        }

    override val roomsFlow: Flow<List<Room>> =
        userRepository.userFlow.flatMapLatest { user ->
            if (user == null) {
                flowOf(emptyList())
            } else {
                val userRef = database.child("users").child(user.id)
                val roomsRef = userRef.child("rooms")
                roomsRef.valueEvents.map { roomsSnapshot ->
                    roomsSnapshot.value<FirebaseRoomCollection?>().orEmpty().map { it.asDomain() }
                }
            }
        }

    override suspend fun createRoom(newRoom: NewRoom) {
        roomsRef.push().setValue(newRoom.asFirebase())
    }

    override suspend fun updateRoom(updatedRoom: UpdatedRoom) {
        roomsRef.child(updatedRoom.id).updateChildren(updatedRoom.asFirebase())
    }

    override suspend fun deleteRoom(roomId: String) {
        roomsRef.child(roomId).removeValue()
    }

    override suspend fun addTask(newTask: NewTask) {
        roomsRef.child(newTask.roomId).child("tasks").push().setValue(newTask.asFirebase())
    }

    override suspend fun updateTask(updatedTask: UpdatedTask) {
        roomsRef.child(updatedTask.roomId).child("tasks").child(updatedTask.id)
            .updateChildren(updatedTask.asFirebase())
    }

    override suspend fun toggleTaskCompleted(taskCompletionToggle: TaskCompletionToggle) {
        val recordsRef = roomsRef.child(taskCompletionToggle.roomId).child("tasks")
            .child(taskCompletionToggle.taskId).child("records")
        val records = recordsRef.valueEvents.first()
            .value<Set<LocalDate>?>().orEmpty()
        val updatedRecords = records.toggle(taskCompletionToggle.date)
        recordsRef.setValue(updatedRecords)
    }

    override suspend fun deleteTask(roomId: String, taskId: String) {
        roomsRef.child(roomId).child("tasks").child(taskId).removeValue()
    }

    companion object Companion {

        val instance by lazy { FirebaseRoomRepository() }
    }
}
