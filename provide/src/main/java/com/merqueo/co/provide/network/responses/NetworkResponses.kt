package com.merqueo.co.provide.network.responses

import com.merqueo.co.core.common.extensions.emptyResult
import com.merqueo.co.core.common.extensions.errorResult
import com.merqueo.co.core.common.extensions.successResult
import com.merqueo.co.core.models.SuperResult
import retrofit2.Response

inline fun <reified T : Any, reified O : Any> Response<T>.getResult(alsoAction: (T) -> O): SuperResult<O> {
    return this.body()?.run {
        successResult(alsoAction(this))
    } ?: this.errorBody()?.let {
        errorResult(this.message(), this.code())
    } ?: emptyResult()
}


