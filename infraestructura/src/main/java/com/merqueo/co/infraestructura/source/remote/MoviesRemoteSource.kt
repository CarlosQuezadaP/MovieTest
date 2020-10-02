package com.merqueo.co.infraestructura.source.remote

import com.merqueo.co.infraestructura.network.api.IMovieApi
import com.merqueo.co.models.ui.MovieItemDomain
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

@InternalCoroutinesApi
class MoviesRemoteSource(
    private val moviesApi: IMovieApi
) : IMoviesRemoteSource {

    override suspend fun getUpcomingMovies(page: Int): Flow<List<MovieItemDomain>> {


        val response = moviesApi.getUpcomingMovies(page)
        val flow = flowOf(response).map {
            response.body().let {
                it!!.movieDtos.map {
                    it.convertTo()
                }
            }


        }
        return flow
    }
}