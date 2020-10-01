package com.merqueo.co.infraestructura.source.local

import com.merqueo.co.models.entities.MovieEntity
import com.merqueo.co.models.ui.MovieItemDomain
import com.merqueo.co.provide.db.dao.IMoviesDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalCoroutinesApi
class MoviesLocalSource(
    private val moviesDao: IMoviesDao
) : IMoviesLocalSource {

    override suspend fun insertAll(data: List<MovieItemDomain>) {
        val dataa = data.map {
            it.convertTo()
        }
        moviesDao.insertAll(dataa)
    }

    override suspend fun getAll(): Flow<List<MovieItemDomain>> {
        val movies = moviesDao.getMovieDistinctUntilChanged().map {
            it.map {
                it.convertTo()
            }
        }
        return movies
    }

    override suspend fun getAllOnStore(): Flow<List<MovieItemDomain>> {
        val movies = moviesDao.getMovieStoreDistinctUntilChanged().map {
            it.map {
                it.convertTo()
            }
        }
        return movies
    }

    override suspend fun changeAllStore() {
        moviesDao.getMovieStoreDistinctUntilChanged().map {
            it.forEach {
                moviesDao.updateToFailStore(false, it.id)
            }
        }
    }


    override suspend fun insert(movieItem: MovieItemDomain) {
        moviesDao.insert(movieItem.convertTo())
    }

    override suspend fun updateMovieState(id: Int, status: Boolean): Boolean {
        var mov = getMoviEntityByID(id)
        mov = changeMovieState(mov, status)
        val movieReturn = (moviesDao.update(mov.onStore, mov.id) != 0)
        return movieReturn
    }

    override suspend fun getCountStoreCart(): Flow<Int> {
        return moviesDao.getOnStoreCount()
    }

    override suspend fun getMovieById(idMovie: Int): MovieItemDomain {
        return moviesDao.getById(idMovie).convertTo()
    }


    private fun changeMovieState(movie: MovieEntity, status: Boolean): MovieEntity {
        movie.onStore = status
        return movie
    }

    private fun getMoviEntityByID(idMovie: Int): MovieEntity {
        return moviesDao.getById(idMovie)
    }


}