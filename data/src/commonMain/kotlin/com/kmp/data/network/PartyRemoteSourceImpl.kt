package com.kmp.data.network

import com.kmp.core.data.safeCall
import com.kmp.core.domain.DataError
import com.kmp.core.domain.Result
import com.kmp.data.dto.party.PartyListDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PartyRemoteSourceImpl(
    private val httpClient: HttpClient
): PartyRemoteSource {
    override suspend fun getPartyList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        partyTypes: List<Int>,
        titleSearch: String?,
        status: String?,
    ): Result<PartyListDto, DataError.Remote> {
        return safeCall<PartyListDto> {
            httpClient.get(
                urlString = "https://partyguham.com/dev/api/parties"
            ){
                parameter("page", page)
                parameter("limit", limit)
                parameter("sort", sort)
                parameter("order", order)
                //parameter("partyTypes", partyTypes)
                titleSearch?.let { parameter("titleSearch", it) }
                status?.let { parameter("status", it) }
            }
        }
    }
}