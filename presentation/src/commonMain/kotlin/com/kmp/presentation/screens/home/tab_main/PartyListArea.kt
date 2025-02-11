package com.kmp.presentation.screens.home.tab_main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kmp.core.presentation.HeightSpacer
import com.kmp.core.presentation.LoadingProgressBar
import com.kmp.core.presentation.PartyListItem1
import com.kmp.core.presentation.Resources
import com.kmp.domain.model.party.PartyList
import com.kmp.presentation.screens.home.HomeState
import com.kmp.presentation.screens.home.component.HomeListTitleArea
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PartyListArea(
    homeState: HomeState,
    onGoParty: () -> Unit,
    onClick: (Int) -> Unit,
) {
    HomeListTitleArea(
        title = stringResource(Resources.String.Home_List_Party_Title),
        titleIcon = painterResource(Resources.Icon.Icon_Arrow_Right),
        description = stringResource(Resources.String.Home_List_Party_Description),
        onReload = onGoParty,
    )

    when {
        homeState.isLoadingPartyList -> LoadingProgressBar()
        homeState.partyList.parties.isNotEmpty() -> {
            PartyListArea(
                partyListResponse = homeState.partyList,
                onClick = onClick,
            )
        }
    }
}

@Composable
private fun PartyListArea(
    partyListResponse: PartyList?,
    onClick: (Int) -> Unit,
) {
    HeightSpacer(heightDp = 20.dp)

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(
            items = partyListResponse?.parties ?: emptyList(),
            key = { index, _ ->
                index
            }
        ) { _, item ->
            PartyListItem1(
                imageUrl = item.image,
                type = item.partyType.type,
                title = item.title,
                recruitmentCount = item.recruitmentCount,
                onClick = {onClick(item.id)}
            )
        }
    }
}