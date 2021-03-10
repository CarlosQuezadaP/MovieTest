package com.merqueo.co.usecases.usecases

import com.merqueo.co.data.localSource.IMoviesLocalSource
import com.merqueo.co.domain.models.MovieItemDomain

interface IMovieDetailUseCase {
     fun invoke(id: Int): MovieItemDomain
}

class MovieDetailUseCase(private val localSource: IMoviesLocalSource) : IMovieDetailUseCase {
    override  fun invoke(id: Int): MovieItemDomain {
        return localSource.getMovieById(id)
    }
}