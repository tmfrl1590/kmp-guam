package com.party.domain.model.user.login

import kotlinx.serialization.Serializable

@Serializable
data class AccessTokenRequest(
    val idToken: String,
)
