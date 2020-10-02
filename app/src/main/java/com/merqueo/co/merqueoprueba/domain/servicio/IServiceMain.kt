package com.merqueo.co.merqueoprueba.domain.servicio

import kotlinx.coroutines.flow.Flow

interface IServiceMain {
    suspend fun getCountStoreCart(): Flow<Int>
    suspend fun deleteAll()
}
