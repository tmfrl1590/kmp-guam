package com.party.core.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

val TAB_AREA_ITEM_MIN_WIDTH = 40.dp
val TAB_AREA_ITEM_MAX_WIDTH = 68.dp

val homeTopTabList = listOf("메인", "파티", "모집공고")
val partyDetailTabList = listOf("홈", "파티원", "모집공고")

val searchTabList = listOf("전체", "파티", "모집공고")

val stateTabList = listOf("내 파티", "지원목록")

val profileEditTendencyTabList = listOf("1단계", "2단계", "3단계")

@Composable
fun TabArea(
    modifier: Modifier = Modifier,
    tabList: List<String>,
    selectedTabText: String,
    onTabClick: (String) -> Unit,
    selectedTabColor: Color = PRIMARY,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(COMPONENT_AREA_HEIGHT),
            horizontalArrangement = Arrangement.Start
        ) {
            tabList.forEachIndexed { index, title ->
                TabAreaItem(
                    text = title,
                    textColor = if (selectedTabText == title) BLACK else GRAY400,
                    isShowSelectedIndicate = selectedTabText == title,
                    onTabClick = { onTabClick(it) },
                    selectedTabColor = selectedTabColor
                )
                WidthSpacer(widthDp = 20.dp)
            }
        }
        HeightSpacer(heightDp = 4.dp)
    }
}
@Composable
fun TabAreaItem(
    text: String,
    textColor: Color,
    isShowSelectedIndicate: Boolean,
    onTabClick: (String) -> Unit,
    selectedTabColor: Color,
){
    Column(
        modifier = Modifier
            .widthIn(min = TAB_AREA_ITEM_MIN_WIDTH, max = TAB_AREA_ITEM_MAX_WIDTH)
            .height(COMPONENT_AREA_HEIGHT)
            .noRippleClickable { onTabClick(text) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ){
            Text(
                text = text,
                color = textColor,
                fontWeight = FontWeight.SemiBold,
                fontSize = T3
            )
        }
        if (isShowSelectedIndicate) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .align(Alignment.CenterHorizontally),
                thickness = 4.dp,
                color = selectedTabColor
            )
        }
    }
}