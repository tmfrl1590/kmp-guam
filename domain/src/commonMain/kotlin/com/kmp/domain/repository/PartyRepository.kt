package com.kmp.domain.repository

import com.kmp.core.domain.DataError
import com.kmp.core.domain.Result
import com.kmp.domain.model.party.PartyList

interface PartyRepository {
    // 파티 리스트 조회
    suspend fun getPartyList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        partyTypes: List<Int>,
        titleSearch: String?,
        status: String?
    ): Result<PartyList, DataError.Remote>
}