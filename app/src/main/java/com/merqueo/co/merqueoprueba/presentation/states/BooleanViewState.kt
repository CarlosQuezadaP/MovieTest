package com.merqueo.co.merqueoprueba.presentation.states

data class BooleanViewState(
    val loading: Boolean = false,
    val data: Boolean = false,
    val error: String? = null
)