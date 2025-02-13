package com.party.presentation.screens.home.component

import androidx.compose.runtime.Composable
import com.party.core.presentation.TabArea

@Composable
fun HomeTopTabArea(
    homeTopTabList: List<String>,
    selectedTabText: String,
    onTabClick: (String) -> Unit,
) {
    TabArea(
        tabList = homeTopTabList,
        selectedTabText = selectedTabText,
        onTabClick = onTabClick
    )
}