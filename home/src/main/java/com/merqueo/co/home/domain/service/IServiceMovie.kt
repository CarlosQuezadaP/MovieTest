package com.merqueo.co.home.domain.service

import com.merqueo.co.models.ui.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface IServiceMovie {

    suspend fun getMovies(): Flow<List<MovieItemDomain>>
    suspend fun getAllFromRemote(): Flow<List<MovieItemDomain>>
    suspend fun getAllFromLocale(): Flow<List<MovieItemDomain>>
    suspend fun saveMovies(movieDtos: List<MovieItemDomain>)
    suspend fun updateMovieState(id: Int, status: Boolean): Boolean
}