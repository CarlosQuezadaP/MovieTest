package com.merqueo.co.merqueoprueba.domain.servicio

import androidx.lifecycle.asLiveData
import com.merqueo.co.infraestructura.source.local.IMoviesLocalSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ServiceMain(private val localSource: IMoviesLocalSource) : IServiceMain {

    override suspend fun getCountStoreCart(): Flow<Int> {
        return localSource.getCountStoreCart()
    }

    override suspend fun deleteAll() {
        localSource.changeAllStore()
    }

}