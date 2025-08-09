package com.maruchin.cleangirl.ui.home.components

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LargeFlexibleTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import com.maruchin.cleangirl.data.model.User

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeTopBar(
    user: User?,
    scrollBehavior: TopAppBarScrollBehavior,
    datePickerState: DatePickerState
) {
    LargeFlexibleTopAppBar(
        title = {
            if (user != null) {
                Text(text = "Witaj ${user.name}")
            }
        },
        subtitle = {
            Text(text = "Lorem ipsum dolor sit amet")
        },
        actions = {
            DateSelector(datePickerState)
        },
        scrollBehavior = scrollBehavior
    )
}
