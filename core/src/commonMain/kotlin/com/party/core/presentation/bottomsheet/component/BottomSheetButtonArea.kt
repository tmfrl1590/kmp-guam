package com.party.core.presentation.bottomsheet.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.core.presentation.B2
import com.party.core.presentation.BLACK
import com.party.core.presentation.LARGE_CORNER_SIZE
import com.party.core.presentation.PRIMARY
import com.party.core.presentation.WHITE
import com.party.core.presentation.WidthSpacer

@Composable
fun BottomSheetButtonArea(
    onReset: () -> Unit,
    onApply: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(vertical = 12.dp, horizontal = 20.dp)
    ) {
        ResetButton(
            modifier = Modifier
                .weight(1f),
            onClick = onReset,
        )
        WidthSpacer(widthDp = 8.dp)
        ApplyButton(
            modifier = Modifier
                .weight(2f),
            onClick = onApply,
        )
    }
}

@Composable
fun ResetButton(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .height(56.dp),
        shape = RoundedCornerShape(LARGE_CORNER_SIZE),
        border = BorderStroke(1.dp, PRIMARY),
        colors = ButtonDefaults.buttonColors(
            containerColor = WHITE,
        ),
    ) {
        Text(
            text = "초기화",
            fontWeight = FontWeight.Bold,
            fontSize = B2,
            color = BLACK,
        )
    }
}

@Composable
fun ApplyButton(
    buttonText: String = "적용하기",
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .height(56.dp),
        shape = RoundedCornerShape(LARGE_CORNER_SIZE),
        border = BorderStroke(1.dp, PRIMARY),
        colors = ButtonDefaults.buttonColors(
            containerColor = PRIMARY,
        ),
    ) {
        Text(
            text = buttonText,
            fontWeight = FontWeight.Bold,
            fontSize = B2,
            color = BLACK,
        )
    }
}