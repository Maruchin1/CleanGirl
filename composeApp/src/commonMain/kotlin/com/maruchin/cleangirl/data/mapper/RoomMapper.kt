package com.maruchin.cleangirl.data.mapper

import com.maruchin.cleangirl.data.firebasemodel.FirebaseRoom
import com.maruchin.cleangirl.data.model.NewRoom
import com.maruchin.cleangirl.data.model.Room
import com.maruchin.cleangirl.data.model.UpdatedRoom
import dev.gitlive.firebase.firestore.DocumentSnapshot

fun NewRoom.toFirebaseRoom() = FirebaseRoom(
    name = name,
    type = type
)

fun UpdatedRoom.toFirebaseRoom() = FirebaseRoom(
    name = name,
    type = type
)

fun DocumentSnapshot.toRoom(): Room {
    val firebaseRoom = data(FirebaseRoom.serializer())
    return Room(
        id = id,
        name = firebaseRoom.name,
        type = firebaseRoom.type
    )
}
