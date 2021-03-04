package com.merqueo.co.merqueoprueba.domain.servicio

import com.merqueo.co.models.ui.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface IServiceShoppingCart {
    suspend fun getAllStore(): Flow<List<MovieItemDomain>>
}