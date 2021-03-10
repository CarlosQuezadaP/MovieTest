package com.merqueo.co.usecases.usecases

import com.merqueo.co.data.localSource.IMoviesLocalSource

interface IUpdateMovieUseCase {
    suspend fun invoke(id: Int, status: Boolean): Boolean
}

class UpdateMovieUseCase(private val localSource: IMoviesLocalSource) : IUpdateMovieUseCase {

    override suspend fun invoke(id: Int, status: Boolean): Boolean {
        val movieState = getMovieState(id)
        if (movieState == status) {
            return false
        }
        return localSource.updateMovieState(id, status)
    }

    private suspend fun getMovieState(id: Int): Boolean {
        return localSource.getMovieById(id).onStore
    }
}