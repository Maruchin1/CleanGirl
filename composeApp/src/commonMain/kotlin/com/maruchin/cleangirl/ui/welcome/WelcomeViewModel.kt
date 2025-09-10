package com.maruchin.cleangirl.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.cleangirl.data.repository.FirebaseUserRepository
import com.maruchin.cleangirl.data.repository.UserRepository
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val userRepository: UserRepository = FirebaseUserRepository.instance
) : ViewModel() {

    fun saveUserName(name: String) = viewModelScope.launch {
        userRepository.setUserName(name)
    }
}
