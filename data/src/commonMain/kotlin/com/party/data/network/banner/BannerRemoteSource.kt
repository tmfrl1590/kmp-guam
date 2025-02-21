package com.party.data.network.banner

import com.party.core.domain.DataError
import com.party.core.domain.Result
import com.party.data.dto.BannerDto

interface BannerRemoteSource {

    // 홈 - 배너 리스트 조회
    suspend fun getBannerList(): Result<BannerDto, DataError>
}