package com.maruchin.cleangirl.data.mapper

import com.maruchin.cleangirl.data.model.User
import dev.gitlive.firebase.auth.FirebaseUser

fun FirebaseUser.toUser() = User(
    id = uid,
    name = displayName ?: ""
)
