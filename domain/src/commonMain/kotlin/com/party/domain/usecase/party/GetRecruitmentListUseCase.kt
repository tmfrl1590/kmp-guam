package com.party.domain.usecase.party

import com.party.core.domain.DataError
import com.party.core.domain.DataErrorRemote
import com.party.core.domain.Result
import com.party.domain.model.party.RecruitmentList
import com.party.domain.repository.PartyRepository

class GetRecruitmentListUseCase(
    private val partyRepository: PartyRepository
) {
    suspend operator fun invoke(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        titleSearch: String?,
        partyTypes: List<Int>,
        position: List<Int>,
    ): Result<RecruitmentList, DataErrorRemote<Unit>> {
        return partyRepository.getRecruitmentList(
            page = page,
            limit = limit,
            sort = sort,
            order = order,
            titleSearch = titleSearch,
            partyTypes = partyTypes,
            position = position
        )
    }
}