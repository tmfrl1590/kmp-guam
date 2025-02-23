package com.party.presentation.screens.search.component.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.core.presentation.HeightSpacer
import com.party.core.presentation.LoadingProgressBar
import com.party.core.presentation.RecruitmentListItem2
import com.party.core.presentation.Resources
import com.party.core.presentation.bottomsheet.PositionBottomSheet
import com.party.core.presentation.no_data.NoDataColumn
import com.party.core.presentation.party_filter.PositionAndPartyTypeAndOrderByArea
import com.party.domain.model.party.RecruitmentItem
import com.party.presentation.screens.home.tab_recruitment.PartyTypeModal
import com.party.presentation.screens.search.SearchState
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchRecruitmentArea(
    searchState: SearchState,
    onPartyTypeModel: (Boolean) -> Unit,
    onChangeOrderBy: (Boolean) -> Unit,
    onClick: (String) -> Unit,
    onReset: () -> Unit,
    onPartyTypeApply2: () -> Unit,
    onPositionSheetClose: (Boolean) -> Unit,
    onPositionSheetClick: () -> Unit,
    onMainPositionClick: (String) -> Unit,
    onSubPositionClick: (String) -> Unit,
    onDelete: (Pair<String, String>) -> Unit,
    onPositionApply: () -> Unit,
    onRecruitmentClick: (Int, Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        PositionAndPartyTypeAndOrderByArea(
            isDescRecruitment = searchState.isDescRecruitment,
            onPositionSheetClick = onPositionSheetClick,
            onPartyTypeFilterClick = { onPartyTypeModel(true) },
            onChangeOrderBy = onChangeOrderBy,
            selectedPartyTypeCount = searchState.selectedTypeListSize,
        )

        HeightSpacer(heightDp = 12.dp)

        when {
            searchState.isLoadingRecruitment -> LoadingProgressBar()
            searchState.recruitmentSearchedList.partyRecruitments.isEmpty() -> NoDataColumn(title = "모집공고가 없어요.", modifier = Modifier.padding(60.dp))
            searchState.recruitmentSearchedList.partyRecruitments.isNotEmpty() -> {
                RecruitmentListArea(
                    recruitmentList = searchState.recruitmentSearchedList.partyRecruitments,
                    onRecruitmentClick = onRecruitmentClick
                )
            }
        }
    }

    if(searchState.isPositionSheetOpen){
        PositionBottomSheet(
            selectedMainPosition = searchState.selectedMainPosition,
            getSubPositionList = searchState.getSubPositionList.map { it.sub to it.id },
            selectedSubPositionList = searchState.selectedSubPositionList.map { it.sub to it.id },
            selectedMainAndSubPositionList = searchState.selectedMainAndSubPosition,
            onSheetClose = { onPositionSheetClose(false) },
            onMainPositionClick = onMainPositionClick,
            onSubPositionClick = onSubPositionClick,
            onDelete = onDelete,
            onReset = onReset,
            onApply = onPositionApply,
        )
    }

    if(searchState.isPartyTypeSheetOpenRecruitment){
        PartyTypeModal(
            titleText = stringResource(Resources.String.Recruitment_Filter2),
            selectedPartyType = searchState.selectedTypeListRecruitment,
            onClick = { onClick(it) },
            onModelClose = { onPartyTypeModel(false) },
            onReset = onReset,
            onApply = onPartyTypeApply2,
        )
    }
}

@Composable
private fun RecruitmentListArea(
    recruitmentList: List<RecruitmentItem>,
    onRecruitmentClick: (Int, Int) -> Unit,
) {
    if(recruitmentList.isNotEmpty()){
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(
                items = recruitmentList,
                key = { index, _ ->
                    index
                }
            ){ _, item ->
                RecruitmentListItem2(
                    id = item.id,
                    partyId = item.party.id,
                    imageUrl = item.party.image,
                    category = item.party.partyType.type,
                    title = item.party.title,
                    main = item.position.main,
                    sub = item.position.sub,
                    recruitingCount = item.recruitingCount,
                    recruitedCount = item.recruitedCount,
                    onClick = onRecruitmentClick
                )
            }
        }
    }
}