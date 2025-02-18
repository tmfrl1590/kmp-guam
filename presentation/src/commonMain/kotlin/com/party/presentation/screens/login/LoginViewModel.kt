package com.party.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.party.core.domain.DataError
import com.party.core.domain.onError
import com.party.core.domain.onSuccess
import com.party.domain.model.user.login.AccessTokenRequest
import com.party.domain.usecase.user.GoogleLoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val googleLoginUseCase: GoogleLoginUseCase,
): ViewModel() {

    private val _goToHomeScreen = MutableSharedFlow<Unit>()
    val goToHomeScreen = _goToHomeScreen.asSharedFlow()

    fun serverToGoogleLogin(userEmail: String, accessToken: String){
        viewModelScope.launch(Dispatchers.IO) {
            googleLoginUseCase(
                accessTokenRequest = AccessTokenRequest(idToken = accessToken)
            ).onSuccess {
                _goToHomeScreen.emit(Unit)
            }.onError { error ->
                when(error)  {
                    DataError.Remote.UNAUTHORIZED -> {} // 회원가입 페이지 이동
                    else -> {}
                }
            }
        }
    }
}