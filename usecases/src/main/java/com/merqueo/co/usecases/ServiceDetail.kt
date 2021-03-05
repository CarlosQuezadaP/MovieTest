package com.merqueo.co.usecases

import com.merqueo.co.data.source.local.IMoviesLocalSource


class ServiceDetail(private val localSource: IMoviesLocalSource) : IserviceDetail {

    override suspend fun getMovie(id: Int): com.merqueo.co.domain.models.MovieItemDomain {
        return localSource.getMovieById(id)
    }


}