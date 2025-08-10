package com.maruchin.cleangirl.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.cleangirl.data.model.TaskCompletionToggle
import com.maruchin.cleangirl.data.repository.InMemoryRoomRepository
import com.maruchin.cleangirl.data.repository.InMemoryUserRepository
import com.maruchin.cleangirl.data.repository.RoomRepository
import com.maruchin.cleangirl.data.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userRepository: UserRepository = InMemoryUserRepository.instance,
    private val roomRepository: RoomRepository = InMemoryRoomRepository.instance
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = combine(
        userRepository.userFlow,
        roomRepository.roomsFlow,
        ::HomeUiState
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState()
    )

    fun toggleTaskCompleted(taskCompletionToggle: TaskCompletionToggle) = viewModelScope.launch {
        roomRepository.toggleTaskCompleted(taskCompletionToggle)
    }
}
