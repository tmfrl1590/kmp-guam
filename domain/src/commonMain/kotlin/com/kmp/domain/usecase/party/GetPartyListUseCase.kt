package com.kmp.domain.usecase.party

import com.kmp.core.domain.DataError
import com.kmp.core.domain.Result
import com.kmp.domain.model.party.PartyList
import com.kmp.domain.repository.PartyRepository

class GetPartyListUseCase(
    private val partyRepository: PartyRepository
) {
    suspend operator fun invoke(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        partyTypes: List<Int>,
        titleSearch: String?,
        status: String?
    ): Result<PartyList, DataError.Remote> {
        return partyRepository.getPartyList(
            page = page,
            limit = limit,
            sort = sort,
            order = order,
            titleSearch = titleSearch,
            status = status,
            partyTypes = partyTypes
        )
    }
}