package com.merqueo.co.core.common.extensions

import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.core.typealiases.UnitHandler

inline fun <reified T : Any> Any.successResult(data: T): SuperResult<T> = SuperResult.Success(data)
fun Any.loadingResult() = SuperResult.Loading
fun Any.emptyResult() = SuperResult.Empty
fun Any.defaultResult() = SuperResult.Default
fun Any.clearResult() = SuperResult.Clear
fun Any.anyResult() = SuperResult.AnySuperResult


fun Any.errorResult(
    message: String = "",
    code: Int = -1,
    exception: Throwable? = null
) = SuperResult.ErrorResult.Error(message, code, exception)

fun Any.alertResult(
    message: Any? = null,
    exception: Throwable? = null,
    okHandler: UnitHandler? = null
) = SuperResult.ErrorResult.Alert(message, okHandler, exception)

inline fun <reified T : Throwable> T.toErrorResult() =
    errorResult(this.message ?: this.cause?.message.orEmpty(), 0, this)