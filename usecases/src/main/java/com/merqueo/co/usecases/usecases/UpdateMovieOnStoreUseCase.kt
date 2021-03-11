package com.merqueo.co.usecases.usecases

import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.data.localSource.IMoviesLocalSource
import kotlinx.coroutines.flow.Flow

interface IUpdateMovieUseCase {
    fun invoke(id: Int, status: Boolean): Flow<Resource<Boolean>>
}

class UpdateMovieUseCase(private val localSource: IMoviesLocalSource) : IUpdateMovieUseCase {

    override fun invoke(id: Int, status: Boolean): Flow<Resource<Boolean>> {
        return localSource.updateMovieState(id, status)
    }
}