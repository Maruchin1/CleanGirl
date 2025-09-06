package com.maruchin.cleangirl.ui.taskeditor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.cleangirl.data.model.NewTask
import com.maruchin.cleangirl.data.model.Room
import com.maruchin.cleangirl.data.model.Task
import com.maruchin.cleangirl.data.model.UpdatedTask
import com.maruchin.cleangirl.data.repository.InMemoryRoomRepository
import com.maruchin.cleangirl.data.repository.RoomRepository
import kotlinx.coroutines.launch

class TaskEditorViewModel(
    private val room: Room,
    private val roomRepository: RoomRepository = InMemoryRoomRepository.instance
) : ViewModel() {

    fun addTask(newTask: NewTask) = viewModelScope.launch {
        roomRepository.addTask(room.id, newTask)
    }

    fun updateTask(updatedTask: UpdatedTask) = viewModelScope.launch {
        roomRepository.updateTask(room.id, updatedTask)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        roomRepository.deleteTask(room.id, task.id)
    }
}
