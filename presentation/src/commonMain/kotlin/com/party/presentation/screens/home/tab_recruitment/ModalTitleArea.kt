package com.party.presentation.screens.home.tab_recruitment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.core.presentation.B2
import com.party.core.presentation.BLACK
import com.party.core.presentation.LARGE_CORNER_SIZE
import com.party.core.presentation.T2
import com.party.core.presentation.TextComponent
import com.party.core.presentation.WidthSpacer

@Composable
fun ModalTitleArea(
    titleText: String,
    onModelClose: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        TextComponent(
            modifier = Modifier.align(Alignment.Center),
            text = titleText,
            fontSize = T2,
            fontWeight = FontWeight.SemiBold,
            textColor = BLACK,
            textAlign = Alignment.Center,
        )

        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "close",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
                .size(24.dp)
                .clickable { onModelClose() }
        )
    }
}

@Composable
fun ModalBottomArea(
    modifier: Modifier = Modifier,
    buttonText1: String,
    buttonTextColor1: Color,
    buttonContainerColor1: Color,
    buttonBorderColor1: Color,
    buttonText2: String,
    buttonTextColor2: Color,
    buttonContainerColor2: Color,
    buttonBorderColor2: Color,
    onReset: () -> Unit,
    onApply: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(top = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ModalBottomAreaItem(
            modifier = Modifier.weight(1f),
            buttonText = buttonText1,
            buttonTextColor = buttonTextColor1,
            buttonContainerColor = buttonContainerColor1,
            buttonBorderColor = buttonBorderColor1,
            onClick = onReset,
        )
        WidthSpacer(widthDp = 8.dp)
        ModalBottomAreaItem(
            modifier = Modifier.weight(2f),
            buttonText = buttonText2,
            buttonTextColor = buttonTextColor2,
            buttonContainerColor = buttonContainerColor2,
            buttonBorderColor = buttonBorderColor2,
            onClick = onApply,
        )
    }
}

@Composable
fun ModalBottomAreaItem(
    modifier: Modifier,
    buttonText: String,
    buttonTextColor: Color = BLACK,
    buttonContainerColor: Color,
    buttonBorderColor: Color,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .height(56.dp),
        shape = RoundedCornerShape(LARGE_CORNER_SIZE),
        border = BorderStroke(1.dp, buttonBorderColor),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonContainerColor,
        ),
    ) {
        Text(
            text = buttonText,
            fontWeight = FontWeight.Bold,
            fontSize = B2,
            color = buttonTextColor,
        )
    }
}