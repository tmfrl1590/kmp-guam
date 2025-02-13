package com.party.presentation.screens.home.tab_party.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.party.core.presentation.Resources
import com.party.core.presentation.chip.OrderByCreateDtChip
import org.jetbrains.compose.resources.stringResource

@Composable
fun FilterDate(
    selectedCreateDataOrderByDesc: Boolean,
    onChangeOrderByPartyArea: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        OrderByCreateDtChip(
            text = stringResource(Resources.String.Filter1),
            orderByDesc = selectedCreateDataOrderByDesc,
            onChangeSelected = { onChangeOrderByPartyArea(it) }
        )
    }
}