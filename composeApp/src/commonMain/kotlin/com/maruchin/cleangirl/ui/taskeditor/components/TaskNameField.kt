package com.maruchin.cleangirl.ui.taskeditor.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldLabelPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskNameField(taskName: TextFieldState, modifier: Modifier = Modifier) {
    TextField(
        state = taskName,
        placeholder = {
            Text(text = "Co masz do zrobienia?")
        },
        labelPosition = TextFieldLabelPosition.Above(),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxWidth()
            .then(modifier)
    )
}
