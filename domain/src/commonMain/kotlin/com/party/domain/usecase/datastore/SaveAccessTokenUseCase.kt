package com.party.domain.usecase.datastore

import com.party.domain.repository.DataStoreRepository

class SaveAccessTokenUseCase (
    private val dataStoreRepository: DataStoreRepository,
) {
    suspend operator fun invoke(token: String){
        dataStoreRepository.saveAccessToken(token = token)
    }
}