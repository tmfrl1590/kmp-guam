package com.kmp.core.presentation.chip

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.kmp.core.presentation.B3
import com.kmp.core.presentation.LARGE_CORNER_SIZE
import com.kmp.core.presentation.TYPE_COLOR_BACKGROUND
import com.kmp.core.presentation.TYPE_COLOR_TEXT

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    containerColor: Color = TYPE_COLOR_BACKGROUND,
    contentColor: Color = TYPE_COLOR_TEXT,
    roundedCornerShape: Dp = LARGE_CORNER_SIZE,
    text: String,
    fontSize: TextUnit = B3,
    fontWeight: FontWeight = FontWeight.SemiBold,
) {
    Card(
        modifier = modifier
            .wrapContentWidth()
            .height(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(roundedCornerShape),
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight()
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = text,
                fontSize = fontSize,
                fontWeight = fontWeight,
            )
        }
    }
}