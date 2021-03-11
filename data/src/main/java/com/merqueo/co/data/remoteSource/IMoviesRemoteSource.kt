package com.merqueo.co.data.remoteSource

import com.merqueo.co.domain.models.MovieItemDomain

interface IMoviesRemoteSource {
    suspend fun getUpcomingMovies(page: Int): List<MovieItemDomain>
}