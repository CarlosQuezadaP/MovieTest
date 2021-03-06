package com.merqueo.co.usecases.usecases

import com.merqueo.co.data.localSource.IMoviesLocalSource
import com.merqueo.co.data.remoteSource.IMoviesRemoteSource
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.home.domain.flattenToList
import com.merqueo.co.usecases.Connectivity
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow


@FlowPreview
class GetMoviesUseCase(
    private val remoteSource: IMoviesRemoteSource,
    private val localSource: IMoviesLocalSource,
    private val connectivity: Connectivity
) : IGetMoviesUseCase {

    suspend fun getAllFromRemote(): Flow<List<MovieItemDomain>> {
        val dataToSave = remoteSource.getUpcomingMovies(1)
        insertMovies(dataToSave.flattenToList())
        return remoteSource.getUpcomingMovies(1)
    }

    suspend fun getAllFromLocale(): Flow<List<MovieItemDomain>> {
        return localSource.getAll()
    }

    override suspend fun invoke(): Flow<List<MovieItemDomain>> {
        if (connectivity.isConnected()) {
            return getAllFromRemote()
        } else {
            return getAllFromLocale()
        }
    }

    suspend fun insertMovies(movieDtos: List<MovieItemDomain>) {
        localSource.insertAll(movieDtos)
    }

}

interface IGetMoviesUseCase {
    suspend fun invoke(): Flow<List<MovieItemDomain>>
}
