package com.rasalexman.data.repository

import com.merqueo.co.core.base.IBaseRepository
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.infraestructura.source.local.IGenresLocalSource
import com.merqueo.co.infraestructura.source.remote.IGenresRemoteSource
import com.merqueo.co.models.entities.GenreEntity

interface IGenresRepository : IBaseRepository<IGenresLocalSource, IGenresRemoteSource> {

    suspend fun getLocalGenresList(): SuperResult<List<GenreEntity>>
    suspend fun getRemoteGenresList(): SuperResult<List<GenreEntity>>
    suspend fun saveGenresList(genresList: List<GenreEntity>)
}