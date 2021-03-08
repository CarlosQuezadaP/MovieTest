package com.merqueo.co.CORE.model

sealed class Resource<out T> {
    data class Success<out T : Any>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: String?,
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}
