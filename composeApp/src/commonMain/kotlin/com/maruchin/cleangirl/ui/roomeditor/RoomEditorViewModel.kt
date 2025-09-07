package com.maruchin.cleangirl.ui.roomeditor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.cleangirl.data.model.NewRoom
import com.maruchin.cleangirl.data.model.Room
import com.maruchin.cleangirl.data.model.UpdatedRoom
import com.maruchin.cleangirl.data.repository.FirebaseRoomRepository
import com.maruchin.cleangirl.data.repository.RoomRepository
import kotlinx.coroutines.launch

class RoomEditorViewModel(
    private val roomRepository: RoomRepository = FirebaseRoomRepository.instance
) : ViewModel() {

    fun addRoom(newRoom: NewRoom) = viewModelScope.launch {
        roomRepository.createRoom(newRoom)
    }

    fun updateRoom(updatedRoom: UpdatedRoom) = viewModelScope.launch {
        roomRepository.updateRoom(updatedRoom)
    }

    fun deleteRoom(room: Room) = viewModelScope.launch {
        roomRepository.deleteRoom(room.id)
    }
}
