package com.merqueo.co.infraestructura.source.remote

import com.merqueo.co.models.ui.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface IMoviesRemoteSource {

    suspend fun getUpcomingMovies(page: Int): Flow<List<MovieItemDomain>>
}