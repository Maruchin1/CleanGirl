package com.maruchin.cleangirl.data.firebasemodel

import com.maruchin.cleangirl.data.model.User
import dev.gitlive.firebase.auth.FirebaseUser

fun FirebaseUser.toUser() = User(
    id = uid,
    name = displayName ?: ""
)