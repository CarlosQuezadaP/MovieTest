package com.merqueo.co.infraestructura.source.local

import androidx.paging.DataSource
import com.merqueo.co.core.base.ILocalDataSource
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.models.entities.MovieEntity

interface IMoviesLocalSource : ILocalDataSource {

    suspend fun getMoviesByGenreDataSourceFactory(genreId: Int): DataSource.Factory<Int, MovieEntity>
    suspend fun getPopularMoviesDataSourceFactory(): DataSource.Factory<Int, MovieEntity>

    suspend fun getPopularMoviesCount(): Int

    suspend fun getCount(genreId: Int): Int

    suspend fun getById(movieId: Int): SuperResult<MovieEntity>

    suspend fun insertAll(data: List<MovieEntity>)

    suspend fun insert(data: MovieEntity)


}