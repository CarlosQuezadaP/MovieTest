package com.merqueo.co.data.localSource

import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface IMoviesLocalSource {
    fun insertAll(data: List<MovieItemDomain>)
    fun getAll(): Flow<Resource<List<MovieItemDomain>>>
    fun insert(movieItem: MovieItemDomain)
    fun updateMovieState(id: Int, status: Boolean): Boolean
    fun getCountStoreCart(): Flow<Int>
    fun getAllOnStore(): Flow<Resource<List<MovieItemDomain>>>
    fun changeAllStore() :Flow<Resource<Boolean>>
    fun getMovieById(idMovie: Int): MovieItemDomain
}

