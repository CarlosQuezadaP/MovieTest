package com.merqueo.co.merqueoprueba.domain.servicio

import com.merqueo.co.data.source.local.IMoviesLocalSource
import com.merqueo.co.data.source.remote.IMoviesRemoteSource
import com.merqueo.co.home.domain.flattenToList
import com.merqueo.co.usecases.Connectivity
import com.merqueo.co.models.ui.MovieItemDomain
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@FlowPreview
class ServiceMovie(
    private val remoteSource: IMoviesRemoteSource,
    private val localSource: IMoviesLocalSource,
    private val connectivity: Connectivity
) : IServiceMovie {

    override suspend fun getAllFromRemote(): Flow<List<MovieItemDomain>> {
        val dataToSave = remoteSource.getUpcomingMovies(1)
        saveMovies(dataToSave.flattenToList())
        return remoteSource.getUpcomingMovies(1)
    }

    override suspend fun getAllFromLocale(): Flow<List<MovieItemDomain>> {
        return localSource.getAll()
    }

    override suspend fun getMovies(): Flow<List<MovieItemDomain>> {
        if (connectivity.isConnected()) {
            return getAllFromRemote()
        } else {
            return getAllFromLocale()
        }
    }

    override suspend fun saveMovies(movieDtos: List<MovieItemDomain>) {
        localSource.insertAll(movieDtos)
    }

    override suspend fun updateMovieState(id: Int, status: Boolean): Boolean {
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