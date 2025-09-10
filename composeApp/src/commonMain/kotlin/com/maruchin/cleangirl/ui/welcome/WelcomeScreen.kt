package com.maruchin.cleangirl.ui.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maruchin.cleangirl.ui.theme.CleanGirlTheme
import com.maruchin.cleangirl.ui.welcome.components.GoToAppButton
import com.maruchin.cleangirl.ui.welcome.components.UserNameField
import com.maruchin.cleangirl.ui.welcome.components.WelcomeTopBar
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun WelcomeScreen(
    onGoToApp: (String) -> Unit,
    userName: TextFieldState = rememberTextFieldState()
) {
    Scaffold(
        topBar = { WelcomeTopBar() }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            UserNameFieldSection(
                userName = userName,
                modifier = Modifier.weight(1f)
            )
            GoToAppButtonSection(
                canGoToApp = userName.text.isNotBlank(),
                onGoToApp = {
                    onGoToApp(userName.text.toString())
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun UserNameFieldSection(userName: TextFieldState, modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.BottomCenter) {
        UserNameField(
            state = userName,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun GoToAppButtonSection(
    canGoToApp: Boolean,
    onGoToApp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier, contentAlignment = Alignment.TopCenter) {
        AnimatedVisibility(visible = canGoToApp, modifier = Modifier.fillMaxWidth()) {
            GoToAppButton(
                onClick = onGoToApp,
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 32.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    CleanGirlTheme {
        WelcomeScreen(onGoToApp = {})
    }
}

@Preview
@Composable
fun WelcomeScreenPreview_UserNameEntered() {
    CleanGirlTheme {
        WelcomeScreen(userName = rememberTextFieldState("Marcin"), onGoToApp = {})
    }
}