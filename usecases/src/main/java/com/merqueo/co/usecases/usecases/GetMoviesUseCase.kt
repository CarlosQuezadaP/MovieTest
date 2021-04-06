package com.merqueo.co.usecases.usecases

import com.merqueo.co.CORE.IConnectivity
import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.data.repository.IMovieRepo
import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.flow.Flow


class GetMoviesUseCase(
    private val iMoviesRepo: IMovieRepo,
    private val iConnectivity: IConnectivity
) : IGetMoviesUseCase {

    override fun invoke(page: Int): Flow<Resource<List<MovieItemDomain>>> {
        return iMoviesRepo.getAll(iConnectivity.isConnected(), page)
    }

}

interface IGetMoviesUseCase {
    fun invoke(page: Int): Flow<Resource<List<MovieItemDomain>>>
}
