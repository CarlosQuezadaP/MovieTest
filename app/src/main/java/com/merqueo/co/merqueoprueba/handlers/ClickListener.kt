package com.merqueo.co.merqueoprueba.handlers

import com.merqueo.co.domain.models.MovieItemDomain


interface ClickListener {
    fun onClick(movieItemDomain: MovieItemDomain)
}
