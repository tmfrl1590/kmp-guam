package com.party.domain.repository

import com.party.core.domain.DataErrorRemote
import com.party.core.domain.Result
import com.party.domain.model.search.Search

interface SearchRepository {

    // 검색어로 파티 리스트 조회
    suspend fun searchPartiesByKeyword(titleSearch: String, page: Int, limit: Int): Result<Search, DataErrorRemote<Unit>>
}