package com.merqueo.co.infraestructura.source.local

import com.merqueo.co.provide.db.dao.IMoviesDao
import com.merqueo.co.models.entities.MovieEntity

class MoviesLocalSource (
    private val moviesDao: IMoviesDao
) : IMoviesLocalSource {

    override suspend fun insertAll(data: List<MovieEntity>) {
        moviesDao.insertAll(data)
    }

    override suspend fun getAll(data: List<MovieEntity>) {
    }

    override suspend fun insert(data: MovieEntity) {
        moviesDao.insert(data)
    }
}