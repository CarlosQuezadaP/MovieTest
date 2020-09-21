package com.merqueo.co.models.dto

import androidx.annotation.Keep
@Keep
data class MovieDto(
    val id: Int,
    val vote_count: Int?,
    val vote_average: Double?,
    val video: Boolean?,
    val title: String?,
    val popularity: Double?,
    val poster_path: String?,
    val original_language: String?,
    val original_title: String?,
    val genre_ids: List<Int>?,
    val backdrop_path: String?,
    val release_date: String?,
    val adult: Boolean?,
    val overview: String?,
    val revenue: Int?,
    val runtime: Int?,
    val status: String?,
    val tagline: String?,
    val budget: Long?
)