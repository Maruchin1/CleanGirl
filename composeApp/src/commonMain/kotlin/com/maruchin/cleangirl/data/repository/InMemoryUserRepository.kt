package com.maruchin.cleangirl.data.repository

import com.maruchin.cleangirl.data.model.User
import com.maruchin.cleangirl.data.model.sampleUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class InMemoryUserRepository : UserRepository {

    override val userFlow: Flow<User> = flowOf(sampleUser)

    companion object {

        val instance by lazy { InMemoryUserRepository() }
    }
}