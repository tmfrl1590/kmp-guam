package com.party.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.party.core.Screens
import com.party.core.presentation.HeightSpacer
import com.party.core.presentation.MEDIUM_PADDING_SIZE
import com.party.core.presentation.Resources
import com.party.core.presentation.ScreenExplainArea
import com.party.core.presentation.WHITE
import com.party.presentation.screens.login.component.LoginButtonArea
import com.party.presentation.screens.login.component.LoginScaffoldArea
import com.party.presentation.screens.login.component.LoginScreenBottomArea
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreenRoute(
    snackBarHostState: SnackbarHostState,
    navController: NavHostController,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        loginViewModel.goToHomeScreen.collectLatest {
            navController.navigate(Screens.Home)
            //navController.navigate(Screens.SelectTendency1)
        }
    }

    LoginScreen(
        onGoogleLoginSuccess = { token ->
            loginViewModel.serverToGoogleLogin(
                userEmail = "",
                accessToken = token
            )
        }
    )
}

@Composable
private fun LoginScreen(
    onGoogleLoginSuccess: (String) -> Unit,
) {
    Scaffold (
        topBar = {
            LoginScaffoldArea()
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WHITE)
                .padding(it)
                .padding(horizontal = MEDIUM_PADDING_SIZE)
        ) {
            ScreenExplainArea(
                mainExplain = stringResource(Resources.String.Login1),
                subExplain = stringResource(Resources.String.Login2),
            )

            HeightSpacer(heightDp = 40.dp)

            LoginButtonArea(
                onGoogleLoginSuccess = onGoogleLoginSuccess
            )

            LoginScreenBottomArea()
        }
    }
}