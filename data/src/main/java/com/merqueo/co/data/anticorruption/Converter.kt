package com.merqueo.co.data.anticorruption

import com.merqueo.co.data.db.entities.MovieEntity
import com.merqueo.co.domain.models.MovieItemDomain

class Converter : IConverter {

    override fun convertEntityToDomain(movieEntity: MovieEntity): MovieItemDomain {
        return MovieItemDomain(
            id = movieEntity.id,
            voteCount = movieEntity.voteCount,
            voteAverage = movieEntity.voteAverage,
            isVideo = movieEntity.isVideo,
            title = movieEntity.title,
            popularity = movieEntity.popularity,
            posterPath = movieEntity.posterPath.takeIf { it.isNotEmpty() }
                ?: movieEntity.backdropPath,
            originalLanguage = movieEntity.originalLanguage,
            originalTitle = movieEntity.originalTitle,
            genreIds = movieEntity.genreIds,
            backdropPath = movieEntity.backdropPath,
            releaseDate = movieEntity.releaseDate,
            adult = movieEntity.adult,
            onStore = movieEntity.onStore,
            overview = movieEntity.overview
        )
    }

    override fun convertDomainToEntity(movieItemDomain: MovieItemDomain): MovieEntity {
        return MovieEntity(
            id = movieItemDomain.id,
            voteCount = movieItemDomain.voteCount,
            voteAverage = movieItemDomain.voteAverage,
            isVideo = movieItemDomain.isVideo,
            title = movieItemDomain.title,
            popularity = movieItemDomain.popularity,
            posterPath = movieItemDomain.posterPath.takeIf { it.isNotEmpty() }
                ?: movieItemDomain.backdropPath,
            originalLanguage = movieItemDomain.originalLanguage,
            originalTitle = movieItemDomain.originalTitle,
            genreIds = movieItemDomain.genreIds,
            backdropPath = movieItemDomain.backdropPath,
            releaseDate = movieItemDomain.releaseDate,
            adult = movieItemDomain.adult,
            onStore = movieItemDomain.onStore,
            overview = movieItemDomain.overview
        )
    }
}