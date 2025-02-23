package com.party.core.presentation.no_data

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.party.core.presentation.B1
import com.party.core.presentation.GRAY500
import com.party.core.presentation.GRAY600
import com.party.core.presentation.Resources
import com.party.core.presentation.TextComponent
import com.party.core.presentation.WidthSpacer
import com.party.core.presentation.icon.DrawableIcon
import org.jetbrains.compose.resources.painterResource

@Composable
fun NoDataRow(
    modifier: Modifier = Modifier,
    text: String,
    spacer: Dp = 4.dp,
    iconColor: Color = GRAY500,
    textColor: Color = GRAY600
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ){
            DrawableIcon(
                icon = painterResource(Resources.Icon.Icon_Info),
                contentDescription = "info",
                modifier = Modifier
                    .size(16.dp),
                tintColor = iconColor
            )
            WidthSpacer(widthDp = spacer)
            TextComponent(
                text = text,
                fontSize = B1,
                textColor = textColor,
            )
        }
    }

}