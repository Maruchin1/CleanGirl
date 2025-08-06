package com.maruchin.cleangirl.ui.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import com.maruchin.cleangirl.data.model.sampleRoomList
import com.maruchin.cleangirl.data.model.sampleUser
import com.maruchin.cleangirl.ui.home.components.HomeTopBar
import com.maruchin.cleangirl.ui.theme.CleanGirlTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun HomeScreen(state: HomeUiState) {
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val currentDate = rememberDatePickerState(
        initialSelectedDateMillis = Clock.System.now().toEpochMilliseconds()
    )

    Scaffold(
        topBar = {
            HomeTopBar(
                user = state.user,
                scrollBehavior = topAppBarScrollBehavior,
                currentDate = currentDate
            )
        }
    ) { innerPadding ->

    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    CleanGirlTheme {
        HomeScreen(
            state = HomeUiState(user = sampleUser, rooms = sampleRoomList)
        )
    }
}