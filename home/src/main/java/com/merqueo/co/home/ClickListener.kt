package com.merqueo.co.home

import com.merqueo.co.models.ui.MovieItemDomain

interface ClickListener {
    fun onClick(movieItemDomain: MovieItemDomain)
}
