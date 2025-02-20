package com.party.core.domain



sealed interface DataError : Error

sealed class DataErrorRemote<E>(val response: E? = null) : DataError {
    class Unauthorized<E>(response: E? = null) : DataErrorRemote<E>(response)
    class RequestTimeout<E>(response: E? = null) : DataErrorRemote<E>(response)
    class TooManyRequests<E>(response: E? = null) : DataErrorRemote<E>(response)
    class NoInternet<E>(response: E? = null) : DataErrorRemote<E>(response)
    class ServerError<E>(response: E? = null) : DataErrorRemote<E>(response)
    class Serialization<E>(response: E? = null) : DataErrorRemote<E>(response)
    class BadRequest<E>(response: E? = null) : DataErrorRemote<E>(response)
    class Unknown<E>(response: E? = null) : DataErrorRemote<E>(response)
}



/*
sealed interface DataError: Error {
    enum class Remote: DataError {
        UNAUTHORIZED,
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN,
        BAD_REQUEST,
    }

    enum class Local: DataError {
        DISK_FULL,
        UNKNOWN
    }
}*/
