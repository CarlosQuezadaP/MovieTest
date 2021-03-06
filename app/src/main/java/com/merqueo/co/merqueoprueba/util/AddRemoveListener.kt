package com.merqueo.co.usecases.util

import com.merqueo.co.domain.models.MovieItemDomain


interface AddRemoveListener {
    fun onItemClickOnButton(
        movieItemDomain: MovieItemDomain,
        type: Boolean
    )
}