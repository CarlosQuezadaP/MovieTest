package com.merqueo.co.usecases.usecases

import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.flow.Flow


interface IServiceShoppingCart {
    suspend fun getAllStore(): Flow<List<MovieItemDomain>>
}