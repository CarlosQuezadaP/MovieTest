package com.merqueo.co.merqueoprueba.util

import com.merqueo.co.domain.models.MovieItemDomain


interface AddRemoveListener {
    fun onItemClickOnButton(
        movieItemDomain: MovieItemDomain,
        type: Boolean
    )
}