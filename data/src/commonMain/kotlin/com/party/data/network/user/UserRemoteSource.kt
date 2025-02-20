package com.party.data.network.user

import com.party.core.domain.DataError
import com.party.core.domain.DataErrorRemote
import com.party.core.domain.Result
import com.party.data.dto.user.detail.PositionListDto
import com.party.data.dto.user.login.SocialLoginDto
import com.party.data.dto.user.login.SocialLoginErrorDto
import com.party.data.dto.user.login.SocialLoginSuccessDto
import com.party.domain.model.user.login.AccessTokenRequest
import com.party.domain.model.user.login.SocialLoginError
import com.party.domain.model.user.login.SocialLoginSuccess

interface UserRemoteSource {

    // 구글 로그인
    suspend fun googleLogin(accessTokenRequest: AccessTokenRequest): Result<SocialLoginSuccessDto, DataErrorRemote<SocialLoginErrorDto>>

    // 특정 직군의 포지션 리스트 조회
    suspend fun getPositions(
        main: String,
    ): Result<List<PositionListDto>, DataError>
}