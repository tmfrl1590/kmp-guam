package com.kmp.data.repository

import com.kmp.core.domain.DataError
import com.kmp.core.domain.Result
import com.kmp.core.domain.map
import com.kmp.data.mapper.PartyMapper
import com.kmp.data.network.PartyRemoteSource
import com.kmp.domain.model.party.PartyList
import com.kmp.domain.model.party.RecruitmentList
import com.kmp.domain.repository.PartyRepository

class PartyRepositoryImpl(
    private val partyRemoteSource: PartyRemoteSource
): PartyRepository {
    override suspend fun getRecruitmentList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        titleSearch: String?,
        partyTypes: List<Int>?,
        position: List<Int>?
    ): Result<RecruitmentList, DataError.Remote> {
        return partyRemoteSource.getRecruitmentList(
            page = page,
            limit = limit,
            sort = sort,
            order = order,
            titleSearch = titleSearch,
            partyTypes = partyTypes,
            position = position,
        ).map { recruitmentListDto ->
            PartyMapper.mapperToRecruitmentList(recruitmentListDto = recruitmentListDto)
        }
    }

    override suspend fun getPartyList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        partyTypes: List<Int>,
        titleSearch: String?,
        status: String?
    ): Result<PartyList, DataError.Remote> {
        return partyRemoteSource.getPartyList(
            page = page,
            limit = limit,
            sort = sort,
            order = order,
            titleSearch = titleSearch,
            status = status,
            partyTypes = partyTypes
        ).map { partyListDto ->
            PartyMapper.mapperToPartyList(partyListDto = partyListDto)
        }
    }
}