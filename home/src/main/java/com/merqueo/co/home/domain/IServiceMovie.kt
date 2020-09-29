package com.merqueo.co.home.domain

import androidx.lifecycle.LiveData
import com.merqueo.co.infraestructura.source.remote.response.AppResult
import com.merqueo.co.models.dto.upcoming.UpcomingResponse
import com.merqueo.co.models.ui.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface IServiceMovie {
    suspend fun getAllFromRemote(): AppResult<UpcomingResponse>
    suspend fun getAllFromLocale(): Flow<List<MovieItemDomain>>
    suspend fun saveMovies(movieDtos: List<MovieItemDomain>)
    suspend fun updateMovieState(movieItemDomain: MovieItemDomain): Boolean
}
