package com.maruchin.cleangirl.ui.home.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.maruchin.cleangirl.core.utils.toLocalDateTime
import com.maruchin.cleangirl.core.utils.toMillis
import com.maruchin.cleangirl.ui.theme.CleanGirlTheme
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DateSelector(datePickerState: DatePickerState) {
    var isSelecting by rememberSaveable { mutableStateOf(false) }

    FilledTonalButton(
        onClick = { isSelecting = true },
        contentPadding = ButtonDefaults.ExtraSmallContentPadding,
        shape = ButtonDefaults.squareShape,
        modifier = Modifier.height(ButtonDefaults.ExtraSmallContainerHeight)
    ) {
        Icon(
            imageVector = Icons.Rounded.CalendarToday,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.ExtraSmallIconSize)
        )
        Spacer(modifier = Modifier.width(ButtonDefaults.ExtraSmallIconSpacing))
        Text(
            text = datePickerState.selectedDateMillis
                ?.toLocalDateTime()
                ?.date
                ?.toString()
                .orEmpty()
        )
    }

    if (isSelecting) {
        DatePickerDialog(
            onDismissRequest = { isSelecting = false },
            confirmButton = {
                Button(onClick = { isSelecting = false }) {
                    Text(text = "Zamknij")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun DateSelectorPreview() {
    CleanGirlTheme {
        DateSelector(
            datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = LocalDateTime(2025, 8, 4, 22, 15).toMillis()
            )
        )
    }
}