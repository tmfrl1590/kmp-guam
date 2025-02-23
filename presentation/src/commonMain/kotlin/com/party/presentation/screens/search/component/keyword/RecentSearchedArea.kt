package com.party.presentation.screens.search.component.keyword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.core.presentation.B3
import com.party.core.presentation.GRAY400
import com.party.core.presentation.HeightSpacer
import com.party.core.presentation.RED
import com.party.core.presentation.T3
import com.party.core.presentation.TextComponent
import com.party.core.presentation.chip.RoundChip
import com.party.core.presentation.no_data.NoDataRow
import com.party.domain.room.KeywordModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecentSearchedArea(
    keywordList: List<KeywordModel>,
    onDelete: (String) -> Unit,
    onAllDelete: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        RecentSearchedAreaTitle(
            isExistKeywordList = keywordList.isNotEmpty(),
            onAllDelete = onAllDelete
        )

        if(keywordList.isEmpty()){
            NoDataRow(
                text = "검색 기록이 없어요",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            )
        }else {
            HeightSpacer(heightDp = 20.dp)
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                keywordList.forEach { keyword ->
                    KeywordListItem(
                        text = keyword.keyword,
                        onDelete = { onDelete(keyword.keyword) }
                    )
                }
            }
        }
    }
}

@Composable
private fun KeywordListItem(
    text: String,
    onDelete: () -> Unit,
) {
    RoundChip(
        text = text,
        onIconClick = onDelete
    )
}

@Composable
fun RecentSearchedAreaTitle(
    isExistKeywordList: Boolean,
    onAllDelete: () -> Unit
) {
    HeightSpacer(heightDp = 24.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextComponent(
            text = "최근 검색",
            fontSize = T3,
            fontWeight = FontWeight.SemiBold,
        )
        TextComponent(
            text = "전체 삭제",
            fontSize = B3,
            textColor = if(isExistKeywordList) RED else GRAY400,
            onClick = onAllDelete
        )
    }
}