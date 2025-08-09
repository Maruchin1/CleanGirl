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
import com.maruchin.cleangirl.data.model.RoomIcon

fun RoomIcon.toImageVector(): ImageVector = when (this) {
    RoomIcon.Home -> Icons.Rounded.Home
    RoomIcon.Kitchen -> Icons.Rounded.Kitchen
    RoomIcon.Bedroom -> Icons.Rounded.KingBed
    RoomIcon.Bathroom -> Icons.Rounded.Bathtub
    RoomIcon.LivingRoom -> Icons.Rounded.Weekend
    RoomIcon.DiningRoom -> Icons.Rounded.Dining
    RoomIcon.Hallway -> Icons.Rounded.DirectionsWalk
    RoomIcon.Office -> Icons.Rounded.Work
    RoomIcon.Garage -> Icons.Rounded.Garage
    RoomIcon.Laundry -> Icons.Rounded.LocalLaundryService
    RoomIcon.Pantry -> Icons.Rounded.Restaurant
    RoomIcon.Basement -> Icons.Rounded.Stairs
    RoomIcon.Attic -> Icons.Rounded.Roofing
    RoomIcon.Balcony -> Icons.Rounded.Balcony
    RoomIcon.Terrace -> Icons.Rounded.Terrain
    RoomIcon.Closet -> Icons.Rounded.Checkroom
    RoomIcon.Nursery -> Icons.Rounded.ChildCare
    RoomIcon.GuestRoom -> Icons.Rounded.Hotel
    RoomIcon.Storage -> Icons.Rounded.Inventory
    RoomIcon.Gym -> Icons.Rounded.FitnessCenter
    RoomIcon.Workshop -> Icons.Rounded.Build
    RoomIcon.Garden -> Icons.Rounded.Yard
    RoomIcon.Toilet -> Icons.Rounded.Wc
}

fun RoomIcon.toText(): String = when (this) {
    RoomIcon.Home -> "Pokój"
    RoomIcon.Kitchen -> "Kuchnia"
    RoomIcon.Bedroom -> "Sypialnia"
    RoomIcon.Bathroom -> "Łazienka"
    RoomIcon.LivingRoom -> "Salon"
    RoomIcon.DiningRoom -> "Jadalnia"
    RoomIcon.Hallway -> "Korytarz"
    RoomIcon.Office -> "Biuro"
    RoomIcon.Garage -> "Garaż"
    RoomIcon.Laundry -> "Pralnia"
    RoomIcon.Pantry -> "Spiżarnia"
    RoomIcon.Basement -> "Piwnica"
    RoomIcon.Attic -> "Poddasze"
    RoomIcon.Balcony -> "Balkon"
    RoomIcon.Terrace -> "Taras"
    RoomIcon.Closet -> "Garderoba"
    RoomIcon.Nursery -> "Pokój Dziecięcy"
    RoomIcon.GuestRoom -> "Pokój Gościnny"
    RoomIcon.Storage -> "Magazyn"
    RoomIcon.Gym -> "Siłownia"
    RoomIcon.Workshop -> "Warsztat"
    RoomIcon.Garden -> "Ogród"
    RoomIcon.Toilet -> "Toaleta"
}
