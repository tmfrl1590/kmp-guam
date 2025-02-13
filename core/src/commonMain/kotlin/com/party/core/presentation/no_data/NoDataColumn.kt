package com.party.core.presentation.no_data

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.core.presentation.B1
import com.party.core.presentation.GRAY500
import com.party.core.presentation.HeightSpacer
import com.party.core.presentation.Resources
import com.party.core.presentation.TextComponent
import com.party.core.presentation.icon.DrawableIcon
import org.jetbrains.compose.resources.painterResource

@Composable
fun NoDataColumn(
    modifier: Modifier = Modifier,
    title: String,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DrawableIcon(
                icon = painterResource(Resources.Icon.Icon_Info),
                contentDescription = "info",
                modifier = Modifier
                    .size(24.dp),
                tintColor = GRAY500
            )
            HeightSpacer(heightDp = 6.dp)
            TextComponent(
                text = title,
                fontSize = B1,
                fontWeight = FontWeight.SemiBold,
                textColor = GRAY500,
            )
        }
    }
}