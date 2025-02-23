package com.party.data.repository

import com.party.core.domain.DataErrorRemote
import com.party.core.domain.Result
import com.party.core.domain.map
import com.party.core.domain.mapError
import com.party.data.mapper.SearchMapper.mapperSearch
import com.party.data.network.search.SearchRemoteSource
import com.party.domain.model.search.Search
import com.party.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val searchRemoteSource: SearchRemoteSource
): SearchRepository {
    override suspend fun searchPartiesByKeyword(
        titleSearch: String,
        page: Int,
        limit: Int
    ): Result<Search, DataErrorRemote<Unit>> {
        return searchRemoteSource.search(
            titleSearch = titleSearch,
            page = page,
            limit = limit
        ).map {
            searchDto -> mapperSearch(searchDto = searchDto)
        }.mapError { error ->
            DataErrorRemote.Unknown()
        }
    }
}