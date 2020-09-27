package com.merqueo.co.models.ui

import androidx.annotation.Keep

@Keep
data class MovieItemUI(
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
    val originalReleaseDate: Long,
    val adult: Boolean,
    val overview: String
)
