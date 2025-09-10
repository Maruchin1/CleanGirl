package com.maruchin.cleangirl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.cleangirl.data.repository.FirebaseUserRepository
import com.maruchin.cleangirl.data.repository.UserRepository
import com.maruchin.cleangirl.ui.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel(
    private val userRepository: UserRepository = FirebaseUserRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            userRepository.signInAnonymouslyIfNeeded()
        }
        val user = userRepository.user
        _uiState.update {
            it.copy(
                isUserConfigured = user != null && user.name.isNotBlank()
            )
        }
    }
}
