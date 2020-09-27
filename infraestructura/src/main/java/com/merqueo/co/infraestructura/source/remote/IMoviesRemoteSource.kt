package com.merqueo.co.infraestructura.source.remote

import com.merqueo.co.models.dto.upcoming.UpcomingResponse

interface IMoviesRemoteSource {

    suspend fun getUpcomingMovies(page: Int): AppResult<UpcomingResponse>
}