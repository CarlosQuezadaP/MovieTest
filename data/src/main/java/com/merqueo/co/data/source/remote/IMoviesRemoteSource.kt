package com.merqueo.co.data.source.remote

import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface IMoviesRemoteSource {

    suspend fun getUpcomingMovies(page: Int): Flow<List<com.merqueo.co.domain.models.MovieItemDomain>>
}