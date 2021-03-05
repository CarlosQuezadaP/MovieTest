package com.merqueo.co.usecases.usecases

import com.merqueo.co.domain.models.MovieItemDomain


interface IserviceDetail {

    suspend fun getMovie(id: Int): MovieItemDomain

}