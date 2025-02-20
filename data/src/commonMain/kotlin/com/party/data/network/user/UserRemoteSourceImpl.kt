package com.party.data.network.user

import com.party.core.Constants.serverUrl
import com.party.core.data.safeCall
import com.party.core.domain.DataError
import com.party.core.domain.DataErrorRemote
import com.party.core.domain.Result
import com.party.data.dto.user.detail.PositionListDto
import com.party.data.dto.user.login.SocialLoginDto
import com.party.data.dto.user.login.SocialLoginErrorDto
import com.party.data.dto.user.login.SocialLoginSuccessDto
import com.party.domain.model.user.login.AccessTokenRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class UserRemoteSourceImpl(
    private val httpClient: HttpClient
): UserRemoteSource{
    override suspend fun googleLogin(accessTokenRequest: AccessTokenRequest): Result<SocialLoginSuccessDto, DataErrorRemote<SocialLoginErrorDto>> {
        return safeCall<SocialLoginSuccessDto, SocialLoginErrorDto> {
            httpClient.post(
                urlString = serverUrl("/users/google/app/login")
            ){
                setBody(accessTokenRequest)
            }
        }
    }

    override suspend fun getPositions(
        main: String
    ): Result<List<PositionListDto>, DataErrorRemote<Unit>> {
        return safeCall<List<PositionListDto>, Unit> {
            httpClient.get(
                urlString = serverUrl("/positions")
            ){
                parameter("main", main)
            }
        }
    }
}