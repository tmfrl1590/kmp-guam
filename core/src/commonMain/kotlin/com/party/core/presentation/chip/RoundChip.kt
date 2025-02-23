package com.party.core.presentation.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.party.core.presentation.B2
import com.party.core.presentation.BLACK
import com.party.core.presentation.GRAY100
import com.party.core.presentation.GRAY500
import com.party.core.presentation.LARGE_CORNER_SIZE
import com.party.core.presentation.Resources
import com.party.core.presentation.TextComponent
import com.party.core.presentation.WidthSpacer
import com.party.core.presentation.noRippleClickable
import org.jetbrains.compose.resources.painterResource

@Composable
fun RoundChip(
    text: String,
    spacer: Dp = 4.dp,
    painter: Painter = painterResource(Resources.Icon.Icon_Close2),
    iconSize: Dp = 16.dp,
    onIconClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(LARGE_CORNER_SIZE),
        modifier = Modifier
            .wrapContentWidth()
            .height(36.dp),
        colors = CardDefaults.cardColors(
            containerColor = GRAY100
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 12.dp, vertical = 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            TextComponent(
                text = text,
                fontSize = B2,
                textColor = BLACK,
            )
            WidthSpacer(widthDp = spacer)
            Icon(
                painter = painter,
                contentDescription = "",
                tint = GRAY500,
                modifier = Modifier
                    .size(iconSize)
                    .noRippleClickable { onIconClick() }
            )
        }
    }
}