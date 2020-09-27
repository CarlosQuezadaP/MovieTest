package com.merqueo.co.infraestructura.source.remote

sealed class AppResult<out T> {

    data class Success<out T>(val successData: T) : AppResult<T>()
    class Error(
        val exception: java.lang.Exception,
        val message: String = exception.localizedMessage
    ) : AppResult<Nothing>()

}