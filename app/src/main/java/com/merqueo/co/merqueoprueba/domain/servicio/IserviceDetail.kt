package com.merqueo.co.merqueoprueba.domain.servicio

import com.merqueo.co.domain.models.MovieItemDomain

interface IserviceDetail {

    suspend fun getMovie(id: Int): com.merqueo.co.domain.models.MovieItemDomain

}