package com.merqueo.co.home.usesCases.movies

import com.merqueo.co.infraestructura.source.remote.IMoviesRemoteSource

internal class GetTopRatedMoviesUseCase(
    private val moviesSource: IMoviesRemoteSource
)

