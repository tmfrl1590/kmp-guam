package com.party.core

import kotlinx.serialization.Serializable

sealed interface Screens {
    @Serializable
    data object Login: Screens
    @Serializable
    data object Home: Screens
    @Serializable
    data object State: Screens
    @Serializable
    data object Profile: Screens
    @Serializable
    data class JoinEmail(val userEmail: String, val signupAccessToken: String): Screens
    @Serializable
    data object Search: Screens
}