package com.party.core.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.party.core.presentation.chip.Chip
import org.jetbrains.compose.resources.stringResource

@Composable
fun RecruitmentListItem2(
    id: Int,
    partyId: Int,
    imageUrl: String? = null,
    category: String,
    title: String,
    main: String,
    sub: String,
    recruitingCount: Int,
    recruitedCount: Int,
    onClick: (Int, Int) -> Unit,
) {
    Card(
        onClick = { onClick(id, partyId) },
        modifier = Modifier
            .fillMaxWidth()
            .height(136.dp),
        shape = RoundedCornerShape(LARGE_CORNER_SIZE),
        border = BorderStroke(1.dp, GRAY100),
        colors = CardDefaults.cardColors(
            containerColor = WHITE,
        ),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            RecruitmentCategory(category = category)
            HeightSpacer(heightDp = 8.dp)
            RecruitmentInfoArea(
                imageUrl = imageUrl,
                title = title,
                main = main,
                sub = sub,
                recruitingCount = recruitingCount,
                recruitedCount = recruitedCount,
            )
        }
    }
}

@Composable
private fun RecruitmentCategory(
    category: String,
) {
    Chip(
        containerColor = TYPE_COLOR_BACKGROUND,
        contentColor = TYPE_COLOR_TEXT,
        text = category,
    )
}

@Composable
private fun RecruitmentInfoArea(
    imageUrl: String?,
    title: String,
    main: String,
    sub: String,
    recruitingCount: Int,
    recruitedCount: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RecruitmentImage(
            imageUrl = imageUrl,
        )
        WidthSpacer(widthDp = 12.dp)
        RecruitmentContent(
            title = title,
            main = main,
            sub = sub,
            recruitingCount = recruitingCount,
            recruitedCount = recruitedCount,
        )
    }
}

@Composable
private fun RecruitmentImage(
    imageUrl: String?,
) {
    ImageLoading(
        modifier = Modifier
            .width(96.dp)
            .height(72.dp),
        imageUrl = imageUrl,
        roundedCornerShape = LARGE_CORNER_SIZE
    )
}

@Composable
private fun RecruitmentContent(
    title: String,
    main: String,
    sub: String,
    recruitingCount: Int,
    recruitedCount: Int,
) {
    Column(
        modifier = Modifier
            .width(195.dp)
            .height(71.dp),
        verticalArrangement = Arrangement.Center
    ) {
        RecruitmentTitle(title = title)
        HeightSpacer(heightDp = 5.dp)
        RecruitmentPositionArea(
            modifier = Modifier
                .height(20.dp),
            main = main,
            sub = sub,
        )
        HeightSpacer(heightDp = 5.dp)
        RecruitmentCountArea(
            modifier = Modifier,
            recruitingCount = recruitingCount,
            recruitedCount = recruitedCount,
        )
    }
}

@Composable
private fun RecruitmentTitle(
    title: String
) {
    TextComponent(
        modifier = Modifier
            .fillMaxWidth()
            .height(22.dp),
        text = title,
        fontSize = T3,
        fontWeight = FontWeight.SemiBold,
        textColor = BLACK,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun RecruitmentPositionArea(
    modifier: Modifier,
    main: String,
    sub: String,
    textColor: Color = BLACK,
) {
    TextComponent(
        modifier = modifier
            .fillMaxWidth(),
        text = "$main | $sub",
        fontSize = B2,
        textColor = textColor,
    )
}

@Composable
private fun RecruitmentCountArea(
    modifier: Modifier,
    recruitingCount: Int,
    recruitedCount: Int,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement,
    ) {
        TextComponent(
            text = stringResource(Resources.String.Home_Common),
            fontSize = B3,
        )

        WidthSpacer(widthDp = 4.dp)

        TextComponent(
            text = "$recruitedCount / $recruitingCount",
            fontSize = B3,
            textColor = RED,
        )
    }
}