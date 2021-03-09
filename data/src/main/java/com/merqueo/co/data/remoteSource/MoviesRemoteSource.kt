package com.merqueo.co.data.remoteSource

import com.merqueo.co.data.responses.upcoming.UpcomingResponse
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.infraestructura.network.api.IMovieApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MoviesRemoteSource(
    private val moviesApi: IMovieApi
) : IMoviesRemoteSource {

    var closure: (UpcomingResponse) -> List<MovieItemDomain> = {
        it.movieDtos.map {
            it.convertTo()
        }
    }


    override suspend fun getUpcomingMovies(page: Int): List<MovieItemDomain> {

        val response = moviesApi.getUpcomingMovies(page)

        return response.body()?.run {
            closure(this)
        } ?: emptyList()
    }
}