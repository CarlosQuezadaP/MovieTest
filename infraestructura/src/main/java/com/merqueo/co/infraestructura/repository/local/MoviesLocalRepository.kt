package com.merqueo.co.infraestructura.repository.local

import androidx.paging.DataSource
import com.merqueo.co.core.common.extensions.emptyResult
import com.merqueo.co.core.common.extensions.successResult
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.provide.db.dao.IMoviesDao
import com.merqueo.co.provide.db.entities.MovieEntity

class MoviesLocalRepository(
    private val moviesDao: IMoviesDao
) : IMoviesLocalRepository {

    override suspend fun getMoviesByGenreDataSourceFactory(genreId: Int): DataSource.Factory<Int, MovieEntity> =
        moviesDao.getAllByGenreId(genreId)

    override suspend fun getPopularMoviesDataSourceFactory(): DataSource.Factory<Int, MovieEntity> {
        return moviesDao.getAllByPopular()
    }

    override suspend fun getById(movieId: Int): SuperResult<MovieEntity> =
        moviesDao.getById(movieId)?.let { localMovie ->
            if (localMovie.hasDetails) successResult(localMovie)
            else emptyResult()
        } ?: emptyResult()

    override suspend fun getPopularMoviesCount(): Int {
        return moviesDao.getPopularCount()
    }

    override suspend fun getCount(genreId: Int): Int {
        return moviesDao.getCount(genreId)
    }

    override suspend fun insertAll(data: List<MovieEntity>) {
        moviesDao.insertAll(data)
    }

    override suspend fun insert(data: MovieEntity) {
        moviesDao.insert(data)
    }
}