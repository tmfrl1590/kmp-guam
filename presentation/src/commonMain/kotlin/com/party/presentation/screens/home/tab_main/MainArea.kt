package com.party.presentation.screens.home.tab_main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.core.presentation.HeightSpacer
import com.party.presentation.screens.home.HomeState

@Composable
fun MainArea(
    homeState: HomeState,
    onReload: () -> Unit,
    onGoRecruitment: () -> Unit,
    onGoParty: () -> Unit,
    onGotoRecruitmentDetail: (Int, Int) -> Unit,
    onGotoPartyDetail: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        BannerArea(
            homeState = homeState,
        )

        HeightSpacer(heightDp = 60.dp)

        NewRecruitmentArea(
            homeState = homeState,
            onGoRecruitment = onGoRecruitment,
            onClick = onGotoRecruitmentDetail
        )

        HeightSpacer(heightDp = 60.dp)

        PartyListArea(
            homeState = homeState,
            onGoParty = onGoParty,
            onClick = onGotoPartyDetail
        )

        HeightSpacer(heightDp = 60.dp)
    }
}