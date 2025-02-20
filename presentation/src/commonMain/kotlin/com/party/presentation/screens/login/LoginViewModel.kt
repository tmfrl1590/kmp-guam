package com.party.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.party.core.domain.DataErrorRemote
import com.party.core.domain.onError
import com.party.core.domain.onSuccess
import com.party.domain.model.user.login.AccessTokenRequest
import com.party.domain.model.user.login.SocialLoginError
import com.party.domain.usecase.datastore.GetAccessTokenUseCase
import com.party.domain.usecase.datastore.SaveAccessTokenUseCase
import com.party.domain.usecase.user.GoogleLoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginViewModel(
    private val googleLoginUseCase: GoogleLoginUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
): ViewModel() {

    init {
        getAccessToken11()
    }

    private val _goToHomeScreen = MutableSharedFlow<Unit>()
    val goToHomeScreen = _goToHomeScreen.asSharedFlow()

    private val _nextScreen = MutableSharedFlow<SocialLoginError>()
    val nextScreen = _nextScreen.asSharedFlow()

    private val _accessToken = MutableStateFlow("")
    val accessToken: StateFlow<String> = _accessToken

    fun serverToGoogleLogin(userEmail: String, accessToken: String){
        viewModelScope.launch(Dispatchers.IO) {
            googleLoginUseCase(
                accessTokenRequest = AccessTokenRequest(idToken = accessToken)
            ).onSuccess {
                saveAccessToken(token = it.accessToken) // 토큰 저장
                _goToHomeScreen.emit(Unit)
            }.onError { error ->
                when(error)  {
                    // 회원가입 페이지 이동
                    is DataErrorRemote.Unauthorized-> {
                        _nextScreen.emit(
                            SocialLoginError(
                                userEmail = userEmail,
                                signupAccessToken = error.response?.signupAccessToken ?: "",
                                message = error.response?.message ?: ""
                            )
                        )
                    }
                    else -> {}
                }
            }
        }
    }

    private fun saveAccessToken(token: String){
        viewModelScope.launch(Dispatchers.IO) {
            saveAccessTokenUseCase(token = token)
        }
    }

    fun getAccessToken11() = viewModelScope.launch(Dispatchers.IO) {
        getAccessTokenUseCase().collectLatest { accessToken ->
            _accessToken.emit(accessToken)
        }
    }
}