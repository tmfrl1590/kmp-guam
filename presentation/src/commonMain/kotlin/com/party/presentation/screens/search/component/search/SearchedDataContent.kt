package com.party.presentation.screens.search.component.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.core.presentation.HeightSpacer
import com.party.core.presentation.searchTabList
import com.party.presentation.screens.search.SearchState

@Composable
fun SearchedDataContent(
    searchState: SearchState,
    onTabClick: (String) -> Unit,
    onToggle: (String) -> Unit,
    onChangeOrderByParty: (Boolean) -> Unit,
    onChangeOrderByRecruitment: (Boolean) -> Unit,
    onPartyTypeModel: (Boolean) -> Unit,
    onPartyTypeRecruitment: (Boolean) -> Unit,
    onPartyTypeItem1: (String) -> Unit,
    onPartyTypeItem2: (String) -> Unit,
    onReset: () -> Unit,
    onPartyTypeApply: () -> Unit,
    onPartyTypeApply2: () -> Unit,
    onPositionSheetClose: (Boolean) -> Unit,
    onPositionSheetClick: () -> Unit,
    onMainPositionClick: (String) -> Unit,
    onSubPositionClick: (String) -> Unit,
    onDelete: (Pair<String, String>) -> Unit,
    onPositionApply: () -> Unit,
    onPartyClick: (Int) -> Unit,
    onRecruitmentClick: (Int, Int) -> Unit
) {
    Column {
        SearchTopTabArea(
            modifier = Modifier.height(48.dp),
            searchTabList = searchTabList,
            selectedTabText = searchState.selectedTabText,
            onTabClick = onTabClick
        )

        HeightSpacer(heightDp = 20.dp)

        when(searchState.selectedTabText) {
            searchTabList[0] -> {
                SearchEntireArea(
                    partyList = searchState.allSearchedList.party.parties,
                    recruitmentList = searchState.allSearchedList.partyRecruitment.partyRecruitments,
                    onPartyClick = onPartyClick,
                    onRecruitmentClick = onRecruitmentClick,
                )
            }
            searchTabList[1] -> {
                SearchPartyArea(
                    searchState = searchState,
                    onChangeOrderBy = onChangeOrderByParty,
                    onToggle = onToggle,
                    onPartyTypeModel = onPartyTypeModel,
                    onClick = onPartyTypeItem1,
                    onReset = onReset,
                    onPartyTypeApply = onPartyTypeApply,
                    onPartyClick = onPartyClick
                )
            }
            searchTabList[2] -> {
                SearchRecruitmentArea(
                    searchState = searchState,
                    onPartyTypeModel = onPartyTypeRecruitment,
                    onChangeOrderBy = onChangeOrderByRecruitment,
                    onClick = onPartyTypeItem2,
                    onReset = onReset,
                    onPartyTypeApply2 = onPartyTypeApply2,
                    onPositionSheetClose = onPositionSheetClose,
                    onPositionSheetClick = onPositionSheetClick,
                    onMainPositionClick = onMainPositionClick,
                    onSubPositionClick = onSubPositionClick,
                    onDelete = onDelete,
                    onPositionApply = onPositionApply,
                    onRecruitmentClick = onRecruitmentClick,
                )
            }
        }
    }
}