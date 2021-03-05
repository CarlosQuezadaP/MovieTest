package com.merqueo.co.usecases.usecases



import com.merqueo.co.data.localSource.IMoviesLocalSource
import kotlinx.coroutines.flow.Flow

class ServiceShoppingCart(private val localSource: IMoviesLocalSource) : IServiceShoppingCart {
    override suspend fun getAllStore(): Flow<List<com.merqueo.co.domain.models.MovieItemDomain>> {
        return localSource.getAllOnStore()
    }
}