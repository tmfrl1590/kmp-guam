package com.kmp.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.core.presentation.PartyListItem1
import com.kmp.core.presentation.WHITE
import com.kmp.presentation.screens.home.component.HomeTopBar
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
        onGotoSearch = {}
    )
}

@Composable
private fun HomeScreen(
    state: HomeState,
    onGotoSearch: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            bottomBar = {

            }
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(WHITE)
                    .padding(it)
                    .padding(horizontal = 20.dp)

            ) {
                HomeTopBar(
                    onGoToSearch = onGotoSearch,
                    onGoToAlarm = {}
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(
                        items = state.partyList.parties,
                        key = { index, _ ->
                            index
                        }
                    ) { _, item ->
                        PartyListItem1(
                            imageUrl = item.image,
                            type = item.partyType.type,
                            title = item.title,
                            recruitmentCount = item.recruitmentCount,
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}