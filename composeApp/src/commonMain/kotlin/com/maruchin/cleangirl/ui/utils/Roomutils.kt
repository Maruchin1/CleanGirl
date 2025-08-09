package com.maruchin.cleangirl.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bathtub
import androidx.compose.material.icons.rounded.KingBed
import androidx.compose.material.icons.rounded.Kitchen
import androidx.compose.material.icons.rounded.Weekend
import androidx.compose.ui.graphics.vector.ImageVector
import com.maruchin.cleangirl.data.model.RoomIcon

fun RoomIcon.toImageVector(): ImageVector = when (this) {
    RoomIcon.Kitchen -> Icons.Rounded.Kitchen
    RoomIcon.Bedroom -> Icons.Rounded.KingBed
    RoomIcon.Bathroom -> Icons.Rounded.Bathtub
    RoomIcon.LivingRoom -> Icons.Rounded.Weekend
}