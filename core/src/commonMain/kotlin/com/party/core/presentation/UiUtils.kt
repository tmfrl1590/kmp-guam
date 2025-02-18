package com.party.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun TextComponent(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.Black,
    textAlign: Alignment = Alignment.CenterStart,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit,
    textDecoration: TextDecoration? = null,
    overflow: TextOverflow = TextOverflow.Visible,
    maxLines: Int = Int.MAX_VALUE,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier.noRippleClickable { onClick() },
        contentAlignment = textAlign,
    ){
        Text(
            text = text,
            color = textColor,
            fontWeight = fontWeight,
            fontSize = fontSize,
            textDecoration = textDecoration,
            overflow = overflow,
            maxLines = maxLines
        )
    }
}

@Composable
fun WidthSpacer(
    widthDp: Dp,
) {
    Spacer(modifier = Modifier.width(widthDp))
}

@Composable
fun HeightSpacer(
    heightDp: Dp,
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightDp)
    )
}

@Composable
fun LoadingProgressBar() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Gray,
        )
    }
}

@Composable
fun ScreenExplainArea(
    mainExplain: String,
    subExplain: String,
) {
    HeightSpacer(heightDp = 32.dp)

    TextComponent(
        text = mainExplain,
        fontWeight = FontWeight.Bold,
        fontSize = T2,
    )

    HeightSpacer(heightDp = 12.dp)

    TextComponent(
        text = subExplain,
        fontSize = T3,
    )
}

@Composable
fun AnnotatedTextComponent(
    modifier: Modifier = Modifier,
    annotatedString: AnnotatedString,
    textColor: Color = Color.Black,
    textAlign: Alignment = Alignment.CenterStart,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit,
    textDecoration: TextDecoration? = null,
) {
    Box(
        modifier = modifier,
        contentAlignment = textAlign,
    ){
        Text(
            text = annotatedString,
            color = textColor,
            fontWeight = fontWeight,
            fontSize = fontSize,
            textDecoration = textDecoration,
        )
    }
}