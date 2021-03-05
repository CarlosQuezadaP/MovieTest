package com.merqueo.co.usecases.usecases

import com.merqueo.co.data.localSource.IMoviesLocalSource


class ServiceDetail(private val localSource: IMoviesLocalSource) : IserviceDetail {

    override suspend fun getMovie(id: Int): com.merqueo.co.domain.models.MovieItemDomain {
        return localSource.getMovieById(id)
    }


}