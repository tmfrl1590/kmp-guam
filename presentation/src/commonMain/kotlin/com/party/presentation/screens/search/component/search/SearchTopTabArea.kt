package com.party.presentation.screens.search.component.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.party.core.presentation.BLACK
import com.party.core.presentation.TabArea

@Composable
fun SearchTopTabArea(
    modifier: Modifier,
    searchTabList: List<String>,
    selectedTabText: String,
    onTabClick: (String) -> Unit,
) {
    TabArea(
        modifier = modifier,
        tabList = searchTabList,
        selectedTabText = selectedTabText,
        onTabClick = onTabClick,
        selectedTabColor = BLACK,
    )
}