package com.party.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.party.core.presentation.WHITE
import com.party.core.presentation.homeTopTabList
import com.party.presentation.navigation.Screens
import com.party.presentation.screens.home.component.HomeTopBar
import com.party.presentation.screens.home.component.HomeTopTabArea
import com.party.presentation.screens.home.tab_main.MainArea
import com.party.presentation.screens.home.tab_party.PartyArea
import com.party.presentation.screens.home.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreenRoute(
    navController: NavHostController,
    homeViewModel: HomeViewModel = koinViewModel(),
) {
    val homeState by homeViewModel.state.collectAsStateWithLifecycle()

    val gridState = rememberLazyGridState()
    val listState = rememberLazyListState()

    LaunchedEffect(key1 = Unit) {
        homeViewModel.getPartyList(page = 1, limit = 50, sort = "createdAt", order = "DESC", partyTypes = emptyList(), titleSearch = null, status = null)
        homeViewModel.getRecruitmentList(page = 1, limit = 50, sort = "createdAt", order = "DESC", titleSearch = null, partyTypes = null, position = null)
    }

    HomeScreen(
        homeState = homeState,
        navController = navController,
        listState = listState,
        gridState = gridState,
        onGotoSearch = {},
        onGotoRecruitmentDetail = { _, _ ->},
        onGotoPartyDetail = {},
        onAction = { action ->
            when(action){
                is HomeAction.OnTabClick -> { homeViewModel.onAction(action) }
                is HomeAction.OnPersonalRecruitmentReload -> { homeViewModel.onAction(action) }
                is HomeAction.OnPartyTypeSheetOpen -> { homeViewModel.onAction(action) }
                is HomeAction.OnSelectedPartyType -> { homeViewModel.onAction(action) }
                is HomeAction.OnSelectedPartyTypeReset -> { homeViewModel.onAction(action) }
                is HomeAction.OnPartyTypeApply -> { homeViewModel.onAction(action) }
                is HomeAction.OnActivePartyToggle -> { homeViewModel.onAction(action) }
                is HomeAction.OnDescPartyArea -> { homeViewModel.onAction(action) }
                is HomeAction.OnPositionSheetOpen -> { homeViewModel.onAction(action) }
                is HomeAction.OnPartyTypeSheetOpenRecruitment -> { homeViewModel.onAction(action) }
                is HomeAction.OnDescRecruitment -> { homeViewModel.onAction(action) }
                is HomeAction.OnMainPositionClick -> { homeViewModel.onAction(action) }
                is HomeAction.OnSubPositionClick -> { homeViewModel.onAction(action) }
                is HomeAction.OnDelete -> { homeViewModel.onAction(action) }
                is HomeAction.OnPositionApply -> { homeViewModel.onAction(action) }
                is HomeAction.OnPositionSheetReset -> { homeViewModel.onAction(action) }
                is HomeAction.OnSelectedPartyTypeResetRecruitmentReset -> { homeViewModel.onAction(action) }
                is HomeAction.OnSelectedPartyTypeRecruitment -> { homeViewModel.onAction(action) }
                is HomeAction.OnPartyTypeApplyRecruitment -> { homeViewModel.onAction(action) }
                is HomeAction.OnExpandedFloating -> { homeViewModel.onAction(action) }
            }
        }
    )
}

@Composable
private fun HomeScreen(
    homeState: HomeState,
    navController: NavHostController,
    listState: LazyListState,
    gridState: LazyGridState,
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
                        PartyArea(
                            gridState = gridState,
                            homeState = homeState,
                            onClick = {  },
                            onPartyTypeModal = { isOpen -> onAction(HomeAction.OnPartyTypeSheetOpen(isOpen)) },
                            onSelectPartyType = { selectedPartyType -> onAction(HomeAction.OnSelectedPartyType(selectedPartyType)) },
                            onReset = { onAction(HomeAction.OnSelectedPartyTypeReset) },
                            onApply = { onAction(HomeAction.OnPartyTypeApply) },
                            onActivePartyToggle = { onToggle -> onAction(HomeAction.OnActivePartyToggle(onToggle)) },
                            onChangeOrderByPartyArea = { isDesc -> onAction(HomeAction.OnDescPartyArea(isDesc)) }
                        )
                    }

                    homeTopTabList[2] -> {

                    }
                }


            }
        }
    }
}