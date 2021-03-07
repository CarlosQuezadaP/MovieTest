package com.merqueo.co.usecases.usecases

import com.merqueo.co.data.localSource.IMoviesLocalSource
import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface IGetMoviesShopCarUseCase {
    suspend fun invoke(): Flow<List<MovieItemDomain>>
}

class GetMoviesShopCarUseCase(private val localSource: IMoviesLocalSource) :
    IGetMoviesShopCarUseCase {
    override suspend fun invoke(): Flow<List<MovieItemDomain>> {
        return localSource.getAllOnStore()
    }
}