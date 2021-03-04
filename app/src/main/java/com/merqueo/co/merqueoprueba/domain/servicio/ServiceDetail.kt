package com.merqueo.co.merqueoprueba.domain.servicio

import com.merqueo.co.data.source.local.IMoviesLocalSource
import com.merqueo.co.models.ui.MovieItemDomain

class ServiceDetail(private val localSource: IMoviesLocalSource) : IserviceDetail {

    override suspend fun getMovie(id: Int): MovieItemDomain {
        return localSource.getMovieById(id)
    }


}