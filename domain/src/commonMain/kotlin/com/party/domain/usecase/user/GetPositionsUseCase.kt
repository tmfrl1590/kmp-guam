package com.party.domain.usecase.user

import com.party.core.domain.DataError
import com.party.core.domain.Result
import com.party.domain.model.user.detail.PositionList
import com.party.domain.repository.UserRepository

class GetPositionsUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(main: String): Result<List<PositionList>, DataError.Remote>{
        return userRepository.getPositions(main = main)
    }
}