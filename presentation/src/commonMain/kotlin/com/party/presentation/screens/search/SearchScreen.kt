package com.party.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.party.core.Screens
import com.party.core.presentation.BottomNavigationBar
import com.party.core.presentation.MEDIUM_PADDING_SIZE
import com.party.core.presentation.WHITE
import com.party.presentation.screens.search.component.SearchArea
import com.party.presentation.screens.search.component.keyword.RecentSearchedArea
import com.party.presentation.screens.search.component.search.SearchedDataContent
import com.party.presentation.screens.search.viewmodel.SearchViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchRoute(
    navController: NavHostController,
    searchViewModel: SearchViewModel = koinViewModel(),
){
    val searchState by searchViewModel.searchState.collectAsStateWithLifecycle()

    SearchScreen(
        navController = navController,
        searchState = searchState,
        onAction = { action ->
            when(action){
                is SearchAction.OnNavigationBack -> { navController.popBackStack() }
                is SearchAction.OnInputKeywordChange -> { searchViewModel.onAction(action) }
                is SearchAction.OnTabClick -> { searchViewModel.onAction(action) }
                is SearchAction.OnIsShowKeywordAreaChange -> { searchViewModel.onAction(action) }
                is SearchAction.OnSearch -> { searchViewModel.onAction(action) }
                is SearchAction.OnDeleteKeyword -> { searchViewModel.onAction(action) }
                is SearchAction.OnAllDeleteKeyword -> { searchViewModel.onAction(action) }
                is SearchAction.OnChangeOrderByParty -> { searchViewModel.onAction(action) }
                is SearchAction.OnChangeActive -> { searchViewModel.onAction(action) }
                is SearchAction.OnPartyTypeModelClose -> { searchViewModel.onAction(action) }
                is SearchAction.OnSelectedPartyType -> { searchViewModel.onAction(action) }
                is SearchAction.OnPartyTypeReset -> { searchViewModel.onAction(action) }
                is SearchAction.OnPartyTypeApply -> { searchViewModel.onAction(action) }
                is SearchAction.OnChangeOrderByRecruitment -> { searchViewModel.onAction(action) }
                is SearchAction.OnPartyTypeRecruitment -> { searchViewModel.onAction(action) }
                is SearchAction.OnSelectedPartyTypeRecruitment -> { searchViewModel.onAction(action) }
                is SearchAction.OnPartyTypeApply2 -> { searchViewModel.onAction(action) }
                is SearchAction.OnPositionSheetClose -> { searchViewModel.onAction(action) }
                is SearchAction.OnPositionSheetOpenClick -> { searchViewModel.onAction(action) }
                is SearchAction.OnMainPositionClick -> { searchViewModel.onAction(action) }
                is SearchAction.OnSubPositionClick -> { searchViewModel.onAction(action) }
                is SearchAction.OnDelete -> { searchViewModel.onAction(action) }
                is SearchAction.OnPositionApply -> { searchViewModel.onAction(action) }
            }
        }
    )
}

@Composable
private fun SearchScreen(
    navController: NavHostController,
    searchState: SearchState,
    onAction: (SearchAction) -> Unit
){
    Scaffold(
        topBar = {
            SearchArea(
                keyword = searchState.inputKeyword,
                onValueChange = { onAction(SearchAction.OnInputKeywordChange(it)) },
                onNavigationClick = { onAction(SearchAction.OnNavigationBack) },
                searchAction = { onAction(SearchAction.OnSearch) }
            )
        },
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
        ) {
            if (searchState.isShowKeywordArea) {
                RecentSearchedArea(
                    keywordList = searchState.keywordList,
                    onAllDelete = { onAction(SearchAction.OnAllDeleteKeyword) },
                    onDelete = { keyword -> onAction(SearchAction.OnDeleteKeyword(keyword)) },
                )
            } else {
                SearchedDataContent(
                    searchState = searchState,
                    onTabClick = { selectedTab -> onAction(SearchAction.OnTabClick(selectedTab)) },
                    onChangeOrderByParty = { isDesc -> onAction(SearchAction.OnChangeOrderByParty(isDesc)) },
                    onToggle = { status -> onAction(SearchAction.OnChangeActive(status)) },
                    onPartyTypeModel = { isVisible -> onAction(SearchAction.OnPartyTypeModelClose(isVisible)) },
                    onPartyTypeItem1 = { selectedPartyType -> onAction(SearchAction.OnSelectedPartyType(selectedPartyType)) },
                    onReset = { onAction(SearchAction.OnPartyTypeReset) },
                    onPartyTypeApply = { onAction(SearchAction.OnPartyTypeApply) },
                    onChangeOrderByRecruitment = { isDesc -> onAction(SearchAction.OnChangeOrderByRecruitment(isDesc)) },
                    onPartyTypeRecruitment = { isVisible -> onAction(SearchAction.OnPartyTypeRecruitment(isVisible)) },
                    onPartyTypeItem2 = { selectedPartyType -> onAction(SearchAction.OnSelectedPartyTypeRecruitment(selectedPartyType)) },
                    onPartyTypeApply2 = { onAction(SearchAction.OnPartyTypeApply2) },
                    onPositionSheetClose = { isVisible -> onAction(SearchAction.OnPositionSheetClose(isVisible)) },
                    onPositionSheetClick = { onAction(SearchAction.OnPositionSheetOpenClick) },
                    onMainPositionClick = { selectedMainPosition -> onAction(SearchAction.OnMainPositionClick(selectedMainPosition)) },
                    onSubPositionClick = { selectedSubPosition -> onAction(SearchAction.OnSubPositionClick(selectedSubPosition)) },
                    onDelete = { position -> onAction(SearchAction.OnDelete(position)) },
                    onPositionApply = { onAction(SearchAction.OnPositionApply) },
                    onPartyClick = { },
                    onRecruitmentClick = { _, _ -> }
                )
            }
        }
    }
}