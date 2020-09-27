package com.merqueo.co.infraestructura.source.remote

import com.merqueo.co.infraestructura.network.api.IMovieApi
import com.merqueo.co.infraestructura.source.remote.Utils.handleApiError
import com.merqueo.co.infraestructura.source.remote.Utils.handleSuccess
import com.merqueo.co.models.dto.upcoming.UpcomingResponse

class MoviesRemoteSource(
    private val moviesApi: IMovieApi
) : IMoviesRemoteSource {

    override suspend fun getUpcomingMovies(page: Int): AppResult<UpcomingResponse> {
        return try {
            val response = moviesApi.getUpcomingMovies(page)
            if (response.isSuccessful) {
                handleSuccess(response)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }
}