package com.merqueo.co.provide.network.responses

import androidx.annotation.Keep
import com.merqueo.co.models.dto.GenreDto

@Keep
data class GetGenresResponse(val genres: List<GenreDto>)