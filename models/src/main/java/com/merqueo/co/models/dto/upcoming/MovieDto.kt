package com.merqueo.co.models.dto.upcoming


import com.google.gson.annotations.SerializedName
import com.merqueo.co.usecases.IConvertableTo
import com.merqueo.co.models.ui.MovieItemDomain

data class MovieDto(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) : IConvertableTo<MovieItemDomain> {
    override fun convertTo(): MovieItemDomain {
        return MovieItemDomain(
            id = id,
            voteCount = voteCount,
            voteAverage = voteAverage,
            isVideo = video,
            title = title,
            popularity = popularity,
            posterPath = posterPath,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle.orEmpty(),
            genreIds = genreIds,
            backdropPath = backdropPath.orEmpty(),
            releaseDate = releaseDate,
            adult = adult,
            onStore = false,
            overview = overview.orEmpty()
        )
    }
}

