package com.merqueo.co.merqueoprueba.util

import com.merqueo.co.domain.models.MovieItemDomain


interface ClickListener {
    fun onClick(movieItemDomain: MovieItemDomain)
}
