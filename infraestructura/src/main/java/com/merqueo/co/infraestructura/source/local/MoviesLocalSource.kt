package com.merqueo.co.infraestructura.source.local

import com.merqueo.co.models.entities.MovieEntity
import com.merqueo.co.models.ui.MovieItemDomain
import com.merqueo.co.provide.db.dao.IMoviesDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesLocalSource(
    private val moviesDao: IMoviesDao
) : IMoviesLocalSource {

    override suspend fun insertAll(data: List<MovieItemDomain>) {
        val dataa = data.map {
            it.convertTo()
        }
        moviesDao.insertAll(dataa)
    }

    @ExperimentalCoroutinesApi
    override suspend fun getAll(): Flow<List<MovieItemDomain>> {
        val movies = moviesDao.getMovieDistinctUntilChanged().map {
            it.map {
                it.convertTo()
            }
        }
        return movies
    }

    override suspend fun insert(data: MovieItemDomain) {
        moviesDao.insert(data.convertTo())
    }

    override suspend fun updateMovieState(movie: MovieItemDomain): Boolean {
        return (moviesDao.update(getMovie(movie)) != 0)
    }

    override suspend fun getCountStoreCart(): Int {
        return moviesDao.getOnStoreCount()
    }

    private fun getMovie(movie: MovieItemDomain): MovieEntity {
        val movie = moviesDao.getById(movie.id)
        movie.onStore = movie.onStore
        return movie
    }

    private fun onstore(movie: MovieItemDomain): Int {
        return moviesDao.getOnStoreCount()
    }


}