package com.maruchin.cleangirl.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bathroom
import androidx.compose.material.icons.rounded.BedroomParent
import androidx.compose.material.icons.rounded.Kitchen
import androidx.compose.material.icons.rounded.Weekend
import androidx.compose.ui.graphics.vector.ImageVector
import com.maruchin.cleangirl.data.model.RoomIcon

fun RoomIcon.asImageVector(): ImageVector = when (this) {
    RoomIcon.Kitchen -> Icons.Rounded.Kitchen
    RoomIcon.Bedroom -> Icons.Rounded.BedroomParent
    RoomIcon.Bathroom -> Icons.Rounded.Bathroom
    RoomIcon.LivingRoom -> Icons.Rounded.Weekend
}