package com.merqueo.co.infraestructura.repository.local

import com.merqueo.co.core.common.extensions.toSuccessResult
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.provide.db.dao.IGenresDao
import com.merqueo.co.provide.db.entities.GenreEntity


class GenresLocalRepository(
    private val genresDao: IGenresDao
) : IGenresLocalRepository {

    override suspend fun getGenresList(): SuperResult<List<GenreEntity>> {
        return genresDao.getAll().toSuccessResult()
    }

    override suspend fun saveGenresList(data: List<GenreEntity>) {
        genresDao.insertAll(data)
    }
}