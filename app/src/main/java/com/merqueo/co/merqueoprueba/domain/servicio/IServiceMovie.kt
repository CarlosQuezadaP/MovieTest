package com.merqueo.co.merqueoprueba.domain.servicio

import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface IServiceMovie {

    suspend fun getMovies(): Flow<List<com.merqueo.co.domain.models.MovieItemDomain>>
    suspend fun getAllFromRemote(): Flow<List<com.merqueo.co.domain.models.MovieItemDomain>>
    suspend fun getAllFromLocale(): Flow<List<com.merqueo.co.domain.models.MovieItemDomain>>
    suspend fun saveMovies(movieDtos: List<com.merqueo.co.domain.models.MovieItemDomain>)
    suspend fun updateMovieState(id: Int, status: Boolean): Boolean
}
