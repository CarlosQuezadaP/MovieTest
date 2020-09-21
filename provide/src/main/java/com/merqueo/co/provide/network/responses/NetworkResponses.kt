package com.merqueo.co.provide.network.responses

import androidx.annotation.Keep
import com.merqueo.co.core.common.extensions.emptyResult
import com.merqueo.co.core.common.extensions.errorResult
import com.merqueo.co.core.common.extensions.successResult
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.models.dto.GenreDto
import retrofit2.Response

@Keep
data class GetGenresResponse(val genres: List<GenreDto>)

data class GetMoviesListResponse<E>(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<E>
)

inline fun <reified T : Any, reified O : Any> Response<T>.getResult(alsoAction: (T) -> O): SuperResult<O> {
    return this.body()?.run {
        successResult(alsoAction(this))
    } ?: this.errorBody()?.let {
        errorResult(this.message(), this.code())
    } ?: emptyResult()
}


