package com.party.core.presentation.party_filter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.core.presentation.B2
import com.party.core.presentation.GRAY200
import com.party.core.presentation.GRAY400
import com.party.core.presentation.GRAY500
import com.party.core.presentation.LARGE_CORNER_SIZE
import com.party.core.presentation.PRIMARY
import com.party.core.presentation.Resources
import com.party.core.presentation.WHITE
import com.party.core.presentation.WidthSpacer
import com.party.core.presentation.chip.OrderByCreateDtChip
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PositionAndPartyTypeAndOrderByArea(
    selectedPartyTypeCount: Int,
    isDescRecruitment: Boolean,
    onPositionSheetClick: () -> Unit,
    onPartyTypeFilterClick: () -> Unit,
    onChangeOrderBy: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SelectFilterItem(
                filterName = "직무",
                onClick = onPositionSheetClick,
                number = 0
            )
            WidthSpacer(widthDp = 8.dp)
            SelectFilterItem(
                filterName = "파티유형",
                onClick = onPartyTypeFilterClick,
                number = selectedPartyTypeCount,
            )
        }

        OrderByCreateDtChip(
            text = stringResource(Resources.String.Filter1),
            orderByDesc = isDescRecruitment,
            onChangeSelected = { onChangeOrderBy(it) }
        )
    }
}

@Composable
private fun SelectFilterItem(
    filterName: String,
    number: Int = 0,
    onClick: () -> Unit,
) {
    Card(
        onClick = { onClick() },
        modifier = Modifier
            .wrapContentWidth()
            .height(36.dp),
        colors = CardDefaults.cardColors(
            containerColor = WHITE,
        ),
        shape = RoundedCornerShape(LARGE_CORNER_SIZE),
        border = BorderStroke(1.dp, GRAY200),
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = filterName,
                color = GRAY500,
                fontSize = B2
            )
            if(number > 0){
                Text(
                    text = number.toString(),
                    color = PRIMARY,
                    fontSize = B2,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.offset(x = (2).dp, y = (1).dp),
                )
            }
            WidthSpacer(widthDp = 2.dp)
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(Resources.Icon.Icon_Arrow_Down),
                contentDescription = "Arrow Down",
                tint = GRAY400,
            )
        }
    }
}