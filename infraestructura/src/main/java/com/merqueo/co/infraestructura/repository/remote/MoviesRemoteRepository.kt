package com.merqueo.co.infraestructura.repository.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.paging.DataSource
import com.merqueo.co.core.common.extensions.loadingResult
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.core.typealiases.ResultList
import com.merqueo.co.infraestructura.network.api.IMovieApi
import com.merqueo.co.infraestructura.repository.SearchDataSourceFactory
import com.merqueo.co.models.dto.MovieDto
import com.merqueo.co.provide.network.handlers.asErrorResult
import com.merqueo.co.provide.network.handlers.errorResultCatchBlock
import com.merqueo.co.provide.network.responses.getResult
import com.rasalexman.coroutinesmanager.CoroutinesProvider
import com.rasalexman.coroutinesmanager.doWithTryCatchAsync
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.text.SimpleDateFormat
import java.util.*

class MoviesRemoteRepository(
    private val moviesApi: IMovieApi
) : IMoviesRemoteRepository {

    override suspend fun getSearchDataSource(
        query: String,
        resultLiveData: MutableLiveData<SuperResult<Any>>
    ): DataSource.Factory<Int, MovieDto> {
        return SearchDataSourceFactory(
            moviesApi,
            query,
            resultLiveData
        )
    }

    /**
     * Get popular movies from Api
     * @param page - current selected page
     */
    override suspend fun getPopularMovies(page: Int): ResultList<MovieDto> =
        doWithTryCatchAsync(
            tryBlock = { moviesApi.getPopularMovie(page).getResult { it.results } },
            catchBlock = errorResultCatchBlock()
        )

    override suspend fun getUpcomingMovies(page: Int): ResultList<MovieDto> =
        doWithTryCatchAsync(
            tryBlock = { moviesApi.getUpcomingMovies(page).getResult { it.results } },
            catchBlock = errorResultCatchBlock()
        )

    override suspend fun getTopRatedMovies(pageLiveData: LiveData<Int>) = flow {
        pageLiveData.asFlow().collect { page ->
            emit(loadingResult())
            emit(moviesApi.getTopRatedMovies(page).getResult { it.results })
        }
    }.catch {
        emit(it.asErrorResult())
    }.flowOn(CoroutinesProvider.IO)

    /**
     * Get movies by genre id with pagination
     */
    override suspend fun getByGenreId(
        genreId: Int,
        lastReleaseDate: Long?
    ): SuperResult<List<MovieDto>> =
        requestHandler(genreId = genreId, lastReleaseDate = lastReleaseDate)

    /**
     * Get New movies list by genre id starting from first page (for swipe to refresh)
     */
    override suspend fun getNewMoviesByGenreId(genreId: Int): SuperResult<List<MovieDto>> {
        return requestHandler(genreId = genreId)
    }

    /**
     * Get all the movie details for current id
     */
    override suspend fun getMovieDetails(movieId: Int): SuperResult<MovieDto> =
        doWithTryCatchAsync(
            tryBlock = { moviesApi.getMovieDetails(movieId).getResult { it } },
            catchBlock = errorResultCatchBlock()
        )

    /**
     * Request data from API
     */
    private suspend fun requestHandler(
        genreId: Int,
        lastReleaseDate: Long? = null
    ): SuperResult<List<MovieDto>> = doWithTryCatchAsync(tryBlock = {
        val releaseDate = lastReleaseDate?.run { Date(lastReleaseDate) } ?: Date()
        val releaseDateFrom =
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(releaseDate)
        moviesApi.getMoviesListByGenreId(genreId, 1, releaseDateFrom).getResult { it.results }
    }, catchBlock = errorResultCatchBlock())


}