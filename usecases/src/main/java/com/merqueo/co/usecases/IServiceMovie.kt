package com.merqueo.co.usecases

import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.flow.Flow


interface IServiceMovie {

    suspend fun getMovies(): Flow<List<MovieItemDomain>>
    suspend fun getAllFromRemote(): Flow<List<MovieItemDomain>>
    suspend fun getAllFromLocale(): Flow<List<MovieItemDomain>>
    suspend fun saveMovies(movieDtos: List<MovieItemDomain>)
    suspend fun updateMovieState(id: Int, status: Boolean): Boolean
}
