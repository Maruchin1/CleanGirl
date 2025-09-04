package com.maruchin.cleangirl.ui.taskeditor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.cleangirl.data.model.NewTask
import com.maruchin.cleangirl.data.repository.InMemoryRoomRepository
import com.maruchin.cleangirl.data.repository.RoomRepository
import kotlinx.coroutines.launch

class TaskEditorViewModel(
    private val roomId: String,
    private val roomRepository: RoomRepository = InMemoryRoomRepository.instance
) : ViewModel() {

    fun saveTask(newTask: NewTask) = viewModelScope.launch {
        roomRepository.addTask(roomId, newTask)
    }
}
