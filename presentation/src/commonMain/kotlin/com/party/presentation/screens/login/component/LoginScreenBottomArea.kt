package com.party.presentation.screens.login.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.party.core.presentation.AnnotatedTextComponent
import com.party.core.presentation.B2
import com.party.core.presentation.GRAY500
import com.party.core.presentation.GRAY600
import com.party.core.presentation.HeightSpacer
import com.party.core.presentation.Resources
import com.party.core.presentation.TextComponent
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreenBottomArea(
) {
    AnnotatedTextComponent(
        annotatedString = makeAnnotatedString(
            text1 = stringResource(Resources.String.Login5),
            text2 = stringResource(Resources.String.Login6),
            text3 = stringResource(Resources.String.Login7),
        ),
        textColor = GRAY600,
        fontSize = B2,
    )

    HeightSpacer(heightDp = 40.dp)

    TextComponent(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(Resources.String.Login8),
        textColor = GRAY500,
        textAlign = Alignment.Center,
        fontSize = B2,
        textDecoration = TextDecoration.Underline,
    )
}

fun makeAnnotatedString(
    text1: String,
    text2: String,
    text3: String,
): AnnotatedString {
    return buildAnnotatedString {
        append(text1)
        withStyle(
            SpanStyle(
                color = GRAY600,
                textDecoration = TextDecoration.Underline,
            )
        ){
            append(text2)
        }
        append(text3)
    }
}