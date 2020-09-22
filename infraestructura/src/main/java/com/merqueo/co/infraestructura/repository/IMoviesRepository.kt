package com.rasalexman.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.merqueo.co.core.base.IBaseRepository
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.core.typealiases.AnyResultMutableLiveData
import com.merqueo.co.core.typealiases.ResultList
import com.merqueo.co.infraestructura.source.local.IMoviesLocalSource
import com.merqueo.co.infraestructura.source.remote.IMoviesRemoteSource
import com.merqueo.co.models.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository : IBaseRepository<IMoviesLocalSource, IMoviesRemoteSource> {

    suspend fun getPopularMoviesDataSource(): DataSource.Factory<Int, MovieEntity>
    suspend fun getPopularMoviesCount(): Int

    suspend fun getMoviesByGenreDataSource(genreId: Int): DataSource.Factory<Int, MovieEntity>

    suspend fun saveMoviesList(data: List<MovieEntity>)

    suspend fun saveMovie(data: MovieEntity)

    suspend fun getMovieById(movieId: Int): SuperResult<MovieEntity>

    suspend fun getRemoteSearchDataSource(
        query: String,
        resultLiveData: AnyResultMutableLiveData
    ): DataSource.Factory<Int, MovieEntity>


    suspend fun getRemoteMovies(genreId: Int, lastReleaseDate: Long?): ResultList<MovieEntity>
    suspend fun getRemotePopularMovies(page: Int): ResultList<MovieEntity>
    suspend fun getRemoteUpcomingMovies(page: Int): ResultList<MovieEntity>
    suspend fun getRemoteTopRatedMovies(pageLiveData: LiveData<Int>): Flow<ResultList<MovieEntity>>
}