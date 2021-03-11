package com.merqueo.co.infraestructura.network.api

import com.merqueo.co.data.responses.upcoming.UpcomingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IMovieApi {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int = 1): Response<UpcomingResponse>
}