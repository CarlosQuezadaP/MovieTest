package com.merqueo.co.home.domain

import androidx.lifecycle.LiveData
import com.merqueo.co.core.Connectivity
import com.merqueo.co.core.ConnectivityException
import com.merqueo.co.infraestructura.source.local.IMoviesLocalSource
import com.merqueo.co.infraestructura.source.remote.IMoviesRemoteSource
import com.merqueo.co.infraestructura.source.remote.response.AppResult
import com.merqueo.co.models.dto.upcoming.UpcomingResponse
import com.merqueo.co.models.ui.MovieItemDomain
import kotlinx.coroutines.flow.Flow

class ServiceMovie(
    private val remoteSource: IMoviesRemoteSource,
    private val localSource: IMoviesLocalSource,
    private val connectivity: Connectivity
) : IServiceMovie {

    override suspend fun getAllFromRemote(): AppResult<UpcomingResponse> {
        if (connectivity.isConnected) {
            return remoteSource.getUpcomingMovies(1)
        } else {
            throw ConnectivityException()
        }

    }

    override suspend fun getAllFromLocale(): Flow<List<MovieItemDomain>> {
        return localSource.getAll()
    }

    override suspend fun saveMovies(movieDtos: List<MovieItemDomain>) {
        localSource.insertAll(movieDtos)
    }

    override suspend fun updateMovieState(movieItemDomain: MovieItemDomain):Boolean {
        return localSource.updateMovieState(movieItemDomain)
    }


}