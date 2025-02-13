package com.party.presentation.screens.home.tab_main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.core.presentation.HeightSpacer
import com.party.core.presentation.LoadingProgressBar
import com.party.core.presentation.PartyListItem1
import com.party.core.presentation.Resources
import com.party.domain.model.party.PartyList
import com.party.presentation.screens.home.HomeState
import com.party.presentation.screens.home.component.HomeListTitleArea
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