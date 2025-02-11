package com.kmp.domain.usecase.party

import com.kmp.core.domain.DataError
import com.kmp.core.domain.Result
import com.kmp.domain.model.party.RecruitmentList
import com.kmp.domain.repository.PartyRepository

class GetRecruitmentListUseCase(
    private val partyRepository: PartyRepository
) {
    suspend operator fun invoke(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        titleSearch: String?,
        partyTypes: List<Int>?,
        position: List<Int>?,
    ): Result<RecruitmentList, DataError.Remote> {
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