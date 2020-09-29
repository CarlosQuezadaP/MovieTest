package com.merqueo.co.models.ui

import androidx.annotation.Keep
import com.merqueo.co.core.IConvertableTo
import com.merqueo.co.models.entities.MovieEntity

@Keep
data class MovieItemDomain(
    val id: Int,
    val voteCount: Int,
    val voteAverage: Double,
    val isVideo: Boolean,
    val title: String,
    val popularity: Double,
    val posterPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val genreIds: List<Int>,
    val backdropPath: String,
    val releaseDate: String,
    val adult: Boolean,
    var onStore: Boolean,
    val overview: String
) : IConvertableTo<MovieEntity> {
    override fun convertTo(): MovieEntity {
        return MovieEntity(
            id = id,
            voteCount = voteCount,
            voteAverage = voteAverage,
            isVideo = isVideo,
            title = title,
            popularity = popularity,
            posterPath = posterPath.takeIf { it.isNotEmpty() } ?: backdropPath,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            genreIds = genreIds,
            backdropPath = backdropPath,
            releaseDate = releaseDate,
            adult = adult,
            onStore = onStore,
            overview = overview
        )
    }
}
