package com.party.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.party.core.presentation.BottomNavigationBar
import com.party.core.presentation.MEDIUM_PADDING_SIZE
import com.party.core.presentation.WHITE

@Composable
fun ProfileScreenRoute(
    navController: NavHostController,
) {
    ProfileScreen(
        navController = navController
    )
}

@Composable
private fun ProfileScreen(
    navController: NavHostController,
){
    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
            )
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WHITE)
                .padding(it)
                .padding(horizontal = MEDIUM_PADDING_SIZE)
                .verticalScroll(scrollState)
        ) {

        }
    }
}