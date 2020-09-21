package com.rasalexman.providers.network.handlers

import com.merqueo.co.core.CustomException
import com.merqueo.co.core.common.extensions.toErrorResult
import com.merqueo.co.core.models.SuperResult
import com.rasalexman.coroutinesmanager.SuspendCatch
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

inline fun <reified T : Any> Any.errorResultCatchBlock(): SuspendCatch<SuperResult<T>> = {
    when (it) {
        is UnknownHostException,
        is IOException -> CustomException.NoInternetConnectionException().toErrorResult()
        is HttpException -> it.toErrorResult()
        else -> it.toErrorResult()
    }
}

suspend inline fun <reified T : Any> Throwable.asErrorResult(): SuperResult<T> {
    return when (this) {
        is UnknownHostException,
        is IOException -> CustomException.NoInternetConnectionException().toErrorResult()
        is HttpException -> this.toErrorResult()
        else -> this.toErrorResult()
    }
}