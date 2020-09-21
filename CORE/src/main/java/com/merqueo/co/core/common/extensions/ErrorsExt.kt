package com.merqueo.co.core.common.extensions

import com.merqueo.co.core.CustomException
import com.merqueo.co.core.R


fun CustomException.getErrorMessageResId(): Int {
    return when(this) {

        is CustomException.NoInternetConnectionException -> R.string.error_internal_exception
        is CustomException.ActivityNullError -> R.string.error_internal_exception
        else -> -1
    }
}

inline fun <reified T : CustomException> throwError(message: String = ""): Nothing {
    throw makeError<T>(message)
}

inline fun <reified T : CustomException> makeError(message: String = ""): Exception {
    return when (T::class) {
        CustomException.ActivityNullError::class -> CustomException.ActivityNullError(message)
        CustomException.EmptyBundleParcelableException::class -> CustomException.EmptyBundleParcelableException(message)

        CustomException.NoInternetConnectionException::class -> CustomException.NoInternetConnectionException()
        else -> Exception(message)
    }
}