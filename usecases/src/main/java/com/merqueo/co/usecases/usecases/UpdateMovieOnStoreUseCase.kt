package com.merqueo.co.usecases.usecases

import com.merqueo.co.data.localSource.IMoviesLocalSource

interface IUpdateMovieUseCase {
    fun invoke(id: Int, status: Boolean): Boolean
}

class UpdateMovieUseCase(private val localSource: IMoviesLocalSource) : IUpdateMovieUseCase {

    override fun invoke(id: Int, status: Boolean): Boolean {
        val movieState = getMovieState(id)
        if (movieState == status) {
            return false
        }
        return localSource.updateMovieState(id, status)
    }

    private fun getMovieState(id: Int): Boolean {
        return localSource.getMovieById(id).onStore
    }
}