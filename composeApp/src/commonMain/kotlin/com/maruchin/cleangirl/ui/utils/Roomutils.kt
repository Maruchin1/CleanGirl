package com.maruchin.cleangirl.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Balcony
import androidx.compose.material.icons.rounded.Bathtub
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Checkroom
import androidx.compose.material.icons.rounded.ChildCare
import androidx.compose.material.icons.rounded.Dining
import androidx.compose.material.icons.rounded.DirectionsWalk
import androidx.compose.material.icons.rounded.FitnessCenter
import androidx.compose.material.icons.rounded.Garage
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Hotel
import androidx.compose.material.icons.rounded.Inventory
import androidx.compose.material.icons.rounded.KingBed
import androidx.compose.material.icons.rounded.Kitchen
import androidx.compose.material.icons.rounded.LocalLaundryService
import androidx.compose.material.icons.rounded.Restaurant
import androidx.compose.material.icons.rounded.Roofing
import androidx.compose.material.icons.rounded.Stairs
import androidx.compose.material.icons.rounded.Terrain
import androidx.compose.material.icons.rounded.Wc
import androidx.compose.material.icons.rounded.Weekend
import androidx.compose.material.icons.rounded.Work
import androidx.compose.material.icons.rounded.Yard
import androidx.compose.ui.graphics.vector.ImageVector
import com.maruchin.cleangirl.data.model.RoomType

fun RoomType.toImageVector(): ImageVector = when (this) {
    RoomType.Default -> Icons.Rounded.Home
    RoomType.Kitchen -> Icons.Rounded.Kitchen
    RoomType.Bedroom -> Icons.Rounded.KingBed
    RoomType.Bathroom -> Icons.Rounded.Bathtub
    RoomType.LivingRoom -> Icons.Rounded.Weekend
    RoomType.DiningRoom -> Icons.Rounded.Dining
    RoomType.Hallway -> Icons.Rounded.DirectionsWalk
    RoomType.Office -> Icons.Rounded.Work
    RoomType.Garage -> Icons.Rounded.Garage
    RoomType.Laundry -> Icons.Rounded.LocalLaundryService
    RoomType.Pantry -> Icons.Rounded.Restaurant
    RoomType.Basement -> Icons.Rounded.Stairs
    RoomType.Attic -> Icons.Rounded.Roofing
    RoomType.Balcony -> Icons.Rounded.Balcony
    RoomType.Terrace -> Icons.Rounded.Terrain
    RoomType.Closet -> Icons.Rounded.Checkroom
    RoomType.Nursery -> Icons.Rounded.ChildCare
    RoomType.GuestRoom -> Icons.Rounded.Hotel
    RoomType.Storage -> Icons.Rounded.Inventory
    RoomType.Gym -> Icons.Rounded.FitnessCenter
    RoomType.Workshop -> Icons.Rounded.Build
    RoomType.Garden -> Icons.Rounded.Yard
    RoomType.Toilet -> Icons.Rounded.Wc
}

fun RoomType.toText(): String = when (this) {
    RoomType.Default -> "Pokój"
    RoomType.Kitchen -> "Kuchnia"
    RoomType.Bedroom -> "Sypialnia"
    RoomType.Bathroom -> "Łazienka"
    RoomType.LivingRoom -> "Salon"
    RoomType.DiningRoom -> "Jadalnia"
    RoomType.Hallway -> "Korytarz"
    RoomType.Office -> "Biuro"
    RoomType.Garage -> "Garaż"
    RoomType.Laundry -> "Pralnia"
    RoomType.Pantry -> "Spiżarnia"
    RoomType.Basement -> "Piwnica"
    RoomType.Attic -> "Poddasze"
    RoomType.Balcony -> "Balkon"
    RoomType.Terrace -> "Taras"
    RoomType.Closet -> "Garderoba"
    RoomType.Nursery -> "Pokój Dziecięcy"
    RoomType.GuestRoom -> "Pokój Gościnny"
    RoomType.Storage -> "Magazyn"
    RoomType.Gym -> "Siłownia"
    RoomType.Workshop -> "Warsztat"
    RoomType.Garden -> "Ogród"
    RoomType.Toilet -> "Toaleta"
}
