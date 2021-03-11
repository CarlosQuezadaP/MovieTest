package com.merqueo.co.CORE.model

sealed class Resource<out T> {
    class Success<out T: Any>(val data: T): Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}