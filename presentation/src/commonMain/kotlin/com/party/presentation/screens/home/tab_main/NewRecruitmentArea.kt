package com.party.presentation.screens.home.tab_main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.core.presentation.GRAY100
import com.party.core.presentation.HeightSpacer
import com.party.core.presentation.ImageLoading
import com.party.core.presentation.LARGE_CORNER_SIZE
import com.party.core.presentation.LoadingProgressBar
import com.party.core.presentation.MEDIUM_CORNER_SIZE
import com.party.core.presentation.Resources
import com.party.core.presentation.T3
import com.party.core.presentation.TextComponent
import com.party.core.presentation.WHITE
import com.party.domain.model.party.RecruitmentItem
import com.party.domain.model.party.RecruitmentList
import com.party.presentation.screens.home.HomeState
import com.party.presentation.screens.home.component.HomeListTitleArea
import com.party.presentation.screens.home.component.PartyCategory
import com.party.presentation.screens.home.component.PositionArea
import com.party.presentation.screens.home.component.RecruitmentCountArea
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NewRecruitmentArea(
    homeState: HomeState,
    onGoRecruitment: () -> Unit,
    onClick: (Int, Int) -> Unit,
) {
    HomeListTitleArea(
        title = stringResource(Resources.String.Home_List_New_Title),
        titleIcon = painterResource(Resources.Icon.Icon_Arrow_Right),
        description = stringResource(Resources.String.Home_List_New_Description),
        onReload = onGoRecruitment,
    )

    when {
        homeState.isLoadingRecruitmentList -> { LoadingProgressBar() }
        homeState.recruitmentList.partyRecruitments.isNotEmpty() -> {
            RecruitmentListArea(
                recruitmentListResponse = homeState.recruitmentList,
                onClick = onClick,
            )
        }
    }
}

@Composable
private fun RecruitmentListArea(
    recruitmentListResponse: RecruitmentList?,
    onClick: (Int, Int) -> Unit,
) {
    HeightSpacer(heightDp = 20.dp)

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(
            items = recruitmentListResponse?.partyRecruitments ?: emptyList(),
            key = { index, _ ->
                index
            }
        ) { _, item ->
            RecruitmentItem(
                recruitmentLisItemResponse = item,
                onClick = onClick,
            )
        }
    }
}


@Composable
private fun RecruitmentItem(
    recruitmentLisItemResponse: RecruitmentItem,
    onClick: (Int, Int) -> Unit,
) {
    Card(
        onClick = { onClick(recruitmentLisItemResponse.party.id, recruitmentLisItemResponse.id) },
        modifier = Modifier.wrapContentSize(),
        shape = RoundedCornerShape(LARGE_CORNER_SIZE),
        colors = CardDefaults.cardColors(
            containerColor = WHITE
        ),
        border = BorderStroke(1.dp, GRAY100),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier = Modifier
                .width(200.dp)
                .height(315.dp)
                .padding(12.dp),
        ) {
            RecruitmentItemTopArea(
                imageUrl = recruitmentLisItemResponse.party.image,
            )
            HeightSpacer(heightDp = 12.dp)
            RecruitmentItemBottomArea(
                category = recruitmentLisItemResponse.party.partyType.type,
                title = recruitmentLisItemResponse.party.title,
                main = recruitmentLisItemResponse.position.main,
                sub = recruitmentLisItemResponse.position.sub,
                recruitingCount = recruitmentLisItemResponse.recruitingCount,
                recruitedCount = recruitmentLisItemResponse.recruitedCount,
            )
        }
    }
}

@Composable
fun RecruitmentItemTopArea(
    imageUrl: String? = null,
) {
    ImageLoading(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        imageUrl = imageUrl,
        roundedCornerShape = MEDIUM_CORNER_SIZE
    )
}

@Composable
fun RecruitmentItemBottomArea(
    category: String,
    title: String,
    main: String,
    sub: String,
    recruitingCount: Int,
    recruitedCount: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(142.dp)
    ) {
        PartyCategory(category = category)
        HeightSpacer(heightDp = 4.dp)
        TextComponent(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            text = title,
            fontSize = T3,
            fontWeight = FontWeight.Bold,
            textAlign = Alignment.TopStart,
        )

        HeightSpacer(heightDp = 4.dp)

        PositionArea(
            modifier = Modifier
                .height(20.dp),
            main = main,
            sub = sub,
        )
        HeightSpacer(heightDp = 12.dp)
        RecruitmentCountArea(
            modifier = Modifier
                .height(20.dp),
            recruitingCount = recruitingCount,
            recruitedCount = recruitedCount,
        )
    }
}