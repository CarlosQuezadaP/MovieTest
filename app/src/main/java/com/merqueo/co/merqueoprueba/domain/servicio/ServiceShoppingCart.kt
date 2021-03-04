package com.merqueo.co.merqueoprueba.domain.servicio

import com.merqueo.co.infraestructura.source.local.IMoviesLocalSource
import com.merqueo.co.models.ui.MovieItemDomain
import kotlinx.coroutines.flow.Flow

class ServiceShoppingCart(private val localSource: IMoviesLocalSource) : IServiceShoppingCart {
    override suspend fun getAllStore(): Flow<List<MovieItemDomain>> {
        return localSource.getAllOnStore()
    }
}