package com.merqueo.co.infraestructura.source.remote

import com.merqueo.co.core.base.IRemoteDataSource
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.models.dto.GenreDto
import com.merqueo.co.models.entities.GenreEntity


interface IGenresRemoteSource : IRemoteDataSource {
    suspend fun getRemoteGenresList(): SuperResult<List<GenreDto>>
    suspend fun getGenresImages(result: List<GenreEntity>)
}