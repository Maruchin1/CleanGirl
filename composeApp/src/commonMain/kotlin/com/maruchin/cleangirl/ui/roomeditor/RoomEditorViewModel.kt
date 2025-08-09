package com.maruchin.cleangirl.ui.roomeditor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.cleangirl.data.model.NewRoom
import com.maruchin.cleangirl.data.repository.InMemoryRoomRepository
import com.maruchin.cleangirl.data.repository.RoomRepository
import kotlinx.coroutines.launch

class RoomEditorViewModel(
    private val roomRepository: RoomRepository = InMemoryRoomRepository.instance
) : ViewModel() {

    fun saveRoom(newRoom: NewRoom) = viewModelScope.launch {
        roomRepository.createRoom(newRoom)
    }
}