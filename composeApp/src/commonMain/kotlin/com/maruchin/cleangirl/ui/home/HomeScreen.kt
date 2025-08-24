package com.maruchin.cleangirl.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.FloatingToolbarExitDirection
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.maruchin.cleangirl.core.utils.currentTimeMillis
import com.maruchin.cleangirl.core.utils.toLocalDateTime
import com.maruchin.cleangirl.data.model.TaskCompletionToggle
import com.maruchin.cleangirl.data.model.sampleRoomList
import com.maruchin.cleangirl.data.model.sampleUser
import com.maruchin.cleangirl.ui.home.components.HomeToolbar
import com.maruchin.cleangirl.ui.home.components.HomeTopBar
import com.maruchin.cleangirl.ui.home.components.RoomSelector
import com.maruchin.cleangirl.ui.home.components.RoomTaskList
import com.maruchin.cleangirl.ui.home.utils.rememberRoompagerState
import com.maruchin.cleangirl.ui.theme.CleanGirlTheme
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.ExperimentalTime

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalTime::class,
    ExperimentalMaterial3ExpressiveApi::class
)
@Composable
fun HomeScreen(state: HomeUiState, onTaskCompleteChange: (TaskCompletionToggle) -> Unit) {
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val toolbarScrollBehavior = FloatingToolbarDefaults.exitAlwaysScrollBehavior(
        exitDirection = FloatingToolbarExitDirection.Bottom
    )
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = currentTimeMillis)
    val roomPagerState = rememberRoompagerState(state.rooms)
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
        },
        modifier = Modifier.nestedScroll(toolbarScrollBehavior)
    ) { innerPadding ->
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding())
            ) {
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
                        onTaskCompleteChange = onTaskCompleteChange,
                        modifier = Modifier
                            .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
                    )
                }
            }
            HomeToolbar(
                currentRoom = state.rooms.getOrNull(roomPagerState.currentPage),
                scrollBehavior = toolbarScrollBehavior,
                modifier = Modifier.align(Alignment.BottomCenter).navigationBarsPadding()
            )
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