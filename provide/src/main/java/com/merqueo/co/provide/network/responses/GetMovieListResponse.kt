package com.merqueo.co.provide.network.responses

data class GetMoviesListResponse<E>(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<E>
)