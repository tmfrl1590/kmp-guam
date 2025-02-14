package com.party.data.network.user

import com.party.core.data.safeCall
import com.party.core.domain.DataError
import com.party.core.domain.Result
import com.party.data.dto.user.detail.PositionListDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class UserRemoteSourceImpl(
    private val httpClient: HttpClient
): UserRemoteSource{
    override suspend fun getPositions(
        main: String
    ): Result<List<PositionListDto>, DataError.Remote> {
        return safeCall<List<PositionListDto>> {
            httpClient.get(
                urlString = "https://partyguham.com/dev/api/positions"
            ){
                parameter("main", main)
            }
        }
    }
}