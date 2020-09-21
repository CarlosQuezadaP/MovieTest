package com.merqueo.co.infraestructura.repository.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.merqueo.co.core.base.IRemoteDataSource
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.core.typealiases.FlowResultList
import com.merqueo.co.core.typealiases.ResultList
import com.merqueo.co.models.dto.MovieDto

interface IMoviesRemoteRepository : IRemoteDataSource {
    suspend fun getSearchDataSource(
        query: String,
        resultLiveData: MutableLiveData<SuperResult<Any>>
    ): DataSource.Factory<Int, MovieDto>
    suspend fun getByGenreId(genreId: Int, lastReleaseDate: Long?): ResultList<MovieDto>
    suspend fun getMovieDetails(movieId: Int): SuperResult<MovieDto>
    suspend fun getNewMoviesByGenreId(genreId: Int): ResultList<MovieDto>
    suspend fun getPopularMovies(page: Int): ResultList<MovieDto>
    suspend fun getTopRatedMovies(pageLiveData: LiveData<Int>): FlowResultList<MovieDto>
    suspend fun getUpcomingMovies(page: Int): ResultList<MovieDto>
}