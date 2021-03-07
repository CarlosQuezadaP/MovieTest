package com.merqueo.co.data.remoteSource

import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.infraestructura.network.api.IMovieApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MoviesRemoteSource(
    private val moviesApi: IMovieApi
) : IMoviesRemoteSource {
    override suspend fun getUpcomingMovies(page: Int): List<MovieItemDomain> {
        val response = moviesApi.getUpcomingMovies(page)
        return response.body()?.run {
            this.movieDtos.map {
                it.convertTo()
            }
        } ?: emptyList()
    }
}