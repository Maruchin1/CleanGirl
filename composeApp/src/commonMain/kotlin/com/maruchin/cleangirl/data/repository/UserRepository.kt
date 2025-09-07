package com.maruchin.cleangirl.data.repository

import com.maruchin.cleangirl.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val userFlow: Flow<User?>

    suspend fun signInAnonymouslyIfNeeded()
}