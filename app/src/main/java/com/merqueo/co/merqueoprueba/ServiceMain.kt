package com.merqueo.co.merqueoprueba

import com.merqueo.co.infraestructura.source.local.IMoviesLocalSource

class ServiceMain(private val localSource: IMoviesLocalSource) : IServiceMain {
    override suspend fun getCountStoreCart(): Int {
        return localSource.getCountStoreCart()
    }

}