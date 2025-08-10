package com.maruchin.cleangirl.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maruchin.cleangirl.core.utils.currentTimeMillis
import com.maruchin.cleangirl.core.utils.toLocalDateTime
import com.maruchin.cleangirl.data.model.TaskCompletionToggle
import com.maruchin.cleangirl.data.model.sampleRoomList
import com.maruchin.cleangirl.data.model.sampleUser
import com.maruchin.cleangirl.ui.home.components.HomeTopBar
import com.maruchin.cleangirl.ui.home.components.RoomSelector
import com.maruchin.cleangirl.ui.home.components.RoomTaskList
import com.maruchin.cleangirl.ui.theme.CleanGirlTheme
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun HomeScreen(state: HomeUiState, onTaskCompleteChange: (TaskCompletionToggle) -> Unit) {
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = currentTimeMillis)
    val roomPagerState = rememberPagerState { state.rooms.size }
    val scope = rememberCoroutineScope()

    val date by remember {
        derivedStateOf {
            datePickerState.selectedDateMillis!!.toLocalDateTime().date
        }
    }

    Scaffold(
        topBar = {
            HomeTopBar(
                user = state.user,
                scrollBehavior = topAppBarScrollBehavior,
                datePickerState = datePickerState
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            RoomSelector(
                rooms = state.rooms,
                currentRoomIndex = roomPagerState.currentPage,
                onRoomChange = { index ->
                    scope.launch {
                        roomPagerState.animateScrollToPage(index)
                    }
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            HorizontalPager(state = roomPagerState) { page ->
                RoomTaskList(
                    room = state.rooms[page],
                    date = date,
                    topAppBarScrollBehavior = topAppBarScrollBehavior,
                    onTaskCompleteChange = onTaskCompleteChange
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    CleanGirlTheme {
        HomeScreen(
            state = HomeUiState(user = sampleUser, rooms = sampleRoomList),
            onTaskCompleteChange = {}
        )
    }
}