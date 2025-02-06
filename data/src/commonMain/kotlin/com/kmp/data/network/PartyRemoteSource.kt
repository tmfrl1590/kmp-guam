package com.kmp.data.network

import com.kmp.core.domain.DataError
import com.kmp.core.domain.Result
import com.kmp.data.dto.party.PartyListDto

interface PartyRemoteSource {

    // 파티 리스트 조회
    suspend fun getPartyList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        partyTypes: List<Int>,
        titleSearch: String?,
        status: String?
    ): Result<PartyListDto, DataError.Remote>
}