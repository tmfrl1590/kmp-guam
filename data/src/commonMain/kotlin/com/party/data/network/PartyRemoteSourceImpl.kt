package com.party.data.network

import com.party.core.data.safeCall
import com.party.core.domain.DataError
import com.party.core.domain.Result
import com.party.data.dto.party.PartyListDto
import com.party.data.dto.party.RecruitmentListDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PartyRemoteSourceImpl(
    private val httpClient: HttpClient
): PartyRemoteSource {
    override suspend fun getRecruitmentList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        titleSearch: String?,
        partyTypes: List<Int>?,
        position: List<Int>?
    ): Result<RecruitmentListDto, DataError.Remote> {
        return safeCall<RecruitmentListDto> {
            httpClient.get(
                urlString = "https://partyguham.com/dev/api/parties/recruitments"
            ){
                parameter("page", page)
                parameter("limit", limit)
                parameter("sort", sort)
                parameter("order", order)
                titleSearch?.let { parameter("titleSearch", it) }
                partyTypes?.let { parameter("partyTypes", it)  }
                position?.let { parameter("position", position) }
            }
        }
    }

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
                if (partyTypes.isNotEmpty()) {
                    partyTypes.forEach { parameter("partyType", it) }
                }


                titleSearch?.let { parameter("titleSearch", it) }
                status?.let { parameter("status", it) }
            }
        }
    }
}