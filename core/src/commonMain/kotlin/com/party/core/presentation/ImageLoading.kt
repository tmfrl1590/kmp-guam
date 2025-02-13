package com.party.core.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import kmp_guam.core.generated.resources.Res
import kmp_guam.core.generated.resources.default_image
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageLoading(
    modifier: Modifier,
    imageUrl: String? = null,
    borderColor: Color = GRAY100,
    borderWidth: Dp = 1.dp,
    roundedCornerShape: Dp = LARGE_CORNER_SIZE,
) {
    Card(
        modifier = modifier,
        border = BorderStroke(borderWidth, borderColor),
        shape = RoundedCornerShape(roundedCornerShape),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            NetworkImageLoad(
                modifier = Modifier
                    .fillMaxSize(),
                url = imageUrl,
            )
        }
    }
}

@Composable
fun NetworkImageLoad(
    modifier: Modifier = Modifier,
    url: String? = null,
) {
    CoilImage(
        modifier = modifier,
        imageModel = { url },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        ),
        failure = {
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(Res.drawable.default_image),
                contentDescription = null
            )
        },
    )
}