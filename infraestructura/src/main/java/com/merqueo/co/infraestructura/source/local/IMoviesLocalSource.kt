package com.merqueo.co.infraestructura.source.local

import com.merqueo.co.models.entities.MovieEntity

interface IMoviesLocalSource  {

    suspend fun insertAll(data: List<MovieEntity>)
    suspend fun getAll(data: List<MovieEntity>)
    suspend fun insert(data: MovieEntity)


}

