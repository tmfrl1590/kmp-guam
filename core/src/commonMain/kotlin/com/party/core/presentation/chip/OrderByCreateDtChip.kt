package com.party.core.presentation.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.core.presentation.B2
import com.party.core.presentation.BLACK
import com.party.core.presentation.noRippleClickable

@Composable
fun OrderByCreateDtChip(
    text: String,
    orderByDesc: Boolean,
    onChangeSelected: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .noRippleClickable {
                onChangeSelected(!orderByDesc)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {
        Text(
            text = text,
            color = BLACK,
            fontSize = B2
        )
        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = if(orderByDesc) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
            contentDescription = "Arrow",
        )
    }
}