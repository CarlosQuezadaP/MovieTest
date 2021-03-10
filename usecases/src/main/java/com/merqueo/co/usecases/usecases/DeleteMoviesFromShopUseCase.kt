package com.merqueo.co.usecases.usecases

import com.merqueo.co.data.localSource.IMoviesLocalSource
import kotlinx.coroutines.flow.Flow

interface IDeleteMoviesFromShopUseCase {
     fun countMovies(): Flow<Int>
     fun invoke()
}


class DeleteMoviesFromShopUseCase(private val localSource: IMoviesLocalSource) :
    IDeleteMoviesFromShopUseCase {

    override  fun countMovies(): Flow<Int> {
        return localSource.getCountStoreCart()
    }

    override  fun invoke() {
        localSource.changeAllStore()
    }

}