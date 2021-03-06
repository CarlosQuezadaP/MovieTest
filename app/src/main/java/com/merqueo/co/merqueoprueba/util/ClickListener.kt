package com.merqueo.co.usecases.util

import com.merqueo.co.domain.models.MovieItemDomain


interface ClickListener {
    fun onClick(movieItemDomain: MovieItemDomain)
}
