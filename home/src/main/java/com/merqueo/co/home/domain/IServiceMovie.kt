package com.merqueo.co.home.domain

import com.merqueo.co.infraestructura.source.remote.AppResult
import com.merqueo.co.models.dto.upcoming.UpcomingResponse


interface IServiceMovie {
    suspend fun getAllMovies(): AppResult<UpcomingResponse>
}
