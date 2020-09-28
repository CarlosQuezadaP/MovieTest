package com.merqueo.co.home.domain

import com.merqueo.co.infraestructura.source.local.IMoviesLocalSource
import com.merqueo.co.infraestructura.source.local.MoviesLocalSource
import com.merqueo.co.infraestructura.source.remote.AppResult
import com.merqueo.co.infraestructura.source.remote.IMoviesRemoteSource
import com.merqueo.co.models.dto.upcoming.UpcomingResponse

class ServiceMovie(
    private val remoteSource: IMoviesRemoteSource, private val localSource: IMoviesLocalSource
) : IServiceMovie {

    override suspend fun getAllMovies(): AppResult<UpcomingResponse> {
        return remoteSource.getUpcomingMovies(1)
    }


}