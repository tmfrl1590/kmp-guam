package com.party.domain.usecase.search

import com.party.domain.repository.SearchRepository

class GetSearchedDataUseCase(
    private val searchRepository: SearchRepository
){
    suspend operator fun invoke(titleSearch: String, page: Int, limit: Int) = searchRepository.searchPartiesByKeyword(titleSearch, page, limit)
}