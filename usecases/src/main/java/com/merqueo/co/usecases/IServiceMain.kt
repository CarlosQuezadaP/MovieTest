package com.merqueo.co.usecases

import kotlinx.coroutines.flow.Flow

interface IServiceMain {
    suspend fun getCountStoreCart(): Flow<Int>
    suspend fun deleteAll()
}
