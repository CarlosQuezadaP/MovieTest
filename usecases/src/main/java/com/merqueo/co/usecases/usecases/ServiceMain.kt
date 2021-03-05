package com.merqueo.co.usecases.usecases

import com.merqueo.co.data.localSource.IMoviesLocalSource
import kotlinx.coroutines.flow.Flow


class ServiceMain(private val localSource: IMoviesLocalSource) : IServiceMain {

    override suspend fun getCountStoreCart(): Flow<Int> {
        return localSource.getCountStoreCart()
    }

    override suspend fun deleteAll() {
        localSource.changeAllStore()
    }

}