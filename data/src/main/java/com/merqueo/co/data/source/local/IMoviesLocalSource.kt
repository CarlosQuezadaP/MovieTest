package com.merqueo.co.data.source.local

import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface IMoviesLocalSource {

    suspend fun insertAll(data: List<com.merqueo.co.domain.models.MovieItemDomain>)
    suspend fun getAll(): Flow<List<com.merqueo.co.domain.models.MovieItemDomain>>
    suspend fun insert(movieItem: com.merqueo.co.domain.models.MovieItemDomain)
    suspend fun updateMovieState(id: Int, status: Boolean): Boolean
    suspend fun getCountStoreCart(): Flow<Int>
    suspend fun getAllOnStore(): Flow<List<com.merqueo.co.domain.models.MovieItemDomain>>
    suspend fun changeAllStore()
    suspend fun getMovieById(idMovie: Int): com.merqueo.co.domain.models.MovieItemDomain


}

