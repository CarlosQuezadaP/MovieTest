package com.merqueo.co.core.common.extensions

import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.core.typealiases.SInHandler
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

inline fun <reified T : Any> T.toSuccessResult(): SuperResult<T> =
    successResult(this)

@Suppress("REDUNDANT_INLINE_SUSPEND_FUNCTION_TYPE")
suspend inline fun <reified I : Any> SuperResult<I>.applyIfSuccessSuspend(crossinline block: SInHandler<I>): SuperResult<I> {
    if (this is SuperResult.Success) block(this.data)
    return this
}


suspend inline fun <reified I : Any, reified O : Any> SuperResult<I>.mapIfSuccessSuspend(crossinline block: suspend I.() -> SuperResult<O>): SuperResult<O> {
    return if (this is SuperResult.Success) block(this.data)
    else this as SuperResult<O>
}

inline fun <reified I : Any, reified O : Any> SuperResult<I>.mapIfSuccess(block: I.() -> SuperResult<O>): SuperResult<O> {
    return if (this is SuperResult.Success) block(this.data)
    else this as SuperResult<O>
}
