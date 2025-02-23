package com.party.data.network.search

import com.party.core.domain.DataError
import com.party.core.domain.Result
import com.party.data.dto.search.SearchDto

interface SearchRemoteSource {

    // 검색하기
    suspend fun search(titleSearch: String, page: Int, limit: Int): Result<SearchDto, DataError>
}