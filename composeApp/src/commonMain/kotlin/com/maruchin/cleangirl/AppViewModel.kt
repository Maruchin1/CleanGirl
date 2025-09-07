package com.maruchin.cleangirl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.cleangirl.data.repository.FirebaseUserRepository
import com.maruchin.cleangirl.data.repository.UserRepository
import kotlinx.coroutines.launch

class AppViewModel(
    private val userRepository: UserRepository = FirebaseUserRepository()
) : ViewModel() {

    init {
        viewModelScope.launch {
            userRepository.signInAnonymouslyIfNeeded()
        }
    }
}
