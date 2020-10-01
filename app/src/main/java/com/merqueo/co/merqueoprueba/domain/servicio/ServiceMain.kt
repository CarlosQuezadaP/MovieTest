package com.merqueo.co.merqueoprueba.domain.servicio

import com.merqueo.co.infraestructura.source.local.IMoviesLocalSource
import kotlinx.coroutines.flow.Flow

class ServiceMain(private val localSource: IMoviesLocalSource) : IServiceMain {

    override suspend fun getCountStoreCart(): Flow<Int> {
        return localSource.getCountStoreCart()
    }

}