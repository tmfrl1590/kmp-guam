package com.party.data.repository

import com.party.core.domain.DataErrorRemote
import com.party.core.domain.Result
import com.party.core.domain.map
import com.party.core.domain.mapError
import com.party.data.network.user.UserRemoteSource
import com.party.domain.model.user.detail.PositionList
import com.party.domain.model.user.login.AccessTokenRequest
import com.party.domain.model.user.login.SocialLoginError
import com.party.domain.model.user.login.SocialLoginSuccess
import com.party.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userRemoteSource: UserRemoteSource
): UserRepository {
    override suspend fun googleLogin(
        accessTokenRequest: AccessTokenRequest
    ): Result<SocialLoginSuccess, DataErrorRemote<SocialLoginError>> {
        return userRemoteSource.googleLogin(accessTokenRequest)
            .map { socialLoginDto ->  //
                SocialLoginSuccess(
                    accessToken = socialLoginDto.accessToken,
                    refreshToken = socialLoginDto.refreshToken
                )
            }
            .mapError { socialLoginErrorDto ->  //
                DataErrorRemote.BadRequest(
                    response = SocialLoginError(
                        message = socialLoginErrorDto.response?.message ?: "Unknown error",
                        signupAccessToken = socialLoginErrorDto.response?.signupAccessToken ?: "",
                        userEmail = ""
                    )
                )
            }
    }


    override suspend fun getPositions(
        main: String
    ): Result<List<PositionList>, DataErrorRemote<Unit>> {
        return userRemoteSource.getPositions(main)
            .map { positionListDtoList ->
                positionListDtoList.map { positionListDto ->
                    PositionList(
                        id = positionListDto.id,
                        main = positionListDto.main,
                        sub = positionListDto.sub
                    )
                }
            }
            .mapError { error ->
                DataErrorRemote.BadRequest(response = Unit) // 실패 시 Unit을 반환
            }
    }
}