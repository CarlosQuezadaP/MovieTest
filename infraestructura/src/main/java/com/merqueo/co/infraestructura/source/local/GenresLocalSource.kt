package com.merqueo.co.infraestructura.source.local

import com.merqueo.co.core.common.extensions.toSuccessResult
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.provide.db.dao.IGenresDao
import com.merqueo.co.models.entities.GenreEntity


class GenresLocalSource(
    private val genresDao: IGenresDao
) : IGenresLocalSource {

    override suspend fun getGenresList(): SuperResult<List<GenreEntity>> {
        return genresDao.getAll().toSuccessResult()
    }

    override suspend fun saveGenresList(data: List<GenreEntity>) {
        genresDao.insertAll(data)
    }
}