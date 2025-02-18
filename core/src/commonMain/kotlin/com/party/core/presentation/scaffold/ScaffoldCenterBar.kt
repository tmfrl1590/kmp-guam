package com.party.core.presentation.scaffold

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.party.core.presentation.WHITE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldCenterBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
    actionIcons: @Composable () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = WHITE
        ),
        title = title,
        navigationIcon = navigationIcon,
        actions = { actionIcons() },
    )
}