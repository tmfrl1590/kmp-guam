package com.party.core

object Constants {

    // DataStore
    const val ACCESS_TOKEN_KEY = "access_token"

    private const val SERVER_URL = "https://partyguham.com/dev/api"
    //private const val SERVER_URL = "https://partyguham.com/api"

    private const val IMAGE_URL = "https://partyguham.com/dev/api/"
    //private const val IMAGE_URL = "https://partyguham.com/api/"

    fun serverUrl(urlString: String): String {
        return "$SERVER_URL$urlString"
    }

    fun convertToImageUrl(imageUrl: String?): String {
        return IMAGE_URL + imageUrl
    }
}