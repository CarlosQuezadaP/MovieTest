package com.merqueo.co.data.localSource

import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface IMoviesLocalSource {
    suspend fun insertAll(data: List<MovieItemDomain>)
    suspend fun getAll(): Flow<Resource<List<MovieItemDomain>>>
    suspend fun insert(movieItem: MovieItemDomain)
    suspend fun updateMovieState(id: Int, status: Boolean): Boolean
    suspend fun getCountStoreCart(): Flow<Int>
    suspend fun getAllOnStore(): Flow<List<MovieItemDomain>>
    suspend fun changeAllStore()
    suspend fun getMovieById(idMovie: Int): MovieItemDomain
}

