package com.merqueo.co.infraestructura.source.local

import com.merqueo.co.core.base.ILocalDataSource
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.models.entities.GenreEntity


interface IGenresLocalSource : ILocalDataSource {
    suspend fun getGenresList(): SuperResult<List<GenreEntity>>
    suspend fun saveGenresList(data: List<GenreEntity>)
}