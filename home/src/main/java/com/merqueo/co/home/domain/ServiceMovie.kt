package com.merqueo.co.home.domain

import com.merqueo.co.infraestructura.source.remote.AppResult
import com.merqueo.co.infraestructura.source.remote.IMoviesRemoteSource
import com.merqueo.co.models.dto.upcoming.UpcomingResponse

class ServiceMovie(
    private val remoteSource: IMoviesRemoteSource
) : IServiceMovie {

    override suspend fun getAllCountries(): AppResult<UpcomingResponse> {
        return remoteSource.getUpcomingMovies(1)
    }


}