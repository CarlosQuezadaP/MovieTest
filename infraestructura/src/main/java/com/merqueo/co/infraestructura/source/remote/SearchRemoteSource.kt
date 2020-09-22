package com.merqueo.co.infraestructura.source.remote

import androidx.paging.PageKeyedDataSource
import com.merqueo.co.core.common.extensions.anyResult
import com.merqueo.co.core.common.extensions.applyIfSuccessSuspend
import com.merqueo.co.core.common.extensions.loadingResult
import com.merqueo.co.core.common.extensions.mapIfSuccessSuspend
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.core.typealiases.AnyResultMutableLiveData
import com.merqueo.co.infraestructura.network.api.IMovieApi
import com.merqueo.co.models.dto.MovieDto
import com.merqueo.co.provide.network.handlers.errorResultCatchBlock
import com.merqueo.co.provide.network.responses.GetMoviesListResponse
import com.merqueo.co.provide.network.responses.getResult
import com.rasalexman.coroutinesmanager.ICoroutinesManager
import com.rasalexman.coroutinesmanager.doWithTryCatchAsync
import com.rasalexman.coroutinesmanager.launchOnUI

class SearchRemoteSource(
    private val movieApi: IMovieApi,
    private val query: String,
    private val resultLiveData: AnyResultMutableLiveData
) : PageKeyedDataSource<Int, MovieDto>(), ICoroutinesManager {

    private var currentPage = 1
    private var maxPages = 1000

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieDto>
    ) = launchOnUI {
        resultLiveData.postValue(
            loadMoviesByQuery(query).mapIfSuccessSuspend {
                callback.onResult(this, currentPage - 1, currentPage)
                anyResult()
            }
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieDto>) =
        launchOnUI {
            resultLiveData.postValue(
                loadMoviesByQuery(query).applyIfSuccessSuspend {
                    callback.onResult(it, currentPage)
                }
            )
        }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieDto>) = Unit

    private suspend fun loadMoviesByQuery(query: String): SuperResult<List<MovieDto>> =
        doWithTryCatchAsync(
            tryBlock = {
                resultLiveData.postValue(loadingResult())
                movieApi.getSearchMovies(query).getResult {
                    changePage(it)
                    it.results
                }
            }, catchBlock = errorResultCatchBlock()
        )

    /**
     * Increase currentPage count if available
     *
     * @param response - current request response data
     */
    private fun changePage(response: GetMoviesListResponse<MovieDto>) {
        val currentPage = response.page
        maxPages = response.total_pages
        if (currentPage < maxPages) this.currentPage++
    }
}