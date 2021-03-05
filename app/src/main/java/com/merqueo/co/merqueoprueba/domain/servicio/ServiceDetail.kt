package com.merqueo.co.merqueoprueba.domain.servicio

import com.merqueo.co.data.source.local.IMoviesLocalSource
import com.merqueo.co.domain.models.MovieItemDomain

class ServiceDetail(private val localSource: IMoviesLocalSource) : IserviceDetail {

    override suspend fun getMovie(id: Int): com.merqueo.co.domain.models.MovieItemDomain {
        return localSource.getMovieById(id)
    }


}