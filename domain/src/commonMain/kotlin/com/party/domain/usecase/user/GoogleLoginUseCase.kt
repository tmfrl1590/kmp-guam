package com.party.domain.usecase.user

import com.party.core.domain.DataError
import com.party.core.domain.Result
import com.party.domain.model.user.login.AccessTokenRequest
import com.party.domain.model.user.login.SocialLoginSuccess
import com.party.domain.repository.UserRepository

class GoogleLoginUseCase (
    private val userRepository: UserRepository,
){
    suspend operator fun invoke(accessTokenRequest: AccessTokenRequest): Result<SocialLoginSuccess, DataError.Remote> {
        return userRepository.googleLogin(accessTokenRequest = accessTokenRequest)
    }
}