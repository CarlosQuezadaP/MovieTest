package com.merqueo.co.infraestructura.repository.local

import com.merqueo.co.core.base.ILocalDataSource
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.provide.db.entities.GenreEntity


interface IGenresLocalRepository : ILocalDataSource {
    suspend fun getGenresList(): SuperResult<List<GenreEntity>>
    suspend fun saveGenresList(data: List<GenreEntity>)
}