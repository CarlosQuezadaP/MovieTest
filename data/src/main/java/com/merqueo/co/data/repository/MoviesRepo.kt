package com.merqueo.co.data.repository

import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.data.anticorruption.IConverter
import com.merqueo.co.data.localSource.IMoviesLocalSource
import com.merqueo.co.data.remoteSource.IMoviesRemoteSource
import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@FlowPreview
class MoviesRepo(
    private val iMoviesLocalSource: IMoviesLocalSource,
    private val iMoviesRemoteSource: IMoviesRemoteSource,
    private val converter: IConverter
) : IMovieRepo {

    override fun insertAll(data: List<MovieItemDomain>) {
        iMoviesLocalSource.insertAll(data)
    }

    override fun getAll(
        connectivity: Boolean,
        page: Int
    ): Flow<Resource<List<MovieItemDomain>>> {
        if (connectivity) getFromRemote(page)
        return iMoviesLocalSource.getAll()
    }

    fun getFromRemote(page: Int) {

        GlobalScope.launch {
            val dataToSave = iMoviesRemoteSource.getUpcomingMovies(page)

            dataToSave.run {
                this.map {
                    converter.convertDomainToEntity(it)
                }
            }

            insertAll(dataToSave)
        }


    }

    override fun insert(movieItem: MovieItemDomain) {
        iMoviesLocalSource.insert(movieItem)
    }

    override fun updateMovieState(id: Int, status: Boolean): Boolean {
        return iMoviesLocalSource.updateMovieState(id, status)
    }

    override fun getCountStoreCart(): Flow<Int> {
        return iMoviesLocalSource.getCountStoreCart()
    }

    override fun getAllOnStore(): Flow<Resource<List<MovieItemDomain>>> {
        return iMoviesLocalSource.getAllOnStore()
    }

    override fun changeAllStore() {
        iMoviesLocalSource.changeAllStore()
    }

    override fun getMovieById(idMovie: Int): MovieItemDomain {
        return iMoviesLocalSource.getMovieById(idMovie)
    }
}