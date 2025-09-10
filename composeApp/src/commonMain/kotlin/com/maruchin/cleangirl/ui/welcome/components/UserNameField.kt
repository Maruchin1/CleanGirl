package com.maruchin.cleangirl.ui.welcome.components

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UserNameField(state: TextFieldState, modifier: Modifier = Modifier) {
    TextField(
        state = state,
        label = {
            Text(text = "Jak masz na imię?")
        },
        modifier = modifier,
    )
}