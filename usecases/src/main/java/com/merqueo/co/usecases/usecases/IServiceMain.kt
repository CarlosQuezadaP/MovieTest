package com.merqueo.co.usecases.usecases

import kotlinx.coroutines.flow.Flow

interface IServiceMain {
    suspend fun getCountStoreCart(): Flow<Int>
    suspend fun deleteAll()
}
