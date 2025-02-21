package com.party.domain.repository

import com.party.core.domain.DataErrorRemote
import com.party.core.domain.Result
import com.party.domain.model.Banner

interface BannerRepository {

    // 홈 - 배너 리스트 조회
    suspend fun getBannerList(): Result<Banner, DataErrorRemote<Unit>>
}