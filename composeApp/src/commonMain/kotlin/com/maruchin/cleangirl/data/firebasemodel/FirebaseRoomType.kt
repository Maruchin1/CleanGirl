package com.maruchin.cleangirl.data.firebasemodel

import com.maruchin.cleangirl.data.model.RoomType
import kotlinx.serialization.Serializable

@Serializable
enum class FirebaseRoomType {
    Default,
    Kitchen,
    Bedroom,
    Bathroom,
    LivingRoom,
    DiningRoom,
    Hallway,
    Office,
    Garage,
    Laundry,
    Pantry,
    Basement,
    Attic,
    Balcony,
    Terrace,
    Closet,
    Nursery,
    GuestRoom,
    Storage,
    Gym,
    Workshop,
    Garden,
    Toilet
}

fun FirebaseRoomType.asDomain() = when (this) {
    FirebaseRoomType.Default -> RoomType.Default
    FirebaseRoomType.Kitchen -> RoomType.Kitchen
    FirebaseRoomType.Bedroom -> RoomType.Bedroom
    FirebaseRoomType.Bathroom -> RoomType.Bathroom
    FirebaseRoomType.LivingRoom -> RoomType.LivingRoom
    FirebaseRoomType.DiningRoom -> RoomType.DiningRoom
    FirebaseRoomType.Hallway -> RoomType.Hallway
    FirebaseRoomType.Office -> RoomType.Office
    FirebaseRoomType.Garage -> RoomType.Garage
    FirebaseRoomType.Laundry -> RoomType.Laundry
    FirebaseRoomType.Pantry -> RoomType.Pantry
    FirebaseRoomType.Basement -> RoomType.Basement
    FirebaseRoomType.Attic -> RoomType.Attic
    FirebaseRoomType.Balcony -> RoomType.Balcony
    FirebaseRoomType.Terrace -> RoomType.Terrace
    FirebaseRoomType.Closet -> RoomType.Closet
    FirebaseRoomType.Nursery -> RoomType.Nursery
    FirebaseRoomType.GuestRoom -> RoomType.GuestRoom
    FirebaseRoomType.Storage -> RoomType.Storage
    FirebaseRoomType.Gym -> RoomType.Gym
    FirebaseRoomType.Workshop -> RoomType.Workshop
    FirebaseRoomType.Garden -> RoomType.Garden
    FirebaseRoomType.Toilet -> RoomType.Toilet
}

fun RoomType.asFirebase() = when (this) {
    RoomType.Default -> FirebaseRoomType.Default
    RoomType.Kitchen -> FirebaseRoomType.Kitchen
    RoomType.Bedroom -> FirebaseRoomType.Bedroom
    RoomType.Bathroom -> FirebaseRoomType.Bathroom
    RoomType.LivingRoom -> FirebaseRoomType.LivingRoom
    RoomType.DiningRoom -> FirebaseRoomType.DiningRoom
    RoomType.Hallway -> FirebaseRoomType.Hallway
    RoomType.Office -> FirebaseRoomType.Office
    RoomType.Garage -> FirebaseRoomType.Garage
    RoomType.Laundry -> FirebaseRoomType.Laundry
    RoomType.Pantry -> FirebaseRoomType.Pantry
    RoomType.Basement -> FirebaseRoomType.Basement
    RoomType.Attic -> FirebaseRoomType.Attic
    RoomType.Balcony -> FirebaseRoomType.Balcony
    RoomType.Terrace -> FirebaseRoomType.Terrace
    RoomType.Closet -> FirebaseRoomType.Closet
    RoomType.Nursery -> FirebaseRoomType.Nursery
    RoomType.GuestRoom -> FirebaseRoomType.GuestRoom
    RoomType.Storage -> FirebaseRoomType.Storage
    RoomType.Gym -> FirebaseRoomType.Gym
    RoomType.Workshop -> FirebaseRoomType.Workshop
    RoomType.Garden -> FirebaseRoomType.Garden
    RoomType.Toilet -> FirebaseRoomType.Toilet
}
