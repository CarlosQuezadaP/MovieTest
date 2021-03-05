package com.merqueo.co.merqueoprueba.domain.servicio

import com.merqueo.co.data.source.local.IMoviesLocalSource
import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.flow.Flow

class ServiceShoppingCart(private val localSource: IMoviesLocalSource) : IServiceShoppingCart {
    override suspend fun getAllStore(): Flow<List<com.merqueo.co.domain.models.MovieItemDomain>> {
        return localSource.getAllOnStore()
    }
}