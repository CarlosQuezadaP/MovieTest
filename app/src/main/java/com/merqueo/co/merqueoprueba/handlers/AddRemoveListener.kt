package com.merqueo.co.merqueoprueba.handlers

import com.merqueo.co.domain.models.MovieItemDomain


interface AddRemoveListener {
    fun onItemClickOnButton(
        movieItemDomain: MovieItemDomain,
        type: Boolean
    )
}