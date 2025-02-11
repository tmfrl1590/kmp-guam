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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.core.presentation.WHITE
import com.kmp.core.presentation.homeTopTabList
import com.kmp.presentation.screens.home.component.HomeTopBar
import com.kmp.presentation.screens.home.component.HomeTopTabArea
import com.kmp.presentation.screens.home.tab_main.MainArea
import com.kmp.presentation.screens.home.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreenRoute(
    homeViewModel: HomeViewModel = koinViewModel(),
) {
    val homeState by homeViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        homeViewModel.getPartyList(page = 1, limit = 50, sort = "createdAt", order = "DESC", partyTypes = listOf(1), titleSearch = null, status = null)
        homeViewModel.getRecruitmentList(page = 1, limit = 50, sort = "createdAt", order = "DESC", titleSearch = null, partyTypes = null, position = null)
    }

    HomeScreen(
        homeState = homeState,
        onGotoSearch = {},
        onGotoRecruitmentDetail = { _, _ ->},
        onGotoPartyDetail = {},
        onAction = { action ->
            when(action){
                is HomeAction.OnTabClick -> { homeViewModel.onAction(action) }
            }
        }
    )
}

@Composable
private fun HomeScreen(
    homeState: HomeState,
    onGotoSearch: () -> Unit,
    onGotoRecruitmentDetail: (Int, Int) -> Unit,
    onGotoPartyDetail: (Int) -> Unit,
    onAction: (HomeAction) -> Unit,
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

                HomeTopTabArea(
                    homeTopTabList = homeTopTabList,
                    selectedTabText = homeState.selectedTabText,
                    onTabClick = { selectedTabText -> onAction(HomeAction.OnTabClick(tabText = selectedTabText)) }
                )

                when (homeState.selectedTabText) {
                    homeTopTabList[0] -> {
                        MainArea(
                            homeState = homeState,
                            onReload = {  },
                            onGoRecruitment = { onAction(HomeAction.OnTabClick(tabText = homeTopTabList[2])) },
                            onGoParty = { onAction(HomeAction.OnTabClick(tabText = homeTopTabList[1])) },
                            onGotoRecruitmentDetail = onGotoRecruitmentDetail,
                            onGotoPartyDetail = onGotoPartyDetail
                        )
                    }

                    homeTopTabList[1] -> {

                    }

                    homeTopTabList[2] -> {

                    }
                }


            }
        }
    }
}