package com.merqueo.co.infraestructura.repository.remote

import com.merqueo.co.core.base.IRemoteDataSource
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.models.dto.GenreDto
import com.merqueo.co.provide.db.entities.GenreEntity


interface IGenresRemoteRepository : IRemoteDataSource {
    suspend fun getRemoteGenresList(): SuperResult<List<GenreDto>>
    suspend fun getGenresImages(result: List<GenreEntity>)
}