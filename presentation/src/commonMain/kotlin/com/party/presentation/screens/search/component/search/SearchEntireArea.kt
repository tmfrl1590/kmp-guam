package com.party.presentation.screens.search.component.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.core.presentation.HeightSpacer
import com.party.core.presentation.PartyListItem1
import com.party.core.presentation.RecruitmentListItem1
import com.party.core.presentation.no_data.NoDataColumn
import com.party.domain.model.search.SearchedPartyData
import com.party.domain.model.search.SearchedRecruitmentData

@Composable
fun SearchEntireArea(
    partyList: List<SearchedPartyData>,
    recruitmentList: List<SearchedRecruitmentData>,
    onPartyClick: (Int) -> Unit,
    onRecruitmentClick: (Int, Int) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        SearchedContentTitle(
            title = "파티",
            onClick = {}
        )
        HeightSpacer(heightDp = 20.dp)
        SearchedPartyList(
            partyList = partyList,
            onPartyClick = onPartyClick
        )

        HeightSpacer(heightDp = 60.dp)

        SearchedContentTitle(
            title = "모집공고",
            onClick = {}
        )
        HeightSpacer(heightDp = 8.dp)
        SearchedRecruitmentList(
            recruitmentList = recruitmentList,
            onRecruitmentClick = onRecruitmentClick
        )
    }
}

@Composable
private fun SearchedPartyList(
    partyList: List<SearchedPartyData>,
    onPartyClick: (Int) -> Unit,
) {
    if(partyList.isNotEmpty()){
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
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
                    onClick = { onPartyClick(item.id) }
                )
            }
        }
    }else {
        NoDataColumn(
            title = "파티가 없어요",
        )
    }

}

@Composable
private fun SearchedRecruitmentList(
    recruitmentList: List<SearchedRecruitmentData>,
    onRecruitmentClick: (Int, Int) -> Unit,
) {
    if(recruitmentList.isNotEmpty()){
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(
                items = recruitmentList,
                key = { index, _ ->
                    index
                }
            ) { _, item ->
                RecruitmentListItem1(
                    imageUrl = item.party.image,
                    category = item.party.partyType.type,
                    title = item.party.title,
                    main = item.position.main,
                    sub = item.position.sub,
                    recruitingCount = 1,
                    recruitedCount = 0,
                    onClick = { onRecruitmentClick(item.id, item.party.id) }
                )
            }
        }
    }else {
        NoDataColumn(title = "모집공고가 없어요")
    }
}