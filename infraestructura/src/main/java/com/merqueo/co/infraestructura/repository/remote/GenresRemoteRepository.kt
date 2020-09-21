package com.merqueo.co.infraestructura.repository.remote

import com.merqueo.co.core.BuildConfig
import com.merqueo.co.core.models.SuperResult
import com.merqueo.co.infraestructura.network.api.IMovieApi
import com.merqueo.co.models.dto.GenreDto
import com.merqueo.co.provide.db.entities.GenreEntity
import com.merqueo.co.provide.network.handlers.errorResultCatchBlock
import com.merqueo.co.provide.network.responses.getResult
import com.rasalexman.coroutinesmanager.doWithTryCatchAsync

class GenresRemoteRepository(
    private val moviesApi: IMovieApi
) : IGenresRemoteRepository {

    private val addedImages by lazy { mutableSetOf<String>() }

    override suspend fun getRemoteGenresList(): SuperResult<List<GenreDto>> = doWithTryCatchAsync(
        tryBlock = {
            moviesApi.getGenresList().getResult {
                it.genres
            }
        },
        catchBlock = errorResultCatchBlock()
    )

    override suspend fun getGenresImages(result: List<GenreEntity>) {
        result.forEach { genreEntity ->
            moviesApi.getMoviesListByPopularity(genreEntity.id).run {
                body()?.results?.apply {
                    filter {
                        val path = it.getImagePath()
                        path.isNotEmpty() && !addedImages.contains(path)
                    }.take(3).mapTo(genreEntity.images) {
                        val imagePoster = it.getImagePath()
                        addedImages.add(imagePoster)
                        buildString {
                            append(BuildConfig.IMAGES_URL)
                            append(imagePoster)
                        }
                    }
                }
            }
        }
    }
}