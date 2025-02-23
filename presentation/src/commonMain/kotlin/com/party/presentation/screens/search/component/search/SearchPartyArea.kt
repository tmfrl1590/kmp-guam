package com.party.presentation.screens.search.component.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.party.core.presentation.GRAY600
import com.party.core.presentation.HeightSpacer
import com.party.core.presentation.LoadingProgressBar
import com.party.core.presentation.PartyListItem1
import com.party.core.presentation.Resources
import com.party.core.presentation.WHITE
import com.party.core.presentation.chip.Chip
import com.party.core.presentation.no_data.NoDataColumn
import com.party.core.presentation.party_filter.PartyTypeAndIngAndOrderByFilterArea
import com.party.domain.model.party.PartyItem
import com.party.presentation.enum.StatusType
import com.party.presentation.screens.home.tab_recruitment.PartyTypeModal
import com.party.presentation.screens.search.SearchState
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchPartyArea(
    searchState: SearchState,
    onToggle: (String) -> Unit,
    onChangeOrderBy: (Boolean) -> Unit,
    onPartyTypeModel: (Boolean) -> Unit,
    onClick: (String) -> Unit,
    onReset: () -> Unit,
    onPartyTypeApply: () -> Unit,
    onPartyClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        PartyTypeAndIngAndOrderByFilterArea(
            isActiveParty = searchState.isActiveParty,
            onToggle = onToggle,
            isPartyTypeFilterClick = { onPartyTypeModel(true) },
            isDescParty = searchState.isDescParty,
            onChangeOrderBy = onChangeOrderBy,
        )

        HeightSpacer(heightDp = 12.dp)

        when{
            searchState.isLoadingParty -> LoadingProgressBar()
            searchState.partySearchedList.parties.isEmpty() -> NoDataColumn(title = "파티가 없어요.", modifier = Modifier.padding(60.dp))
            searchState.partySearchedList.parties.isNotEmpty() -> PartyListArea(
                partyList = searchState.partySearchedList.parties,
                onPartyClick = onPartyClick
            )
        }
    }

    if(searchState.isPartyTypeSheetOpen){
        PartyTypeModal(
            titleText = stringResource(Resources.String.Recruitment_Filter2),
            selectedPartyType = searchState.selectedTypeListParty,
            onClick = { onClick(it) },
            onModelClose = { onPartyTypeModel(false) },
            onReset = onReset,
            onApply = onPartyTypeApply
        )
    }
}

@Composable
private fun PartyListArea(
    partyList: List<PartyItem>,
    onPartyClick: (Int) -> Unit,
) {
    val listState = rememberLazyGridState()

    if(partyList.isNotEmpty()){
        LazyVerticalGrid(
            modifier = Modifier
                .padding(bottom = 8.dp),
            state = listState,
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            itemsIndexed(
                items = partyList,
                key = { index, _ ->
                    index
                }
            ) { _, item ->
                PartyListItem1(
                    imageUrl = item.image,
                    type = item.partyType.type,
                    title = item.title,
                    recruitmentCount = item.recruitmentCount,
                    typeChip = {
                        Chip(
                            modifier = Modifier
                                .padding(end = 4.dp),
                            containerColor = if(item.status == "active") Color(0xFFD5F0E3) else GRAY600,
                            contentColor = if(item.status == "active") Color(0xFF016110) else WHITE,
                            text = StatusType.fromType(item.status).toDisplayText(),
                        )
                    },
                    onClick = { onPartyClick(item.id) }
                )
            }
        }
    }
}