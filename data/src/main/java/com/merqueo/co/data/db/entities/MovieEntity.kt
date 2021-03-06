package com.merqueo.co.data.db.entities

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.usecases.IConvertableTo

@Keep
@Entity(
    indices = [
        Index(
            value = ["title"],
            unique = true
        ),
        Index(
            value = ["id"],
            unique = true
        )
    ]
)
data class MovieEntity(
    @PrimaryKey
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
    val overview: String,
    var onStore: Boolean = false,
    var hasDetails: Boolean = false,
    var isPopular: Boolean = false,
    var isTopRated: Boolean = false,
    var isUpComing: Boolean = false
) : IConvertableTo<MovieItemDomain> {
    override fun convertTo(): MovieItemDomain {
        return MovieItemDomain(
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

