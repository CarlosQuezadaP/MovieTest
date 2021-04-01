package com.co.merqueoprueba

import com.merqueo.co.domain.models.MovieItemDomain

class MovieBuilder {

    private var id: Int = 1
    private var voteCount: Int = 0
    private var voteAverage: Double = 0.0
    private var isVideo: Boolean = false
    private lateinit var title: String
    private var popularity: Double = 0.0
    private lateinit var posterPath: String
    private lateinit var originalLanguage: String
    private lateinit var originalTitle: String
    private lateinit var genreIds: List<Int>
    private lateinit var backdropPath: String
    private lateinit var releaseDate: String
    private var adult: Boolean = false
    private var onStore: Boolean = false
    private lateinit var overview: String

    init {
        setData()

    }

    fun setData() {
        id = 1
        voteCount = 1
        voteAverage = 9.9
        title = "Los vengadores 4"
        popularity = 9.9
        posterPath = "none"
        originalLanguage = "English"
        originalTitle = "The Avengers"
        genreIds = emptyList()
        backdropPath = "none"
        releaseDate = "2020/12/12"
        overview = "none"
    }

    fun build() = MovieItemDomain(
        id,
        voteCount,
        voteAverage,
        isVideo,
        title,
        popularity,
        posterPath,
        originalLanguage,
        originalTitle,
        genreIds,
        backdropPath,
        releaseDate,
        adult,
        onStore,
        overview
    )

    fun buildAsList() = listOf(build())

}