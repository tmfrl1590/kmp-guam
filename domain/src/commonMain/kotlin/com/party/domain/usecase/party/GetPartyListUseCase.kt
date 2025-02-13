package com.party.domain.usecase.party

import com.party.core.domain.DataError
import com.party.core.domain.Result
import com.party.domain.model.party.PartyList
import com.party.domain.repository.PartyRepository

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