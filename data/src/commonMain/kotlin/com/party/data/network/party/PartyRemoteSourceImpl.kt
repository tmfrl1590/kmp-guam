package com.party.data.network.party

import com.party.core.Constants.serverUrl
import com.party.core.data.safeCall
import com.party.core.domain.DataError
import com.party.core.domain.DataErrorRemote
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
        partyTypes: List<Int>,
        position: List<Int>,
    ): Result<RecruitmentListDto, DataErrorRemote<Unit>> {
        return safeCall<RecruitmentListDto, Unit> {
            httpClient.get(
                urlString = serverUrl("/parties/recruitments")
            ){
                parameter("page", page)
                parameter("limit", limit)
                parameter("sort", sort)
                parameter("order", order)
                if (partyTypes.isNotEmpty()) {
                    partyTypes.forEach { parameter("partyType", it) }
                }
                titleSearch?.let { parameter("titleSearch", it) }
                if(position.isNotEmpty()){
                    position.forEach { parameter("position", it) }
                }
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
    ): Result<PartyListDto, DataErrorRemote<Unit>> {
        return safeCall<PartyListDto, Unit> {
            httpClient.get(
                urlString = serverUrl("/parties")
            ){
                parameter("page", page)
                parameter("limit", limit)
                parameter("sort", sort)
                parameter("order", order)
                if (partyTypes.isNotEmpty()) {
                    partyTypes.forEach { parameter("partyType", it) }
                }
                titleSearch?.let { parameter("titleSearch", it) }
                status?.let { parameter("status", it) }
            }
        }
    }
}