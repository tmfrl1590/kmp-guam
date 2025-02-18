package com.party.data.repository.user

import com.party.core.domain.DataError
import com.party.core.domain.Result
import com.party.core.domain.map
import com.party.data.network.user.UserRemoteSource
import com.party.domain.model.user.detail.PositionList
import com.party.domain.model.user.login.AccessTokenRequest
import com.party.domain.model.user.login.SocialLogin
import com.party.domain.model.user.login.SocialLoginSuccess
import com.party.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userRemoteSource: UserRemoteSource
): UserRepository {
    override suspend fun googleLogin(accessTokenRequest: AccessTokenRequest): Result<SocialLoginSuccess, DataError.Remote> {
        return userRemoteSource.googleLogin(accessTokenRequest = accessTokenRequest).map { socialLoginDto ->
            SocialLoginSuccess(
                accessToken = socialLoginDto.accessToken,
                refreshToken = socialLoginDto.refreshToken
            )
        }
    }

    override suspend fun getPositions(
        main: String
    ): Result<List<PositionList>, DataError.Remote> {
        return userRemoteSource.getPositions(main).map { positionListDtoList ->
            positionListDtoList.map { positionListDto ->
                PositionList(
                    id = positionListDto.id,
                    main = positionListDto.main,
                    sub = positionListDto.sub
                )
            }
        }
    }
}