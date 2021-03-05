package com.merqueo.co.data.remoteSource

import kotlinx.coroutines.flow.Flow

interface IMoviesRemoteSource {

    suspend fun getUpcomingMovies(page: Int): Flow<List<com.merqueo.co.domain.models.MovieItemDomain>>
}