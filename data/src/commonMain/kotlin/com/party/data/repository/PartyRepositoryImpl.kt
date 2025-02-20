package com.party.data.repository

import com.party.core.domain.DataError
import com.party.core.domain.DataErrorRemote
import com.party.core.domain.Result
import com.party.core.domain.map
import com.party.data.mapper.PartyMapper
import com.party.data.network.party.PartyRemoteSource
import com.party.domain.model.party.PartyList
import com.party.domain.model.party.RecruitmentList
import com.party.domain.repository.PartyRepository

class PartyRepositoryImpl(
    private val partyRemoteSource: PartyRemoteSource
): PartyRepository {
    override suspend fun getRecruitmentList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        titleSearch: String?,
        partyTypes: List<Int>,
        position: List<Int>
    ): Result<RecruitmentList, DataErrorRemote<Unit>> {
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
    ): Result<PartyList, DataErrorRemote<Unit>> {
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