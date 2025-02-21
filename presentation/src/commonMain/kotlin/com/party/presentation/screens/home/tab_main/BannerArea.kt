package com.party.presentation.screens.home.tab_main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.party.core.presentation.GRAY300
import com.party.core.presentation.HeightSpacer
import com.party.core.presentation.ImageLoading
import com.party.core.presentation.LoadingProgressBar
import com.party.core.presentation.PRIMARY
import com.party.domain.model.BannerItem
import com.party.presentation.screens.home.HomeState
import kotlinx.coroutines.delay

val contentPadding = 16.dp
val pageSpacing = 6.dp //  페이지 사이 간격

@Composable
fun BannerArea(
    homeState: HomeState,
) {
    when {
        homeState.isLoadingBanner -> LoadingProgressBar()
        homeState.banner.banner.isNotEmpty() -> {
            val pagerState = rememberPagerState(
                initialPage = 0,
                pageCount = { homeState.banner.banner.size }
            )

            LaunchedEffect(key1 = pagerState){
                autoScrollInfinity(pagerState)
            }

            HeightSpacer(heightDp = 20.dp)

            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = contentPadding),
                pageSpacing = pageSpacing,
            ) { page ->
                BannerItemImage(bannerItem = homeState.banner.banner[page])
            }

            HeightSpacer(heightDp = 12.dp)

            BannerIndicator(pagerState = pagerState)
        }
    }
}

@Composable
fun BannerItemImage(bannerItem: BannerItem) {
    ImageLoading(
        modifier = Modifier
            .fillMaxWidth()
            .height(175.dp),
        imageUrl = bannerItem.image,
    )
}

@Composable
fun BannerIndicator(
    pagerState: PagerState,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
    ) {
        repeat(pagerState.pageCount){ iteration ->
            val color = if (pagerState.currentPage == iteration) PRIMARY else GRAY300
            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(8.dp)
            )
        }
    }
}

private suspend fun autoScrollInfinity(pagerState: PagerState){
    while (true){
        delay(2000)
        val currentPage = pagerState.currentPage
        var nextPage = currentPage + 1
        if(nextPage >= pagerState.pageCount){
            nextPage = 0
        }
        pagerState.animateScrollToPage(nextPage)
    }
}