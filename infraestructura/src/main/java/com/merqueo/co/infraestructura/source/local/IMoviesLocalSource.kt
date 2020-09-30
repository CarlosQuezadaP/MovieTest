package com.merqueo.co.infraestructura.source.local

import androidx.lifecycle.LiveData
import com.merqueo.co.models.ui.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface IMoviesLocalSource {

    suspend fun insertAll(data: List<MovieItemDomain>)
    suspend fun getAll(): Flow<List<MovieItemDomain>>
    suspend fun insert(data: MovieItemDomain)
    suspend fun updateMovieState(movie: MovieItemDomain): Boolean
    suspend fun getCountStoreCart():Int


}

