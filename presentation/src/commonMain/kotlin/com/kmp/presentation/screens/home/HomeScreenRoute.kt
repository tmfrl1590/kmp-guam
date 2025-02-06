package com.kmp.presentation.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreenRoute(
    homeViewModel: HomeViewModel = koinViewModel(),
) {
    val state by homeViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        homeViewModel.getPartyList(page = 1, limit = 50, sort = "createdAt", order = "DESC", partyTypes = listOf(1), titleSearch = null, status = null)
    }

    HomeScreen(
        state = state,
    )
}

@Composable
private fun HomeScreen(
    state: HomeState,
) {
    Text("Home Screen ${state.partyList.parties}")
}