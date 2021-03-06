package com.merqueo.co.usecases.usecases

import com.merqueo.co.data.localSource.IMoviesLocalSource
import kotlinx.coroutines.flow.Flow

interface IDeleteMoviesFromShopUseCase {
    suspend fun countMovies(): Flow<Int>
    suspend fun invoke()
}


class DeleteMoviesFromShopUseCase(private val localSource: IMoviesLocalSource) :
    IDeleteMoviesFromShopUseCase {

    override suspend fun countMovies(): Flow<Int> {
        return localSource.getCountStoreCart()
    }

    override suspend fun invoke() {
        localSource.changeAllStore()
    }

}