package com.maruchin.cleangirl.data.repository

import com.maruchin.cleangirl.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val user: User?

    val userFlow: Flow<User?>

    suspend fun signInAnonymouslyIfNeeded()
}