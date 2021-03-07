package com.merqueo.co.usecases.usecases

import com.merqueo.co.data.repository.IMovieRepo
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.CORE.IConnectivity
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow


@FlowPreview
class GetMoviesUseCase(
    private val iMoviesRepo: IMovieRepo,
    private val iConnectivity: IConnectivity
) : IGetMoviesUseCase {

    override suspend fun invoke(page: Int): Flow<List<MovieItemDomain>> {
        return iMoviesRepo.getAll(iConnectivity.isConnected(), page)
    }

}

interface IGetMoviesUseCase {
    suspend fun invoke(page: Int): Flow<List<MovieItemDomain>>
}
