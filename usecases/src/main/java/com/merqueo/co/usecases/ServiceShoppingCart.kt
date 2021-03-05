package com.merqueo.co.usecases



import com.merqueo.co.data.source.local.IMoviesLocalSource
import kotlinx.coroutines.flow.Flow

class ServiceShoppingCart(private val localSource: IMoviesLocalSource) : IServiceShoppingCart {
    override suspend fun getAllStore(): Flow<List<com.merqueo.co.domain.models.MovieItemDomain>> {
        return localSource.getAllOnStore()
    }
}