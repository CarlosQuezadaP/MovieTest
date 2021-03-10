package com.merqueo.co.data.repository

import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface IMovieRepo {
    fun insertAll(data: List<MovieItemDomain>)
    fun getAll(connectivity: Boolean, page: Int): Flow<Resource<List<MovieItemDomain>>>
    fun insert(movieItem: MovieItemDomain)
    fun updateMovieState(id: Int, status: Boolean): Boolean
    fun getCountStoreCart(): Flow<Int>
    fun getAllOnStore(): Flow<Resource<List<MovieItemDomain>>>
    fun changeAllStore()
    fun getMovieById(idMovie: Int): MovieItemDomain
}
