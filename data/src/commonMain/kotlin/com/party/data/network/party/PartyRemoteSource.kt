package com.party.data.network.party

import com.party.core.domain.DataError
import com.party.core.domain.DataErrorRemote
import com.party.core.domain.Result
import com.party.data.dto.party.PartyListDto
import com.party.data.dto.party.RecruitmentListDto

interface PartyRemoteSource {


    // 홈화면 - 모집공고 리스트
    suspend fun getRecruitmentList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        titleSearch: String?,
        partyTypes: List<Int>,
        position: List<Int>
    ): Result<RecruitmentListDto, DataErrorRemote<Unit>>

    // 파티 리스트 조회
    suspend fun getPartyList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        partyTypes: List<Int>,
        titleSearch: String?,
        status: String?
    ): Result<PartyListDto, DataErrorRemote<Unit>>
}