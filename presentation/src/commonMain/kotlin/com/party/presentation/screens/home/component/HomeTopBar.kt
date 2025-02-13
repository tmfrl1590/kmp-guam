package com.party.presentation.screens.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.party.core.presentation.BLACK
import com.party.core.presentation.LOGO_COLOR_END
import com.party.core.presentation.LOGO_COLOR_START
import com.party.core.presentation.Resources
import com.party.core.presentation.WidthSpacer
import com.party.core.presentation.icon.DrawableIconButton
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeTopBar(
    onGoToSearch: () -> Unit,
    onGoToAlarm: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        LogoText()
        HomeTopBarIconArea(
            onGoToSearch = onGoToSearch,
            onGoToAlarm = onGoToAlarm
        )
    }
}

@Composable
fun LogoText() {
    Text(
        text = stringResource(Resources.String.Home1),
        fontSize = 24.sp,
        style = TextStyle(
            brush = Brush.linearGradient(
                colors = listOf(LOGO_COLOR_START, LOGO_COLOR_END),
            )
        )
    )
}

@Composable
fun HomeTopBarIconArea(
    onGoToSearch: () -> Unit,
    onGoToAlarm: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DrawableIconButton(
            modifier = Modifier.size(24.dp),
            icon = painterResource(Resources.Icon.Icon_Search),
            contentDescription = "search",
            onClick = onGoToSearch,
            iconColor = BLACK
        )
        WidthSpacer(widthDp = 12.dp)
        DrawableIconButton(
            modifier = Modifier.size(24.dp),
            icon = painterResource(Resources.Icon.Icon_Alarm),
            contentDescription = "alaram",
            onClick = onGoToAlarm,
            iconColor = BLACK
        )
    }
}