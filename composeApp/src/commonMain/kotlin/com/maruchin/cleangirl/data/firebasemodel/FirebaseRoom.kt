package com.maruchin.cleangirl.data.firebasemodel

import com.maruchin.cleangirl.data.model.RoomType
import kotlinx.serialization.Serializable

@Serializable
data class FirebaseRoom(
    val name: String,
    val type: RoomType
)
