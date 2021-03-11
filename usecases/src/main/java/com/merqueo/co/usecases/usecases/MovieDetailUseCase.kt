package com.merqueo.co.usecases.usecases

import com.merqueo.co.data.localSource.IMoviesLocalSource
import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface IMovieDetailUseCase {
    fun invoke(id: Int): Flow<MovieItemDomain>
}

class MovieDetailUseCase(private val localSource: IMoviesLocalSource) : IMovieDetailUseCase {
    override fun invoke(id: Int): Flow<MovieItemDomain> {
        return localSource.getMovieById(id)
    }
}