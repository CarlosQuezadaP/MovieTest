package com.merqueo.co.models.dto.upcoming


import com.google.gson.annotations.SerializedName

data class UpcomingResponse(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movieDtos: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)