package com.merqueo.co.data.repository

import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface IMovieRepo {
    suspend fun insertAll(data: List<MovieItemDomain>)
    suspend fun getAll(connectivity: Boolean, page: Int): Flow<Resource<List<MovieItemDomain>>>
    suspend fun insert(movieItem: MovieItemDomain)
    suspend fun updateMovieState(id: Int, status: Boolean): Boolean
    suspend fun getCountStoreCart(): Flow<Int>
    suspend fun getAllOnStore(): Flow<List<MovieItemDomain>>
    suspend fun changeAllStore()
    suspend fun getMovieById(idMovie: Int): MovieItemDomain
}
