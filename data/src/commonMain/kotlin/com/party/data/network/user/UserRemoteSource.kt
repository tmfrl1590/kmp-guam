package com.party.data.network.user

import com.party.core.domain.DataError
import com.party.core.domain.Result
import com.party.data.dto.user.detail.PositionListDto

interface UserRemoteSource {

    // 특정 직군의 포지션 리스트 조회
    suspend fun getPositions(
        main: String,
    ): Result<List<PositionListDto>, DataError.Remote>
}