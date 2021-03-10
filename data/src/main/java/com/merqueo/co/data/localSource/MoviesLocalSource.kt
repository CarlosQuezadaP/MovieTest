package com.merqueo.co.data.localSource

import com.merqueo.co.CORE.model.Resource
import com.merqueo.co.data.anticorruption.IConverter
import com.merqueo.co.data.db.dao.IMoviesDao
import com.merqueo.co.data.db.entities.MovieEntity
import com.merqueo.co.domain.models.MovieItemDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

@ExperimentalCoroutinesApi
class MoviesLocalSource(
    private val moviesDao: IMoviesDao,
    private val converter: IConverter,
) : IMoviesLocalSource {

    override fun insertAll(data: List<MovieItemDomain>) {
        val dataa = data.map {
            converter.convertDomainToEntity(it)
        }
        moviesDao.insertAll(dataa)
    }

    override fun getAll(): Flow<Resource<List<MovieItemDomain>>> {
        val movies: Flow<Resource<List<MovieItemDomain>>> =
            moviesDao.getMovieDistinctUntilChanged().map {
                it.map {
                    it.convertTo()
                }
            }.map {
                Resource.Success(it)
            }
        return movies
    }

    override fun changeAllStore() {
        moviesDao.updateAll(getAllStore())
    }

    private fun getAllStore(): List<MovieEntity> {
        val movies = moviesDao.getAllByStore()
        movies.map {
            it.onStore = false
        }
        return movies
    }

    override fun insert(movieItem: MovieItemDomain) {
        moviesDao.insert(converter.convertDomainToEntity(movieItem))
    }

    override fun updateMovieState(id: Int, status: Boolean): Boolean {
        var mov = getMoviEntityByID(id)
        mov = changeMovieState(mov, status)
        val movieReturn = (moviesDao.update(mov.onStore, mov.id) != 0)
        return movieReturn
    }

    override fun getCountStoreCart(): Flow<Int> {
        return moviesDao.getOnStoreCount()
    }

    override fun getAllOnStore(): Flow<Resource<List<MovieItemDomain>>> {

        val reso = moviesDao.getAllByStore2().map { it.map { it.convertTo() } }.map {
            val response: Resource<List<MovieItemDomain>> = Resource.Success(it)
            response
        }
        reso.catch {
            emit(Resource.Error("Error"))
        }.onStart {
            emit(Resource.Loading)
        }

        return reso
    }

    override fun getMovieById(idMovie: Int): MovieItemDomain {
        return moviesDao.getById(idMovie).convertTo()
    }


    private fun changeMovieState(movie: MovieEntity, status: Boolean): MovieEntity {
        movie.onStore = status
        return movie
    }

    private fun getMoviEntityByID(idMovie: Int): MovieEntity {
        return moviesDao.getById(idMovie)
    }


}