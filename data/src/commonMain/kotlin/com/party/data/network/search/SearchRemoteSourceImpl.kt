package com.party.data.network.search

import com.party.core.Constants.serverUrl
import com.party.core.data.safeCall
import com.party.core.domain.DataError
import com.party.core.domain.Result
import com.party.data.dto.search.SearchDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class SearchRemoteSourceImpl(
    private val httpClient: HttpClient
): SearchRemoteSource {
    override suspend fun search(
        titleSearch: String,
        page: Int,
        limit: Int
    ): Result<SearchDto, DataError> {
        return safeCall<SearchDto, Unit> {
            httpClient.get(
                urlString = serverUrl("/parties/search")
            ){
                parameter("titleSearch", titleSearch)
                parameter("page", page)
                parameter("limit", limit)
            }
        }
    }
}