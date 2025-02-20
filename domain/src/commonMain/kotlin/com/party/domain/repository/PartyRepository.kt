package com.party.domain.repository

import com.party.core.domain.DataError
import com.party.core.domain.DataErrorRemote
import com.party.core.domain.Result
import com.party.domain.model.party.PartyList
import com.party.domain.model.party.RecruitmentList

interface PartyRepository {

    // 홈화면 - 모집공고 리스트
    suspend fun getRecruitmentList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        titleSearch: String?,
        partyTypes: List<Int>,
        position: List<Int>
    ): Result<RecruitmentList, DataErrorRemote<Unit>>

    // 파티 리스트 조회
    suspend fun getPartyList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        partyTypes: List<Int>,
        titleSearch: String?,
        status: String?
    ): Result<PartyList, DataErrorRemote<Unit>>
}