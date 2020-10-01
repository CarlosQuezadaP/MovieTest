package com.merqueo.co.infraestructura.source.local

import com.merqueo.co.models.entities.MovieEntity
import com.merqueo.co.models.ui.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface IMoviesLocalSource {

    suspend fun insertAll(data: List<MovieItemDomain>)
    suspend fun getAll(): Flow<List<MovieItemDomain>>
    suspend fun insert(movieItem: MovieItemDomain)
    suspend fun updateMovieState(id: Int, status: Boolean): Boolean
    suspend fun getCountStoreCart(): Flow<Int>
    suspend fun getAllOnStore(): Flow<List<MovieItemDomain>>
    suspend fun changeAllStore()
    suspend fun getMovieById(idMovie: Int): MovieItemDomain


}

