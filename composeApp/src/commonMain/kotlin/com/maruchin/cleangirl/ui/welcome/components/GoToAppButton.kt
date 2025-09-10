package com.maruchin.cleangirl.ui.welcome.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun GoToAppButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        contentPadding = ButtonDefaults.MediumContentPadding,
        modifier = modifier.height(ButtonDefaults.MediumContainerHeight),
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ExitToApp,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.MediumIconSize),
        )
        Spacer(modifier = Modifier.width(ButtonDefaults.MediumIconSpacing))
        Text(text = "Przejdź do aplikacji")
    }
}