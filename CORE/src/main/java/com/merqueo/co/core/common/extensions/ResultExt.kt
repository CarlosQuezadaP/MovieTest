package com.merqueo.co.core.common.extensions

import com.merqueo.co.core.CustomException
import com.merqueo.co.core.base.IConvertableTo
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.core.typealiases.InHandler
import com.merqueo.co.core.typealiases.InOutHandler
import com.merqueo.co.core.typealiases.SInHandler
import com.merqueo.co.core.typealiases.UnitHandler


// /------ ViewResult extensions
inline fun <reified T : Any> Any.successResult(data: T): SuperResult<T> = SuperResult.Success(data)
fun Any.loadingResult() = SuperResult.Loading
fun Any.emptyResult() = SuperResult.Empty
fun Any.defaultResult() = SuperResult.Default
fun Any.clearResult() = SuperResult.Clear

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

// /-------- toState Convertables
inline fun <reified T : Any> T.toSuccessResult(): SuperResult<T> =
    successResult(this)


inline fun <reified T : Throwable> T.toErrorResult() =
    errorResult(this.message ?: this.cause?.message.orEmpty(), 0, this)

inline fun <reified T : Throwable> T.toAlertResult() =
    alertResult(message = this.message ?: this.cause?.message.orEmpty(), exception = this)

// /-------- HANDLE FUNCTION
fun SuperResult<*>.handle() {
    if (isNeedHandle) isHandled = true
}

// /------- Mapper function
@Suppress("UNCHECKED_CAST")
inline fun <reified O : Any, reified I : IConvertableTo<O>> SuperResult<List<I>>.mapListTo(noinline converter: InOutHandler<I, O?>? = null): SuperResult<List<O>> {
    return when (this) {
        is SuperResult.Success -> {
            data.mapNotNull { it.convertTo() ?: converter?.invoke(it) }.toSuccessResult()
        }
        else -> this as SuperResult<List<O>>
    }
}


fun Any.anyResult() = SuperResult.AnySuperResult

@Suppress("UNCHECKED_CAST")
inline fun <reified O : Any, reified I : Any> SuperResult<List<I>>.mapListBy(noinline converter: (I.() -> O)? = null): SuperResult<List<O>> {
    return when (this) {
        is SuperResult.Success -> {
            data.mapNotNull { converter?.invoke(it) }.toSuccessResult()
        }
        else -> this as SuperResult<List<O>>
    }
}

inline fun <reified O : Any, reified I : IConvertableTo<O>> SuperResult<I>.mapTo(): SuperResult<*> {
    return when (this) {
        is SuperResult.Success -> {
            this.data.convertTo()?.run {
                this.toSuccessResult()
            } ?: emptyResult()
        }
        else -> this
    }
}

fun SuperResult.ErrorResult.getMessage(): Any? {
    return (this.message?.takeIf { (it as? String)?.isNotEmpty() == true || (it as? Int) != null && it > 0 }
        ?: (this.exception as? CustomException)?.getErrorMessageResId())
        ?: this.exception?.cause?.message
}

///--- Inline Applying functions
inline fun <reified I : Any> SuperResult<I>.doIfSuccess(block: UnitHandler): SuperResult<I> {
    if (this is SuperResult.Success) block()
    return this
}

suspend inline fun <reified I : Any> SuperResult<I>.doIfSuccessSuspend(crossinline block: suspend () -> Unit): SuperResult<I> {
    if (this is SuperResult.Success) block()
    return this
}

// /--- Inline Applying functions
inline fun <reified I : Any> SuperResult<I>.applyIfSuccess(block: InHandler<I>): SuperResult<I> {
    if (this is SuperResult.Success) block(this.data)
    return this
}

@Suppress("REDUNDANT_INLINE_SUSPEND_FUNCTION_TYPE")
suspend inline fun <reified I : Any> SuperResult<I>.applyIfSuccessSuspend(crossinline block: SInHandler<I>): SuperResult<I> {
    if (this is SuperResult.Success) block(this.data)
    return this
}

inline fun <reified I : Any, reified O : Any> SuperResult<I>.mapIfSuccess(block: I.() -> SuperResult<O>): SuperResult<O> {
    return if (this is SuperResult.Success) block(this.data)
    else this as SuperResult<O>
}


inline fun <reified I : SuperResult<*>> SuperResult<*>.applyIfType(block: I.() -> Unit): SuperResult<*> {
    if (this::class == I::class) block(this as I)
    return this
}

@Suppress("REDUNDANT_INLINE_SUSPEND_FUNCTION_TYPE")
suspend inline fun <reified I : SuperResult<*>> SuperResult<*>.applyIfTypeSuspend(crossinline block: suspend I.() -> Unit): SuperResult<*> {
    if (this::class == I::class) block(this as I)
    return this
}


suspend inline fun <reified I : Any, reified O : Any> SuperResult<I>.mapIfSuccessSuspend(crossinline block: suspend I.() -> SuperResult<O>): SuperResult<O> {
    return if (this is SuperResult.Success) block(this.data)
    else this as SuperResult<O>
}

inline fun <reified I : SuperResult<*>> SuperResult<*>.mapIfType(block: I.() -> SuperResult<*>): SuperResult<*> {
    return if (this::class == I::class) block(this as I)
    else this
}

suspend inline fun <reified I : SuperResult<*>> SuperResult<*>.mapIfTypeSuspend(crossinline block: suspend I.() -> SuperResult<*>): SuperResult<*> {
    return if (this::class == I::class) block(this as I)
    else this
}
