package com.party.data.network.banner

import com.party.core.Constants.serverUrl
import com.party.core.data.safeCall
import com.party.core.domain.DataError
import com.party.core.domain.Result
import com.party.data.dto.BannerDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class BannerRemoteSourceImpl(
    private val httpClient: HttpClient
): BannerRemoteSource{
    override suspend fun getBannerList(): Result<BannerDto, DataError> {
        return safeCall<BannerDto, Unit> {
            httpClient.get(
                urlString = serverUrl("/banner/app")
            )
        }
    }
}