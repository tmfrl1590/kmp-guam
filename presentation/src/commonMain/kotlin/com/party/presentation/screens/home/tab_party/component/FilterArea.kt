package com.party.presentation.screens.home.tab_party.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.core.presentation.B2
import com.party.core.presentation.GRAY500
import com.party.core.presentation.PRIMARY
import com.party.core.presentation.Resources
import com.party.core.presentation.TextComponent
import com.party.core.presentation.WidthSpacer
import com.party.core.presentation.noRippleClickable
import com.party.presentation.screens.home.tab_recruitment.SelectFilterItem
import org.jetbrains.compose.resources.painterResource

@Composable
fun FilterArea(
    checked: Boolean,
    onToggle: (Boolean) -> Unit,
    isPartyTypeFilterClick: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        SelectFilterItem(
            filterName = "파티유형",
            isSheetOpen = false,
            onClick = { isPartyTypeFilterClick(it) }
        )

        IngToggle(
            checked = checked,
            onToggle = onToggle
        )
    }
}

@Composable
fun IngToggle(
    checked: Boolean,
    onToggle: (Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ){
        TextComponent(
            text = "진행중",
            textColor = if (checked) PRIMARY else GRAY500,
            fontSize = B2,
        )

        WidthSpacer(widthDp = 2.dp)
        Image(
            painter = if (checked) painterResource(Resources.Icon.Icon_Toggle_On) else painterResource(Resources.Icon.Icon_Toggle_Off),
            contentDescription = "toggle",
            modifier = Modifier.noRippleClickable {
                onToggle(!checked)
            }
        )
    }
}