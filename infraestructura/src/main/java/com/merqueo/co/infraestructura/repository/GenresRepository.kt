package com.rasalexman.data.repository

import com.merqueo.co.core.common.extensions.applyIfSuccessSuspend
import com.merqueo.co.core.common.extensions.mapListTo
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.infraestructura.source.local.IGenresLocalSource
import com.merqueo.co.infraestructura.source.remote.IGenresRemoteSource
import com.merqueo.co.models.entities.GenreEntity


class GenresRepository(
    override val localDataSource: IGenresLocalSource,
    override val remoteDataSource: IGenresRemoteSource
) : IGenresRepository {

    override suspend fun getLocalGenresList(): SuperResult<List<GenreEntity>> {
        return localDataSource.getGenresList()
    }

    override suspend fun getRemoteGenresList(): SuperResult<List<GenreEntity>> {
        return remoteDataSource.getRemoteGenresList()
            .mapListTo()
            .applyIfSuccessSuspend(remoteDataSource::getGenresImages)
    }

    override suspend fun saveGenresList(genresList: List<GenreEntity>) {
        localDataSource.saveGenresList(genresList)
    }
}