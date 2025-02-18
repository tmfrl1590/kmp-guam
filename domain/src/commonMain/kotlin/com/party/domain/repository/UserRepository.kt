package com.party.domain.repository

import com.party.core.domain.DataError
import com.party.core.domain.Result
import com.party.domain.model.user.detail.PositionList
import com.party.domain.model.user.login.AccessTokenRequest
import com.party.domain.model.user.login.SocialLogin
import com.party.domain.model.user.login.SocialLoginSuccess

interface UserRepository {

    // 구글 로그인
    suspend fun googleLogin(accessTokenRequest: AccessTokenRequest): Result<SocialLoginSuccess, DataError.Remote>

    // 특정 직군의 포지션 리스트 조회
    suspend fun getPositions(
        main: String,
    ): Result<List<PositionList>, DataError.Remote>
}