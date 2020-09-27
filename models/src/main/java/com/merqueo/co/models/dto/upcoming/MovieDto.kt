package com.merqueo.co.models.dto.upcoming


import com.google.gson.annotations.SerializedName
import com.merqueo.co.core.IConvertableTo
import com.merqueo.co.models.entities.MovieEntity
import java.text.SimpleDateFormat
import java.util.*

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
) : IConvertableTo<MovieEntity> {
    override fun convertTo(): MovieEntity? {
        return releaseDate.run {
            MovieEntity(
                id = id,
                voteCount = voteCount ?: 0,
                voteAverage = voteAverage ?: 0.0,
                isVideo = video,
                title = title.orEmpty(),
                popularity = popularity,
                posterPath = posterPath.orEmpty(),
                originalLanguage = originalLanguage.orEmpty(),
                originalTitle = originalTitle.orEmpty(),
                genreIds = genreIds ?: emptyList(),
                backdropPath = backdropPath.orEmpty(),
                releaseDate = parseReleaseDate(),
                adult = adult,
                overview = overview.orEmpty()
            )
        }
    }

    private fun parseReleaseDate(): Long {
        return releaseDate.let {
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(it)?.time ?: 0L
        } ?: 0L
    }

    fun getImagePath() = posterPath ?: backdropPath.orEmpty()
}