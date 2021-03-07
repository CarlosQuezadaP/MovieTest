package com.merqueo.co.CORE.domain.exceptions

sealed class CustomException(message: String = "") : Exception(message)
{
    class ActivityNullError(message: String = "") : CustomException(message)
    class IllegalRemoteTypeException : CustomException("This type of Remote Config Values Doesn't supported")
    class NoInternetConnectionException : CustomException("There is no internet connection. Please try later")
    class EmptyBundleParcelableException(message: String) : CustomException(message)

}