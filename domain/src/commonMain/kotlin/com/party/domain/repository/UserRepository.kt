package com.party.domain.repository

import com.party.core.domain.DataError
import com.party.core.domain.Result
import com.party.domain.model.user.detail.PositionList

interface UserRepository {

    // 특정 직군의 포지션 리스트 조회
    suspend fun getPositions(
        main: String,
    ): Result<List<PositionList>, DataError.Remote>
}