package com.party.presentation.screens.state

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.party.core.presentation.BottomNavigationBar
import com.party.core.presentation.MEDIUM_PADDING_SIZE
import com.party.core.presentation.WHITE

@Composable
fun StateScreenRoute(
    navController: NavHostController,
) {
    StateScreen(
        navController = navController
    )
}

@Composable
private fun StateScreen(
    navController: NavHostController,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    //context = context,
                    navController = navController,
                )
            },
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(WHITE)
                    .padding(it)
                    .padding(horizontal = MEDIUM_PADDING_SIZE),
            ) {

            }
        }
    }
}