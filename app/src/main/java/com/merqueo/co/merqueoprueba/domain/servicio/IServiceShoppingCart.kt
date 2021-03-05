package com.merqueo.co.merqueoprueba.domain.servicio

import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface IServiceShoppingCart {
    suspend fun getAllStore(): Flow<List<com.merqueo.co.domain.models.MovieItemDomain>>
}