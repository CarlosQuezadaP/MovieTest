package com.merqueo.co.data.repository

import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.data.anticorruption.IConverter
import com.merqueo.co.data.localSource.IMoviesLocalSource
import com.merqueo.co.data.remoteSource.IMoviesRemoteSource
import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.flow.*

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
        return getFromLocal().flatMapLatest { localData: List<MovieItemDomain> ->
            if (connectivity and localData.isEmpty()) {
                getFromRemote(page)
            } else {
                flowOf(localData)
            }
        }.map {
            val response: Resource<List<MovieItemDomain>> = Resource.Success(it)
            response
        }.onStart {
            emit(Resource.Loading)
        }.catch {
            emit(Resource.Error("Network error"))
        }
    }


    fun getFromLocal() = iMoviesLocalSource.getAll()

    fun getFromRemote(page: Int) = flow {

        val dataToSave: List<MovieItemDomain> = iMoviesRemoteSource.getUpcomingMovies(page)

        dataToSave.run {
            this.map {
                converter.convertDomainToEntity(it)
            }
        }
        insertAll(dataToSave)

        emit(iMoviesLocalSource.getAllList())
    }

    override fun insert(movieItem: MovieItemDomain) {
        iMoviesLocalSource.insert(movieItem)
    }

    override fun updateMovieState(id: Int, status: Boolean): Flow<Resource<Boolean>> {
        return iMoviesLocalSource.updateMovieState(id, status)
    }

    override fun getCountStoreCart(): Flow<Int> {
        return iMoviesLocalSource.getCountStoreCart()
    }

    override fun getAllOnStore(): Flow<Resource<List<MovieItemDomain>>> {
        return iMoviesLocalSource.getAllOnStore()
    }

    override fun changeAllStore() =
        iMoviesLocalSource.changeAllStore()


    override fun getMovieById(idMovie: Int): Flow<MovieItemDomain> {
        return iMoviesLocalSource.getMovieById(idMovie)
    }
}