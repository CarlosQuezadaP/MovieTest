package com.merqueo.co.merqueoprueba.domain.servicio

import com.merqueo.co.models.ui.MovieItemDomain

interface IserviceDetail {

    suspend fun getMovie(id: Int): MovieItemDomain

}