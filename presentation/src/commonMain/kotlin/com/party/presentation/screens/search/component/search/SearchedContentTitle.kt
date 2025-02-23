package com.party.presentation.screens.search.component.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.core.presentation.GRAY500
import com.party.core.presentation.Resources
import com.party.core.presentation.T2
import com.party.core.presentation.TextComponent
import com.party.core.presentation.icon.DrawableIconButton
import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchedContentTitle(
    title: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(25.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextComponent(
            text = title,
            fontSize = T2,
            fontWeight = FontWeight.SemiBold
        )

        DrawableIconButton(
            icon = painterResource(Resources.Icon.Icon_Arrow_Right),
            contentDescription = "더보기",
            onClick = onClick,
            iconColor = GRAY500,
            iconSize = 24.dp
        )
    }
}