package com.maruchin.cleangirl.data.repository

import co.touchlab.kermit.Logger
import com.maruchin.cleangirl.data.firebasemodel.toUser
import com.maruchin.cleangirl.data.model.User
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseException
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FirebaseUserRepository : UserRepository {
    private val auth = Firebase.auth

    override val user: User?
        get() = auth.currentUser?.toUser()

    override val userFlow: Flow<User?> =
        auth.authStateChanged.map { it?.toUser() }

    init {
        Logger.d { "${auth.currentUser?.toUser()}" }
    }

    override suspend fun signInAnonymouslyIfNeeded() {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            signInAnonymously()
        }
    }

    override suspend fun setUserName(name: String) {
        val user = auth.currentUser
        checkNotNull(user) { "User must be not null" }
        user.updateProfile(displayName = name)
    }

    private suspend fun signInAnonymously() {
        try {
            auth.signInAnonymously()
        } catch (e: FirebaseException) {
            Logger.e(e) { "Sign in anonymously failed" }
        }
    }

    companion object Companion {

        val instance by lazy { FirebaseUserRepository() }
    }
}
