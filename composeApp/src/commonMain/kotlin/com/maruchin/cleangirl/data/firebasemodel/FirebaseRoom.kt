package com.maruchin.cleangirl.data.firebasemodel

import com.maruchin.cleangirl.data.model.NewRoom
import com.maruchin.cleangirl.data.model.Room
import com.maruchin.cleangirl.data.model.UpdatedRoom
import kotlinx.serialization.Serializable

@Serializable
data class FirebaseRoom(
    val name: String,
    val type: FirebaseRoomType,
    val tasks: Map<String, FirebaseTask>
)

typealias FirebaseRoomCollection = Map<String, FirebaseRoom>

fun Map.Entry<String, FirebaseRoom>.asDomain() = Room(
    id = key,
    name = value.name,
    type = value.type.asDomain(),
    tasks = value.tasks.map { it.asDomain() }
)

fun NewRoom.asFirebase() = FirebaseRoom(
    name = name,
    type = type.asFirebase(),
    tasks = emptyMap()
)

fun UpdatedRoom.asFirebase() = mapOf(
    "name" to name,
    "type" to type.asFirebase()
)
