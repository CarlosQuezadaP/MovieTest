package com.merqueo.co.home.domain.service

import com.merqueo.co.models.ui.MovieItemDomain

interface IserviceDetail {

    suspend fun getMovie(id: Int): MovieItemDomain

}