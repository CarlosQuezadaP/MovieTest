package com.merqueo.co.infraestructura.repository.local

import androidx.paging.DataSource
import com.merqueo.co.core.base.ILocalDataSource
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.provide.db.entities.MovieEntity

interface IMoviesLocalRepository : ILocalDataSource {

    suspend fun getMoviesByGenreDataSourceFactory(genreId: Int): DataSource.Factory<Int, MovieEntity>
    suspend fun getPopularMoviesDataSourceFactory(): DataSource.Factory<Int, MovieEntity>

    suspend fun getPopularMoviesCount(): Int

    suspend fun getCount(genreId: Int): Int

    suspend fun getById(movieId: Int): SuperResult<MovieEntity>

    suspend fun insertAll(data: List<MovieEntity>)

    suspend fun insert(data: MovieEntity)


}