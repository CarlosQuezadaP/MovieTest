package com.merqueo.co.merqueoprueba.presentation.states

import com.merqueo.co.domain.models.MovieItemDomain

data class MovieViewState(
    val loading: Boolean = false,
    val data: List<MovieItemDomain> = emptyList(),
    val error: String? = null
)