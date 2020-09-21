package com.merqueo.co.core.models

import com.merqueo.co.core.typealiases.UnitHandler

sealed class SuperResult<out T : Any> {

    open var isNeedHandle: Boolean = false
    open var isHandled: Boolean = false

    class Success<out T : Any>(
        val data: T
    ) : SuperResult<T>()

    object AnySuperResult : SuperResult<Any>()
    object Loading : SuperResult<Nothing>()
    object Empty : SuperResult<Nothing>()
    object Default : SuperResult<Nothing>() {
        override var isHandled: Boolean = true
    }

    object Clear : SuperResult<Nothing>()


    //---- Error States
    sealed class ErrorResult : SuperResult<Nothing>() {
        open val message: Any? = null
        open val exception: Throwable? = null
        override var isNeedHandle = true

        data class Error(
            override val message: Any?,
            val code: Int,
            override val exception: Throwable? = null
        ) : ErrorResult()

        data class Alert(
            override val message: Any? = null,
            val okHandler: UnitHandler? = null,
            override val exception: Throwable? = null
        ) : ErrorResult()
    }
}
