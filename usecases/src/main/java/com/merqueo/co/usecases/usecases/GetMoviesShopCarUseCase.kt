package com.merqueo.co.usecases.usecases

import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.data.localSource.IMoviesLocalSource
import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface IGetMoviesShopCarUseCase {
    fun invoke(): Flow<Resource<List<MovieItemDomain>>>
}

class GetMoviesShopCarUseCase(private val localSource: IMoviesLocalSource) :
    IGetMoviesShopCarUseCase {
    override  fun invoke():Flow<Resource<List<MovieItemDomain>>> {
        return localSource.getAllOnStore()
    }
}